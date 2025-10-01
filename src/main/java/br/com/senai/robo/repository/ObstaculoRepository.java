package br.com.senai.robo.repository;

import br.com.senai.robo.entities.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObstaculoRepository extends JpaRepository<Obstaculo, Long> {
}