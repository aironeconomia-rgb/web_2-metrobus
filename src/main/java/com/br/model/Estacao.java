package com.br.model;

// Importações para trabalhar com lista
import java.util.ArrayList;
import java.util.List;

// Importações das anotações JPA usadas no mapeamento da entidade
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

// Import incluido para solucionar possiveis problemas de loop
import com.fasterxml.jackson.annotation.JsonIgnore;

// Indica que esta classe representa uma entidade do banco de dados
@Entity

// Define o nome da tabela no banco
@Table(name = "estacao")
public class Estacao {

    // Define a chave primária da tabela
    @Id

    // Configura o ID para ser gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia a coluna nome
    // nullable = false indica que o campo é obrigatório
    @Column(name = "nome", nullable = false)
    private String nome;

    // Mapeia a coluna codigoestacao
    // Campo obrigatório
    // Alterado de int para Integer para evitar erro de desserialização com JSON parcial
    @Column(name = "codigoestacao", nullable = false)
    private Integer codigoestacao;

    // Mapeia a coluna endereco
    // Campo obrigatório
    @Column(name = "endereco", nullable = false)
    private String endereco;

    // Mapeia a coluna capacidade_media_usuarios
    // Pode aceitar valor nulo
    @Column(name = "capacidade_media_usuarios")
    private Integer capacidadeMediaUsuarios;

    // Mapeia a coluna ativa
    // Indica se a estação está ativa ou não
    @Column(name = "ativa")
    private Boolean ativa;

    // Relacionamento muitos para muitos com Linha
    // mappedBy = "estacoes" indica que este lado é o inverso do relacionamento
    @JsonIgnore
    @ManyToMany(mappedBy = "estacoes")
    private List<Linha> linhas = new ArrayList<>();

    // Construtor vazio obrigatório para o JPA
    public Estacao() {
    }

    // Construtor com os atributos principais da entidade
    public Estacao(Long id, String nome, Integer codigoestacao, String endereco, Integer capacidadeMediaUsuarios, Boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.codigoestacao = codigoestacao;
        this.endereco = endereco;
        this.capacidadeMediaUsuarios = capacidadeMediaUsuarios;
        this.ativa = ativa;
    }

    // Retorna o ID da estação
    public Long getId() {
        return id;
    }

    // Define o ID da estação
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o nome da estação
    public String getNome() {
        return nome;
    }

    // Define o nome da estação
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o código da estação
    public Integer getCodigoestacao() {
        return codigoestacao;
    }

    // Define o código da estação
    public void setCodigoestacao(Integer codigoestacao) {
        this.codigoestacao = codigoestacao;
    }

    // Retorna o endereço da estação
    public String getEndereco() {
        return endereco;
    }

    // Define o endereço da estação
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Retorna a capacidade média de usuários
    public Integer getCapacidadeMediaUsuarios() {
        return capacidadeMediaUsuarios;
    }

    // Define a capacidade média de usuários
    public void setCapacidadeMediaUsuarios(Integer capacidadeMediaUsuarios) {
        this.capacidadeMediaUsuarios = capacidadeMediaUsuarios;
    }

    // Retorna se a estação está ativa
    public Boolean getAtiva() {
        return ativa;
    }

    // Define se a estação está ativa
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    // Retorna a lista de linhas associadas à estação
    public List<Linha> getLinhas() {
        return linhas;
    }

    // Define a lista de linhas associadas à estação
    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }
}