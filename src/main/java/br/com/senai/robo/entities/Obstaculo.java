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

    @Column(name = "data_hora_registro", nullable = false)
    private LocalDateTime dataHoraRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idrobo", nullable = false)
    private Robo robo;


}