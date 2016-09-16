package negocio;

public class FileLogger implements ILogger {
	private StringBuilder log;
	
	public FileLogger() {
		log = new StringBuilder();
		log.append("ARRECADADO;NUM_ID_PARQ;ENDERECO;NUM_SERIAL;EMISSAO;VALIDADE;METODO_PAGAMENTO;\n");
	}
	
	@Override
	public String getLog() {
		return log.toString();
	}

	@Override
	public void addLog(String arrecadado, String id_parq, String endereco, String num_serial, String emissao, String validade, String metodo_pagamento) {
		log.append(String.format("%s;%s;%s;%s;%s;%s;%s;\n", arrecadado, id_parq, endereco, num_serial, emissao, validade, metodo_pagamento));
		//System.out.println(this.getLog());
	}

}
