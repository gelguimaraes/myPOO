
public class Motor {

	private String nome;
	private double potencia;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPotencia() {
		return potencia;
	}
	
	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}
	@Override
	public String toString() {
		return " Motor: " + nome + ", Potencia: " + potencia + ", ";
	}
	

	
}
