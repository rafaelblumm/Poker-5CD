// RAFAEL FLORES BLUMM

public class Baralho {
	private Carta[] cartas;

	public Baralho() {
		cartas = new Carta[52];
		String[] nomeCartas = {"Dois", "Três", "Quatro", "Cinco", "Seis", "Sete",
							   "Oito", "Nove", "Dez", "Valete", "Dama", "Rei", "Às"};
		String[] nomeNaipes = {"Espadas", "Paus", "Copas", "Ouros"};
		
		int i = 0;
		for(int contNaipe = 0; contNaipe < 4; contNaipe++)
			for(int contCarta = 0; contCarta < 13; contCarta++) {
				cartas[i] = new Carta(nomeCartas[contCarta], nomeNaipes[contNaipe], contCarta+2 );
				i++;
			}
	}
	
	public Carta[] getCartas() {
		return cartas;
	}

	public void setCartas(Carta[] cartas) {
		this.cartas = cartas;
	}
	
	/**
	 * Método que embaralha cartas de maneira aleatória.
	 */
	public void embaralha() {
		int valorAleatorioA, valorAleatorioB;
		Carta holdCarta;
		for(int i = 0; i < 200; i++) {
			valorAleatorioA = (int)(Math.random() * 52);
			valorAleatorioB = (int)(Math.random() * 52);
			holdCarta = cartas[valorAleatorioB];
			
			cartas[valorAleatorioB] = cartas[valorAleatorioA];
			cartas[valorAleatorioA] = holdCarta;
		}
	}
	
	/**
	 * Método que remove e retorna a carta do topo do baralho.
	 * @return (Carta) carta do topo do baralho.
	 */
	public Carta distribuiCarta() {
		Carta holdCarta;
		int i = 0;
		while(true) {
			if(cartas[i] != null) {
				holdCarta = cartas[i];
				cartas[i] = null;
				return holdCarta;
			} else
				i++;
		}
	}
	
	/**
	 * Método que recebe um objeto Carta por parâmetro e a insere no Array de Carta.
	 * @param (Carta) carta a ser inserida no Array de Carta.
	 */
	public void adicionaCarta(Carta c) {
		for(int i = 0; i < cartas.length; i++)
			if(cartas[i] == null) {
				cartas[i] = c;
				break;
			}
	}
	
	/**
	 * Método que conta quantos objetos de Carta existem no Array de Carta.
	 * @return (int) quantidade de cartas no baralho.
	 */
	public int quantCartas() {
		int total = 0;
		for(Carta c: cartas)
			if(c != null)
				total++;
		return total;
	}
	
	/**
	 * Método que imprime o baralho carta por carta.
	 */
	public void imprimeBaralho() {
		for(Carta c: cartas)
			if(c != null)
				System.out.println(c.toString());
	}
}
