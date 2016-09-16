package negocio;

public class Cartao {
	private String num_cartao, nome, cpf, rg, telefone;
	private double saldo;

	public Cartao(String num_cartao, String nome, String cpf, String rg, String telefone, double saldo) {
		this.setNum_Cartao(num_cartao);
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.saldo = saldo;
	}
	
	public String getNum_cartao() {
		return num_cartao;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	/**
	 * Debita no saldo de do objeto o valordo ticket contanto que o saldo seja suficiente.
	 */
	public void debitarTicket(double valor_do_ticket) {
		if (valor_do_ticket < 0 || this.getSaldo() < valor_do_ticket)
			throw new IllegalArgumentException(String.format("O valor do ticket (R$ %.2f) inválido ou saldo (R$ %.2f) insuficiente!", valor_do_ticket, this.getSaldo()));
		this.saldo -= valor_do_ticket;
	}
	
	private void setNum_Cartao(String num_cartao) {
		if (num_cartao.isEmpty() || num_cartao.length() != 128)
			throw new IllegalArgumentException("O nº do cartão não possui 128 caracteres!");
		this.num_cartao = num_cartao;
	}

}
