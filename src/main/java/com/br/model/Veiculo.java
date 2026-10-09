package com.br.model;

// Importação da classe LocalDate para trabalhar com datas sem horário
import java.time.LocalDate;

// Importações para trabalhar com lista
import java.util.ArrayList;
import java.util.List;

// Import incluído para evitar loop no retorno JSON
import com.fasterxml.jackson.annotation.JsonIgnore;

// Importações das anotações JPA usadas no mapeamento da entidade
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

// Indica que esta classe representa uma entidade do banco de dados
@Entity

// Define o nome da tabela no banco
@Table(name = "veiculo")
public class Veiculo {

    // Define a chave primária da tabela
    @Id

    // Configura o ID para ser gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia a coluna numero_frota
    // Alterado de int para Integer para evitar erro de desserialização com JSON parcial
    @Column(name = "numero_frota")
    private Integer numeroFrota;

    // Mapeia a coluna placa
    @Column(name = "placa")
    private String placa;

    // Mapeia a coluna modelo
    @Column(name = "modelo")
    private String modelo;

    // Mapeia a coluna fabricante
    @Column(name = "fabricante")
    private String fabricante;

    // Mapeia a coluna data_de_fabricacao
    // O tipo LocalDate representa apenas a data
    @Column(name = "data_de_fabricacao")
    private LocalDate dataDeFabricacao;

    // Mapeia a coluna capacidade_passageiros
    @Column(name = "capacidade_passageiros")
    private Integer capacidadePassageiros;

    // Mapeia a coluna consumo_por_km
    @Column(name = "consumo_por_km")
    private Double consumoPorKM;

    // Mapeia a coluna ativo
    // Indica se o veículo está ativo ou não
    @Column(name = "ativo")
    private Boolean ativo;

    // Relacionamento muitos para muitos com Linha
    // mappedBy = "veiculos" indica que este lado é o inverso do relacionamento
    // e que a tabela intermediária é controlada pelo atributo veiculos da classe Linha
    @JsonIgnore
    @ManyToMany(mappedBy = "veiculos")
    private List<Linha> linhas = new ArrayList<>();

    // Relacionamento muitos para muitos com Motorista
    // Um veículo pode ser dirigido por vários motoristas
    // Um motorista pode dirigir vários veículos
    @ManyToMany
    @JoinTable(
        name = "motorista_veiculo",
        joinColumns = @JoinColumn(name = "veiculo_id"),
        inverseJoinColumns = @JoinColumn(name = "motorista_id")
    )
    private List<Motorista> motoristas = new ArrayList<>();

    // Construtor vazio obrigatório para o JPA
    public Veiculo() {
    }

    // Retorna o ID do veículo
    public Long getId() {
        return id;
    }

    // Define o ID do veículo
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o número de frota
    public Integer getNumeroFrota() {
        return numeroFrota;
    }

    // Define o número de frota
    public void setNumeroFrota(Integer numeroFrota) {
        this.numeroFrota = numeroFrota;
    }

    // Retorna a placa do veículo
    public String getPlaca() {
        return placa;
    }

    // Define a placa do veículo
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Retorna o modelo do veículo
    public String getModelo() {
        return modelo;
    }

    // Define o modelo do veículo
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Retorna o fabricante do veículo
    public String getFabricante() {
        return fabricante;
    }

    // Define o fabricante do veículo
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    // Retorna a data de fabricação do veículo
    public LocalDate getDataDeFabricacao() {
        return dataDeFabricacao;
    }

    // Define a data de fabricação do veículo
    public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
        this.dataDeFabricacao = dataDeFabricacao;
    }

    // Retorna a capacidade de passageiros
    public Integer getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    // Define a capacidade de passageiros
    public void setCapacidadePassageiros(Integer capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }

    // Retorna o consumo por km
    public Double getConsumoPorKM() {
        return consumoPorKM;
    }

    // Define o consumo por km
    public void setConsumoPorKM(Double consumoPorKM) {
        this.consumoPorKM = consumoPorKM;
    }

    // Retorna se o veículo está ativo
    public Boolean getAtivo() {
        return ativo;
    }

    // Define se o veículo está ativo
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // Retorna a lista de linhas associadas ao veículo
    public List<Linha> getLinhas() {
        return linhas;
    }

    // Define a lista de linhas associadas ao veículo
    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    // Retorna a lista de motoristas associados ao veículo
    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    // Define a lista de motoristas associados ao veículo
    public void setMotoristas(List<Motorista> motoristas) {
        this.motoristas = motoristas;
    }
}