package br.com.senai.robo.repository;

import br.com.senai.robo.acao.Acao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcaoRepository extends JpaRepository<Acao, Long> {

    Page<Acao> findAllByRoboId(Long idrobo, Pageable paginacao);

}