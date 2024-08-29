package com.example.meuprograma.domain;

import com.example.meuprograma.enums.Opcoes;
import lombok.Data;

@Data
public class Request {
    private Opcoes opcao;
    private String recurso;
}
