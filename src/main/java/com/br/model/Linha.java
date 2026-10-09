package com.br.model;

// Importações para trabalhar com lista
import java.util.ArrayList;
import java.util.List;

// Importações das anotações JPA usadas para mapear a classe no banco de dados
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

// Indica que esta classe é uma entidade JPA, ou seja, representa uma tabela no banco
@Entity

// Define explicitamente o nome da tabela no banco de dados
@Table(name = "linha")
public class Linha {

    // Define o atributo id como chave primária da tabela
    @Id

    // Configura a geração automática do ID pelo banco, normalmente auto incremento
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Mapeia a coluna codigo_linha
    // nullable = false -> campo obrigatório
    // unique = true -> não permite dois registros com o mesmo código
    // Alterado de int para Integer para evitar erro de desserialização com JSON parcial
    @Column(name = "codigo_linha", nullable = false, unique = true)
    private Integer codigolinha;

    // Mapeia a coluna nome
    // nullable = false -> obrigatório
   
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    // Mapeia a coluna origem
    @Column(name = "origem", nullable = false, length = 100)
    private String origem;

    // Mapeia a coluna destino
    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    // Mapeia a coluna ativa
    // Boolean indica se a linha está ativa ou não
    @Column(name = "ativa", nullable = false)
    private Boolean ativa;

    // Relacionamento muitos para muitos com Estacao
    // Uma linha pode passar por várias estações
    // Uma estação pode pertencer a várias linhas
    @ManyToMany
    @JoinTable(
        name = "linha_estacao",
        joinColumns = @JoinColumn(name = "linha_id"),
        inverseJoinColumns = @JoinColumn(name = "estacao_id")
    )
    private List<Estacao> estacoes = new ArrayList<>();

    // Relacionamento muitos para muitos com Veiculo
    // Uma linha pode utilizar vários veículos
    // Um veículo pode ser utilizado em várias linhas
    @ManyToMany
    @JoinTable(
        name = "linha_veiculo",
        joinColumns = @JoinColumn(name = "linha_id"),
        inverseJoinColumns = @JoinColumn(name = "veiculo_id")
    )
    private List<Veiculo> veiculos = new ArrayList<>();

    // Construtor vazio obrigatório para o JPA
    public Linha() {
    }

    // Construtor com os atributos principais da entidade
    public Linha(Long id, Integer codigolinha, String nome, String origem, String destino, Boolean ativa) {
        this.id = id;
        this.codigolinha = codigolinha;
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.ativa = ativa;
    }

    // Retorna o ID da linha
    public Long getId() {
        return id;
    }

    // Define o ID da linha
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o código da linha
    public Integer getCodigolinha() {
        return codigolinha;
    }

    // Define o código da linha
    public void setCodigolinha(Integer codigolinha) {
        this.codigolinha = codigolinha;
    }

    // Retorna o nome da linha
    public String getNome() {
        return nome;
    }

    // Define o nome da linha
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a origem da linha
    public String getOrigem() {
        return origem;
    }

    // Define a origem da linha
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    // Retorna o destino da linha
    public String getDestino() {
        return destino;
    }

    // Define o destino da linha
    public void setDestino(String destino) {
        this.destino = destino;
    }

    // Retorna se a linha está ativa
    public Boolean getAtiva() {
        return ativa;
    }

    // Define se a linha está ativa
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    // Retorna a lista de estações associadas à linha
    public List<Estacao> getEstacoes() {
        return estacoes;
    }

    // Define a lista de estações associadas à linha
    public void setEstacoes(List<Estacao> estacoes) {
        this.estacoes = estacoes;
    }

    // Retorna a lista de veículos associados à linha
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    // Define a lista de veículos associados à linha
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}