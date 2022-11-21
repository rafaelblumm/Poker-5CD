
public class Teste {

	public static void main(String[] args) {
		Controle ctrl = new Controle();
		ctrl.adicionaJogadores();
		ctrl.novaRodada();
		
		int[] arr = {10, 10, 10, 10, 10};
		ctrl.setApostasRodada(arr);
		System.out.println(ctrl.todasApostasIguais());

	}

}
