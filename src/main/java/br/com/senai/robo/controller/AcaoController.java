package br.com.senai.robo.controller;

import br.com.senai.robo.acao.Acao;
import br.com.senai.robo.dto.DadosDetalhamentoAcao;
import br.com.senai.robo.dto.DadosRegistroAcao;
import br.com.senai.robo.infra.ApiResponse;
import br.com.senai.robo.infra.exception.ValidacaoException;
import br.com.senai.robo.repository.AcaoRepository;
import br.com.senai.robo.repository.RoboRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/log")
public class AcaoController {

    @Autowired
    private RoboRepository roboRepository;

    @Autowired
    private AcaoRepository acaoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosRegistroAcao dados, UriComponentsBuilder uriBuilder) {
        if (!roboRepository.existsById(dados.idrobo())) {
            throw new ValidacaoException("Robô com id " + dados.idrobo() + " não foi encontrado.");
        }

        var robo = roboRepository.getReferenceById(dados.idrobo());

        if (!robo.getAtivo()) {
            throw new ValidacaoException("Não é possível registrar ação para um robô que está inativo.");
        }

        var acao = new Acao(null, robo, dados.descricao(), LocalDateTime.now());
        acaoRepository.save(acao);

        var uri = uriBuilder.path("/acoes/{id}").buildAndExpand(acao.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApiResponse<>(LocalDateTime.now(), "Ação registrada no histórico!", new DadosDetalhamentoAcao(acao)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAcao>> listar(@PageableDefault(size = 10, sort = {"dataHoraInicio"}) Pageable paginacao) {
        var page = acaoRepository.findAll(paginacao).map(DadosDetalhamentoAcao::new);
        return ResponseEntity.ok(page);
    }
}