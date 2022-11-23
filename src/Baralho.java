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
	
	public void embaralha() {
		int valorAleatorioA, valorAleatorioB;
		Carta holdCarta;
		for(int i = 0; i < 200; i++) {
			valorAleatorioA = (int)(Math.random() * 52);          // Math.random() * (max - min) + min
			valorAleatorioB = (int)(Math.random() * 52);          // Math.random() * (52 - 0) + 0
			holdCarta = cartas[valorAleatorioB];
			
			cartas[valorAleatorioB] = cartas[valorAleatorioA];
			cartas[valorAleatorioA] = holdCarta;
		}
	}
	
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
	
	public void adicionaCarta(Carta c) {
		for(int i = 0; i < cartas.length; i++)
			if(cartas[i] == null) {
				cartas[i] = c;
				break;
			}
	}
	
	public int quantCartas() {
		int total = 0;
		for(Carta c: cartas)
			if(c != null)
				total++;
		return total;
	}
	
	public void imprimeBaralho() {
		for(Carta c: cartas)
			if(c != null)
				System.out.println(c.toString());
	}
}
