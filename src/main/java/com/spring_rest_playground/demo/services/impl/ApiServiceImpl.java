package com.spring_rest_playground.demo.services.impl;

import com.spring_rest_playground.demo.api.domain.Job;
import com.spring_rest_playground.demo.api.domain.Name;
import com.spring_rest_playground.demo.api.domain.User;
import com.spring_rest_playground.demo.api.domain.UserData;
import com.spring_rest_playground.demo.services.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements  ApiService {

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        List<User> users;
        try {
            users = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class).getData();
        } catch (ResourceAccessException resourceAccessException){
            users = populateUsers(limit);
        }
        return users;
    }

    private List<User> populateUsers(Integer limit) {
        User user = createUsers();
        int numberOfUsers = 0;
        List<User> users = new ArrayList<>();
        while (numberOfUsers < limit){
            users.add(user);
            numberOfUsers++;
        }
        return users;
    }

    private User createUsers() {
        User user = new User();
        user.setName(Name.builder().first("John").last("Smith").build());
        user.setCurrency("PLN");
        user.setEmail("email@mail.com");
        user.setGender("Male");
        user.setLanguage("Polish");
        user.setPhone("324 654 154");
        user.setJob(Job.builder().company("Google").title("Senior Java Developer").build());
        return user;

    }
}