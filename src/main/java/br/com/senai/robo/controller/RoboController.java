package br.com.senai.robo.controller;

import br.com.senai.robo.dto.DadosAtualizacaoRobo;
import br.com.senai.robo.dto.DadosCadastroRobo;
import br.com.senai.robo.dto.DadosDetalhamentoRobo;
import br.com.senai.robo.dto.DadosListagemRobo;
import br.com.senai.robo.entities.Robo;
import br.com.senai.robo.infra.ApiResponse;
import br.com.senai.robo.infra.exception.ValidacaoException;
import br.com.senai.robo.repository.RoboRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/robos")
@Tag(name = "Robôs", description = "API para o gerenciamento completo de robôs")
public class RoboController {

    @Autowired
    private RoboRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRobo dados, UriComponentsBuilder uriBuilder) {
        var robo = new Robo(dados);
        repository.save(robo);

        var roboDetalhado = new DadosDetalhamentoRobo(robo);

        var response = new ApiResponse<>(
                LocalDateTime.now(),
                "Robô cadastrado com sucesso!",
                roboDetalhado
        );

        var uri = uriBuilder.path("/robos/{id}").buildAndExpand(robo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApiResponse<>(LocalDateTime.now(), "Robô cadastrado!", new DadosDetalhamentoRobo(robo)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRobo>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemRobo::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<DadosDetalhamentoRobo>> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoRobo dados) {
        var roboEncontrado = repository.findById(id);

        if (roboEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var robo = roboEncontrado.get();
        robo.atualizarInformacoes(dados);

        var response = new ApiResponse<>(
                LocalDateTime.now(),
                "Dados do robô atualizados com sucesso!",
                new DadosDetalhamentoRobo(robo)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<Object>> excluir(@PathVariable Long id) {
        var robo = repository.getReferenceById(id);
        robo.excluir();

        if (!robo.getAtivo()) {
            throw new ValidacaoException("Este robô já está inativo.");
        }

        var response = new ApiResponse<> (
                LocalDateTime.now(),
                "Robô desativado com sucesso!",
                null
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<ApiResponse<DadosDetalhamentoRobo>> reativar(@PathVariable Long id) {
        var robo = repository.getReferenceById(id);
        robo.reativar();

        if (robo.getAtivo()) {
            throw new ValidacaoException("Este robô já está ativo.");
        }

        var response = new ApiResponse<>(
                LocalDateTime.now(),
                "Robô reativado com sucesso!",
                new DadosDetalhamentoRobo(robo)
        );

        return ResponseEntity.ok(response);
    }
}
