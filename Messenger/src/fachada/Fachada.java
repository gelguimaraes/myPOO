package fachada;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Mensagem;
import modelo.Pessoa;
import repositorio.Messenger;

public class Fachada{
	

	private static Messenger messenger = new Messenger();	
	private static Pessoa logada = null;
	private static int idMensagem; //autoincremento
	

	
	public static void login(String email, String senha) throws Exception { // valida a pessoa no Messenger, que passa a ser a pessoa logada
		if(logada != null)
			throw new Exception ("Já existe uma pessoa logada");
		if (email.isEmpty()){
			throw new Exception("Preencha o campo E-mail corretamente!");
		}
		if (senha.isEmpty()){
			throw new Exception("Preencha o campo Senha corretamente!");
		}
		Pessoa p = messenger.localizarLogin(email, senha);
		if(p == null)
			throw new Exception ("Login não foi possível! Dados E-mail ou Senha Inválidos!");
		else
			logada = p;
	
	}
	
	public static void logoff() throws Exception {	// anula a pessoa logada
		if(logada == null)
			throw new Exception ("Não existe uma pessoa logada");
		logada = null;
	}

	
	public static Pessoa cadastrarPessoa(String nome, String email, String senha) throws Exception{ // cadastra (e retorna) uma pessoa no Messenger
		
		Pessoa pessoa = messenger.localizarPessoa(nome);
		if (pessoa != null) {
				throw new Exception("Esta pessoa já cadastrada: " + pessoa.getNome());
			}
		if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
			throw new Exception("Preencha todos os campos!");
		}
		 pessoa = new Pessoa(nome, email+"@messenger.com", senha); //criar pessoa e adicionar no messenger
			messenger.adicionarPessoa(pessoa);	
			return pessoa;
	}
	
	public static ArrayList<Pessoa> listarPessoas(String nome) throws  Exception{ // localiza e retorna as pessoas do Messenger que contém o nome (ou parte dele) fornecido
		if (messenger.getPessoas() == null) {
			 throw new Exception("Lista de Pessoas vazia!");
		}
		ArrayList<Pessoa> listapessoas = new  ArrayList<Pessoa>();
		
		if(nome.equals("")) {
			for(Pessoa p : messenger.getPessoas()) 
				listapessoas.add(p);
		}else {		
			for(Pessoa p : messenger.getPessoas()) {
				if(p.getNome().contains(nome)) {
					listapessoas.add(p);
				}
			}
		}
		return listapessoas;
		
		
	}
	
	public static Mensagem enviarMensagem(Pessoa destinatario, String texto) throws  Exception { // cria (e retorna) uma mensagem no Messenger, onde o emitente é a pessoa logada.
		if(logada!=null) {
			if(texto.isEmpty())
				throw new Exception("Campo Mensagem não pode estar vazio!");
			
				DateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss'h'");
				Date date = new Date();
				idMensagem++;
				Mensagem msg = new Mensagem(idMensagem, logada, destinatario, texto, dataformatada.format(date));
				//adiciona mensagem na caixa de saida de logada e na caixa de entrada destinatario
				
				messenger.adicionarMensagem(msg);
				logada.AddCxSaida(msg);
				destinatario.AddCxEntrada(msg);
				
				
				return msg;
				
		}
		else 
			throw new Exception("Login necessário");
	}
	
	
	public static ArrayList<Mensagem> listarCxEntrada() throws  Exception{ // retorna as mensagens recebidas pela pessoa logada
		if(logada!=null) 
			return logada.getCaixaEntrada();	
		else 
			throw new Exception("Login necessário");
	}
	
	
	public static ArrayList<Mensagem> listarCxSaida() throws  Exception{ // retorna as mensagens enviadas pela pessoa logada
		if(logada!=null) 
			return logada.getCaixaSaida();	
		else 
			throw new Exception("Login necessário");
	}
	
	public static Mensagem obterMensagem(int id) throws  Exception{ // retorna a mensagem especificada da pessoa logada
		if(logada!=null) {
			if(messenger.localizarMensagem(id) != null) {
				return messenger.localizarMensagem(id);
			}else
				throw new Exception("Selecione uma Mensagem!");	
		}
		else 
		throw new Exception("Login necessário");
	}
	
	public static boolean apagarMensagem(int id) throws  Exception { // exclui a mensagem especificada da pessoa logada, na cx de entrada e/ou na cx de saída
		if(logada!=null) {
			if(messenger.localizarMensagem(id) != null) {
				for (int i=0; i < logada.getCaixaEntrada().size(); i++) {
					Mensagem m = logada.getCaixaEntrada().get(i);
						if(m.getId() == id) {
							logada.RemoveCxEntrada(m);
							i--;
						}
				}
				
				for (int i=0; i < logada.getCaixaSaida().size(); i++) {
					Mensagem m = logada.getCaixaSaida().get(i);
						if(m.getId() == id) {
							logada.RemoveCxSaida(m);
							i--;
						}
				}

				messenger.removerMensagem(messenger.localizarMensagem(id));
				return true;
			}else
				throw new Exception("Selecione uma Mensagem!");	
		}
		else 
		throw new Exception("Login necessário");
	}
	
	public static Pessoa getLogada() { // retorna a pessoa logada
		return logada;
	}
	
	public static Pessoa emailDestinatario(String email) throws Exception{
		if(email.isEmpty())
			throw new Exception("Destinatário Inválido!");
		Pessoa destinatario = messenger.localizarEmail(email);
		if (destinatario == null) {
			throw new Exception("Destinatário não encontrado com esse email: " + email + "!");
		}
		return destinatario;
	}
}
