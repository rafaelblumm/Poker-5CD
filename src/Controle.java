// RAFAEL FLORES BLUMM

import java.util.ArrayList;
import java.util.Arrays;

public class Controle {
	private ArrayList<Jogador> jogadores;
	private Baralho baralho;
	private ArrayList<Carta> pilhaDescarte;
	private int smallBlind, blind, pote, rodada;
	private Jogador jogadorDealer, jogadorSB, jogadorBB;
	private int[] apostasRodada;
	
	public Controle() {
		this.jogadores = new ArrayList<Jogador>();
		this.baralho = new Baralho();
		this.pilhaDescarte = new ArrayList<Carta>();
		this.smallBlind = blind / 2;
		this.blind = 10;
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

	public int getBlind() {
		return blind;
	}

	public void setBigBlind(int bigBlind) {
		this.blind = bigBlind;
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
		
		for(int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).setJogandoRodada(true);
			jogadores.get(i).setPontuacao(0);
		}
		
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
		for(Jogador j: jogadores) {
			for(int i = 0; i < 5; i++)
				j.recebeCarta(baralho.distribuiCarta(), i);
			Arrays.sort(j.getMao());
			j.setPontuacao(achaCombinacao(j.getMao()));
		}
	}
	
	public void apostasIniciais() {
		if(jogadorSB.getFichas() <= smallBlind) {
			pote += jogadorSB.getFichas();
			jogadorSB.setFichas(0);
		} else {
			jogadorSB.setFichas(jogadorSB.getFichas() - smallBlind);
			pote += smallBlind;
		}
		
		if(jogadorBB.getFichas() <= blind) {
			pote += jogadorBB.getFichas();
			jogadorBB.setFichas(0);
		} else {
			jogadorBB.setFichas(jogadorBB.getFichas() - blind);
			pote += blind;
		}
	}
	
