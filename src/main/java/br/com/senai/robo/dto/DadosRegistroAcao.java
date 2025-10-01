package br.com.senai.robo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroAcao(
        @NotNull
        Long idrobo,
        @NotBlank
        String descricao
) {}