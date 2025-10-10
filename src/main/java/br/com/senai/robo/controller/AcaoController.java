package br.com.senai.robo.controller;

import br.com.senai.robo.dto.DadosDetalhamentoAcao;
import br.com.senai.robo.repository.AcaoRepository;
import br.com.senai.robo.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @GetMapping("/acoes")
    public ResponseEntity<Page<DadosDetalhamentoAcao>> listarTodasAsAcoes(@PageableDefault(size = 10, sort = {"dataHoraInicio"}) Pageable paginacao) {
        var page = acaoService.listarTodas(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/robos/{idrobo}/acoes")
    public ResponseEntity<Page<DadosDetalhamentoAcao>> listarAcoesPorRobo(@PathVariable Long idrobo, @PageableDefault(size = 10, sort = {"dataHoraInicio"}) Pageable paginacao) {
        var page = acaoService.listarPorRobo(idrobo, paginacao);
        return ResponseEntity.ok(page);
    }

}