package negocio;

/***
 * Classe que implementa as operações da interface.
 * 
 * @author Pedro E. M. Lemos
 * @version 26/05/2016
 */

public class Repositorio_de_moedas implements IRepositorio_de_moedas {
	private final int maxMoedas; // este atributo serve para definir o máximo de moedas que podem ser inseridas no repositório.
	private int	saldo, contMoedas; // o atributo saldo corresponde ao valor em centavos inseridos no repositório.
	private int[] arrayMoedas; // array de 6 posições: [0] 1 ¢, [1] 5 ¢, [2] 10 ¢, [3] 25 ¢, [4] 50 ¢, [5] 100 ¢.
	
	public Repositorio_de_moedas(int maxMoedas) {
		this.setSaldo(0); 
		this.setContMoedas(0);
		this.maxMoedas = maxMoedas;
		this.arrayMoedas = new int[6];
	}
	
	@Override
	public void	insereMoeda(int valor) throws Exception {
		if (this.getContMoedas() == this.getMaxMoedas())
			throw new Exception("O repositório de moedas está cheio!");
		switch (valor) {
			case 1:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[0] += 1;
				break;
			case 5:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[1] += 1;
				break;
			case 10:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[2] += 1;
				break;
			case 25:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[3] += 1;
				break;
			case 50:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[4] += 1;
				break;
			case 100:
				this.updateSaldo_ContMoedas(valor);
				this.arrayMoedas[5] += 1;
				break;
			default:
				throw new IllegalArgumentException(String.format("Este repositório de moedas aceita apenas moedas de 1, 5, 10, 25, 50 e 100 ¢.\nValor inserido: R$ $.2f", valor/100.0));
		}	
	}
	
	private void updateSaldo_ContMoedas(int valor) {
		this.setSaldo(this.getSaldo() + valor);
		this.setContMoedas(this.getContMoedas() + 1);
	}
	
	@Override
	public int getSaldo(){
		return this.saldo; 
	}
	
	private void setSaldo(int saldo) {
		if (saldo >= 0)
			this.saldo = saldo;
		else
			throw new IllegalArgumentException(String.format("O valor passado por parâmetro não é maior ou igual a 0. Valor do parâmetro: %d", saldo));
	}

	@Override
	public int getMaxMoedas() {
		return maxMoedas;
	}

	@Override
	public int getContMoedas() {
		return contMoedas;
	}

	private void setContMoedas(int contMoedas) {
		this.contMoedas = contMoedas;
	}
	
	@Override
	public int troco() throws Exception {
		int troco = 0, saldo = this.getSaldo();
		
		if (saldo == 0)
			return 0;
		
		for (int i = this.arrayMoedas.length-1; i != -1; i--)	
			for (int j = this.arrayMoedas[i]; j > 0; j--)
				switch (i) {
					case 0:
						if (saldo/1 >= 1) { // se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 1;	// o troco recebe esta moeda
							saldo -= 1; // substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1; // remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1); // decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					case 1:
						if (saldo/5 >= 1) {	// se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 5;	// o troco recebe esta moeda
							saldo -= 5;	// substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1;	// remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1);	// decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					case 2:
						if (saldo/10 >= 1) {	// se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 10;	// o troco recebe esta moeda
							saldo -= 10;	// substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1;	// remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1);	// decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					case 3:
						if (saldo/25 >= 1) {	// se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 25;	// o troco recebe esta moeda
							saldo -= 25;	// substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1;	// remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1);	// decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					case 4:
						if (saldo/50 >= 1) {	// se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 50;	// o troco recebe esta moeda
							saldo -= 50;	// substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1;	// remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1);	// decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					case 5:
						if (saldo/100 >= 1) {	// se a divisão inteira for maior que 1, então dá para retirarmos esta moeda do repositório
							troco += 100;	// o troco recebe esta moeda
							saldo -= 100;	// substrai o valor da moeda do saldo
							this.arrayMoedas[i] -= 1;	// remove a moeda do array de moedas
							this.setContMoedas(this.getContMoedas() - 1);	// decrementa o contador de moedas
						}
						else // senão, então o saldo é menor que o valor da moeda da posição 'i'.
							j = 0; // define 'j' como 0 para a verificação 'j>0' falhar e podermos avançar para o próximo 'i' do for mais externo.
						break;
					default:
						throw new IndexOutOfBoundsException(String.format("Acessando índice não existente no arrayMoedas. Comprimento do arrayMoedas: %d. Índice: %d", this.arrayMoedas.length, i));
				}
		
		/*if (this.getSaldo() != troco)
			throw new Exception(String.format("O SALDO (R$ %.2f) É DIFERENTE DO TROCO (R$ %.2f)", this.getSaldo()/100.0, troco/100.0));
		if (saldo != 0)
			throw new Exception(String.format("O SALDO CALCULADO A PARTIR DA REMOÇÃO DAS MOEDAS (R$ %.2f) É DIFERENTE DO SALDO REAL (R$ %.2f)!", saldo/100.0, this.getSaldo()/100.0));
		*/
		this.setSaldo(0);
		return troco;
	}
	
	@Override
	public int valorTotal_arrecadado() {
		int dinheiro = 0;
		for (int i = 0; i < this.arrayMoedas.length; i++)
			for (int j = this.arrayMoedas[i]; j > 0; j--)
				switch (i) {
					case 0:
						dinheiro += 1;
						break;
					case 1:
						dinheiro += 5;
						break;
					case 2:
						dinheiro += 10;
						break;
					case 3:
						dinheiro += 25;
						break;
					case 4:
						dinheiro += 50;
						break;
					case 5:
						dinheiro += 100;
						break;
					default:
						throw new IndexOutOfBoundsException(String.format("Acessando índice não existente no arrayMoedas. Comprimento do arrayMoedas: %d. Índice: %d", this.arrayMoedas.length, i));
				}
		return dinheiro;
	}
	
	@Override
	public int devolve() {
		int dinheiro = this.valorTotal_arrecadado();
		this.setSaldo(0);
		this.setContMoedas(0);
		for (int i = 0; i < this.arrayMoedas.length; i++)
			this.arrayMoedas[i] = 0;
		return dinheiro;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Saldo: R$ %.2f ContMoedas: %d\n", this.getSaldo()/100.0, this.getContMoedas()));
		for (int i = 0; i<this.arrayMoedas.length; i++) {
			switch (i) {
				case 0:
					str.append("1¢ :");
					break;
				case 1:
					str.append("5¢ :");
					break;
				case 2:
					str.append("10¢ :");
					break;
				case 3:
					str.append("25¢ :");
					break;
				case 4:
					str.append("50¢ :");
					break;
				case 5:
					str.append("100¢ :");
					break;
				default:
					break;
			}
			str.append("["+this.arrayMoedas[i]+"]; ");
		}
		str.append("\n");
		return str.toString();
	}

	@Override
	public void comprarTicket(int valor_do_ticket) {
		if (valor_do_ticket < 0 || this.getSaldo() < valor_do_ticket)
			throw new IllegalArgumentException(String.format("O valor do ticket (R$ %.2f) inválido ou saldo (R$ %.2f) insuficiente!", valor_do_ticket/100.0, this.getSaldo()/100.0));
		this.setSaldo(this.getSaldo()-valor_do_ticket);
	}
}
