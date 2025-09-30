package br.com.senai.robo.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRobo(

        @NotBlank
        String nome,
        @NotBlank
        String tecnologia

) {
}
