package modelo;

public class Mensagem {

	private int id;
	private Pessoa emitente;
	private Pessoa destinatario;
	private String texto;
	private String txtCompac;
	private String data;
	private String idFormat;
	
	
	public Mensagem (int id, Pessoa emitente, Pessoa destinatario, String texto, String data) {
		this.id = id;
		this.emitente = emitente;
		this.destinatario = destinatario;
		this.texto = texto;
		this.data = data;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getEmitente() {
		return emitente;
	}

	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String cxEntrada(){
		if(getTexto().length() > 30) 
			txtCompac = getTexto().substring(0, 30);
		else
			txtCompac = getTexto();
		idFormat = String.format("%02d", id);
		return "Mensagem nº: " + idFormat + ", De: " + emitente.getNome() 
		+ ", Em: " + data + "\n Resumo: " + txtCompac + " ...";
	}
	
	public String cxSaida(){
		if(getTexto().length() >30) 
			txtCompac = getTexto().substring(0, 30);
		else
			txtCompac = getTexto();
		
		idFormat = String.format("%02d", id);
		return "Mensagem nº: " + idFormat + ", Para: " + destinatario.getNome() 
		+ ", Em: " + data + "\n Resumo: " + txtCompac + " ...";
	}

	

}
