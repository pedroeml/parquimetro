package negocio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/***
 * Classe que implementa as operações da interface.
 * 
 * @author Pedro E. M. Lemos
 * @version 26/05/2016
 */

public class Ticket {
	private Tarifa tarifa;
	private final String serial_number;
	private Calendar emissao_do_ticket, validade_do_ticket;
	private IParquimetro parquimetro;
	private SimpleDateFormat sdf;
	private String generated_ticket;
	private boolean isPagamento_Cartao;
	
	public Ticket(Tarifa tarifa, String serial_number, IParquimetro parquimetro, boolean isPagamento_Cartao) {
		this.tarifa = tarifa;
		this.serial_number = serial_number;
		this.parquimetro = parquimetro;
		this.emissao_do_ticket = GregorianCalendar.getInstance();
		this.validade_do_ticket = GregorianCalendar.getInstance();
		this.validade_do_ticket.add(Calendar.MINUTE, tarifa.getTempo_de_permanencia());	// soma os minutos para obeter o tempo de valide.
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.isPagamento_Cartao = isPagamento_Cartao;
		this.generateTicket();
	}
	
	/**
	 * Retorna String formatada com SimpleDateFormat de um Calendar.
	 */
	public String emissao_do_ticket_SDF() {
		return sdf.format(this.emissao_do_ticket.getTime());
	}
	
	/**
	 * Retorna String formatada com SimpleDateFormat de um Calendar.
	 */
	public String validade_do_ticket_SDF() {
		return sdf.format(this.validade_do_ticket.getTime());
	}
	
	private void generateTicket() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Nº ID parq.:\t%s\n", this.parquimetro.getId()));
		str.append(String.format("Endereço:\t%s\n", this.parquimetro.getEndereco()));
		str.append(String.format("Nº serial:\t%s\n", this.serial_number));
		str.append(String.format("Emissão:\t%s\n", this.emissao_do_ticket_SDF()));
		str.append(String.format("Validade:\t%s\n", this.validade_do_ticket_SDF()));
		str.append(String.format("Pagamento:\t%s", this.isPagamento_Cartao ? "Cartão" : "Moedas"));
		this.generated_ticket = str.toString();
		//return this.generated_ticket;
	}
	
	protected String parquimetroID() {
		return this.parquimetro.getId();
	}
	
	protected String endereco() {
		return this.parquimetro.getEndereco();
	}
	
	protected String serial_number() {
		return this.serial_number; 
	}
	
	protected String metodo_pagamento() {
		return this.isPagamento_Cartao ? "Cartão" : "Moedas";
	}
	
	/**
	 * Retorna o ticket formatado para visualização humana.
	 */
	public String toString() {
		return this.generated_ticket;
	}
	
}
