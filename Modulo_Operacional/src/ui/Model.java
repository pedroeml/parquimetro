package ui;

import java.util.Observable;

import negocio.Fachada;

public class Model extends Observable {
	private Fachada fachada;
	private String tempo_de_permanencia, validade_do_ticket, valor_total_a_ser_pago, saldo;
	
	public Model() {
		super();
		saldo = "R$ 0,00";
		fachada = new Fachada();
	}	
	
	public String getTempo_de_permanencia() {
		return tempo_de_permanencia;
	}

	public String getValidade_do_ticket() {
		return validade_do_ticket;
	}

	public String getValor_total_a_ser_pago() {
		return valor_total_a_ser_pago;
	}

	public String getSaldo() {
		return saldo;
	}

	public void validade_do_ticket() {
		this.validade_do_ticket = fachada.validade_do_ticket();
		setChanged();
		notifyObservers();
	}
	
	public void valor_total_a_ser_pago() {
		this.valor_total_a_ser_pago = fachada.valor_total_a_ser_pago();
		setChanged();
		notifyObservers();
	}
	
	public void incrementa_tempo_de_permanencia() {
		this.tempo_de_permanencia = String.format("%s", fachada.incrementa_tempo_de_permanencia());
		setChanged();
		notifyObservers();
	}
	
	public void decrementa_tempo_de_permanencia() {
		this.tempo_de_permanencia = String.format("%s", fachada.decrementa_tempo_de_permanencia());
		setChanged();
		notifyObservers();
	}
	
	public String confirma_e_retorna_o_troco() throws Exception {
		return fachada.confirma_e_retorna_o_troco();
	}
	
	public String cancela_e_devolve_o_dinheiro() throws Exception {
		return fachada.cancela_e_devolve_o_dinheiro();
	}
	
	public String imprime_ticket() {
		return fachada.imprime_ticket();
	}
	
	public void saldo_disponivel_repositorio_moedas() {
		this.saldo = fachada.saldo_disponivel_repositorio_moedas();
		setChanged();
		notifyObservers();
	}
	
	public void inserir_moeda(int moeda) throws Exception {
		fachada.inserir_moeda(moeda);
	}
	
	public void pesquisa_num_cartao(String num_cartao) throws Exception {
		fachada.pesquisa_num_cartao(num_cartao);
	}
	
	public String titular_nome_cartao(String num_cartao) {
		return fachada.titular_nome_cartao(num_cartao);
	}
	
	public void saldo_disponivel_cartao(String num_cartao) {
		this.saldo = fachada.saldo_disponivel_cartao(num_cartao);
		setChanged();
		notifyObservers();
	}
	
	public void confirmar_cartao(String num_cartao) throws Exception {
		fachada.confirmar_cartao(num_cartao);
	}
}
