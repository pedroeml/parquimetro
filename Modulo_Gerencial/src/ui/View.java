package ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class View extends JFrame implements Observer {
	
	private JPanel contentPane;
	private JButton btnImportarLog;
	
	private javax.swing.filechooser.FileFilter filter;
	private JTextField txtFiltro;
	private JTextField txtNum_ID_Parq;
	private JButton btnRelatorioPesquisarParquimetro;
	private JButton btnBuscar;
	private JLabel lblAgruparRelatrioPor;
	private JButton btnAno;
	private JButton btnMes;
	private JButton buttonRelatorioTodosParquimetros;
	private JButton btnFiltrar;
	private JLabel lblFiltrarPorData;
	private JLabel lblIdDoParqumetro;
	private JLabel labelEndereco;
	private JLabel lblIDParquimetro;
	private JLabel lblEndereco;
	private JLabel lblNAOENCONTRADO;
	private JLabel lblENCONTRADO;
	private JTextField txtAnoPAgrupar;
	private JTextArea textArea;
	private JPanel panel;
	private Component currentComponent;
	
	public View() {
		filter = new javax.swing.filechooser.FileFilter() {
			
			@Override
			public boolean accept(File arg0) {
				if (arg0.getName().endsWith(".csv"))
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				return "Arquivo texto CSV formatado com separador ';'";
			}
			
		};
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		super.setTitle("Módulo Gerencial");
		
		btnImportarLog = new JButton("Importar log");
		btnImportarLog.setBackground(new Color(204, 204, 204));
		btnImportarLog.setBounds(10, 11, 123, 23);
		contentPane.add(btnImportarLog);
		
		buttonRelatorioTodosParquimetros = new JButton("Todos Parquímetros");
		buttonRelatorioTodosParquimetros.setBackground(new Color(204, 204, 204));
		buttonRelatorioTodosParquimetros.setBounds(275, 11, 158, 23);
		contentPane.add(buttonRelatorioTodosParquimetros);
		
		txtFiltro = new JTextField();
		txtFiltro.setText("DD/MM/YYYY ou MM/YYYY");
		txtFiltro.setBounds(117, 55, 158, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		lblFiltrarPorData = new JLabel("Filtrar por data:");
		lblFiltrarPorData.setBounds(10, 58, 97, 14);
		contentPane.add(lblFiltrarPorData);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setForeground(new Color(255, 255, 255));
		btnFiltrar.setBackground(new Color(0, 102, 255));
		btnFiltrar.setBounds(285, 54, 73, 23);
		contentPane.add(btnFiltrar);
		
		btnMes = new JButton("Mês");
		btnMes.setBackground(new Color(204, 204, 204));
		btnMes.setBounds(174, 150, 62, 23);
		contentPane.add(btnMes);
		
		btnAno = new JButton("Ano");
		btnAno.setBackground(new Color(204, 204, 204));
		btnAno.setBounds(174, 120, 62, 23);
		contentPane.add(btnAno);
		
		lblAgruparRelatrioPor = new JLabel("Agrupar relatório por:");
		lblAgruparRelatrioPor.setBounds(10, 124, 123, 14);
		contentPane.add(lblAgruparRelatrioPor);
		
		txtNum_ID_Parq = new JTextField();
		txtNum_ID_Parq.setText("Nº ID do Parquímetro");
		txtNum_ID_Parq.setColumns(10);
		txtNum_ID_Parq.setBounds(10, 85, 123, 20);
		contentPane.add(txtNum_ID_Parq);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(new Color(0, 102, 255));
		btnBuscar.setBounds(143, 85, 80, 23);
		contentPane.add(btnBuscar);
		
		btnRelatorioPesquisarParquimetro = new JButton("Pesquisar Parquímetro");
		btnRelatorioPesquisarParquimetro.setBackground(new Color(204, 204, 204));
		btnRelatorioPesquisarParquimetro.setBounds(443, 11, 177, 23);
		contentPane.add(btnRelatorioPesquisarParquimetro);
		
		lblIdDoParqumetro = new JLabel("ID do parquímetro:");
		lblIdDoParqumetro.setBounds(10, 289, 105, 14);
		contentPane.add(lblIdDoParqumetro);
		
		labelEndereco = new JLabel("Endereço:");
		labelEndereco.setBounds(10, 314, 91, 14);
		contentPane.add(labelEndereco);
		
		lblIDParquimetro = new JLabel("");
		lblIDParquimetro.setBounds(143, 289, 80, 14);
		contentPane.add(lblIDParquimetro);
		
		lblEndereco = new JLabel("");
		lblEndereco.setBounds(143, 314, 100, 14);
		contentPane.add(lblEndereco);
		
		lblNAOENCONTRADO = new JLabel("PARQUÍMETRO NÃO ENCONTRADO!");
		lblNAOENCONTRADO.setForeground(Color.RED);
		lblNAOENCONTRADO.setBounds(16, 264, 227, 14);
		contentPane.add(lblNAOENCONTRADO);
		
		lblENCONTRADO = new JLabel("Parquímetro localizado com sucesso!");
		lblENCONTRADO.setBounds(16, 264, 227, 14);
		contentPane.add(lblENCONTRADO);
		
		txtAnoPAgrupar = new JTextField();
		txtAnoPAgrupar.setText("Ano p/ agrupar por mês");
		txtAnoPAgrupar.setBounds(10, 151, 154, 20);
		contentPane.add(txtAnoPAgrupar);
		txtAnoPAgrupar.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(new Color(240, 240, 240));
		textArea.setBounds(388, 55, 388, 273);
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(388, 55, 388, 273);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel = new JPanel();
		panel.setBounds(784, 57, 450, 271);
		contentPane.add(panel);
		
		this.currentComponent = null;
		
		this.show_and_hide_elements_for_loadLog();
	}
	
	public void associaController_btnImportarLog(ActionListener c) {
		btnImportarLog.addActionListener(c);
	}
	
	public void associaController_btnRelatorioTodosParquimetros(ActionListener c) {
		buttonRelatorioTodosParquimetros.addActionListener(c);
	}
	
	public void associaController_btnRelatorioPesquisarParquimetros(ActionListener c) {
		btnRelatorioPesquisarParquimetro.addActionListener(c);
	}
	
	public void associaController_btnAno(ActionListener c) {
		btnAno.addActionListener(c);
	}
	
	public void associaController_btnMes(ActionListener c) {
		btnMes.addActionListener(c);
	}
	
	public void associaController_btnBuscar(ActionListener c) {
		btnBuscar.addActionListener(c);
	}
	
	public void associaController_btnFiltrar(ActionListener c) {
		btnFiltrar.addActionListener(c);
	}
	
	public void show_and_hide_elements_for_loadLog() {
		this.show_buttonsRelatorios(false);
		this.lblFiltrarPorData.setVisible(false);
		this.txtFiltro.setVisible(false);
		this.btnFiltrar.setVisible(false);
		this.txtNum_ID_Parq.setVisible(false);
		this.btnBuscar.setVisible(false);
		this.btnAno.setVisible(false);
		this.btnMes.setVisible(false);
		this.lblAgruparRelatrioPor.setVisible(false);
		
		this.lblIdDoParqumetro.setVisible(false);
		this.labelEndereco.setVisible(false);
		this.lblIDParquimetro.setVisible(false);
		this.lblEndereco.setVisible(false);
		this.lblNAOENCONTRADO.setVisible(false);
		this.lblENCONTRADO.setVisible(false);
		this.txtAnoPAgrupar.setVisible(false);
	}
	
	public void show_and_hide_elements_for_RelatorioPesquisarParquimetro() {
		this.lblFiltrarPorData.setVisible(false);
		this.txtFiltro.setVisible(false);
		this.btnFiltrar.setVisible(false);
		this.txtNum_ID_Parq.setVisible(true);
		this.btnBuscar.setVisible(true);
		this.show_elements_for_AgruparRelatorio(false);
		
		this.lblIdDoParqumetro.setVisible(true);
		this.labelEndereco.setVisible(true);
		this.lblIDParquimetro.setVisible(true);
		this.lblEndereco.setVisible(true);
		this.lblNAOENCONTRADO.setVisible(false);
		this.lblENCONTRADO.setVisible(false);
		this.txtNum_ID_Parq.setText("Nº ID do Parquímetro");
	}
	
	public void show_elements_for_AgruparRelatorio(boolean b) {
		this.btnAno.setVisible(b);
		this.btnMes.setVisible(b);
		this.lblAgruparRelatrioPor.setVisible(b);
		this.txtAnoPAgrupar.setVisible(b);
	}
	
	public void show_and_hide_elements_for_RelatorioTodosParquimetros() {
		this.lblFiltrarPorData.setVisible(true);
		this.txtFiltro.setVisible(true);
		this.btnFiltrar.setVisible(true);
		this.txtNum_ID_Parq.setVisible(false);
		this.btnBuscar.setVisible(false);
		this.btnAno.setVisible(false);
		this.btnMes.setVisible(false);
		this.lblAgruparRelatrioPor.setVisible(false);
		this.txtAnoPAgrupar.setVisible(false);
		
		this.lblIdDoParqumetro.setVisible(false);
		this.labelEndereco.setVisible(false);
		this.lblIDParquimetro.setVisible(false);
		this.lblEndereco.setVisible(false);
		this.lblNAOENCONTRADO.setVisible(false);
		this.lblENCONTRADO.setVisible(false);
	}
	
	public void show_buttonsRelatorios(boolean b) {
		this.btnRelatorioPesquisarParquimetro.setVisible(b);
		this.buttonRelatorioTodosParquimetros.setVisible(b);
	}
	
	public void isParquimetroEncontrado(boolean b) {
		this.lblNAOENCONTRADO.setVisible(!b);
		this.lblENCONTRADO.setVisible(b);
		this.txtAnoPAgrupar.setText("Ano p/ agrupar por mês");
	}
	
	public File fileChooserDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Abra um arquivo texto de log");
		fileChooser.showOpenDialog(this);
		return fileChooser.getSelectedFile();
	}
	
	public String getText_ID_Parq() {
		return this.txtNum_ID_Parq.getText();
	}
	
	public String getText_txtNum_do_ano() {
		return this.txtAnoPAgrupar.getText();
	}
	
	public String getText_txtFiltro() {
		return this.txtFiltro.getText();
	}
	
	public void setTextof_txtFiltro_toDefault() {
		this.txtFiltro.setText("DD/MM/YYYY ou MM/YYYY");
	}
	
	public String getText_lblIDParquimetro() {
		return this.lblIDParquimetro.getText();
	}
	
	public void setText_lblIDParquimetro(String text) {
		this.lblIDParquimetro.setText(text);
	}
	
	public String getText_lblEndereco() {
		return this.lblEndereco.getText();
	}
	
	public void setText_lblEndereco(String text) {
		this.lblEndereco.setText(text);
	}
	
	public void setText_textArea(String text) {
		this.textArea.setText(text);
	}
	
	public int panel_getX() {
		return this.panel.getX();
	}
	
	public int panel_getY() {
		return this.panel.getY();
	}
	
	public int panel_getWidth() {
		return this.panel.getWidth();
	}
	
	public int panel_getHeight() {
		return this.panel.getHeight();
	}
	
	public void panel_add(Component comp) {
		comp.setVisible(true);
		this.panel.add(comp);
		this.panel.setVisible(true);
		this.currentComponent = comp;
	}
	
	public void panel_removeCurrentComponent() {
		if (this.currentComponent != null) {
			this.currentComponent.setVisible(false);
			this.panel.remove(this.currentComponent);
			this.panel.setVisible(false);
			this.currentComponent = null;
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Model model = (Model) arg0;
		this.lblEndereco.setText(model.getEndereco());
		this.lblIDParquimetro.setText(model.getId_parquimetro());
	}
}
