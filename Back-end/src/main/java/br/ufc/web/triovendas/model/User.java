package br.ufc.web.triovendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue
	int id;
	
	@Column(length = 11)
	@Size(min = 11, max = 11, message = "Seu cpf deve possuir 11 digitos")
	String cpf;
	
	@Size(min = 6, max = 50, message = "sua senha deve possuir pelo menos 6 caracteres")
	String senha;

	@Size(min = 1, max = 100, message = "O nome é obrigatório")
	@NotNull(message = "O nome não pode ser nulo")
	String nome;

	@Size(min = 1, max = 100, message = "O email é obrigatório")
	String email;
	
	@Size(min = 12, max = 12, message = "Seu telefone deve possuir 12 digitos")
	@Column(length = 12)
	String telefone;
	
	@Size(min = 1, max = 100, message = "O estado é obrigatório")
	@NotNull(message = "O estado não pode ser nulo")
	String estado;

	@Size(min = 1, max = 100, message = "A cidade é obrigatório")
	@NotNull(message = "A cidade não pode ser nulo")
	String cidade;
	
	@Size(min = 1, max = 100, message = "O complemento é obrigatório")
	@NotNull(message = "O complemento não pode ser nulo")
	String complemento;

	public User() {
	}

	public User(int id, String cpf, String senha, String nome, String email, String telefone, String estado,
			String cidade, String complemento) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.estado = estado;
		this.cidade = cidade;
		this.complemento = complemento;
	}

	public User(String cpf, String senha, String nome, String email, String telefone, String estado, String cidade,
			String complemento) {
		super();
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.estado = estado;
		this.cidade = cidade;
		this.complemento = complemento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", cpf=" + cpf + ", senha=" + senha + ", nome=" + nome + ", email=" + email
				+ ", telefone=" + telefone + ", estado=" + estado + ", cidade=" + cidade + ", complemento="
				+ complemento + "]";
	}

}
