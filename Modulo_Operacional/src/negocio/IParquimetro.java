package negocio;

/***
 * Interface que define as operações mínimas a serem implementadas.
 * 
 * @author Pedro E. M. Lemos
 * @version 07/06/2016
 */

public interface IParquimetro {
	public String getId();
	public String getEndereco();
	public double getValor_arrecadado_CARTAO();
	
	/**
	 * Permite inserir moedas no repositório de moedas (soma no saldo).  
	 * @param valor			valor da moeda a ser inserida. Moedas aceitáveis: 1, 5, 10, 25, 50 e 100.
	 * @throws Exception	se o repositório estiver cheio.
	 * @throws IllegalArgumentException 	caso o valor não for uma moeda aceitável.
	 */
	public void inserirMoeda(int valor) throws IllegalArgumentException, Exception;
	
	/**
	 * Retorna o saldo acumulado no repositório de moedas.
	 * @return int	o atributo saldo.
	 */
	public double saldoEmModas();
	
	/**
	 * Subtrai o valor do ticket do saldo (no repositório de moedas) se a operação não resultar em saldo negativo.
	 */
	public void comprarTicket(int valor_do_ticket);
	
	/**
	 * Retorna o troco removendo as moedas do repositório próximo (ou equivalente) ao valor do troco zerando o saldo.
	 * @return int			o saldo, mas removendo as moedas do repositório.
	 * @throws Exception 	se por alguma razão o troco não corresponder ao saldo.
	 */
	public double trocoEmMoedas() throws Exception;
	
	public double valorArrecadado_MOEDAS();
	
	/**
	 * Soma no atributo o valor do último ticket emitido pelo parquímetro.
	 */
	public void aumentarValorArrecadado_CARTAO(double valor_a_ser_somado);
	public double valorArecadado_MOEDAS();
	public void setValorArrecadado_MOEDAS(double valor_arrecadado_MOEDAS);
	/**
	 * Retorna a soma dos atributos de valor arrecadado de moedas e cartão.
	 */
	public double totalValorArrecadado();
}
