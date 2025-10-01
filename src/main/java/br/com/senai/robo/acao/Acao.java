package br.com.senai.robo.acao;

import br.com.senai.robo.entities.Robo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "acao")
@Entity(name = "Acao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Acao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrobo")
    private Robo robo;

    private String descricao;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;
}