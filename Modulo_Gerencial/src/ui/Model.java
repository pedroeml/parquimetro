package ui;

import java.io.File;
import java.util.Iterator;
import java.util.Observable;

import negocio.Fachada;

public class Model extends Observable {
	private Fachada fachada;
	private String id_parquimetro, endereco;
	
	public Model() {
		this.fachada = new Fachada();
		this.updateId_parquimetro();
		this.updateEndereco();
	}
	
	public String getId_parquimetro() {
		return id_parquimetro;
	}
	
	public void updateId_parquimetro() {
		this.id_parquimetro = fachada.getId_parquimetro_pesquisado();
		setChanged();
		notifyObservers();
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void updateEndereco() {
		this.endereco = fachada.getEndereco_parquimetro_pesquisado();
		setChanged();
		notifyObservers();
	}
	
	public void loadLogFile(File file) {
		fachada.loadLogFile(file);
	}
	
	public boolean pesquisarIDParquimetro(String id_parquimetro) {
		return fachada.pesquisarIDParquimetro(id_parquimetro);
	}
	
	public String relatorio_AgrupadoEmAnos() {
		return fachada.relatorio_AgrupadoEmAnos();
	}
	
	public BarChart graficoEmBarras(String title, String y_axis_label, String x_axis_label) {
		BarChart chart = new BarChart(title, y_axis_label, x_axis_label);
		Iterator<String[]> it = fachada.iterateDados_grafico();
		
		while (it.hasNext()) {
			String[] str = it.next();
			String valor = str[1].replace(',', '.');
			chart.addData(Double.parseDouble(valor), y_axis_label, str[0]);
		}
		
		return chart;
	}
	
	public String relatorio_AgrupadoEmMeses(int numero_do_ano) {
		return fachada.relatorio_AgrupadoEmMeses(numero_do_ano);
	}
	
	public String relatorio_TodosParquimetros(int numero_do_dia, int numero_do_mes, int numero_do_ano) {
		return fachada.relatorio_TodosParquimetros(numero_do_dia, numero_do_mes, numero_do_ano);
	}
	
	public String relatorio_TodosParquimetros(int numero_do_mes, int numero_do_ano) {
		return fachada.relatorio_TodosParquimetros(numero_do_mes, numero_do_ano);
	}
	
	
}
