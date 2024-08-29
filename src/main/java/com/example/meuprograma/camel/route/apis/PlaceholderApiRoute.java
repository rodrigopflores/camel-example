package com.example.meuprograma.camel.route.apis;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PlaceholderApiRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:placeholderApi")
                .routeId("placeholderApi")
                .log("Iniciando chamada de API para placeholder")
                .setBody(exchangeProperty("recursoRequest"))
                .log("Body: ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://jsonplaceholder.typicode.com/posts/${body}")
                .log("Retorno da rota: ${body}");

    }
}
