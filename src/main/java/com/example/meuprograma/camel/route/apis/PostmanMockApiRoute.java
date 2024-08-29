package com.example.meuprograma.camel.route.apis;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PostmanMockApiRoute extends RouteBuilder {

    private static final String POSTMAN_HOST = "https://27ea1ca0-05f5-47c9-bcc5-c150c133e446.mock.pstmn.io";

    @Override
    public void configure() {
        from("direct:postmanMockApi")
                .routeId("postmanMockApi")
                .setBody(exchangeProperty("recursoRequest"))
                .log("Recurso: ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .choice()
                    .when(simple("${body} == 'A'"))
                        .to("direct:postmanMockApiRecursoA")
                    .when(simple("${body} == 'B'"))
                        .to("direct:postmanMockApiRecursoB")
                    .otherwise()
                        .setBody(simple("Recurso inválido"))
                .end()
                .log("Retorno da rota: ${body}");

        from("direct:postmanMockApiRecursoA")
                .routeId("postmanMockApiRecursoA")
                .log("Iniciando chamada API de mock do Postman")
                .to(POSTMAN_HOST + "/recurso-a");

        from("direct:postmanMockApiRecursoB")
                .routeId("postmanMockApiRecursoB")
                .log("Iniciando chamada API B")
                .to(POSTMAN_HOST + "/recurso-b");
    }
}
