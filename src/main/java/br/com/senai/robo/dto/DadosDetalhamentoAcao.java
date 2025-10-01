package br.com.senai.robo.dto;

import br.com.senai.robo.acao.Acao;

import java.time.LocalDateTime;

public record DadosDetalhamentoAcao(Long id, Long idrobo, String descricao, LocalDateTime dataHoraInicio, Float distancia) {
    public DadosDetalhamentoAcao(Acao acao) {
        this(acao.getId(), acao.getRobo().getId(), acao.getDescricao(), acao.getDataHoraInicio(), acao.getDistancia());
    }
}