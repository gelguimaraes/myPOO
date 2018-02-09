package modelo;

public class Funcionario extends Pessoa {

	private String departamento;
	
	public Funcionario(String nome, String email, String senha, String departamento) {
		super(nome, email, senha);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return super.toString() + " - Departamento: " + departamento;
	}
	
	

}
