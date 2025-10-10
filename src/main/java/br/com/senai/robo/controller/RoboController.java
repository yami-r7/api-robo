package br.com.senai.robo.controller;

import br.com.senai.robo.acao.Acao;
import br.com.senai.robo.acao.DadosRegistroAcaoArduino;
import br.com.senai.robo.dto.*;
import br.com.senai.robo.entities.Robo;
import br.com.senai.robo.infra.ApiResponse;
import br.com.senai.robo.infra.exception.ValidacaoException;
import br.com.senai.robo.repository.AcaoRepository;
import br.com.senai.robo.repository.RoboRepository;
import br.com.senai.robo.service.RoboService;
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
@CrossOrigin(origins = "*")
public class RoboController {

    @Autowired
    private RoboService roboService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRobo dados, UriComponentsBuilder uriBuilder) {
        var robo = roboService.cadastrar(dados);
        var uri = uriBuilder.path("/robos/{id}").buildAndExpand(robo.id()).toUri();
        return ResponseEntity.created(uri).body(new ApiResponse<>(LocalDateTime.now(), "Robô cadastrado!", robo));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRobo>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = roboService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DadosDetalhamentoRobo>> detalhar(@PathVariable Long id) {
        var roboDetalhado = roboService.detalhar(id);
        var response = new ApiResponse<>(LocalDateTime.now(), "Detalhes do robô obtidos com sucesso.", roboDetalhado);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DadosDetalhamentoRobo>> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoRobo dados) {
        var roboAtualizado = roboService.atualizar(id, dados);
        var response = new ApiResponse<>(
                LocalDateTime.now(),
                "Dados do robô atualizados com sucesso!",
                roboAtualizado
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> excluir(@PathVariable Long id) {
        roboService.excluir(id);
        return ResponseEntity.ok(new ApiResponse<>(LocalDateTime.now(), "Robô desativado com sucesso!", null));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<ApiResponse<DadosDetalhamentoRobo>> reativar(@PathVariable Long id) {
        var roboReativado = roboService.reativar(id);
        var response = new ApiResponse<>(
                LocalDateTime.now(),
                "Robô reativado com sucesso!",
                roboReativado
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idrobo}/acoes")
    public ResponseEntity<ApiResponse<DadosDetalhamentoAcao>> registrarAcaoPeloRobo(@PathVariable Long idrobo, @RequestBody @Valid DadosRegistroAcaoArduino dados, UriComponentsBuilder uriBuilder) {
        var acaoDetalhada = roboService.registrarAcaoPeloRobo(idrobo, dados);
        var uri = uriBuilder.path("/acoes/{id}").buildAndExpand(acaoDetalhada.id()).toUri();
        var response = new ApiResponse<>(LocalDateTime.now(), "Ação registrada pelo robô!", acaoDetalhada);
        return ResponseEntity.created(uri).body(response);
    }
}
