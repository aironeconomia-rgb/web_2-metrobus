package com.br.model;

// Importação da classe LocalDate para trabalhar com datas sem horário
import java.time.LocalDate;

// Importações para trabalhar com lista
import java.util.ArrayList;
import java.util.List;

// Import incluído para evitar loop infinito no retorno JSON
import com.fasterxml.jackson.annotation.JsonIgnore;

// Importações das anotações JPA usadas no mapeamento da entidade
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "motorista")
public class Motorista {

	// Define a chave primária da tabela
	@Id

	// Configura o ID para ser gerado automaticamente pelo banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Mapeia a coluna nome
	@Column(name = "nome")
	private String nome;

	// Mapeia a coluna cpf
	@Column(name = "cpf")
	private String cpf;

	// Mapeia a coluna matricula
	@Column(name = "matricula")
	private String matricula;

	// Mapeia a coluna data_admissao
	@Column(name = "data_admissao")
	private LocalDate dataAdmissao;

	// Mapeia a coluna categoria_cnh
	@Column(name = "categoria_cnh")
	private String categoriaCnh;

	// Mapeia a coluna ativo
	@Column(name = "ativo")
	private Boolean ativo;

	// Relacionamento muitos para muitos com Veiculo
	// mappedBy = "motoristas" indica que este lado é o inverso do relacionamento
	// e que a tabela intermediária é controlada pelo atributo motoristas da classe Veiculo
	// JsonIgnore evita loop infinito no retorno JSON
	@JsonIgnore
	@ManyToMany(mappedBy = "motoristas")
	private List<Veiculo> veiculos = new ArrayList<>();

	// Construtor vazio obrigatório para o JPA
	public Motorista() {
	}

	// Retorna o ID do motorista
	public Long getId() {
		return id;
	}

	// Define o ID do motorista
	public void setId(Long id) {
		this.id = id;
	}

	// Retorna o nome do motorista
	public String getNome() {
		return nome;
	}

	// Define o nome do motorista
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna o CPF do motorista
	public String getCpf() {
		return cpf;
	}

	// Define o CPF do motorista
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	// Retorna a matrícula do motorista
	public String getMatricula() {
		return matricula;
	}

	// Define a matrícula do motorista
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	// Retorna a data de admissão do motorista
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	// Define a data de admissão do motorista
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	// Retorna a categoria da CNH do motorista
	public String getCategoriaCnh() {
		return categoriaCnh;
	}

	// Define a categoria da CNH do motorista
	public void setCategoriaCnh(String categoriaCnh) {
		this.categoriaCnh = categoriaCnh;
	}

	// Retorna se o motorista está ativo
	public Boolean getAtivo() {
		return ativo;
	}

	// Define se o motorista está ativo
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	// Retorna a lista de veículos associados ao motorista
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	// Define a lista de veículos associados ao motorista
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
}