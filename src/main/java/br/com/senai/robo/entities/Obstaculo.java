package br.com.senai.robo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "obstaculo")
public class Obstaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_registro", nullable = false) // Mapeia para a coluna 'data_hora_registro'
    private LocalDateTime dataHoraRegistro;

    // Define o relacionamento: Muitos Obstáculos para Um Robô
    @ManyToOne(fetch = FetchType.EAGER) // EAGER para sempre trazer os dados do robô junto com o obstáculo
    @JoinColumn(name = "idrobo", nullable = false) // Especifica a coluna de chave estrangeira (FK) nesta tabela
    private Robo robo;


}