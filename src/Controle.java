// RAFAEL FLORES BLUMM

import java.util.ArrayList;
import java.util.Arrays;

public class Controle {
	private ArrayList<Jogador> jogadores;
	private Jogador[] arrayJogadores;
	private Baralho baralho;
	private ArrayList<Carta> pilhaDescarte;
	private int blind, smallBlind, pote, rodada;
	private Jogador jogadorDealer, jogadorSB, jogadorBB;
	private int[] apostasRodada;
	
	public Controle() {
		this.jogadores = new ArrayList<Jogador>();
		this.baralho = new Baralho();
		this.pilhaDescarte = new ArrayList<Carta>();
		this.blind = 10;
		this.smallBlind = blind / 2;
		this.pote = 0;
		this.rodada = 0;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public Jogador[] getArrayJogadores() {
		return arrayJogadores;
	}

	public void setArrayJogadores(Jogador[] arrayJogadores) {
		this.arrayJogadores = arrayJogadores;
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

	public int blind() {
		return blind;
	}

	public void setBlind(int blind) {
		this.blind = blind;
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
	
	/**
	 * M??todo que pausa o programa em ms milissegundos.
	 * @param ms (int) Valor da pausa em ms.
	 */
	public void pausa(int ms) {
		try{
			Thread.sleep(ms);
		}
		catch(Exception e){}
	}
	
	/**
	 * M??todo que gerencia o menu inicial.
	 * @return (boolean) Indica 'true' caso o jogador escolha iniciar o jogo; 'false', caso escolha sair. 
	 */
	public static boolean menuInicial() {
		imprimeTitulo();
		int op = Teclado.leInt("\nDigite a op????o desejada: ");
		while(op < 1 || op > 3)
			op = Teclado.leInt("Digite uma op????o v??lida: (1, 2, ou 3");
		
		if(op == 1) {
			System.out.println("\n> Seja bem-vindo!\n> Iniciando novo jogo.");
			return true;
		} else if(op == 2) {
			if(!Controle.imprimeRegras())
				return false;
			return true;
		} else
			return false;
	}
	
	/**
	 * M??todo que imprime o t??tulo do jogo em ASCII ART.
	 */
	public static void imprimeTitulo() {
		System.out.println(
				"                 _____      _\r\n"
				+ "                |  __ \\    | |\r\n"
				+ "                | |__) ___ | | _____ _ __\r\n"
				+ "                |  ___/ _ \\| |/ / _ | '__|\r\n"
				+ "                | |  | (_) |   |  __| |\r\n"
				+ "  _____         |_|   \\___/| |\\_\\___|_|\r\n"
				+ " | ____|                   | |     | |\r\n"
				+ " | |__     ___ __ _ _ __ __| |   __| |_ __ __ ___      __\r\n"
				+ " |___ \\   / __/ _` | '__/ _` |  / _` | '__/ _` \\ \\ /\\ / /\r\n"
				+ "  ___) | | (_| (_| | | | (_| | | (_| | | | (_| |\\ V  V / \r\n"
				+ " |____/   \\___\\__,_|_|  \\__,_|  \\__,_|_|  \\__,_| \\_/\\_/\r\n\n"
				+ "                       1. NOVO JOGO\n"
				+ "                       2. REGRAS\n"
				+ "                       3. SAIR");
	}
	
	/**
	 * M??todo que imprime as regras do jogo.
	 */
	public static boolean imprimeRegras() {
		System.out.println("\n> POSI????ES NA MESA\r\n"
				+ "O jogador ao lado do DEALER ?? chamado de SMALL BLIND,\r\n"
				+ "e o jogador ao lado do SMALL BLIND ?? chamado de BIG BLIND.\r\n"
				+ "\r\n"

				+ "> ETAPAS DO JOGO\r\n"
				+ "   1. Distribui????o das cartas:\r\n"
				+ "S??o distribu??das 5 cartas para cada jogador da mesa.\r\n"
				+ "\r\n"
				+ "   2. Apostas iniciais:\r\n"
				+ "O SMALL BLIND aposta meio blind (5 fichas) seguido pelo\r\n"
				+ "BIG BLIND, que aposta 1 blind (10 fichas).\r\n"
				+ "\r\n"
				+ "   3. Troca de cartas:\r\n"
				+ "Os jogadores selecionam quais cartas da pr??pria m??o desejam\r\n"
				+ "trocar, podendo ser feito apenas 1 vez para cada carta.\r\n"
				+ "\r\n"
				+ "   4. Apostas:\r\n"
				+ "O primeiro jogador a falar ?? o jogador depois do BIG BLIND.\r\n"
				+ "A aposta m??nima ?? de 1 blind e a m??xima ?? de todas as fichas\r\n"
				+ "que tiver (ALL-IN).\r\n"
				+ "Esta etapa do jogo finaliza quando todos os jogadores que n??o\r\n"
				+ "desistiram tenham colocado o mesmo valor no pote.\r\n"
				+ "  - CALL: iguala a aposta feita pelo jogador anterior;\r\n"
				+ "	- RAISE: aumenta a aposta em, no m??nimo, 1 blind;\r\n"
				+ "	- FOLD: desiste da m??o e espera pr??xima rodada;\r\n"
				+ "	- CHECK (exclusivo para o BIG BLIND): caso os jogadores\r\n"
				+ "		tenham apostado a aposta m??nima de 1 blind, o BIG BLIND\r\n"
				+ "		pode n??o apostar nada e continuar no jogo.\r\n"
				+ "\r\n"
				+ "   5. Resultado:\r\n"
				+ "Os jogadores restantes comparam a pontua????o das suas cartas.\r\n"
				+ "Quem tiver mais pontos ganha as fichas do pote. Em caso de\r\n"
				+ "empate, as fichas s??o distribu??das igualmente entre os\r\n"
				+ "jogadores que empataram.\r\n"
				+ "Ent??o, as posi????es dos jogadores DEALER, SMALL BLIND e BIG BLIND\r\n"
				+ "rotacionam em sentido hor??rio.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "> PONTUA????O DAS COMBINA????ES DE POKER\r\n"
				+ "PONTOS   NOME              DESCRI????O\r\n"
				+ "1        High Card         Carta mais alta da m??o.\r\n"
				+ "2        Um Par            2 cartas iguais.\r\n"
				+ "3        Dois Pares        2 pares + kicker.\r\n"
				+ "4        Trinca            3 cartas iguais.\r\n"
				+ "5        Straight          Sequ??ncia sem naipe definido.\r\n"
				+ "6        Flush             Todas do mesmo naipe.\r\n"
				+ "7        Full House        Trinca + Par.\r\n"
				+ "8        Quadra            4 cartas iguais.\r\n"
				+ "9        Straight Flush    Straight (qualquer) + Flush.\r\n"
				+ "10       Royal Flush       Straight (10-J-Q-K-A) + Flush.\r\n\n"
				+ "*********************************************\n\n"
				+ "     1. INICIAR O JOGO\n"
				+ "     2. SAIR");
		int op = Teclado.leInt("\nDigite a op????o desejada: ");
		while(op < 1 || op > 2)
			op = Teclado.leInt("Digite uma op????o v??lida: (1 ou 2)");
		if(op == 2)
			return false;
		System.out.println("\n> Seja bem-vindo!\n> Iniciando novo jogo.");
		return true;
	}
	
	/**
	 * M??todo que inicializa inst??ncias de Jogador.
	 */
	public void adicionaJogadores() {
		String[] nomes = {"Alberto", "Bianca", "Carla", "Diego"};
		for(int i = 0; i < nomes.length; i++)
			jogadores.add(i, new Jogador(nomes[i], true));
		
		String nomeJogador = Teclado.leString("Jogador, digite seu nome: ");
		while(nomeJogador.isEmpty() || nomeJogador.length() > 21)
			nomeJogador = Teclado.leString("Digite um nome v??lido (max. 21 caracteres): ");
		jogadores.add(jogadores.size(), new Jogador(nomeJogador, false));
		
		this.jogadorDealer = jogadores.get(jogadores.size() - 1);
		this.jogadorSB = jogadores.get(0);
		this.jogadorBB = jogadores.get(1);
		this.arrayJogadores = new Jogador[jogadores.size()];
		
		for(int i = 0; i < arrayJogadores.length; i++)
			arrayJogadores[i] = jogadores.get(i);
	}
	
	/**
	 * M??todo que imprime informa????es de todos os jogadores.
	 */
	public void imprimeJogadores() {
		for(Jogador j: jogadores)
			System.out.println(j.toString()+"\n");
	}
	
	/**
	 * M??todo que imprime a mesa de jogadores.
	 */
	public void imprimeMesa() {
		String l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
		l1 = "           _____________________ \n";
		
		l2 = "          |                     | \n";
		
		l3 = "  "+(jogadores.contains(arrayJogadores[1]) ? arrayJogadores[1].getNome() : "      ")+"  |  "+
					(jogadorDealer.equals(arrayJogadores[1]) ? "D " : (jogadorSB.equals(arrayJogadores[1]) ? "SB" :
					(jogadorBB.equals(arrayJogadores[1]) ? "BB" : "  ")))+"             "+
					(jogadorDealer.equals(arrayJogadores[2]) ? "D " : (jogadorSB.equals(arrayJogadores[2]) ? "SB" :
					(jogadorBB.equals(arrayJogadores[2]) ? "BB" : "  ")))+"  |"+
					(jogadores.contains(arrayJogadores[2]) ? " "+arrayJogadores[2].getNome() : "")+"\n";
		
		l4 = "  "+(!jogadores.contains(arrayJogadores[1]) ? "       " : "$"+arrayJogadores[1].getFichas()+
				(arrayJogadores[1].getFichas() < 10 ? "     " : arrayJogadores[1].getFichas() < 100 ? "    " : "   "))+
				" |                     |"+(jogadores.contains(arrayJogadores[2]) ? " $"+arrayJogadores[2].getFichas() : "")+"\n";
		
		l5 = "          |                     | \n";
		
		l6 = "  "+(jogadores.contains(arrayJogadores[0]) ? arrayJogadores[0].getNome() : "       ")+" |  "+
				(jogadorDealer.equals(arrayJogadores[0]) ? "D " : (jogadorSB.equals(arrayJogadores[0]) ? "SB" :
				(jogadorBB.equals(arrayJogadores[0]) ? "BB" : "  ")))+"             "+
				(jogadorDealer.equals(arrayJogadores[3]) ? "D " : (jogadorSB.equals(arrayJogadores[3]) ? "SB" :
				(jogadorBB.equals(arrayJogadores[3]) ? "BB" : "  ")))+"  |"+
				(jogadores.contains(arrayJogadores[3]) ? " "+arrayJogadores[3].getNome() : "")+"\n";
		
		l7 = "  "+(!jogadores.contains(arrayJogadores[0]) ? "       " : "$"+arrayJogadores[0].getFichas()+
				(arrayJogadores[0].getFichas() < 10 ? "     " : arrayJogadores[0].getFichas() < 100 ? "    " : "   "))+
				" |          "+(jogadorDealer.equals(arrayJogadores[4]) ? "D " : (jogadorSB.equals(arrayJogadores[4]) ? "SB" :
				(jogadorBB.equals(arrayJogadores[4]) ? "BB" : "  ")))+"         |"+
				(jogadores.contains(arrayJogadores[3]) ? " $"+arrayJogadores[3].getFichas() : "")+"\n";
		
		l8 = "          |_____________________| \n";
		
		l9 = "";
		if(jogadores.contains(arrayJogadores[4])) {
			while(l9.length() < (11 + (21 - arrayJogadores[4].getNome().length()) / 2))
				l9 += " ";
			l9 += arrayJogadores[4].getNome() + "\n";
		}
		
		l10 = (jogadores.contains(arrayJogadores[4]) ? "                   $"+arrayJogadores[4].getFichas() : "");

		System.out.println(l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 + l10);
	}
	
	/**
	 * M??todo que reseta os valores necess??rios para o in??cio de uma nova rodada do jogo.
	 */
	public void novaRodada() {		
		rodada++;
		pote = 0;
		apostasRodada = new int[jogadores.size()];
		
		for(int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).setJogandoRodada(true);
			jogadores.get(i).setPontuacao(0);
		}
		
		System.out.println();
		String str = "################ RODADA N?? "+rodada+" ################";
		for(int i = 0; i < 3; i++)
			if(i == 1)
				System.out.println("\n"+str);
			else
			for(int j = 0; j < str.length(); j++)
				System.out.print("#");
		
		System.out.println();
		imprimeMesa();
		resetBaralho();
		System.out.print("\n> Embaralhando");
		for(int i = 0; i < 7; i++) {
			pausa(300);
			System.out.print(".");
			baralho.embaralha();
		}
		System.out.println("\n> Cartas embaralhadas!");
	}
	
	/**
	 * M??todo que insere as cartas da pilha de descarte de volta no baralho.
	 */
	public void resetBaralho() {
		recolheMaos();
		
		while(!pilhaDescarte.isEmpty()) {
			baralho.adicionaCarta(pilhaDescarte.get(0));
			pilhaDescarte.remove(0);
		}
	}
	
	/**
	 * M??todo que adiciona cartas da m??o dos jogadores na pilha de descarte.
	 */
	public void recolheMaos() {
		for(Jogador j: jogadores)
			for(Carta c: j.getMao())
				if(c != null)
					pilhaDescarte.add(c);
	}
	
	/**
	 * M??todo que gerencia a etapa de DISTRIBUI????O DE CARTAS.
	 */
	public void distribuiMaos() {
		for(int i = 0; i < 5; i++)
			for(Jogador j: jogadores) 
				j.recebeCarta(baralho.distribuiCarta(), i);
		
		for(Jogador j: jogadores) {
			Arrays.sort(j.getMao());
			j.setPontuacao(achaCombinacao(j.getMao()));
		}
	}
	
	/**
	 * M??todo que gerencia a etapa de APOSTAS INICIAIS.
	 */
	public void apostasIniciais() {
		int apostaSB, apostaBB;
		if(jogadorSB.getFichas() <= smallBlind) {
			apostaSB = jogadorSB.getFichas(); 
			jogadorSB.setFichas(0);
		} else {
			apostaSB = smallBlind;
			jogadorSB.setFichas(jogadorSB.getFichas() - apostaSB);
		}
		
		if(jogadorBB.getFichas() <= blind) {
			apostaBB = jogadorBB.getFichas();
			jogadorBB.setFichas(0);
		} else {
			apostaBB = blind;
			jogadorBB.setFichas(jogadorBB.getFichas() - apostaBB);
		}
		
		pote += apostaSB + apostaBB;
		System.out.println("SMALL BLIND | "+jogadorSB.getNome()+": $"+apostaSB+
							"\nBIG BLIND   | "+jogadorBB.getNome()+": $"+apostaBB+
							"\n> Total de fichas no pote: $"+pote);
	}
	
	/**
	 * M??todo que gerencia a etapa de TROCA DE CARTAS.
	 */
	public void rodadaTrocaCartas() {
		int posPrimeiroJogador, posAtualJogador;
		if(jogadores.indexOf(jogadorDealer) == jogadores.size() - 1)
			posPrimeiroJogador = 0;
		else
			posPrimeiroJogador = jogadores.indexOf(jogadorDealer) + 1;
		posAtualJogador = posPrimeiroJogador;
		
		int i = 0;
		Jogador j;
		while(i < jogadores.size()) {
			j = jogadores.get(posAtualJogador);
			
			if(j.isBot()) {
				if(j.getPontuacao() >= 5)                                               // Mant??m a m??o inicial caso tenha, pelo menos, um STRAIGHT.
					System.out.println(j.getNome()+" manteve sua m??o inicial.");
				else {	
					int valorAleatorio = (int)(Math.random() * 10 + 1);
					if(valorAleatorio > 5)                                              // 50% de chance de manter a m??o inicial.
						System.out.println(j.getNome()+" manteve sua m??o inicial.");
					else
						trocaCartasIndividual(j);
				}
				
			} else {
				System.out.println();
				j.setPontuacao(achaCombinacao(j.getMao()));
				j.imprimeMao();
				j.imprimeCombinacao();
				
				String op = Teclado.leString("Voc?? gostaria de trocar alguma carta? (S/N)");
				while(!op.equalsIgnoreCase("S") && !op.equalsIgnoreCase("N"))
					op = Teclado.leString("Digite uma op????o v??lida. (S/N)");
				
				if(op.equalsIgnoreCase("N"))
					System.out.println(j.getNome()+" manteve sua m??o inicial.\n");
				else
					trocaCartasIndividual(j);
			}
			
			if(posAtualJogador < jogadores.size() - 1)
				posAtualJogador++;
			else
				posAtualJogador = 0;
			i++;
			pausa(750);
		}
	}
	
	/**
	 * M??todo que troca as cartas de cada jogador de acordo com sua escolha.
	 * Cartas de BOTs a serem trocadas s??o escolhidas aleatoriamente, tanto a quantidade de cartas
	 * quanto as cartas da m??o que ser??o trocadas.
	 * @param jogador (Jogador) Atual jogador da rodada de troca de cartas.
	 */
	public void trocaCartasIndividual(Jogador jogador) {
		if(jogador.isBot()) {
			int[] opCartas = new int[(int)(Math.random() * 5 + 1)];
			
			for (int i = 0; i < opCartas.length; i++) {
				boolean valorExiste = true;
				int valorAleatorio;
				
				while(valorExiste) {
					valorExiste = false;
					valorAleatorio = (int)(Math.random() * 5 + 1);
				    for(int k = 0; k < i; k++)
				    	if(valorAleatorio == opCartas[k]) {
				    		valorExiste = true;
				    		break;
				    	}
				    if(!valorExiste)
				    	opCartas[i] = valorAleatorio;
				}
			}
			for(int pos: opCartas) {
				pilhaDescarte.add(jogador.getMao()[pos - 1]);
				jogador.recebeCarta(baralho.distribuiCarta(), pos - 1);
			}
			System.out.println(jogador.getNome()+" trocou "+opCartas.length+" cartas.");
			
		} else {
			int[] opCartas;
			while(true) {
				String[] arrStr = Teclado.leString("Digite o n??mero das cartas que gostria de trocar. (1 2 3 4 5)").split(" ");
				opCartas = new int[arrStr.length];
				
				for(int i = 0; i < opCartas.length; i++) {
					int numCarta = 0;
					
					try
					{
						numCarta = Integer.parseInt(arrStr[i]);
					}
					catch (NumberFormatException e)
					{
						System.out.println("Entrada inv??lida: digite apenas n??meros.");
						opCartas = null;
						break;
					}
					
					if(numCarta < 1 || numCarta > 5) {
						System.out.println("Entrada inv??lida: digite os n??meros no formato indicado.");
						opCartas = null;
						break;
					}
					opCartas[i] = numCarta - 1;
				}
				
				if(opCartas.length > 1)
					outer: for(int i = 0; i < opCartas.length - 1; i++)
						for(int j = i + 1; j < opCartas.length; j++ )
							if(i == j) {
								System.out.println("Entrada inv??lida: n??o ?? poss??vel trocar a mesma carta duas vezes.");
								opCartas = null;
								break outer;
							}
				
				if(opCartas != null)
					break;
			}
			
			for(int pos: opCartas) {
				pilhaDescarte.add(jogador.getMao()[pos]);
				jogador.recebeCarta(baralho.distribuiCarta(), pos);
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
	
	/**
	 * M??todo que gerencia a etapa de APOSTAS.
	 * Reinicia a contagem da rodada caso haja um aumento na aposta, terminando somente quando todos
	 * os jogadores tiverem apostado o mesmo valor.
	 */
	public void rodadaApostas() {
		int posPrimeiroJogador;
		if(jogadores.indexOf(jogadorBB) == jogadores.size() - 1)
			posPrimeiroJogador = 0;
		else
			posPrimeiroJogador = jogadores.indexOf(jogadorBB) + 1;
		int posAtualJogador = posPrimeiroJogador;
		
		boolean apostaAumentou = false;
		outer: while(true) {
			for(int i = 0; i < jogadores.size(); i++) {
				if(jogadores.get(posAtualJogador).isJogandoRodada())
					if(contaJogadoresRodada() == 1)
						break outer;
					else if(apostaIndividual(posAtualJogador, apostaAumentou)) {
						i = 0;
						apostaAumentou = true;
					}
				
				posAtualJogador++;
				if(posAtualJogador == jogadores.size())
					posAtualJogador = 0;
			}
			if((todasApostasIguais() && contaJogadoresRodada() > 1) || contaJogadoresRodada() == 1)
				break;
		}
	}
	
	/**
	 * M??todo que realiza as apostas individuais dos jogadores dentro da rodada de apostas.
	 * Jogadores computador (BOT) t??m 50% de chance de realizar uma jogada aleat??ria.
	 * @param posJogador (int) Posi????o do atual jogador.
	 * @param apostaAumentou (boolean) Recebe se houve aumento de aposta na rodada.
	 * @return (boolean) 'true' se algum jogador aumentou a aposta; 'false', caso contr??rio.
	 */
	public boolean apostaIndividual(int posJogador, boolean apostaAumentou) {
		Jogador atualJogador = jogadores.get(posJogador);
		int apostaJogador = 0;
		int maiorAposta = blind;
		for(int aposta: apostasRodada)
			if(aposta > maiorAposta)
				maiorAposta = aposta;
		
		System.out.println("\nVEZ DE "+atualJogador.getNome().toUpperCase()+
							"\nPote: $"+pote+
							"\nFichas: $"+atualJogador.getFichas());
		if(!atualJogador.isBot())
			System.out.println("Voc?? tem: "+atualJogador.nomeCombinacao()+".\n"+
								"\n    1. Call ($"+maiorAposta+")"+
								"\n    2. Raise (m??nimo $"+(maiorAposta + blind)+")"+
								"\n    3. Fold (N??o participa da rodada)"+
								(posJogador == jogadores.indexOf(jogadorBB) && podeDarCheck(apostaAumentou) ? "\n    4. Check ($0)\n" : "\n"));
		else
			System.out.println();
		
		int op = 0;
		while(true) {
			if(atualJogador.isBot()) {
				if((int)(Math.random() * 10 + 1) > 5) {            // 50% de chance de ser uma jogada aleat??ria.
					if(posJogador == jogadores.indexOf(jogadorBB) && podeDarCheck(apostaAumentou))
						op = (int)(Math.random() * 4 + 1);
					else
						op = (int)(Math.random() * 3 + 1);

				} else
					if(atualJogador.getPontuacao() >= 5 && atualJogador.getFichas() > maiorAposta + blind) // Aumenta a aposta se o bot tiver, pelo menos, um STRAIGHT.
						op = 2;
					else if(posJogador == jogadores.indexOf(jogadorBB) && podeDarCheck(apostaAumentou))
						op = 4;
					else
						op = 1;
				
			} else {
				op = Teclado.leInt("Digite sua jogada: ");
				while(op < 1 && op > 4)
					if(op == 4 && posJogador != jogadores.indexOf(jogadorBB) && !podeDarCheck(apostaAumentou))
						op = Teclado.leInt("Digite uma jogada v??lida: ");
			}
			
			/*
			 * CALL
			 */
			if(op == 1) {
				if(atualJogador.getFichas() > 0) {
					if(atualJogador.getFichas() <= maiorAposta) {
						apostaJogador = atualJogador.getFichas();
						pote += apostaJogador;
						atualJogador.setFichas(0);
						System.out.println("> "+atualJogador.getNome()+" apostou suas ??ltimas $"+apostaJogador+" fichas.");
						apostaJogador = maiorAposta;
					} else {
						pote += maiorAposta;
						atualJogador.setFichas(atualJogador.getFichas() - maiorAposta);
						apostaJogador = maiorAposta;
						System.out.println("> "+atualJogador.getNome()+" igualou a aposta de $"+maiorAposta+".");
					}
				} else {
					if(!atualJogador.isBot())
						System.out.println("JOGADA ILEGAL: voc?? n??o tem fichas suficientes para dar CALL.");
					continue;
				}
			
			/*
			 * RAISE
			 * A????es dos BOTs:
			 * 	- ALL-IN caso o jogador tenha, pelo menos, uma QUADRA.
			 * 	- 5% de chance de um ALL-IN aleat??rio.
			 */
			} else if(op == 2) {
				if(atualJogador.isBot()) {
					if(atualJogador.getFichas() >= maiorAposta + blind) {
						if(atualJogador.getPontuacao() >= 8 || atualJogador.getFichas() == maiorAposta)
							apostaJogador = atualJogador.getFichas();
						else if((int)(Math.random() * 100) < 5)
							apostaJogador = atualJogador.getFichas();
						else {
							apostaJogador = (int)(Math.random() * (atualJogador.getFichas() * 0.3) + (maiorAposta + blind));
							while(apostaJogador > atualJogador.getFichas() || apostaJogador % 5 != 0) {
								apostaJogador = (int)(Math.random() * (atualJogador.getFichas() * 0.3) + (maiorAposta + blind));
								if(apostaJogador == atualJogador.getFichas())
									break;
							}
						}
					} else
						continue;
					
				} else {
					if(atualJogador.getFichas() >= maiorAposta + blind) {
						while(true) {
							apostaJogador = Teclado.leInt("Digite a quantidade de fichas que gostaria de apostar: ");
							if(apostaJogador > atualJogador.getFichas())
								System.out.println("Voc?? tem apenas $"+atualJogador.getFichas()+". Aposte as fichas que tem!");
							else if(apostaJogador < maiorAposta + blind) 
								System.out.println("O valor ??nimo do aumento da aposta ?? de $"+(maiorAposta + blind)+".");
							else
								break;
						}
					}
					else {
						System.out.println("JOGADA ILEGAL: voc?? n??o tem fichas suficientes para apostas.");
						continue;
					}
				}
				
				pote += apostaJogador;
				if(apostaJogador == atualJogador.getFichas())
					System.out.println("> ALL-IN!");
				atualJogador.setFichas(atualJogador.getFichas() - apostaJogador);
				System.out.println("> "+atualJogador.getNome()+" aumentou a aposta para $"+apostaJogador+"!");
			
			/*
			 * FOLD	
			 */
			} else if(op == 3) {
				atualJogador.setJogandoRodada(false);
				apostaJogador = 0;
				System.out.println("> "+atualJogador.getNome()+" desistiu da rodada.");
			
			/*
			 * CHECK
			 */
			} else if(op == 4){
				apostaJogador = maiorAposta;
				System.out.println("> "+atualJogador.getNome()+" deu check, n??o apostando nenhuma ficha.");
			
			/*
			 * JOGADA INV??LIDA
			 */
			} else {
				System.out.println("Digite uma jogada v??lida.");
				continue;
			}
			
			System.out.println("\nPote: $"+pote+"\nFichas: $"+atualJogador.getFichas());
			System.out.println("\n*********************************************");
			pausa(875);
			apostasRodada[posJogador] = apostaJogador;
			return (op == 2 ? true : false);
		}
	}
	
	/**
	 * M??todo que checa se todas as apostas da rodada s??o CALL ou FOLD, e n??o RAISE.
	 * @return (boolean) Indica 'true' se apostas s??o iguais; 'false', caso contr??rio.
	 */
	public boolean todasApostasIguais() {
		for(int i = 1; i < apostasRodada.length; i++)
			if(jogadores.get(i).isJogandoRodada() && jogadores.get(i - 1).isJogandoRodada() && apostasRodada[i] != apostasRodada[i - 1])
				return false;
		return true;
	}
	
	/**
	 * M??todo que avalia as jogadas da rodada a procura de aumentos na aposta (RAISE) ou desist??ncias (FOLD).
	 * @param apostaAumentou (boolean) Recebe se algum jogador escolheu aumentar a aposta, incluindo o primeiro jogador da rodada.
	 * @return (boolean) Indica 'true' se o jogador na posi????o BIG BLIND pode escolher dar CHECK na rodada; 'false', caso contr??rio.
	 */
	public boolean podeDarCheck(boolean apostaAumentou) {
		if(apostaAumentou || contaJogadoresRodada() < jogadores.size())
			return false;
		else {
			for(int aposta: apostasRodada)
				if(aposta != blind && aposta != 0)
					return false;
			return true;
		}
	}
	
	/**
	 * M??todo que realiza a contagem de jogadores que n??o desistiram da rodada (FOLD).
	 * @return (int) Jogadores que est??o jogando a atual rodada.
	 */
	public int contaJogadoresRodada() {
		int totalJogadores = 0;
		for(Jogador j: jogadores)
			if(j.isJogandoRodada())
				totalJogadores++;
		return totalJogadores;
	}
	
	/**
	 * M??todo que procura todas as combina????es de cartas poss??veis.
	 * @param mao (Carta[]) M??o do atual jogador da rodada.
	 * @return (int) Pontua????o da combina????o. MAX = 10 (ROYAL-FLUSH); MIN = 1 (HIGH-CARD).
	 */
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
	
	/**
	 * Procura uma sequ??ncia de ROYAL-FLUSH.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean royalFlush(Carta[] mao) {
		if(mao[0].getValor() == 10 && flush(mao) && straight(mao))
			return true;
		
		return false;
	}
	
	/**
	 * Procura uma sequ??ncia de STRAIGHT-FLUSH.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean straightFlush(Carta[] mao) {
		if(straight(mao) && flush(mao))
			return true;
		
		return false;
	}
	
	/**
	 * Procura uma combina????o de QUADRA..
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
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
	
	/**
	 * Procura uma combina????o de FULL-HOUSE.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean fullHouse(Carta[] mao) {
		if(mao[0].getValor() == mao[1].getValor() && mao[3].getValor() == mao[4].getValor())
			if(mao[1].getValor() == mao[2].getValor() || mao[3].getValor() == mao[2].getValor())
				return true;
		return false;
	}
	
	/**
	 * Procura uma combina????o de FLUSH.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean flush(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(!mao[i].getNaipe().equalsIgnoreCase(mao[i - 1].getNaipe()))
				return false;
		
		return true;
	}
	
	/**
	 * Procura uma sequ??ncia de STRAIGHT.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
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
	
	/**
	 * Procura uma combina????o de TRINCA.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean trinca(Carta[] mao) {
		for(int i = 2; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor() && mao[i].getValor() == mao[i - 2].getValor())
				return true;
		
		return false;
	}
	
	/**
	 * Procura uma combina????o de DOIS PARES.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean doisPares(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor())
				for(int j = i + 2; j < mao.length; j++)
					if(mao[j].getValor() == mao[j - 1].getValor())
						return true;
		
		return false;
	}
	
	/**
	 * Procura uma combina????o de UM PAR.
	 * @param mao (Carta[]) Recebe m??o do atual jogador da rodada.
	 * @return (boolean) Indica se a combina????o existe.
	 */
	public boolean umPar(Carta[] mao) {
		for(int i = 1; i < mao.length; i++)
			if(mao[i].getValor() == mao[i - 1].getValor())
				return true;
		return false;
	}
	
	/**
	 * M??todo que gerencia a etapa de RESULTADO e imprime mensagens de acordo com a rodada.
	 */
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
			
			String msgEmpate = "> ";
			for(int i = 0; i < jogadoresEmpate.length; i++) {
				jogadoresEmpate[i].setFichas(jogadoresEmpate[i].getFichas() + pote / jogadoresEmpate.length);
				
				msgEmpate += jogadoresEmpate[i].getNome();
				if(i != jogadoresEmpate.length - 1)
					msgEmpate += (i < jogadoresEmpate.length - 2 ? ", " : " e ");
			}
			
			msgEmpate += " empataram com "+jogadoresEmpate[0].nomeCombinacao()+"."
					+ "\n> Cada um ganhou $"+(pote / jogadoresEmpate.length+" do total de $"+pote+" fichas!");
			System.out.println(msgEmpate);
			
		} else {
			Jogador vencedor = jogadoresRestantes[jogadoresRestantes.length - 1];
			vencedor.setFichas(vencedor.getFichas() + pote);
			System.out.println("> "+vencedor.getNome()+" venceu a rodada com "+vencedor.nomeCombinacao()+" e ganhou $"+pote+" fichas!");
		}
		
		int posDealer = jogadores.indexOf(jogadorDealer);
		int posSB = jogadores.indexOf(jogadorSB);
		int posBB = jogadores.indexOf(jogadorBB);
		
		if(checaPerdedores())
			removePerdedores(encontraPerdedores(contaPerdedores()));
		
		if(jogadores.size() >= 2)
			rotacionaOrdemJogadores(posDealer, posSB, posBB);
	}
	
	/**
	 * M??todo que checa a pontua????o dos jogadores restantes da rodada a procura de um empate.
	 * @param jRestantes (Jogador[]) Recebe Array com os jogadores que n??o desistiram da rodada (FOLD).
	 * @return (boolean) Indica se houve empate.
	 */
	public boolean checaEmpate(Jogador[] jRestantes) {
		if(jRestantes.length > 1)
			if(jRestantes[jRestantes.length - 1].getPontuacao() == jRestantes[jRestantes.length - 2].getPontuacao())
				return true;
		return false;
	}
	
	/**
	 * M??todo que realiza a contagem dos jogadores restantes com pontua????es iguais na rodada.
	 * @param jRestantes (Jogador[]) Recebe Array com os jogadores que n??o desistiram da rodada (FOLD).
	 * @param maiorPontuacao (int) Recebe maior pontua????o obtida pelos jogadores restantes da rodada.
	 * @return (int) Indica n??mero de jogadores que empataram.
	 */
	public int contaJogadoresEmpate(Jogador[] jRestantes, int maiorPontuacao) {
		int total = 0;
		for(Jogador j: jRestantes)
			if(j.getPontuacao() == maiorPontuacao)
				total++;
		
		return total;
	}
	
	/**
	 * M??todo que troca a fun????o dos jogadores na mesa (DEALER, SMALL BLIND, BIG BLIND).
	 * @param posDealer (int) Posi????o atual do DEALER.
	 * @param posSB (int) Posi????o atual do SMALL BLIND.
	 * @param posBB (int) Posi????o atual do BIG BLIND.
	 */
	public void rotacionaOrdemJogadores(int posDealer, int posSB, int posBB) {
		if(jogadores.contains(jogadorDealer))
			posDealer = (posDealer < jogadores.size() - 1 ? posDealer + 1 : 0);
		else if(posDealer >= jogadores.size())
			posDealer = 0;
		
		if(jogadores.size() > 2) {
			posSB = (posDealer < jogadores.size() - 1 ? posDealer + 1 : 0);
			posBB = (posSB < jogadores.size() - 1 ? posSB + 1 : 0);
		}
		else {
			posSB = posDealer;
			posBB = (posSB == 0 ? 1 : 0);
		}
		
		jogadorDealer = jogadores.get(posDealer);
		jogadorSB = jogadores.get(posSB);
		jogadorBB = jogadores.get(posBB);
	}
	
	/**
	 * M??todo que procura jogadores com 0 fichas.
	 * @return (boolean) Indica se h?? algum perdedor.
	 */
	public boolean checaPerdedores() {
		for(Jogador j: jogadores)
			if(j.getFichas() <= 0)
				return true;
		return false;
	}
	
	/**
	 * M??todo que realiza a contagem dos jogadores com 0 fichas.
	 * @return (int) N??mero de jogadores com 0 fichas.
	 */
	public int contaPerdedores() {
		int quantPerdedores = 0;
		for(Jogador j: jogadores)
			if(j.getFichas() <= 0)
				quantPerdedores += 1;
		
		return quantPerdedores;
	}
	
	/**
	 * M??todo que indica quais jogadores n??o t??m mais fichas.
	 * @param quantPerdedores (int) Recebe a quantidade de jogadores com 0 fichas.
	 * @return (Jogador[]) Indica Array de jogadores com 0 fichas.
	 */
	public Jogador[] encontraPerdedores(int quantPerdedores) {
		Jogador[] arrayPerdedores = new Jogador[quantPerdedores];
		for(Jogador j: jogadores)
			if(j.getFichas() <= 0)
				for(int i = 0; i < arrayPerdedores.length; i++)
					if(arrayPerdedores[i] == null) {
						arrayPerdedores[i] = j;
						break;
					}
		
		return arrayPerdedores;
	}
	
	/**
	 * M??todo que remove todos jogadores com 0 fichas e imprime mensagem indicando quem s??o os jogadores perdedores.
	 * @param perdedores (Jogador[]) Recebe Array que aponta para jogadores com 0 fichas.
	 */
	public void removePerdedores(Jogador[] perdedores) {
		String msg = "\n> ";
		for(int i = 0; i < perdedores.length; i++) {
			if(jogadores.contains(perdedores[i])) {
				jogadores.remove(perdedores[i]);
				
				msg += perdedores[i].getNome();
				if(i != perdedores.length - 1)
					msg += (i < perdedores.length - 2 ? ", " : " e ");
			}
		}
		msg += (perdedores.length > 1 ? " perderam todas suas fichas e est??o fora do jogo!" : " perdeu todas suas fichas e est?? fora do jogo!");
		System.out.println(msg);
	}
	
	/**
	 * M??todo que indica se todos os jogadores da mesa s??o bots.
	 * @return (boolean) Indica se todos jogadores s??o BOTS.
	 */
	public boolean checaSeTodosBots() {
		for(Jogador j: jogadores)
			if(!j.isBot())
				return false;
		return true;
	}
}
