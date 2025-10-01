package br.com.senai.robo.controller;

import br.com.senai.robo.dto.DadosDetalhamentoObstaculo;
import br.com.senai.robo.dto.DadosRegistroObstaculo;
import br.com.senai.robo.entities.Obstaculo;
import br.com.senai.robo.infra.ApiResponse;
import br.com.senai.robo.repository.ObstaculoRepository;
import br.com.senai.robo.repository.RoboRepository;
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
@RequestMapping("/obstaculos")
public class ObstaculoController {

    @Autowired
    private RoboRepository roboRepository;

    @Autowired
    private ObstaculoRepository obstaculoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<DadosDetalhamentoObstaculo>> registrar(@RequestBody @Valid DadosRegistroObstaculo dados, UriComponentsBuilder uriBuilder) {
        var robo = roboRepository.getReferenceById(dados.idrobo());

        // Agora o construtor da entidade recebe a distância também
        var obstaculo = new Obstaculo(null, robo, dados.data_hora_registro(), dados.distancia());
        obstaculoRepository.save(obstaculo);

        var uri = uriBuilder.path("/obstaculos/{id}").buildAndExpand(obstaculo.getId()).toUri();
        var response = new ApiResponse<>(LocalDateTime.now(), "Obstáculo registrado com sucesso!", new DadosDetalhamentoObstaculo(obstaculo));

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoObstaculo>> listar(@PageableDefault(size = 10, sort = {"dataHoraRegistro"}) Pageable paginacao) {
        var page = obstaculoRepository.findAll(paginacao).map(DadosDetalhamentoObstaculo::new);
        return ResponseEntity.ok(page);
    }
}