package br.com.senai.robo.entities;

import br.com.senai.robo.dto.DadosAtualizacaoRobo;
import br.com.senai.robo.dto.DadosCadastroRobo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "robo")
@Entity(name = "Robo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Robo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tecnologia;
    @Column(columnDefinition = "tinyint")
    private Boolean ativo;

    public Robo(DadosCadastroRobo dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.tecnologia = dados.tecnologia();
    }

    public void atualizarInformacoes(DadosAtualizacaoRobo dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.tecnologia() != null) {
            this.tecnologia = dados.tecnologia();
        }
    }

    public void excluir() { this.ativo = false; }

    public void reativar() { this.ativo = true; }
}