package negocio;

public class OperacoesCartao implements IOperacoesStrategy {
	private Cartao cartao;
	private Tarifa tarifa;
	private Parquimetro parquimetro;
	
	/**
	 * Antes de começar utilizar a classe deve primeiro settar o atributo de tipo Cartao
	 */
	public OperacoesCartao(Parquimetro parquimetro, Tarifa tarifa) {
		this.parquimetro = parquimetro;
		this.tarifa = tarifa;
	}
	
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	@Override
	public String saldo() {
		return String.format("R$ %.2f", cartao.getSaldo());
	}

	@Override
	public String confirmar() throws Exception {
		if (this.tarifa.getValor_a_pagar() > (cartao.getSaldo()))
			throw new Exception("Não há saldo suficiente para realizar a operação!");
		cartao.debitarTicket(this.tarifa.getValor_a_pagar());		
		this.parquimetro.aumentarValorArrecadado_CARTAO(this.tarifa.getValor_a_pagar());
		return null;
	}

	@Override
	public String cancelar() throws Exception {
		return null;
	}

}
