package br.com.senai.robo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public record DadosRegistroObstaculo(
        @NotNull
        Long idrobo,

        @NotNull
        @PastOrPresent
        LocalDateTime data_hora_registro,

        @NotNull
        Float distancia // Novo campo obrigatório
) {}