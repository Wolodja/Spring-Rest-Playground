package com.spring_rest_playground.demo.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location implements Serializable
{

    private String street;
    private String city;
    private String state;
    private String postcode;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = -3532048267747973846L;

}
