package org.example.service;

import org.example.exception.ResponseErrorException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class HttpRequestHandler {

    private final RestTemplate restTemplate;

    public HttpRequestHandler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//  todo  https://www.baeldung.com/spring-rest-template-error-handling
    public<T, R> R post(T model, Class<R> returnClass, URI url) throws ResponseErrorException {
        ResponseEntity<R> responseEntity = restTemplate.postForEntity(url, model, returnClass);
        if (responseEntity.getStatusCode().isError()) {
            throw new ResponseErrorException(responseEntity.getStatusCode().value());
        }
        return responseEntity.getBody();
    }
}
