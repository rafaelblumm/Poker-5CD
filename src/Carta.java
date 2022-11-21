// RAFAEL FLORES BLUMM

public class Carta {
	private String nome, naipe;
	private int valor;

	public Carta(String nome, String naipe, int valor) {
		this.nome = nome;
		this.naipe = naipe;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNaipe() {
		return naipe;
	}

	public void setNaipe(String naipe) {
		this.naipe = naipe;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String toString() {
		return nome+" de "+naipe;
	}
	
	public String cartaSimbolo() {
		String str;
		if(valor >= 2 && valor <= 10)
			str = ""+valor;
		else if(valor == 11)
			str = "J";
		else if(valor == 12)
			str = "Q";
		else if(valor == 13)
			str = "K";
		else
			str = "A";
		
		if(naipe.equalsIgnoreCase("Espadas"))
			str += "E";
		else if(naipe.equalsIgnoreCase("Paus"))
			str += "P";
		else if(naipe.equalsIgnoreCase("Copas"))
			str += "C";
		else
			str += "O";
		
		return str;
	}
}