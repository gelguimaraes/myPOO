package modelo;

public class Aluno extends Pessoa{
	
	private String curso;
	
	public Aluno(String nome, String email, String senha, String curso) {
		super(nome, email, senha);
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return super.toString() + " - Curso: " + curso;
	}
	
	

}
