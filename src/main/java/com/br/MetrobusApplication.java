package com.br;

// Importa a classe responsável por iniciar a aplicação Spring Boot
import org.springframework.boot.SpringApplication;

// Importa a anotação que marca esta classe como a classe principal da aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Indica que esta é a classe principal do projeto.
@SpringBootApplication
public class MetrobusApplication {

	// Método principal da aplicação Java.
	public static void main(String[] args) {
		
		// Inicializa e executa a aplicação Spring Boot
		SpringApplication.run(MetrobusApplication.class, args);
	}

}