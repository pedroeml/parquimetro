package negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Relatorio {
	
	private static double totalArrecadadoNoMes(Mes<Ticket> mes) {
		return mes.getLast().getValor_arrecadado();
	}

	private static double totalArrecadadoNoAno(Ano<Ticket> ano) {
		Iterator<Mes<Ticket>> it = ano.iterate_list();
		double total = 0;
		
		while (it.hasNext()) {
			Mes<Ticket> mes = it.next();
			total += totalArrecadadoNoMes(mes);
		}
		
		return total;
	}
	
	private static Ano<Ticket> getAno_fromParquimetro(Parquimetro parquimetro, int numero_do_ano) throws Exception {
		Ano<Ticket> ano = parquimetro.getAno(numero_do_ano);
		
		if (ano == null)
			throw new Exception(String.format("Não há dados no parquímetro (%s) sobre o ano %d", parquimetro.getId(), numero_do_ano));
		
		return ano;
	}
	
	private static Mes<Ticket> getMes_inAno_fromParquimetro(Parquimetro parquimetro, Ano<Ticket> ano, int numero_do_mes) throws Exception {
		Mes<Ticket> mes = ano.getMes(numero_do_mes);
		
		if (mes == null)
			throw new Exception(String.format("Não há dados no parquímetro (%s) sobre o mês %d no ano %d", parquimetro.getId(), numero_do_mes, ano.getAno()));
		
		return mes;
	}
	
	public static double totalArrecadadoNoAno(Parquimetro parquimetro, int numero_do_ano) throws Exception {
		Ano<Ticket> ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
		
		return totalArrecadadoNoAno(ano);
	}
	
	public static double totalArrecadadoEm(Parquimetro parquimetro, int numero_do_ano, int numero_do_mes) throws Exception {
		Ano<Ticket> ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
		
		Mes<Ticket> mes = getMes_inAno_fromParquimetro(parquimetro, ano, numero_do_mes);
		
		return mes.getLast().getValor_arrecadado();
	}
	
	public static String relatorio_AgrupadoEmAnos(Parquimetro parquimetro) {
		Iterator<Ano<Ticket>> it = parquimetro.iterate_list();
		StringBuilder str = new StringBuilder();
		str.append(String.format("Parquímetro ID: %s\tEndereço: %s\n", parquimetro.getId(), parquimetro.getEndereco()));
		
		while (it.hasNext()) {
			Ano<Ticket> ano = it.next();
			str.append(String.format("Ano: %4d\tTotal Arrecadado: R$ %.2f\n", ano.getAno(), totalArrecadadoNoAno(ano)));
		}
		
		return str.toString();
	}
	
	public static String relatorio_AgrupadoEmMeses(Parquimetro parquimetro, int numero_do_ano) throws Exception {
		Ano<Ticket> ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
		
		StringBuilder str = new StringBuilder();
		str.append(String.format("Parquímetro ID: %s\tEndereço: %s\n", parquimetro.getId(), parquimetro.getEndereco()));
		str.append(String.format("Ano: %4d\tTotal Arrecadado: R$ %.2f\n", ano.getAno(), totalArrecadadoNoAno(ano)));
		Iterator<Mes<Ticket>> it = ano.iterate_list();
		
		while (it.hasNext()) {
			Mes<Ticket> mes = it.next();
			str.append(String.format("Mês: %s\tTotal Arrecadado: R$ %.2f\n", mes.getMesStringFormat(), totalArrecadadoNoMes(mes)));
		}
		
		return str.toString();
	}
	
	public static List<String[]> grafico_AgrupadoEmMeses(Parquimetro parquimetro, int numero_do_ano) throws Exception {
		Ano<Ticket> ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
		List<String[]> list = new ArrayList<>(12);
		
		Iterator<Mes<Ticket>> it = ano.iterate_list();
		
		while (it.hasNext()) {
			Mes<Ticket> mes = it.next();
			String[] str = new String[2];
			str[0] = mes.getMesStringFormat();
			str[1] = String.format("%.2f", totalArrecadadoNoMes(mes));
			list.add(str);
		}
		
		return list;
	}
	
	public static String relatorio_TodosParquimetros(List<Parquimetro> list_parquimetros, int numero_do_dia, int numero_do_mes, int numero_do_ano) {
		Iterator<Parquimetro> it_p = list_parquimetros.iterator();
		StringBuilder str = new StringBuilder();
		
		while (it_p.hasNext()) {
			Parquimetro parquimetro = it_p.next();
			
			Ano<Ticket> ano;
			try {
				ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
			} catch (Exception e) {
				continue;
			}
			
			Mes<Ticket> mes;
			try {
				mes = getMes_inAno_fromParquimetro(parquimetro, ano, numero_do_mes);
			} catch (Exception e) {
				continue;
			}
			
			Iterator<Ticket> it_t = mes.iterate_list();
			boolean first_ocurrence = true;
			
			while (it_t.hasNext()) {
				Ticket ticket = it_t.next();
				
				if (ticket.isSameDayOfMonth(numero_do_dia)) {
					if (first_ocurrence) {
						str.append(String.format("Parquímetro ID: %s\tEndereço: %s\n", parquimetro.getId(), parquimetro.getEndereco()));
						str.append(String.format("Data:\t%02d/%02d/%4d\n", numero_do_dia, mes.getMes()+1, ano.getAno()));
						first_ocurrence = !first_ocurrence;
					}
					
					str.append(String.format("%s\n", ticket.relatorio_dia_especifico()));
				}
			}
		}
		return str.toString();
	}
	
	public static String relatorio_TodosParquimetros(List<Parquimetro> list_parquimetros, int numero_do_mes, int numero_do_ano) {
		Iterator<Parquimetro> it_p = list_parquimetros.iterator();
		StringBuilder str = new StringBuilder();
		
		while (it_p.hasNext()) {
			Parquimetro parquimetro = it_p.next();
			
			Ano<Ticket> ano;
			try {
				ano = getAno_fromParquimetro(parquimetro, numero_do_ano);
			} catch (Exception e) {
				continue;
			}
			
			Mes<Ticket> mes;
			try {
				mes = getMes_inAno_fromParquimetro(parquimetro, ano, numero_do_mes);
			} catch (Exception e) {
				continue;
			}
			
			Iterator<Ticket> it_t = mes.iterate_list();
			boolean first_ocurrence = true;
			
			while (it_t.hasNext()) {
				Ticket ticket = it_t.next();
				
				if (first_ocurrence) {
					str.append(String.format("Parquímetro ID: %s\tEndereço: %s\n", parquimetro.getId(), parquimetro.getEndereco()));
					str.append(String.format("Ano: %4d\tMês: %s\n", ano.getAno(), mes.getMesStringFormat()));
					first_ocurrence = !first_ocurrence;
				}
					
				str.append(String.format("%s\n", ticket.relatorio_mes()));
			}
			
		}
		
		return str.toString();
	}
}
