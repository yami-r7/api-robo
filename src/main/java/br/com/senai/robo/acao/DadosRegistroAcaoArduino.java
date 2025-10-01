package br.com.senai.robo.acao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DadosRegistroAcaoArduino(
        @NotBlank
        @JsonProperty("acao")
        String descricao,
        Float distancia
) {}