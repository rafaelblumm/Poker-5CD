// RAFAEL FLORES BLUMM

public class Jogador implements Comparable<Jogador>{
	private String nome;
	private int fichas, pontuacao;
	private Carta[] mao;
	private boolean bot, jogandoRodada;
	
	public Jogador(String nome, boolean bot) {
		this.nome = nome;
		this.fichas = 200;
		this.pontuacao = 0;
		this.mao = new Carta[5];
		this.bot = bot;
		this.jogandoRodada = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Carta[] getMao() {
		return mao;
	}

	public void setMao(Carta[] mao) {
		this.mao = mao;
	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public boolean isJogandoRodada() {
		return jogandoRodada;
	}

	public void setJogandoRodada(boolean jogandoRodada) {
		this.jogandoRodada = jogandoRodada;
	}

	@Override
	public String toString() {
		return "É bot: "+bot+"\nNome: "+nome+"\nFichas: $"+fichas;
	}
	
	@Override
	public int compareTo(Jogador j) {
		return pontuacao - j.getPontuacao();
	}
	
	public void imprimeMao() {
		for(int i = 0; i < mao.length; i++)
			System.out.print(mao[i].cartaSimbolo() + (i == mao.length-1 ? "\n" : " - "));
	}
	
	public void imprimeCombinacao() {
		if(pontuacao == 10)
			System.out.println("Você tem um ROYAL-FLUSH.");
		else if(pontuacao == 9)
			System.out.println("Você tem um STRAIGHT-FLUSH.");
		else if(pontuacao == 8)
			System.out.println("Você tem uma QUADRA.");
		else if(pontuacao == 7)
			System.out.println("Você tem um FULL-HOUSE.");
		else if(pontuacao == 6)
			System.out.println("Você tem um FLUSH.");
		else if(pontuacao == 5)
			System.out.println("Você tem um STRAIGHT.");
		else if(pontuacao == 4)
			System.out.println("Você tem uma TRINCA de "+mao[2].getNome()+".");
		else if(pontuacao == 3)
			System.out.println("Você tem DOIS PARES.");
		else if(pontuacao == 2)
			System.out.println("Você tem UM PAR.");
		else
			System.out.println("Você tem uma HIGH CARD: "+mao[mao.length - 1].toString()+".");
	}
	
	public String nomeCombinacao() {
		if(pontuacao == 10)
			return "ROYAL-FLUSH";
		else if(pontuacao == 9)
			return "STRAIGHT-FLUSH";
		else if(pontuacao == 8)
			return "QUADRA";
		else if(pontuacao == 7)
			return "FULL-HOUSE";
		else if(pontuacao == 6)
			return "FLUSH";
		else if(pontuacao == 5)
			return "STRAIGHT";
		else if(pontuacao == 4)
			return "TRINCA";
		else if(pontuacao == 3)
			return "DOIS PARES";
		else if(pontuacao == 2)
			return "UM PAR";
		else
			return "HIGH CARD";
	}
	
	public void recebeCarta(Carta c, int pos) {
		mao[pos] = c;
	}
}
