package br.com.senai.robo.controller;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRobo(
        @NotBlank
        String nome,
        @NotBlank
        String tecnologia) {

}
