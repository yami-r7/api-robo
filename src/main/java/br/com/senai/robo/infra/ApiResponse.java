package br.com.senai.robo.infra;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        LocalDateTime timestamp,
        String message,
        T data
) {
}
