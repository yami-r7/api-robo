package br.com.senai.robo.repository;


import br.com.senai.robo.entities.Robo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoboRepository extends JpaRepository<Robo,Long> {
}
