


public class Conta {
		
		private String numero;
		private String cliente;
		private double saldo;
		
		public Conta (String num, String cli) {
			super();
			this.numero = num;
			this.cliente = cli;
		}
		public void creditar(double valor) {
			saldo = saldo + valor;
		}
		public void debitar(double valor) {
			saldo = saldo - valor;
		}
	

		
		@Override
		public String toString() {
			return "Conta [numero=" + numero + ", cliente=" + cliente + ", saldo=" + saldo + "]";
		}
}