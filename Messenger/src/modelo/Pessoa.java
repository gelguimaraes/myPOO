package modelo;
import java.util.ArrayList;

public class Pessoa {
	private String email;
	private String senha;
	private String nome;
	private ArrayList<Mensagem> caixaEntrada = new ArrayList<Mensagem>();
	private ArrayList<Mensagem> caixaSaida = new ArrayList<Mensagem>();
	
	public Pessoa(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void AddCxEntrada(Mensagem m) {
		caixaEntrada.add(m);
	}
	
	public void RemoveCxEntrada(Mensagem m) {
		caixaEntrada.remove(m);
	}
	
	
	public void AddCxSaida(Mensagem m) {
		caixaSaida.add(m);
	}
	
	public void RemoveCxSaida(Mensagem m) {
		caixaSaida.remove(m);
	}

	public ArrayList<Mensagem> getCaixaEntrada() {
		return caixaEntrada;
	}

	public ArrayList<Mensagem> getCaixaSaida() {
		return caixaSaida;
	}

	@Override
	public String toString() {
		return nome + " (" + email + ")";
	}

}
