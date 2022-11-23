// RAFAEL FLORES BLUMM

public class Principal {
	public static void main(String[] args) {
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
			System.out.println("SMALL BLIND | "+ctrl.getJogadorSB().getNome()+": +$"+ctrl.getSmallBlind()+
								"\nBIG BLIND   | "+ctrl.getJogadorBB().getNome()+": +$"+ctrl.getBlind()+
								"\n> Total de fichas no pote: $"+ctrl.getPote());
			
			
			// ETAPA 3: TROCA DE CARTAS
			System.out.println("\nTROCA DE CARTAS");
			ctrl.rodadaTrocaCartas();
			
			
			// ETAPA 4: APOSTAS
			System.out.println("\nRODADA DE APOSTAS");
			ctrl.rodadaApostas();
			System.out.println("\nFIM DA RODADA DE APOSTAS");
			
			
			// ETAPA 5: RESULTADO
			ctrl.rodadaResultado();
		
		}
		
		System.out.println("***********************************************\n\n"+
							"Parabéns, "+ctrl.getJogadores().get(0).getNome()+"! Você venceu a partida!");
	}
}
