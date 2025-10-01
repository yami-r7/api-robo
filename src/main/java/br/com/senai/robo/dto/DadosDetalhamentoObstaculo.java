package br.com.senai.robo.dto;

import br.com.senai.robo.entities.Obstaculo;

import java.time.LocalDateTime;

public record DadosDetalhamentoObstaculo(Long id, Long idrobo, String nomeRobo, LocalDateTime dataHoraRegistro, Float distancia) {
    public DadosDetalhamentoObstaculo(Obstaculo obstaculo) {
        this(
                obstaculo.getId(),
                obstaculo.getRobo().getId(),
                obstaculo.getRobo().getNome(),
                obstaculo.getDataHoraRegistro(),
                obstaculo.getDistancia()
        );
    }
}