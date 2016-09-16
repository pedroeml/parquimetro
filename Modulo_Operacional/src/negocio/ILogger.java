package negocio;

public interface ILogger {
	public String getLog();
	/**
	 * Adiciona todos os parâmetros como última linha do StringBuilder, esta linha está formatada como arquivo CSV.
	 */
	public void addLog(String arrecadado, String id_parq, String endereco, String num_serial, String emissao, String validade, String metodo_pagamento);
	
}
