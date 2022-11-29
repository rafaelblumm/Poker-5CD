// RAFAEL FLORES BLUMM

public class Carta implements Comparable<Carta>{
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
	
	@Override
	public String toString() {
		return nome+" de "+naipe;
	}
	
	@Override
	public int compareTo(Carta c) {
		return valor - c.getValor();
	}
	
	/**
	 * Método que informa o valor e naipe da carta com a formatação VVN (Valor/Naipe).
	 * @return (String) Valor da carta no formato VVN (Valor/Naipe).
	 */
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
