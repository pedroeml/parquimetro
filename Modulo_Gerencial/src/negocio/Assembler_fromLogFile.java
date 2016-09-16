package negocio;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import persistencia.IOpenLogFileDAO;
import persistencia.OpenLogFileDAOderby;

public class Assembler_fromLogFile {
	private IOpenLogFileDAO openLogFile;
	private ColecaoParquimetro colecao_parquimetro;
	
	public Assembler_fromLogFile(File file, ColecaoParquimetro colecao_parquimetro) {
		this.openLogFile = new OpenLogFileDAOderby(file);
		this.colecao_parquimetro = colecao_parquimetro;
		this.criarObjetos();
	}
	
	private void criarObjetos() {
		for (String[] line : this.openLogFile.getLogFileData()) {
			String id_parq = line[1], endereco = line[2], num_serial = line[3], metodo_pagamento = line[6];
			double valor_arrecadado = Double.parseDouble((line[0].substring(3, line[0].length())).replace(',', '.'));
			
			int date_emissao[] = this.parseSDFtoInt(line[4]), date_validade[] = this.parseSDFtoInt(line[5]);
			
			GregorianCalendar emissao = new GregorianCalendar(date_emissao[0], date_emissao[1], date_emissao[2], date_emissao[3], date_emissao[4], 0);
			GregorianCalendar validade = new GregorianCalendar(date_validade[0], date_validade[1], date_validade[2], date_validade[3], date_validade[4], 0);
			
			Ticket ticket = new Ticket(num_serial, emissao, validade, metodo_pagamento, valor_arrecadado);
			
			this.addTicketAoParquimetro(id_parq, endereco, ticket, emissao);
		}
	}
	
	/**
	 * Adciona o objeto Ticket ao objeto Parquimetro correspondente ao id do parquímetro na coleção de parquímetros.
	 * Se a id do parquímetro não existir na coleção, então um novo objeto Parquímetro é criado - adicionando o objeto 
	 * Ticket corretamente nas listas de Ano e Mes - e adicionado na coleção. Caso o contrário, o objeto Ticket é 
	 * adicionado nas listas de acordo com seu ano e mês.
	 */
	private void addTicketAoParquimetro(String id_parq, String endereco, Ticket ticket, Calendar emissao) {
		Parquimetro parquimetro = this.colecao_parquimetro.getParquimetro(id_parq);
		
		if (parquimetro != null) {	// se existir a id do parquímetro na coleção
			Ano<Ticket> ano = parquimetro.getAno(emissao.get(Calendar.YEAR));
			if (ano != null) {	// se existir o ano na lista de parquímetro
				Mes<Ticket> mes = ano.getMes(emissao.get(Calendar.MONTH));
				if (mes != null)	// se existir o mês na lista de ano
					mes.addObjToList(ticket);
				else {	// se não existir o mês na lista de ano
					mes = new Mes<>(emissao.get(Calendar.MONTH));
					mes.addObjToList(ticket);
					ano.addObjToList(mes);
				}
			}
			else {	// se não existir o ano na lista de parquímetro
				ano = new Ano<>(emissao.get(Calendar.YEAR));
				Mes<Ticket> mes = new Mes<>(emissao.get(Calendar.MONTH));
				mes.addObjToList(ticket);
				ano.addObjToList(mes);
				parquimetro.addObjToList(ano);
			}
		}
		else {	// se não existir a id do parquímetro na coleção
			parquimetro = new Parquimetro(id_parq, endereco);
			Ano<Ticket> ano = new Ano<>(emissao.get(Calendar.YEAR));
			Mes<Ticket> mes = new Mes<>(emissao.get(Calendar.MONTH));
			mes.addObjToList(ticket);
			ano.addObjToList(mes);
			parquimetro.addObjToList(ano);
			this.colecao_parquimetro.addParquimetro(id_parq, parquimetro);
		}
	}
	
	private int[] parseSDFtoInt(String sdf) {
		int date[] = new int[5];
		
		String[] date_and_clock = sdf.split(" ");	// date_and_clock[0] = "dd/MM/yyyy"	date_and_clock[1] = "HH:mm"
		String[] date_array = date_and_clock[0].split("/");	// date_array[0] = "dd"		date_array[1] = "MM"	date_array[2] = "yyyy"
		String[] clock_array = date_and_clock[1].split(":");	// clock_array[0] = "HH"	clock_array[1] = "mm"
		
		date[0] = Integer.parseInt(date_array[2]);	// "yyyy"
		date[1] = Integer.parseInt(date_array[1]) - 1;	// "MM" - 1, pois JANUARY é 0. Logo o intervalo é de 0 à 11
		date[2] = Integer.parseInt(date_array[0]);	// "dd"
		date[3] = Integer.parseInt(clock_array[0]); // "HH"
		date[4] = Integer.parseInt(clock_array[1]);	// "mm"
		
		return date;
	}

}
