// RAFAEL FLORES BLUMM

public class Principal {
	public static void main(String[] args) {
		
		System.out.println(""+
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
		+ "                       2. REGRAS");
		
		int op = Teclado.leInt("\nDigite a opção desejada: ");
		while(op < 1 || op > 2)
			op = Teclado.leInt("Digite uma opção válida: ");
		
		if(op == 2)
			System.out.println("\nRegras");;
			
			
		Controle ctrl = new Controle();
		ctrl.adicionaJogadores();
			
		
		while(ctrl.getJogadores().size() > 1) {
			ctrl.novaRodada();
			
			
			// ETAPA 1: DISTRIBUIÇÃO DAS CARTAS
			System.out.println("\nDISTRIBUIÇÃO DAS CARTAS");
			ctrl.distribuiMaos();
			System.out.println(ctrl.getJogadorDealer().getNome()+" está na posição do DEALER.");
			
			
			// ETAPA 2: APOSTAS INICIAIS
			System.out.println("\nAPOSTAS INICIAIS");
			ctrl.apostasIniciais();
			
			
			// ETAPA 3: TROCA DE CARTAS
			System.out.println("\nTROCA DE CARTAS");
			ctrl.rodadaTrocaCartas();
			
			
			// ETAPA 4: APOSTAS
			System.out.println("\nRODADA DE APOSTAS");
			ctrl.rodadaApostas();
			System.out.println("\nFIM DA RODADA DE APOSTAS");
			
			
			// ETAPA 5: RESULTADO
			ctrl.rodadaResultado();
			
			if(ctrl.checaSeTodosBots()) {
				System.out.println("Você perdeu a partida e só restaram jogadores BOT.\n"
									+ "\n     1. Sair da mesa."
									+ "\n     2. Ficar na mesa e acompanhar a partida.\n");
				
				op = Teclado.leInt("Digite sua escolha: ");
				while(op < 1 || op > 2)
					op = Teclado.leInt("Digite uma opção válida: ");
				
				if(op == 1)
					System.out.println("\n> Você fez um bom jogo!");
				else
					System.out.println("\n> Relaxe e acompanhe o jogo!");
			}
			
		
		}
		
		System.out.println("***********************************************\n\n"+
							"Parabéns, "+ctrl.getJogadores().get(0).getNome()+"! Você venceu a partida!");
	}
}
