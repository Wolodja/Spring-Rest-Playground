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
public class Job implements Serializable
{

    private String title;
    private String company;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = -4985150429002262656L;

}
