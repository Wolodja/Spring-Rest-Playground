package com.spring_rest_playground.demo.services;

import com.spring_rest_playground.demo.api.domain.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}
