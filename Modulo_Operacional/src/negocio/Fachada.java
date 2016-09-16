package negocio;


public class Fachada {
	private Main main;
	
	public Fachada() {
		this.main = new Main();
	}
	
	public String imprime_ticket() {
		return this.main.imprimir_ticket();
	}
	
	public String validade_do_ticket() {
		return this.main.validade_do_ticket();
	}
	
	public String valor_total_a_ser_pago() {
		return this.main.valor_total_a_ser_pago();
	}
	
	public int incrementa_tempo_de_permanencia() {
		return this.main.incrementar_tempo_de_permanencia();
	}
	
	public int decrementa_tempo_de_permanencia() {
		return this.main.decrementar_tempo_de_permanencia();
	}
	
	public void inserir_moeda(int moeda) throws Exception {
		this.main.inserir_moeda(moeda);
	}
	
	public String saldo_disponivel_repositorio_moedas() {
		return this.main.saldo_disponivel_repositorio_moedas();
	}
	
	public String confirma_e_retorna_o_troco() throws Exception {
		return this.main.confirmar_retornar_troco();
	}
	
	public String cancela_e_devolve_o_dinheiro() throws Exception {
		return this.main.cancelar_devolver_dinheiro();
	}
	
	public boolean pesquisa_num_cartao(String num_cartao) throws Exception {
		return this.main.pesquisar_num_cartao(num_cartao);
	}
	
	public String saldo_disponivel_cartao(String num_cartao) {
		return this.main.saldo_disponivel_cartao(num_cartao);
	}
	
	public String titular_nome_cartao(String num_cartao) {
		return this.main.titular_nome_cartao(num_cartao);
	}
	
	public void confirmar_cartao(String num_cartao) throws Exception {
		this.main.confirmar_cartao(num_cartao);
	}
	
}
