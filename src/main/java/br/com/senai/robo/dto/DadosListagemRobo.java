package br.com.senai.robo.dto;

import br.com.senai.robo.entities.Robo;

public record DadosListagemRobo(Long id, String nome) {
    public DadosListagemRobo(Robo robo) { this(robo.getId(), robo.getNome()); }
}
