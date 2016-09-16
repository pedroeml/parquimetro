package negocio;

public interface IOperacoesStrategy {

	public String saldo();
	public String confirmar() throws Exception;
	public String cancelar() throws Exception;
}