	public void rodadaTrocaCartas() {
		for(Jogador j: jogadores) {
			if(j.isBot()) {
				int valorAleatorio = (int)(Math.random() * 9);
				if(valorAleatorio < 5)
					System.out.println(j.getNome()+" manteve sua mão inicial.");
				else
					trocaCartasIndividual(j);
			}else {
				System.out.println();
				j.setPontuacao(achaCombinacao(j.getMao()));
				j.imprimeMao();
				j.imprimeCombinacao();
				
				String op = Teclado.leString("Você gostaria de trocar alguma carta? (S/N)");
				while(!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
					op = Teclado.leString("Digite uma opção válida. (S/N)");
				
				if(op.equalsIgnoreCase("N"))
					System.out.println("Nenhuma carta será trocada.");
				else
					trocaCartasIndividual(j);
			}
		}
	}
	
	public void trocaCartasIndividual(Jogador jogador) {
		if(jogador.isBot()) {
			int[] opCartas = new int[(int)(Math.random() * 5 + 1)];  // Math.random() * (max - min) + min
			for (int i = 0; i < opCartas.length; i++) {
				boolean existe = true;
				int valorAleatorio;
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
			
		}else {
			String[] opCartas = Teclado.leString("Digite o número das cartas que gostria de trocar. (1 2 3 4 5)").split(" ");
			for(String pos: opCartas) {
				pilhaDescarte.add(jogador.getMao()[Integer.parseInt(pos) - 1]);
				jogador.recebeCarta(baralho.distribuiCarta(), Integer.parseInt(pos) - 1);
			}
			System.out.println(jogador.getNome()+" trocou "+opCartas.length+" cartas.");
		}
		
		Arrays.sort(jogador.getMao());
		jogador.setPontuacao(achaCombinacao(jogador.getMao()));
		if(!jogador.isBot()) {
			jogador.imprimeMao();
			jogador.imprimeCombinacao();
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
					if(apostaIndividual(posAtualJogador))                    // se jogador aumentar aposta,
						i = 0;                                               // a rodada de apostas ocorre novamente.
					
					posAtualJogador++;
					if(posAtualJogador == jogadores.size())
						posAtualJogador = 0;
				}
			
			if((todasApostasIguais() && contaJogadoresRodada() > 1) || contaJogadoresRodada() == 1)
				break;
		}
	}
	
	public boolean apostaIndividual(int posJogador) {
		int maiorAposta = blind;
		for(int aposta: apostasRodada)
			if(aposta > maiorAposta)
				maiorAposta = aposta;
		
		System.out.println("\n******************************************************************");
		System.out.println("\nVEZ DE "+jogadores.get(posJogador).getNome().toUpperCase()+
							"\nPote: $"+pote+
							"\nSuas fichas: $"+jogadores.get(posJogador).getFichas()+"\n"+
							"\n    1. Call ($"+maiorAposta+")"+
							"\n    2. Raise (mínimo $"+(maiorAposta + blind)+")"+
							"\n    3. Fold (Não participa da rodada)"+
							(posJogador == jogadores.indexOf(jogadorBB) && podeDarCheck() ? "\n    4. Check ($0)\n" : "\n"));
		
		while(true) {
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
					apostaJogador = Teclado.leInt("Digite a quantidade de fichas que gostaria de apostar: ");
					if(apostaJogador > jogadores.get(posJogador).getFichas())
						System.out.println("Você tem apenas $"+jogadores.get(posJogador).getFichas()+". Aposte as fichas que tem!");
					else if(apostaJogador < maiorAposta + blind) 
						System.out.println("O valor ínimo do aumento da aposta é de $"+(maiorAposta + blind)+".");
					else
						break;
				}
				
				pote += apostaJogador;
				if(apostaJogador == jogadores.get(posJogador).getFichas())
					System.out.println("> ALL-IN!");
				System.out.println("> "+jogadores.get(posJogador).getNome()+" aumentou a aposta para $"+apostaJogador+"!");
				
			} else if(op == 3) {        // Fold
				jogadores.get(posJogador).setJogandoRodada(false);
				apostaJogador = 0;
				System.out.println("> "+jogadores.get(posJogador).getNome()+" desistiu da rodada.");
				
			} else if(op == 4){         // Check
				apostaJogador = maiorAposta;
				System.out.println("> "+jogadores.get(posJogador).getNome()+" deu check, não apostando nenhuma ficha.");
			} else {
				System.out.println("Digite uma jogada válida.");
				continue;
			}
			apostasRodada[posJogador] = apostaJogador;
			return (op == 2 ? true : false);
		}
	}
	
	public boolean todasApostasIguais() {
		for(int i = 1; i < apostasRodada.length; i++)
			if(jogadores.get(i).isJogandoRodada() && jogadores.get(i - 1).isJogandoRodada() && apostasRodada[i] != apostasRodada[i - 1])
				return false;
		return true;
	}
	
	public boolean podeDarCheck() {
		for(int i = 1; i < apostasRodada.length - 1; i++)
			if(jogadores.get(i).isJogandoRodada() && jogadores.get(i - 1).isJogandoRodada()
																			&& apostasRodada[i] != 0 && apostasRodada[i - 1] != 0
																			&& apostasRodada[i] != apostasRodada[i - 1])
				return false;
		return true;
	}
	
	public int contaJogadoresRodada() {
		int totalJogadores = 0;
		for(Jogador j: jogadores)
			if(j.isJogandoRodada())
				totalJogadores++;
		return totalJogadores;
	}
	
	public int achaCombinacao(Carta[] mao) {
		if(royalFlush(mao))
			return 10;
		else if(straightFlush(mao))
			return 9;
		else if(quadra(mao))
			return 8;
		else if(fullHouse(mao))
			return 7;
		else if(flush(mao))
			return 6;
		else if(straight(mao))
			return 5;
		else if(trinca(mao))
			return 4;
		else if(doisPares(mao))
			return 3;
		else if(umPar(mao))
			return 2;
		else
			return 1;
	}
	
	public boolean royalFlush(Carta[] mao) {
		if(mao[0].getValor() == 10 && flush(mao) && straight(mao))
			return true;
		
		return false;
	}
	
	public boolean straightFlush(Carta[] mao) {
		if(straight(mao) && flush(mao))
			return true;
		
		return false;
	}
	
	public boolean quadra(Carta[] mao) {
		for(int i = 0; i < 2; i++)
			for(int j = i + 1; j < i + 4; j++) {
				if(mao[i].getValor() != mao[j].getValor())
					break;
				if(j == i + 3)
					return true;
			}
		
		return false;
	}
	
	public boolean fullHouse(Carta[] mao) {
		if(mao[0].getValor() == mao[1].getValor() && mao[3].getValor() == mao[4].getValor())
			if(mao[1].getValor() == mao[2].getValor() || mao[3].getValor() == mao[2].getValor())
				return true;
		return false;
	}
	
	public boolean flush(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(!mao[i].getNaipe().equalsIgnoreCase(mao[i - 1].getNaipe()))
				return false;
		
		return true;
	}
	
	public boolean straight(Carta[] mao) {
		if(!(mao[mao.length - 1].getValor() == 14 &&
									mao[0].getValor() == 2 &&
									mao[1].getValor() == 3 &&
									mao[2].getValor() == 4 &&
									mao[3].getValor() == 5))
			for(int i = 1; i < mao.length; i++)
				if(mao[i].getValor() != mao[i - 1].getValor() + 1)
					return false;
		
		return true;
	}
	
	public boolean trinca(Carta[] mao) {
		for(int i = 2; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor() && mao[i].getValor() == mao[i - 2].getValor())
				return true;
		
		return false;
	}
	
	public boolean doisPares(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor())
				for(int j = i + 2; j < mao.length; j++)
					if(mao[j].getValor() == mao[j - 1].getValor())
						return true;
		
		return false;
	}
	
	public boolean umPar(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor())
				return true;
		return false;
	}
	
	public void rodadaResultado() {
		Jogador[] jogadoresRestantes = new Jogador[contaJogadoresRodada()];
		for(Jogador j: jogadores)
			if(j.isJogandoRodada())
				for(int i = 0; i <  jogadoresRestantes.length; i++)
					if(jogadoresRestantes[i] == null) {
						jogadoresRestantes[i] = j;
						break;
					}
		Arrays.sort(jogadoresRestantes);
		
		if(checaEmpate(jogadoresRestantes)) {
			int maiorPontuacao = jogadoresRestantes[jogadoresRestantes.length - 1].getPontuacao();
			Jogador[] jogadoresEmpate = new Jogador[contaJogadoresEmpate(jogadoresRestantes, maiorPontuacao)];
			for(Jogador j: jogadoresRestantes)
				if(j.getPontuacao() == maiorPontuacao)
					for(int i = 0; i <  jogadoresEmpate.length; i++)
						if(jogadoresEmpate[i] == null) {
							jogadoresEmpate[i] = j;
							break;
						}
			
			String strEmpate = "";
			for(int i = 0; i < jogadoresEmpate.length; i++) {
				jogadoresEmpate[i].setFichas(jogadoresEmpate[i].getFichas() + pote / jogadoresEmpate.length);
				
				if(i == jogadoresEmpate.length - 1)
					strEmpate += jogadoresEmpate[i].getNome();
				else
					strEmpate += jogadoresEmpate[i].getNome()+(i < jogadoresEmpate.length - 2 ? ", " : " e ");
			}
			
			strEmpate += " empataram com "+jogadoresEmpate[0].nomeCombinacao()+"."
					+ "\nCada um ganhou $"+(pote / jogadoresEmpate.length+" do total de $"+pote+" fichas!");
			System.out.println(strEmpate);
			
		} else {
			Jogador vencedor = jogadoresRestantes[jogadoresRestantes.length - 1];
			vencedor.setFichas(vencedor.getFichas() + pote);
			System.out.println(vencedor.getNome()+" venceu a rodada com "+vencedor.nomeCombinacao()+" e ganhou $"+pote+" fichas!");
		}
	}
	
	public boolean checaEmpate(Jogador[] jRestantes) {
		if(jRestantes.length > 1)
			if(jRestantes[jRestantes.length - 1].getPontuacao() == jRestantes[jRestantes.length - 2].getPontuacao())
				return true;
		return false;
	}

	public int contaJogadoresEmpate(Jogador[] jRestantes, int maiorPontuacao) {
		int total = 0;
		for(Jogador j: jRestantes)
			if(j.getPontuacao() == maiorPontuacao)
				total++;
		
		return total;
	}

	/* TODO
	 * arrumar método rotacionaOrdemJogadores()
	 * criar método que exclui jogadores que estão sem fichas e perderam o jogo 
	 */
	
	
	
	
	
	public void rotacionaOrdemJogadores() {
		// TODO método rotaciona a posição dos jogadores na mesa
		ArrayList<Jogador> ordemJogadores = new ArrayList<Jogador>(); 
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
