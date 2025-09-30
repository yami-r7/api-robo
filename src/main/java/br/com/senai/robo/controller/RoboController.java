package br.com.senai.robo.controller;

import br.com.senai.robo.entities.Robo;
import br.com.senai.robo.repository.RoboRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robo")


public class RoboController {
    @Autowired
    private RoboRepository dadosRobo;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid DadosCadastroRobo dados){
        var robo = new Robo(dados);
        dadosRobo.save(robo);

        return robo.getNome()+robo.getTecnologia();

    }


}
