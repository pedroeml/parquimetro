package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.PatternSyntaxException;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.model.addObserver(this.view);
		this.view.associaController_btnImportarLog(new Action_ImportarLog());
		this.view.associaController_btnRelatorioPesquisarParquimetros(new Action_RelatorioPesquisarParquimetro());
		this.view.associaController_btnRelatorioTodosParquimetros(new Action_RelatorioTodosParquimetros());
		this.view.associaController_btnAno(new Action_Ano());
		this.view.associaController_btnMes(new Action_Mes());
		this.view.associaController_btnBuscar(new Action_Buscar());
		this.view.associaController_btnFiltrar(new Action_Filtrar());
		
	}
	
	private class Action_ImportarLog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File file = view.fileChooserDialog();
			
			if (file == null)
				return;
			
			model.loadLogFile(file);
			view.show_and_hide_elements_for_loadLog();
			view.show_buttonsRelatorios(true);
			view.setTextof_txtFiltro_toDefault();
			view.panel_removeCurrentComponent();
		}
		
	}
	
	private class Action_RelatorioTodosParquimetros implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.show_and_hide_elements_for_RelatorioTodosParquimetros();
			view.setTextof_txtFiltro_toDefault();
			view.panel_removeCurrentComponent();
		}
		
	}
	
	private class Action_RelatorioPesquisarParquimetro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.show_and_hide_elements_for_RelatorioPesquisarParquimetro();
			view.setTextof_txtFiltro_toDefault();
			view.panel_removeCurrentComponent();
		}
		
	}
	
	private class Action_Filtrar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] filtro = null; 
			
			try {
				filtro = view.getText_txtFiltro().split("/");
			} catch (PatternSyntaxException e) {
				view.setText_textArea("O FORMATO DO FILTRO DEVE SER DD/MM/YYYY OU MM/YYYY!");
				return;
			}
			
			if (filtro.length == 2) {
				int numero_do_mes, numero_do_ano;
				
				try {
					numero_do_mes = this.validate_mes(Integer.parseInt(filtro[0]));
					numero_do_ano = this.validate_ano(Integer.parseInt(filtro[1]));
				} catch (IllegalArgumentException e) {
					view.setText_textArea(e.getMessage());
					return;
				}
				
				numero_do_mes--;	// para evitar problemas por conta da numeração variar de 0 a 11 no Calendar
				
				String text = model.relatorio_TodosParquimetros(numero_do_mes, numero_do_ano);
				
				view.setText_textArea(text.isEmpty() ? "NENHUM DADO PARA ESTA DATA" : text);
			}
			else if (filtro.length == 3) {
				int numero_do_dia, numero_do_mes, numero_do_ano;
				
				try {
					numero_do_dia = this.validate_dia(Integer.parseInt(filtro[0]));
					numero_do_mes = this.validate_mes(Integer.parseInt(filtro[1]));
					numero_do_ano = this.validate_ano(Integer.parseInt(filtro[2]));
				} catch (IllegalArgumentException e) {
					view.setText_textArea(e.getMessage());
					return;
				}
				
				numero_do_mes--;
				
				String text = model.relatorio_TodosParquimetros(numero_do_dia, numero_do_mes, numero_do_ano);
				
				view.setText_textArea(text.isEmpty() ? "NENHUM DADO PARA ESTA DATA" : text);
			}
			else {
				view.setText_textArea("O FORMATO DO FILTRO DEVE SER DD/MM/YYYY OU MM/YYYY!");
				return;
			}
			
		}
		
		private int validate_dia(int numero_do_dia) {
			if (numero_do_dia < 1 || numero_do_dia > 31)
				throw new IllegalArgumentException("O número do dia está fora do intervalo [1; 31]!");
			return numero_do_dia;
		}
		
		private int validate_mes(int numero_do_mes) {
			if (numero_do_mes < 1 || numero_do_mes > 12)
				throw new IllegalArgumentException("O número do mês está fora do intervalo [1; 12]!");
			return numero_do_mes;
		}
		
		private int validate_ano(int numero_do_ano) {
			if (numero_do_ano < 2016)
				throw new IllegalArgumentException("O número do ano está inválido!");
			return numero_do_ano;
		}
		
	}
	
	private class Action_Buscar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean isParquimetroEncontrado = model.pesquisarIDParquimetro(view.getText_ID_Parq());
			view.show_elements_for_AgruparRelatorio(isParquimetroEncontrado);
			view.isParquimetroEncontrado(isParquimetroEncontrado);
			view.setText_textArea("");
			
			if (!isParquimetroEncontrado) {
				view.setText_lblEndereco("");
				view.setText_lblIDParquimetro("");
			}
			else {
				model.updateId_parquimetro();
				model.updateEndereco();
			}
			
			view.panel_removeCurrentComponent();
		}
		
	}
	
	private class Action_Ano implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.setText_textArea(model.relatorio_AgrupadoEmAnos());
			view.panel_removeCurrentComponent();
		}
		
	}
	
	private class Action_Mes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int numero_do_ano = 0;
			try {
				numero_do_ano = Integer.parseInt(view.getText_txtNum_do_ano());
			} catch (NumberFormatException e) {
				view.setText_textArea("INFORME SOMENTE NÚMEROS NO CAMPO ANO!");
				return;
			}
			String txt = model.relatorio_AgrupadoEmMeses(numero_do_ano);
			view.setText_textArea(txt);
			if (!txt.startsWith("N") && !txt.startsWith("O n")) { // A mensagem de warning começa com "Não ..." ou "O número ..."
				BarChart chart = model.graficoEmBarras("("+view.getText_lblIDParquimetro()+") "+view.getText_lblEndereco(), "Valor arrecadado", "Meses de "+numero_do_ano);
				view.panel_removeCurrentComponent();
				view.panel_add(chart.getPane(view.panel_getX(), view.panel_getY(), view.panel_getWidth(), view.panel_getHeight()));
			}
		}
		
	}
}
