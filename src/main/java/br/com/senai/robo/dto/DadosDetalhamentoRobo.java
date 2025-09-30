package br.com.senai.robo.dto;

import br.com.senai.robo.entities.Robo;

public record DadosDetalhamentoRobo(Long id, String nome, String tecnologia) {
    public DadosDetalhamentoRobo(Robo robo) { this(robo.getId(), robo.getNome(), robo.getTecnologia()); }
}
