package negocio;

/***
 * Classe que implementa as operações da interface.
 * 
 * @author Pedro E. M. Lemos
 * @version 26/05/2016
 */

public class Parquimetro implements IParquimetro {
	private final String id, endereco;
	private double valor_arrecadado_CARTAO, valor_arrecadado_MOEDAS;
	private IRepositorio_de_moedas repositorio;
	
	public Parquimetro(String id, String endereco, int maxMoedas) {
		if (!this.validate_ID(id))
			throw new IllegalArgumentException(String.format("A identificação do parquímetro deve possuir apenas 5 digitos! ID: %s ", id));
		this.id = id;
		this.endereco = endereco;
		this.repositorio = new Repositorio_de_moedas(maxMoedas);
		this.valor_arrecadado_CARTAO = 0;
		this.valor_arrecadado_MOEDAS = 0;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getEndereco() {
		return endereco;
	}
	
	private boolean validate_ID(String id) {
		if (id.length() == 5 && id.matches("[0-9]+"))	// verifica se o comprimento é 5 e possui apenas números.
			return true;
		return false;
	}

	@Override
	public double getValor_arrecadado_CARTAO() {
		return this.valor_arrecadado_CARTAO;
	}

	@Override
	public void aumentarValorArrecadado_CARTAO(double valor_a_ser_somado) {
		if (valor_a_ser_somado <= 0)
			throw new IllegalArgumentException("O valor passado por parâmetro deve ser maior que 0!");
		this.valor_arrecadado_CARTAO += valor_a_ser_somado;
	}
	
	@Override
	public double valorArecadado_MOEDAS() {
		return valor_arrecadado_MOEDAS;
	}
	
	@Override
	public void setValorArrecadado_MOEDAS(double valor_arrecadado_MOEDAS) {
		this.valor_arrecadado_MOEDAS = valor_arrecadado_MOEDAS;
	}
	
	@Override
	public double totalValorArrecadado() {
		return this.valor_arrecadado_CARTAO + this.valor_arrecadado_MOEDAS;
	}

	@Override
	public void inserirMoeda(int valor) throws IllegalArgumentException, Exception {
		this.repositorio.insereMoeda(valor);
	}

	@Override
	public double saldoEmModas() {
		return this.repositorio.getSaldo()/100.0;
	}

	@Override
	public void comprarTicket(int valor_do_ticket) {
		this.repositorio.comprarTicket(valor_do_ticket);	
	}

	@Override
	public double trocoEmMoedas() throws Exception {
		return this.repositorio.troco()/100.0;
	}

	@Override
	public double valorArrecadado_MOEDAS() {
		return this.repositorio.valorTotal_arrecadado()/100.0;
	}
	
}
