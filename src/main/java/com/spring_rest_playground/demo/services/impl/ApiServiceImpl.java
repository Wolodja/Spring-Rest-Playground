package com.spring_rest_playground.demo.services.impl;

import com.github.javafaker.Faker;
import com.spring_rest_playground.demo.api.domain.Job;
import com.spring_rest_playground.demo.api.domain.Name;
import com.spring_rest_playground.demo.api.domain.User;
import com.spring_rest_playground.demo.api.domain.UserData;
import com.spring_rest_playground.demo.services.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ApiServiceImpl implements  ApiService {

    private RestTemplate restTemplate;

    private final String apiUri;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUri) {
        this.restTemplate = restTemplate;
        this.apiUri = apiUri;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(apiUri)
                .queryParam("limit", limit);
        List<User> users;
        try {
            users = restTemplate.getForObject(builder.toUriString(), UserData.class).getData();
        } catch (ResourceAccessException resourceAccessException){
            users = populateUsers(limit);
        }
        return users;
    }

    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {

        return WebClient
                .create(apiUri)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(resp -> resp.bodyToMono(UserData.class))
                .flatMapIterable(UserData::getData);
    }

    private List<User> populateUsers(Integer limit) {
        int numberOfUsers = 0;
        List<User> users = new ArrayList<>();
        while (numberOfUsers < limit){
            User user = createUsers();
            users.add(user);
            numberOfUsers++;
        }
        return users;
    }

    private User createUsers() {
        Faker faker = new Faker(new Locale("pl"));
        User user = new User();
        user.setName(Name.builder().first(faker.name().firstName()).last(faker.name().lastName()).build());
        user.setCurrency(faker.code().asin());
        user.setEmail(faker.internet().emailAddress());
        user.setGender(faker.options().option("Kobieta", "Mężczyzna"));
        user.setLanguage(faker.address().countryCode());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setJob(Job.builder().company(faker.company().name()).build());
        return user;

    }
}