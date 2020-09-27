package com.sergio.binance.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestHttpCallerService {
    private RestTemplate restTemplate = new RestTemplate();
    private String endpoint;
    private HttpHeaders headers = new HttpHeaders();
    private HttpMethod method;
    private HttpEntity entity;

    public RestHttpCallerService toEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public RestHttpCallerService method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public RestHttpCallerService addHeader(String name, String value) {
        headers.set(name, value);
        return this;
    }

    public <T> RestHttpCallerService withEntity(T entity) {
        this.entity = new HttpEntity<T>(entity, headers);
        return this;
    }

    public <E> ResponseEntity<E> call(Class<E> clazz) {
        return restTemplate.exchange(endpoint, method, entity, clazz);
    }

    public <E> ResponseEntity<E> call(ParameterizedTypeReference<E> responseType) {
        return restTemplate.exchange(endpoint, method, entity, responseType);
    }



}
