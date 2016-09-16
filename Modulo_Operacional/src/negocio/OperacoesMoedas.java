package negocio;

public class OperacoesMoedas implements IOperacoesStrategy {
	private Tarifa tarifa;
	private Parquimetro parquimetro;
	
	public OperacoesMoedas(Parquimetro parquimetro, Tarifa tarifa) {
		this.tarifa = tarifa;
		this.parquimetro = parquimetro;
	}

	@Override
	public String saldo() {
		return String.format("R$ %.2f", this.parquimetro.saldoEmModas());
	}

	@Override
	public String confirmar() throws Exception {
		if (this.tarifa.getValor_a_pagar() > this.parquimetro.saldoEmModas())
			throw new Exception("Não há saldo suficiente para realizar a operação!");
		this.parquimetro.comprarTicket((int) (this.tarifa.getValor_a_pagar()*100));
		String troco = String.format("R$ %.2f", this.parquimetro.trocoEmMoedas());
		this.parquimetro.setValorArrecadado_MOEDAS(this.parquimetro.valorArrecadado_MOEDAS());
		return troco;
	}

	@Override
	public String cancelar() throws Exception {
		return String.format("R$ %.2f", this.parquimetro.trocoEmMoedas());
	}

}
