// RAFAEL FLORES BLUMM

import java.util.ArrayList;

public class Controle {
	private ArrayList<Jogador> jogadores;
	private Baralho baralho;
	private ArrayList<Carta> pilhaDescarte;
	private int smallBlind, bigBlind, pote, rodada;
	private Jogador jogadorDealer, jogadorSB, jogadorBB;
	private int[] apostasRodada;
	
	public Controle() {
		this.jogadores = new ArrayList<Jogador>();
		this.baralho = new Baralho();
		this.pilhaDescarte = new ArrayList<Carta>();
		this.smallBlind = 5;
		this.bigBlind = smallBlind * 2;
		this.pote = 0;
		this.rodada = 0;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public ArrayList<Carta> getPilhaDescarte() {
		return pilhaDescarte;
	}

	public void setPilhaDescarte(ArrayList<Carta> pilhaDescarte) {
		this.pilhaDescarte = pilhaDescarte;
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getPote() {
		return pote;
	}

	public void setPote(int pote) {
		this.pote = pote;
	}

	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public Jogador getJogadorDealer() {
		return jogadorDealer;
	}

	public void setJogadorDealer(Jogador jogadorDealer) {
		this.jogadorDealer = jogadorDealer;
	}

	public Jogador getJogadorSB() {
		return jogadorSB;
	}

	public void setJogadorSB(Jogador jogadorSB) {
		this.jogadorSB = jogadorSB;
	}

	public Jogador getJogadorBB() {
		return jogadorBB;
	}

	public void setJogadorBB(Jogador jogadorBB) {
		this.jogadorBB = jogadorBB;
	}

	public int[] getApostasRodada() {
		return apostasRodada;
	}

	public void setApostasRodada(int[] apostasRodada) {
		this.apostasRodada = apostasRodada;
	}

	public void adicionaJogadores() {
		String[] nomes = {"Alberto", "Bianca", "Carla", "Diego"};
		for(int i = 0; i < nomes.length; i++)
			jogadores.add(i, new Jogador(nomes[i], true));
		jogadores.add(jogadores.size(), new Jogador(Teclado.leString("Jogador, digite seu nome: "), false));
		
		this.jogadorDealer = jogadores.get(jogadores.size() - 1);
		this.jogadorSB = jogadores.get(0);
		this.jogadorBB = jogadores.get(1);
	}
	
	public void imprimeJogadores() {
		for(Jogador j: jogadores)
			System.out.println(j.toString()+"\n");
	}
	
	public void novaRodada() {
		pote = 0;
		rodada += 1;
		apostasRodada = new int[jogadores.size()];
		
		for(int i = 0; i < jogadores.size(); i++)
			jogadores.get(i).setJogandoRodada(true);
		
		resetBaralho();
		System.out.println("Embaralhando...");
		baralho.embaralha();
	}
	
	public void resetBaralho() {
		while(!pilhaDescarte.isEmpty()) {
			baralho.adicionaCarta(pilhaDescarte.get(0));
			pilhaDescarte.remove(0);
		}
	}
	
	public void distribuiMaos() {
		for(Jogador j: jogadores)
			for(int i = 0; i < 5; i++)
				j.recebeCarta(baralho.distribuiCarta(), i);
	}
	
	public void apostasIniciais() {
		if(jogadorSB.getFichas() <= smallBlind) {
			pote += jogadorSB.getFichas();
			jogadorSB.setFichas(0);
		} else {
			jogadorSB.setFichas(jogadorSB.getFichas() - smallBlind);
			pote += smallBlind;
		}
		
		if(jogadorBB.getFichas() <= bigBlind) {
			pote += jogadorBB.getFichas();
			jogadorBB.setFichas(0);
		} else {
			jogadorBB.setFichas(jogadorBB.getFichas() - bigBlind);
			pote += bigBlind;
		}
	}
	
	public void trocaCartas(Jogador jogador) {
		if(jogador.isBot()) {
			int valorAleatorio = (int)(Math.random() * 9);
			if(valorAleatorio < 5)
				System.out.println(jogador.getNome()+" manteve sua mão inicial.");
			else {
				int[] opCartas = new int[(int)(Math.random() * 5 + 1)];  // Math.random() * (max - min) + min
				for (int i = 0; i < opCartas.length; i++) {
					boolean existe = true;
					while(existe) {
						existe = false;
						valorAleatorio = (int)(Math.random() * 5 + 1);
				    
					    for(int k = 0; k < i; k++) {
					    	if(valorAleatorio == opCartas[k]) {
					    		existe = true;
					    		break;
					    	}
					    }
					    if(!existe) {
					    	opCartas[i] = valorAleatorio;
					    }
					}
				}
				for(int pos: opCartas) {
					pilhaDescarte.add(jogador.getMao()[pos - 1]);
					jogador.recebeCarta(baralho.distribuiCarta(), pos - 1);
				}
				System.out.println(jogador.getNome()+" trocou "+opCartas.length+" cartas.");
			}
		}else {
			String op = Teclado.leString("Você gostaria de trocar alguma carta? (S/N)");
			while(!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
				op = Teclado.leString("Digite uma opção válida. (S/N)");
			
			if(op.equalsIgnoreCase("N"))
				System.out.println("Nenhuma carta será trocada.");
			else {
				String[] opCartas = Teclado.leString("Digite o número das cartas que gostria de trocar. (1 2 3 4 5)").split(" ");
				for(String pos: opCartas) {
					pilhaDescarte.add(jogador.getMao()[Integer.parseInt(pos) - 1]);
					jogador.recebeCarta(baralho.distribuiCarta(), Integer.parseInt(pos) - 1);
				}
				System.out.println(jogador.getNome()+" trocou "+opCartas.length+" cartas.");
			}
		}
	}
	
	public void rodadaApostas() {
		int posPrimeiroJogador;
		if(jogadores.indexOf(jogadorBB) == jogadores.size() - 1)
			posPrimeiroJogador = 0;
		else
			posPrimeiroJogador = jogadores.indexOf(jogadorBB) + 1;
		int posAtualJogador = posPrimeiroJogador;
		
		while(true) {
			for(int i = 0; i < jogadores.size(); i++) 
				if(jogadores.get(posAtualJogador).isJogandoRodada()) {
					apostaIndividual(posAtualJogador);
					
					posAtualJogador++;
					if(posAtualJogador == jogadores.size())
						posAtualJogador = 0;
				}
			
			if(todasApostasIguais() && quantJogadoresRodada() > 1)
				break;
		}
	}
	
	public void apostaIndividual(int posJogador) {
		int maiorAposta = bigBlind;
		for(int aposta: apostasRodada)
			if(aposta > maiorAposta)
				maiorAposta = aposta;
		
		System.out.println("\n******************************************************************");
		System.out.println("\nVEZ DE "+jogadores.get(posJogador).getNome()+
							"\nPote: $"+pote+
							"\nSuas fichas: $"+jogadores.get(posJogador).getFichas()+"\n"+
							"\n    1. Call ($"+maiorAposta+")"+
							"\n    2. Raise (mínimo $"+(maiorAposta + bigBlind)+")"+
							"\n    3. Fold (Não participa da rodada)"+
							(posJogador == jogadores.indexOf(jogadorBB) && todasApostasIguais() ? "\n    4. Check ($0)\n" : "\n"));
		int op = Teclado.leInt("Digite sua jogada: ");
		while(op < 1 && op > 4)
			if(op == 4 && posJogador != jogadores.indexOf(jogadorBB))
				op = Teclado.leInt("Digite uma jogada válida: ");
		
		int apostaJogador;
		if(op == 1) {              // Call
			if(jogadores.get(posJogador).getFichas() <= maiorAposta) {
				apostaJogador = jogadores.get(posJogador).getFichas();
				pote += apostaJogador;
				jogadores.get(posJogador).setFichas(0);
				System.out.println(jogadores.get(posJogador).getNome()+" apostou suas últimas $"+apostaJogador+" fichas.");
				apostaJogador = maiorAposta;
			} else {
				pote += maiorAposta;
				jogadores.get(posJogador).setFichas(jogadores.get(posJogador).getFichas() - maiorAposta);
				apostaJogador = maiorAposta;
				System.out.println("> "+jogadores.get(posJogador).getNome()+" igualou a aposta de $"+maiorAposta+".");
			}
			
		} else if(op == 2) {       // Raise
			while(true) {
				System.out.println("Você tem $"+jogadores.get(posJogador).getFichas()+" fichas.");
				apostaJogador = Teclado.leInt("Digite a quantidade de fichas que gostaria de apostar: ");
				if(apostaJogador > jogadores.get(posJogador).getFichas())
					System.out.println("Você tem apenas $"+jogadores.get(posJogador).getFichas()+". Aposte as fichas que tem!");
				else if(apostaJogador < maiorAposta + bigBlind) 
					System.out.println("O valor mínimo do aumento da aposta é de $"+(maiorAposta + bigBlind)+".");
				else
					break;
			}

			if(apostaJogador == jogadores.get(posJogador).getFichas())
				System.out.println("> ALL-IN!");
			System.out.println("> "+jogadores.get(posJogador).getNome()+" aumentou a aposta para $"+apostaJogador+"!");
			
		} else if(op == 3) {       // Fold
			jogadores.get(posJogador).setJogandoRodada(false);
			apostaJogador = 0;
			System.out.println("> "+jogadores.get(posJogador).getNome()+" desistiu da rodada.");
			
		} else {                   // Check
			apostaJogador = 0;
			System.out.println("> "+jogadores.get(posJogador).getNome()+" deu check, não apostando nenhuma ficha.");
		}
		
		apostasRodada[posJogador] = apostaJogador;
	}
	
	public boolean todasApostasIguais() {
		for(int i = 1; i < apostasRodada.length; i++)
			if(jogadores.get(i).isJogandoRodada() && apostasRodada[i] != apostasRodada[i - 1])
				return false;
		
		return true;
	}
	
	public int quantJogadoresRodada() {
		int totalJogadores = 0;
		for(Jogador j: jogadores)
			if(j.isJogandoRodada())
				totalJogadores++;
		return totalJogadores;
	}
	
	
	
	
	
	
	public void rotacionaOrdemJogadores() {
		// TODO cansado demais pra fazer isso aqui direito
		ArrayList<Jogador> ordemJogadores = new ArrayList<Jogador>(); // NÃO PRECISA FAZER OUTRO ARRAYLIST!!!!!!!!!!!!!!!
		if(jogadores.indexOf(jogadorDealer) < jogadores.size() - 1)
			jogadorDealer = jogadores.get(jogadores.indexOf(jogadorDealer) + 1);
		else
			jogadorDealer = jogadores.get(0);
		
		if(jogadores.size() == 2)
			jogadorSB = jogadorDealer;
		else if(jogadores.indexOf(jogadorSB) < jogadores.size() - 1)
			jogadorSB = jogadores.get(jogadores.indexOf(jogadorSB) + 1);
		else
			jogadorSB = jogadores.get(0);
		
		if(jogadores.indexOf(jogadorBB) < jogadores.size() - 1)
			jogadorBB = jogadores.get(jogadores.indexOf(jogadorBB) + 1);
		else
			jogadorBB = jogadores.get(0);
		
		ordemJogadores.add(jogadorSB);
		
	}
}











