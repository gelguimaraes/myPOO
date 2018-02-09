package repositorio;
import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Mensagem;
import modelo.Pessoa;

public class Messenger {

	private TreeMap<String, Pessoa> pessoas = new TreeMap<>();
	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();

	public TreeMap<String, Pessoa> getPessoas() {
		return pessoas;
	}

	public ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}

	public Pessoa localizarLogin(String email, String senha) {
		
		for(Pessoa p : pessoas.values()) {
			if(p.getEmail().equals(email) && p.getSenha().equals(senha))
				return p;
		}
		return null;
	}
	
	public Pessoa localizarPessoa(String nome, String email) {
		for(Pessoa p : pessoas.values()){
			if(p.getNome().equals(nome) && p.getEmail().equals(email))
				return p;
		}
		return null;
	}
	
	public Mensagem localizarMensagem(int id) {
		for(Mensagem m : mensagens){
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	
	public Pessoa localizarEmail(String email) {
		for(Pessoa p : pessoas.values()){
			if(p.getEmail().equals(email))
				return p;
		}
		return null;
	}
	
	
	public void adicionarPessoa(Pessoa p) {
		pessoas.put(p.getEmail(), p);
	}
	
	public void removerPessoa(Pessoa p) {
		pessoas.remove(p.getEmail());
	}
	
	public void adicionarMensagem(Mensagem m) {
		mensagens.add(m);
	}
	
	public void removerMensagem(Mensagem m, Pessoa logada, int id) {
		for (int i=0; i < logada.getCaixaEntrada().size(); i++) {
			m = logada.getCaixaEntrada().get(i);
			if(m.getId() == id) {
				logada.RemoveCxEntrada(m);
				i--;
			}
		}

		for (int i=0; i < logada.getCaixaSaida().size(); i++) {
			m = logada.getCaixaSaida().get(i);
			if(m.getId() == id) {
				logada.RemoveCxSaida(m);
				i--;
			}
		}
		//mensagens.remove(m);
	}

}
