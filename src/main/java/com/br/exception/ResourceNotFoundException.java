package com.br.exception;

// Importa o status HTTP 404 (NOT_FOUND), que será retornado quando essa exceção ocorrer
import org.springframework.http.HttpStatus;

// Importa a anotação usada para associar uma exceção a um status HTTP específico
import org.springframework.web.bind.annotation.ResponseStatus;

// Define que, quando essa exceção for lançada, a resposta HTTP será 404 - Not Found
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Identificador de versão da classe para controle de serialização
    private static final long serialVersionUID = 1L;

    // Construtor da exceção, que recebe uma mensagem personalizada
    public ResourceNotFoundException(String msg) {
        
        // Envia a mensagem para a classe pai RuntimeException
        super(msg);
    }
}