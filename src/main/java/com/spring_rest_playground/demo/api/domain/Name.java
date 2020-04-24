package com.spring_rest_playground.demo.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
@Getter
@Setter
public class Name implements Serializable
{

    private String title;
    private String first;
    private String last;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 420620315591775395L;

}