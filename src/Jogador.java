// RAFAEL FLORES BLUMM

public class Jogador {
	private String nome;
	private int fichas;
	private Carta[] mao;
	private boolean bot, jogandoRodada;
	
	public Jogador(String nome, boolean bot) {
		this.nome = nome;
		this.fichas = 200;
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
	
	public void imprimeMao() {
		for(int i = 0; i < mao.length; i++)
			System.out.print(mao[i].cartaSimbolo() + (i == mao.length-1 ? "\n" : " - "));
	}
	
	public void recebeCarta(Carta c, int pos) {
		mao[pos] = c;
	}
}