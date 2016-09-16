package negocio;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticket implements Comparable<Ticket>, ICompareCalendar<Integer> {
	private Calendar emissao, validade;
	private String num_serial, metodo_pagamento;
	private double valor_arrecadado;
	private SimpleDateFormat sdf, sdf_hour_minute;
	
	public Ticket(String num_serial, Calendar emissao, Calendar validade, String metodo_pagamento, double valor_arrecadado) {
		this.num_serial = num_serial;
		this.emissao = emissao;
		this.validade = validade;
		this.metodo_pagamento = metodo_pagamento;
		this.valor_arrecadado = valor_arrecadado;
		this.sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.sdf_hour_minute = new SimpleDateFormat("HH:mm");
	}
	
	public String getEmissaoSDF() {
		return sdf.format(this.emissao.getTime());
	}
	
	public String getValidadeSDF() {
		return sdf.format(this.validade.getTime());
	}
	
	public String getNum_serial() {
		return num_serial;
	}
	
	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}
	
	public double getValor_arrecadado() {
		return valor_arrecadado;
	}
	
	public String relatorio_dia_especifico() {
		return String.format("R$ %.2f\t%s    %s - %s    %s;", this.getValor_arrecadado(), this.getNum_serial(), this.sdf_hour_minute.format(this.emissao.getTime()), this.sdf_hour_minute.format(this.validade.getTime()), this.getMetodo_pagamento());
	}
	
	public String relatorio_mes() {
		return String.format("R$ %.2f\t%s    Dia do mês: %2d, %s - %s    %s;", this.getValor_arrecadado(), this.getNum_serial(), emissao.get(Calendar.DAY_OF_MONTH), this.sdf_hour_minute.format(this.emissao.getTime()), this.sdf_hour_minute.format(this.validade.getTime()), this.getMetodo_pagamento());
	}
	
	@Override
	public String toString() {
		return String.format("R$ %.2f;%s;%s;%s;%s;", this.getValor_arrecadado(), this.getNum_serial(), this.getEmissaoSDF(), this.getValidadeSDF(), this.getMetodo_pagamento());
	}
	
	@Override
	public int compareTo(Ticket arg0) {
		if (this.emissao.before(arg0.emissao))
			return -1;
		else if (this.emissao.after(arg0.emissao))
			return 1;
		return 0;
	}

	@Override
	public boolean isSameYear(Integer obj) {
		if (this.emissao.get(Calendar.YEAR) == obj.intValue())
			return true;
		return false;
	}

	@Override
	public boolean isSameMonth(Integer obj) {
		if (this.emissao.get(Calendar.MONTH) == obj.intValue())
			return true;
		return false;
	}

	@Override
	public boolean isSameDayOfMonth(Integer obj) {
		if (this.emissao.get(Calendar.DAY_OF_MONTH) == obj.intValue())
			return true;
		return false;
	}

	@Override
	public boolean isSameDayOfWeek(Integer obj) {
		if (this.emissao.get(Calendar.DAY_OF_WEEK) == obj.intValue())
			return true;
		return false;
	}
}
