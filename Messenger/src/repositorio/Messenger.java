package repositorio;
import java.util.ArrayList;

import modelo.Mensagem;
import modelo.Pessoa;

public class Messenger {

	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}

	public Pessoa localizarLogin(String email, String senha) {
		for(Pessoa p : pessoas){
			if(p.getEmail().equals(email) && p.getSenha().equals(senha))
				return p;
		}
		return null;
	}
	
	public Pessoa localizarPessoa(String nome) {
		for(Pessoa p : pessoas){
			if(p.getNome().equals(nome))
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
		for(Pessoa p : pessoas){
			if(p.getEmail().equals(email))
				return p;
		}
		return null;
	}
	
	//adicionar e remover pessoa em arraylist de pessoas ou mensagem em arraylist de mensagems
	public void adicionarPessoa(Pessoa p) {
		pessoas.add(p);
	}
	
	public void removerPessoa(Pessoa p) {
		pessoas.remove(p);
	}
	
	public void adicionarMensagem(Mensagem m) {
		mensagens.add(m);
	}
	
	public void removerMensagem(Mensagem m) {
		mensagens.remove(m);
	}
	
	public int getTotalMenaagens(){
		return mensagens.size();
	}

}
