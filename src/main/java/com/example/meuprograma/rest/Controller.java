package com.example.meuprograma.rest;

import com.example.meuprograma.domain.Request;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/start")
    public String start(@RequestBody Request request) {
        System.out.println(request);
        return producerTemplate.requestBody("direct:startRoute", request, String.class);
    }
}
