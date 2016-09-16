package negocio;
/***
 * Interface que define as operações mínimas a serem implementadas.
 * 
 * @author Pedro E. M. Lemos
 * @version 26/05/2016
 */

public interface IRepositorio_de_moedas {
	/**
	 * Permite inserir moedas no repositório de moedas (soma no saldo).  
	 * @param valor			valor da moeda a ser inserida. Moedas aceitáveis: 1, 5, 10, 25, 50 e 100.
	 * @throws Exception	se o repositório estiver cheio.
	 * @throws IllegalArgumentException 	caso o valor não for uma moeda aceitável.
	 */
	public void insereMoeda(int valor) throws IllegalArgumentException, Exception;

	/**
	 * Retorna o saldo acumulado no repositório de moedas.
	 * @return int	o atributo saldo.
	 */
	public int getSaldo();
	
	/**
	 * Retorna o máximo de moedas que podem ser inseridas no repositório.
	 * @return int	o atributo constante que define o limite de moedas no repositório.
	 */
	public int getMaxMoedas();
	
	/**
	 * Retorna o contador de moedas que estão dentro do repositório.
	 * @return int	o atributo contador de moedas.
	 */
	public int getContMoedas();

	/**
	 * Retorna o troco removendo as moedas do repositório próximo (ou equivalente) ao valor do troco zerando o saldo.
	 * @return int			o saldo, mas removendo as moedas do repositório.
	 * @throws Exception 	se por alguma razão o troco não corresponder ao saldo.
	 */
	public int troco() throws Exception;
	
	/**
	 * Devolve o valor total com base nas moedas dentro do repositório.
	 * @return int	o valor do somatório de todas as moedas do repositório.
	 */
	public int devolve();
	
	/**
	 * Subtrai o valor do ticket do saldo se a operação não resultar em saldo negativo.
	 */
	public void comprarTicket(int valor_do_ticket);
	
	public int valorTotal_arrecadado();
}
