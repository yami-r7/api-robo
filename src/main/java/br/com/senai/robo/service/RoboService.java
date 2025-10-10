package br.com.senai.robo.service;

import br.com.senai.robo.acao.Acao;
import br.com.senai.robo.acao.DadosRegistroAcaoArduino;
import br.com.senai.robo.dto.*;
import br.com.senai.robo.entities.Robo;
import br.com.senai.robo.infra.exception.ValidacaoException;
import br.com.senai.robo.repository.AcaoRepository;
import br.com.senai.robo.repository.RoboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RoboService {

    @Autowired
    private RoboRepository roboRepository;

    @Autowired
    private AcaoRepository acaoRepository;

    @Transactional
    public DadosDetalhamentoRobo cadastrar(DadosCadastroRobo dados) {
        var robo = new Robo(dados);
        roboRepository.save(robo);
        return new DadosDetalhamentoRobo(robo);
    }

    public Page<DadosListagemRobo> listar(Pageable paginacao) {
        return roboRepository.findAllByAtivoTrue(paginacao).map(DadosListagemRobo::new);
    }

    public DadosDetalhamentoRobo detalhar(Long id) {
        var robo = roboRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Robô não encontrado com o ID: " + id));
        return new DadosDetalhamentoRobo(robo);
    }

    @Transactional
    public DadosDetalhamentoRobo atualizar(Long id, DadosAtualizacaoRobo dados) {
        var robo = roboRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Robô não encontrado com o ID: " + id));
        robo.atualizarInformacoes(dados);
        return new DadosDetalhamentoRobo(robo);
    }

    @Transactional
    public void excluir(Long id) {
        var robo = roboRepository.getReferenceById(id);
        if (!robo.getAtivo()) {
            throw new ValidacaoException("Este robô já está inativo.");
        }
        robo.excluir();
    }

    @Transactional
    public DadosDetalhamentoRobo reativar(Long id) {
        var robo = roboRepository.getReferenceById(id);
        if (robo.getAtivo()) {
            throw new ValidacaoException("Este robô já está ativo.");
        }
        robo.reativar();
        return new DadosDetalhamentoRobo(robo);
    }

    @Transactional
    public DadosDetalhamentoAcao registrarAcaoPeloRobo(Long idrobo, DadosRegistroAcaoArduino dados) {
        var robo = roboRepository.getReferenceById(idrobo);
        if (!robo.getAtivo()) {
            throw new ValidacaoException("Ação não registrada: o robô com id " + idrobo + " está inativo.");
        }

        var acao = new Acao(null, robo, dados.descricao(), LocalDateTime.now(), dados.distancia());
        acaoRepository.save(acao);
        return new DadosDetalhamentoAcao(acao);
    }
}
