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
public class Login implements Serializable {

    private String username;
    private String password;
    private String md5;
    private String sha1;
    private String sha256;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 1041720428871730372L;
}