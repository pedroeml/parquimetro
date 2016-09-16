package negocio;

public enum EMetodoPagamento {
	MOEDAS {
		@Override
		public IOperacoesStrategy getInstance(Parquimetro parquimetro, Tarifa tarifa) {
			return new OperacoesMoedas(parquimetro, tarifa);
		}
	},
	CARTAO {
		@Override
		public IOperacoesStrategy getInstance(Parquimetro parquimetro, Tarifa tarifa) {
			return new OperacoesCartao(parquimetro, tarifa);
		}
	};
	
	public abstract IOperacoesStrategy getInstance(Parquimetro parquimetro, Tarifa tarifa);
}
