package br.com.senai.robo.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    // Tratador para quando um ID não é encontrado (ex: /robos/999)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Tratador para erros de validação do Bean Validation (ex: campo em branco)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // ##### NOSSO NOVO TRATADOR PARA REGRAS DE NEGÓCIO #####
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        var erro = new DadosErroGenerico(ex.getMessage());
        return ResponseEntity.badRequest().body(erro);
    }

    // Tratador para qualquer outro erro inesperado (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        var erro = new DadosErroGenerico("Erro inesperado no servidor: " + ex.getLocalizedMessage());
        return ResponseEntity.internalServerError().body(erro);
    }
}