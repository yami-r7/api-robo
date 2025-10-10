package br.com.senai.robo.service;

import br.com.senai.robo.dto.DadosDetalhamentoAcao;
import br.com.senai.robo.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AcaoService {

    @Autowired
    private AcaoRepository repository;

    public Page<DadosDetalhamentoAcao> listarTodas(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhamentoAcao::new);
    }

    public Page<DadosDetalhamentoAcao> listarPorRobo(Long idRobo, Pageable paginacao) {
        return repository.findAllByRoboId(idRobo, paginacao).map(DadosDetalhamentoAcao::new);
    }
}