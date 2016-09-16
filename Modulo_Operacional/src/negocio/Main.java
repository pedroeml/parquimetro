package negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.FileLoggerDAOderby;
import persistencia.IFileLoggerDAO;
import persistencia.IStartLoadSettings;
import persistencia.StartLoadSettings;

public class Main {
	private Parquimetro parquimetro;
	private Tarifa tarifa;
	private Ticket ticket;
	private Map<String, Cartao> map_cartao;
	private Assembler_forLogger assembler_forLogger;
	private static int serial_number;
	private final int tempo_de_permanencia_inicial;
	private final int maxMoedas;
	
	public Main() {
		IStartLoadSettings load_settings = new StartLoadSettings();
		String id = load_settings.getDados_Parquimetro().get("ID_PARQUIMETRO");
		String endereco = load_settings.getDados_Parquimetro().get("ENDERECO");
		this.tempo_de_permanencia_inicial = Integer.parseInt(load_settings.getDados_Parquimetro().get("TEMPO_PERM_INICIAL"));
		this.maxMoedas = Integer.parseInt(load_settings.getDados_Parquimetro().get("MAX_MOEDAS"));
		
		List<String[]> list_dados_cartoes = load_settings.getDados_Cartoes();
		this.map_cartao = new HashMap<>();
		this.setMap_Cartao(list_dados_cartoes);
		Main.serial_number = 0;
		
		this.parquimetro = new Parquimetro(id, endereco, this.maxMoedas);
		this.tarifa = Tarifa.getInstance_forTF(this.tempo_de_permanencia_inicial);
		this.ticket = new Ticket(this.tarifa, this.formatedString_serial_number(), this.parquimetro, false);
		this.assembler_forLogger = new Assembler_forLogger();
	}
	
	private void setMap_Cartao(List<String[]> list_dados_cartoes) {
		for (String[] line : list_dados_cartoes) {
			Cartao cartao = new Cartao(line[0], line[1], line[2], line[3], line[4], Double.parseDouble(line[5]));
			this.map_cartao.put(line[0], cartao);
		}
	}
	
	private void incrementar_serial_number() {
		Main.serial_number++;
	}
	
	private String formatedString_serial_number() {
		return String.format("%05d", Main.serial_number);
	}
	
	private void logIt() {
		this.assembler_forLogger.getLogger().addLog(String.format("R$ %.2f", this.parquimetro.totalValorArrecadado()), ((Ticket) ticket).parquimetroID(), ((Ticket) ticket).endereco(), ((Ticket) ticket).serial_number(), ticket.emissao_do_ticket_SDF(), ticket.validade_do_ticket_SDF(), ((Ticket) ticket).metodo_pagamento());
		IFileLoggerDAO file_logger = new FileLoggerDAOderby(this.assembler_forLogger.getLogger());
		file_logger.armazenarLog();
	}
	
	public String imprimir_ticket() {
		return this.ticket.toString();
	}
	
	public String validade_do_ticket() {
		return this.ticket.validade_do_ticket_SDF();
	}
	
	public String valor_total_a_ser_pago() {
		return String.format("R$ %.2f", this.tarifa.getValor_a_pagar());
	}
	
	public int incrementar_tempo_de_permanencia() {
		if (this.tarifa.getTempo_de_permanencia() >= this.tarifa.getTempo_minimo() && this.tarifa.getTempo_de_permanencia() < this.tarifa.getTempo_maximo())
			this.tarifa = Tarifa.getInstance_forTF(this.tarifa.getTempo_de_permanencia()+this.tarifa.getIncremento_em_minutos());
		this.ticket = new Ticket(this.tarifa, this.formatedString_serial_number(), this.parquimetro, false);
		return this.tarifa.getTempo_de_permanencia();
	}
	
	public int decrementar_tempo_de_permanencia() {
		if (this.tarifa.getTempo_de_permanencia() > this.tarifa.getTempo_minimo() && this.tarifa.getTempo_de_permanencia() <= this.tarifa.getTempo_maximo())
			this.tarifa = Tarifa.getInstance_forTF(this.tarifa.getTempo_de_permanencia()-this.tarifa.getIncremento_em_minutos());
		this.ticket = new Ticket(this.tarifa, this.formatedString_serial_number(), this.parquimetro, false);
		return this.tarifa.getTempo_de_permanencia();
	}
	
	// OS MÉTODOS ABAIXO SÃO PARA O PAGAMENTO COM MOEDAS
	
	public void inserir_moeda(int moeda) throws Exception {
		try {
			this.parquimetro.inserirMoeda(moeda);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	public String saldo_disponivel_repositorio_moedas() {
		IOperacoesStrategy operacoes = EMetodoPagamento.MOEDAS.getInstance(parquimetro, tarifa);
		return operacoes.saldo();
	}
	
	public String confirmar_retornar_troco() throws Exception {
		IOperacoesStrategy operacoes = EMetodoPagamento.MOEDAS.getInstance(parquimetro, tarifa);
		String troco = operacoes.confirmar();
		
		this.ticket = new Ticket(this.tarifa, this.formatedString_serial_number(), this.parquimetro, false);
		this.incrementar_serial_number();
		
		this.logIt();
		
		return troco;
	}
	
	public String cancelar_devolver_dinheiro() throws Exception {
		IOperacoesStrategy operacoes = EMetodoPagamento.MOEDAS.getInstance(parquimetro, tarifa);
		return operacoes.cancelar();
	}
	
	// OS MÉTODOS ABAIXO SÃO PARA O PAGAMENTO COM CARTÃO RECARREGÁVEL
	
	public boolean pesquisar_num_cartao(String num_cartao) throws Exception {
		if (num_cartao.isEmpty() || num_cartao.length() != 128)
			throw new IllegalArgumentException("O nº do cartão informado é inválido!");
		if (!this.map_cartao.containsKey(num_cartao))
			throw new Exception("O nº do cartão informado não existe.");
		return true;
	}
	
	public String saldo_disponivel_cartao(String num_cartao) {
		Cartao cartao = this.getCartao_from(num_cartao);
		IOperacoesStrategy operacoes = EMetodoPagamento.CARTAO.getInstance(parquimetro, tarifa);
		((OperacoesCartao) operacoes).setCartao(cartao);
		return operacoes.saldo();
	}
	
	public String titular_nome_cartao(String num_cartao) {
		Cartao cartao = this.getCartao_from(num_cartao);
		return cartao.getNome();
	}
	
	public void confirmar_cartao(String num_cartao) throws Exception {
		Cartao cartao = this.getCartao_from(num_cartao);
		IOperacoesStrategy operacoes = EMetodoPagamento.CARTAO.getInstance(parquimetro, tarifa);
		((OperacoesCartao) operacoes).setCartao(cartao);
		operacoes.confirmar();
		
		this.ticket = new Ticket(this.tarifa, this.formatedString_serial_number(), this.parquimetro, true);
		this.incrementar_serial_number();
		
		this.logIt();
	}
	
	private Cartao getCartao_from(String num_cartao) {
		Cartao cartao = this.map_cartao.get(num_cartao);
		if (cartao == null)
			throw new IllegalArgumentException("O nº do cartão informado é inválido!");
		return cartao;
	}
}