package com.example.meuprograma.camel.route;

import com.example.meuprograma.camel.processor.ValidacaoProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StartRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:startRoute")
                .routeId("startRoute")
                .log("Iniciando rota principal. Request: ${body}")
                .process(new ValidacaoProcessor())
                .setProperty("opcaoRequest", simple("${body.opcao}"))
                .setProperty("recursoRequest", simple("${body.recurso}"))
                .choice()
                    .when(simple("${body.opcao} == 'OPCAO_1'"))
                        .to("direct:placeholderApi")
                    .when(simple("${body.opcao} == 'OPCAO_2'"))
                        .to("direct:postmanMockApi")
                    .otherwise()
                        .setBody(simple("Requisição inválida"))
                .end()
                .log("Response final: ${body}");
    }
}
