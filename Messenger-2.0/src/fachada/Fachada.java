package fachada;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import modelo.Administrador;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Mensagem;
import modelo.Pessoa;
import repositorio.Messenger;

public class Fachada{


	private static Messenger messenger = new Messenger();	
	private static Pessoa logada = null;
	private static int idMensagem; 
	
	
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
		logada = p;
		

	}

	public static void logoff() throws Exception {	// anula a pessoa logada
		if(logada == null)
			throw new Exception ("Não existe uma pessoa logada");
		logada = null;
	}


	public static Pessoa cadastrarPessoa(String nome, String email, String senha, String tipo, String nomeCD) throws Exception{ // cadastra (e retorna) uma pessoa no Messenger
		if(logada != null && logada instanceof Administrador) {
			Pessoa pessoa = messenger.localizarPessoa(nome, email+"@messenger.com");
			if (pessoa != null) {
				throw new Exception("Esta pessoa já está cadastrada: " + pessoa.getNome());
			}
			if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || nomeCD.equals("Selecione")) {
				throw new Exception("Preencha todos os campos!");
			}
			
			if (tipo == "Aluno") {
				pessoa = new Aluno (nome, email+"@messenger.com", senha, nomeCD);
				messenger.adicionarPessoa(pessoa);	
				return pessoa;
			} else if (tipo == "Funcion\u00E1rio") {
				pessoa = new Funcionario (nome, email+"@messenger.com", senha, nomeCD);
				messenger.adicionarPessoa(pessoa);	
				return pessoa;
				
			}else
				throw new Exception("Tipo de pessoa errado!");
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	
	
	public static void cadastrarAdm(String nome, String email, String senha, String nomeCD){
		Pessoa pessoa = new Administrador (nome, email+"@messenger.com", senha, nomeCD);
		messenger.adicionarPessoa(pessoa);	
	}

	

	public static ArrayList<Pessoa> listarPessoas(String nome) throws  Exception{ 
		if(logada != null) {
			if (messenger.getPessoas() == null) {
				throw new Exception("Lista de Pessoas vazia!");
			}
			ArrayList<Pessoa> listapessoas = new  ArrayList<Pessoa>();
	
			if(nome.equals("")) {
				for(Pessoa p : messenger.getPessoas().values()) 
					listapessoas.add(p);
			}else {		
				for(Pessoa p : messenger.getPessoas().values()) {
					if(p.getNome().contains(nome)) {
						listapessoas.add(p);
					}
				}
			}
			return listapessoas;
		}else 
			throw new Exception("Login necessário");
	}
	
	
	public static ArrayList<Pessoa> listarTipo(String nome, String tipo) throws  Exception{ 
		
		if(logada != null && logada instanceof Administrador) {
			if (messenger.getPessoas() == null) {
				throw new Exception("Lista de Pessoas vazia!");
			}
			ArrayList<Pessoa> listatipo = new ArrayList<Pessoa>();
			
			if(!nome.equals("")) {
					if (tipo.equals("Curso")) {
							for(Pessoa p : messenger.getPessoas().values()) {
								if(p instanceof Aluno && ((Aluno) p).getCurso().contains(nome))
								listatipo.add(p);
							}
					}else if (tipo.equals("Departamento")) {
							for(Pessoa p : messenger.getPessoas().values()) {
								if(p instanceof Funcionario && ((Funcionario) p).getDepartamento().contains(nome))
								listatipo.add(p);
							}
					}
			}else {
				throw new Exception("Campo nome em branco!");
			}
			return listatipo;
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	
	
	
	public static String listaPessoasSemEnviarMsg() throws  Exception{ 
		if(logada != null && logada instanceof Administrador) {
			
			if (messenger.getPessoas() == null) {
				throw new Exception("Lista de Pessoas vazia!");
			}
			String listapessoas ="";
			
				for(Pessoa p : messenger.getPessoas().values()) {
					if(p.getCaixaSaida().size() == 0)
						listapessoas += p.getNome() + " (" + p.getEmail() + ")\n";
				}
			
			return listapessoas;
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	
	
	public static String listaMsgsMesmoEmitRem() throws  Exception{ 
		if(logada != null && logada instanceof Administrador) {
			if (messenger.getMensagens() == null) {
				throw new Exception("Lista de mensagens vazia!");
			}
			String listamensagens="", idFormat;
	
					for(Mensagem m : messenger.getMensagens()) {
						if (m.getEmitente().equals(m.getDestinatario())) {
							idFormat = String.format("%02d", m.getId());
							listamensagens += "Mensagem nº: " + idFormat + " (" + m.getEmitente().getNome() + ")\n";
						}
				}
			
			return listamensagens;
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	
	public static ArrayList<Aluno> listaAlunos() throws  Exception{
		
		if (messenger.getPessoas() == null) {
			throw new Exception("Lista de Pessoas vazia!");
		}
		
		ArrayList<Aluno> listaalunos = new ArrayList<Aluno>();
		
		for(Pessoa p : messenger.getPessoas().values()) {
			if(p instanceof Aluno)
				listaalunos.add((Aluno)p);
		}		
		return listaalunos;
	}
	
	
	public static String listaAlunosPorCurso() throws  Exception{ 
		if(logada != null && logada instanceof Administrador) {
			if (listaAlunos() == null) {
				throw new Exception("Lista de alunos vazia!");
			}
			String curso = "", totalAlunosCurso = "";
			int  total;
			TreeMap<String, Integer> alunosporcurso = new TreeMap<>();
			for(Aluno a : listaAlunos()) {
				curso = a.getCurso();	
				if (alunosporcurso.containsKey(curso)){
					total = alunosporcurso.get(curso) + 1;
					alunosporcurso.put(curso, total);
					
				}else {
					alunosporcurso.put(curso, 1);	
					
				}		
			}
			
			for(String c : alunosporcurso.keySet()) {
				String alunos = "";
				for (Aluno a : listaAlunos()){
					if (a.getCurso().equals(c))
					alunos += "  • " + a.getNome() + "\n";
				}
				totalAlunosCurso += c + " [" + alunosporcurso.get(c) + "]\n" 
				+ alunos + "--------------------------------------------------\n";  	
			}
			
			return totalAlunosCurso 
					+ "Total de alunos: [" + listaAlunos().size() + "]";
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	

	public static Mensagem enviarMensagem(Pessoa destinatario, String texto) throws  Exception { // cria (e retorna) uma mensagem no Messenger, onde o emitente é a pessoa logada.
		if(logada!=null) {
			if(texto.isEmpty())
				throw new Exception("Campo Mensagem não pode estar vazio!");

			DateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss'h'");
			Date date = new Date();
			Mensagem msg = new Mensagem(++idMensagem, logada, destinatario, texto, dataformatada.format(date));
			//adiciona mensagem na caixa de saida de logada e na caixa de entrada destinatario

			messenger.adicionarMensagem(msg);
			logada.AddCxSaida(msg);
			destinatario.AddCxEntrada(msg);
			
			return msg;

		}else 
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
	
	
	public static ArrayList<Mensagem> listarAllMensagens() throws  Exception{ // retorna todas as mensagens recebidas
		if(logada != null && logada instanceof Administrador) {	
			return messenger.getMensagens();	
		}
		else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	

	

	public static Mensagem obterMensagem(int id) throws  Exception{ // retorna a mensagem especificada da pessoa logada
		if(logada != null) {
			if(messenger.localizarMensagem(id) != null) {
				return messenger.localizarMensagem(id);
			}else
				throw new Exception("Selecione uma Mensagem!");	
		}else 
			throw new Exception("Login necessário");
	}
	
	
	public static ArrayList<Mensagem> consultarMensagens(String texto) throws  Exception{ 
		if(logada != null && logada instanceof Administrador) {
			if (messenger.getMensagens() == null) {
				throw new Exception("Lista de Mensagens vazia!");
			}
			ArrayList<Mensagem> listamensagens = new  ArrayList<>();
	
			if(!texto.equals("")) {
				for(Mensagem m : messenger.getMensagens()) {
					if(m.getTexto().contains(texto))
					listamensagens.add(m);
				}
			}else {
				throw new Exception("Campo texto em branco!");
			}
			return listamensagens;
		}else 
			throw new Exception("Usuário sem Permissão para esta operação!");
	}
	

	public static boolean apagarMensagem(int id, Pessoa logada) throws  Exception { // exclui a mensagem especificada da pessoa logada na cx de entrada e/ou na cx de saída
		if(logada != null) {
			if(messenger.localizarMensagem(id) != null) {
				messenger.removerMensagem(messenger.localizarMensagem(id), logada, id);
				return true;
			}else
				throw new Exception("Selecione uma Mensagem!");	
		}else 
			throw new Exception("Login necessário");
	}

	
	
	public static Pessoa getLogada() { // retorna a pessoa logada
		return logada;
	}

	
	
	public static Pessoa getEmailDestinatario(String email) throws Exception{
		if(email.isEmpty())
			throw new Exception("Destinatário Inválido!");
		Pessoa destinatario = messenger.localizarEmail(email);
		if (destinatario == null) {
			throw new Exception("Destinatário não encontrado com esse email: " + email + "!");
		}
		return destinatario;
	}
	
}
