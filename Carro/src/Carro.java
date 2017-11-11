
public class Carro {

	private String placa;
	public Motor motor;
	public Motorista motorista;
	

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	
	

	@Override
	public String toString() {
		return "Placa: " + placa +", "+ motor + motorista;
	}
	
	

	public static void main(String[] args) {
		Motorista motorista = new Motorista();
		motorista.setNome("Joao");
		
		Motor motor = new Motor();
		motor.setNome("Zetec");
		
		Carro carro = new Carro();
		carro.setPlaca("AAA1234");
		carro.setMotorista(motorista);
		carro.setMotor(motor);
		
		motor.setPotencia(1.4);
		
		motorista.setNome("jose");
		
		System.out.println(carro.toString());
		
		
		Motor motor2 = new Motor();
		motor2.setNome("fire");
		motor2.setPotencia(2.0);
		carro.setMotor(motor2);
		
		System.out.println(carro.toString());
		
		Carro carro2 = new Carro();
		
		carro2.setPlaca("XYZ2000");
		carro2.setMotorista(motorista);
		carro2.setMotor(motor);
		
		System.out.println(carro.toString());
		System.out.println(carro2.toString());
		
		

	}

}
