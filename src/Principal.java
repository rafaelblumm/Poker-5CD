// RAFAEL FLORES BLUMM

public class Principal {
	public static void main(String[] args) {
		Controle ctrl = new Controle();
		ctrl.adicionaJogadores();
		ctrl.imprimeJogadores();
		
		//while(ctrl.getJogadores().size() > 1) {}
		ctrl.novaRodada();
		System.out.println("RODADA Nº "+ctrl.getRodada());
		
		
		// DISTRIBUIÇÃO DAS CARTAS
		System.out.println("\nDISTRIBUIÇÃO DAS CARTAS");
		ctrl.distribuiMaos();
		
		
		// APOSTAS INICIAIS
		System.out.println("\nAPOSTAS INICIAIS");
		ctrl.apostasIniciais();
		System.out.println("SMALL BLIND | "+ctrl.getJogadorSB().getNome()+": +$"+ctrl.getSmallBlind()+
							"\nBIG BLIND   | "+ctrl.getJogadorBB().getNome()+": +$"+ctrl.getBigBlind()+
							"\n> Total de fichas no pote: $"+ctrl.getPote());
		
		
		// TROCA DE CARTAS
		System.out.println("\nTROCA DE CARTAS");
		for(int i = 0; i < ctrl.getJogadores().size(); i++) {
			ctrl.getJogadores().get(i).imprimeMao();
			ctrl.trocaCartas(ctrl.getJogadores().get(i));
			ctrl.getJogadores().get(i).imprimeMao();
			System.out.print("\n");
		}
		
		
		// APOSTAS
		System.out.println("\nRODADA DE APOSTAS");
		ctrl.rodadaApostas();
		System.out.println("\nFIM DA RODADA DE APOSTAS");
		
		// RESULTADO
		
		
	}
}