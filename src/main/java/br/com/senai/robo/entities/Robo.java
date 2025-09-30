package br.com.senai.robo.entities;

import br.com.senai.robo.controller.DadosCadastroRobo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "robo")
public class Robo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String tecnologia;

    // Relacionamento com Obstaculo
    @OneToMany(mappedBy = "robo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Obstaculo> obstaculos;

    public Robo(DadosCadastroRobo dados){
        this.nome = dados.nome();
        this.tecnologia = dados.tecnologia();
    }

}
