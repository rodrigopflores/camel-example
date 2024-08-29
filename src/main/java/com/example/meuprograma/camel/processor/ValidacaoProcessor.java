package com.example.meuprograma.camel.processor;

import com.example.meuprograma.domain.Request;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidacaoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Request request = exchange.getIn().getBody(Request.class);

        if (request == null || request.getOpcao() == null) {
            throw new RuntimeException("Request inválido");
            // teria que implementar um tratamento pra isso, um handler
        }
    }
}
