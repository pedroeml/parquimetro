package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class View extends JFrame implements Observer {
	
	private JPanel contentPane;
	private JLabel lblHorario;
	private JLabel label_permanencia;
	private JLabel lblSaldo;
	private JLabel labelValidadeTicket;
	private JLabel lblValorSerPago;
	private JTextPane textPane_visor;
	private JTextField txt_num_do_cartao_recarregavel;
	private JButton button_plus;
	private JButton button_minus;
	private JButton btnConfirm_Moedas;
	private JButton btnCancel_Moedas;
	private JLabel lblSaldoDisponivel;
	private JLabel lblHorarioDeValidade;
	private JLabel lblValorTotalASerPago;
	private JButton button_1c;
	private JButton button_5c;
	private JButton button_10c;
	private JButton button_25c;
	private JButton button_50c;
	private JButton button_1rs;
	private JLabel lblOsBotesAbaixo;
	private JLabel lblTempoDePermanncia;
	private JButton btnOk;
	private JLabel lblDefinaOTempo;
	private JLabel lblMin;
	private JButton btnMoedas;
	private JButton btnCartao;
	private JButton btnCancel_Cartao;
	private JButton btnConfirm_Cartao;
	private JLabel lblSelecioneOMtodo;
	
	public View(String validade_do_ticket, String valor_total_a_ser_pago) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		super.setTitle("Módulo Operacional");
		
		lblHorario = new JLabel("00/00/0000 00:00:00");
		lblHorario.setForeground(Color.GRAY);
		lblHorario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHorario.setBounds(10, 11, 156, 17);
		contentPane.add(lblHorario);
		
		lblSaldoDisponivel = new JLabel("Saldo disponível:");
		lblSaldoDisponivel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldoDisponivel.setBounds(451, 11, 94, 14);
		contentPane.add(lblSaldoDisponivel);
		
		lblSaldo = new JLabel("R$ 0,00");
		lblSaldo.setBounds(606, 14, 56, 14);
		contentPane.add(lblSaldo);
		
		lblHorarioDeValidade = new JLabel("Validade do ticket: ");
		lblHorarioDeValidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorarioDeValidade.setBounds(37, 178, 107, 14);
		contentPane.add(lblHorarioDeValidade);
		
		labelValidadeTicket = new JLabel(validade_do_ticket);
		labelValidadeTicket.setBounds(186, 178, 107, 14);
		contentPane.add(labelValidadeTicket);
		
		lblValorTotalASerPago = new JLabel("Valor total a ser pago:");
		lblValorTotalASerPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorTotalASerPago.setBounds(451, 36, 124, 14);
		contentPane.add(lblValorTotalASerPago);
		
		lblValorSerPago = new JLabel(valor_total_a_ser_pago);
		lblValorSerPago.setBounds(606, 36, 56, 14);
		contentPane.add(lblValorSerPago);
		
		textPane_visor = new JTextPane();
		textPane_visor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPane_visor.setBackground(SystemColor.control);
		textPane_visor.setEditable(false);
		textPane_visor.setBounds(451, 74, 333, 198);
		contentPane.add(textPane_visor);
				
		lblOsBotesAbaixo = new JLabel("Os botões abaixo são apenas para inserção de moedas");
		lblOsBotesAbaixo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOsBotesAbaixo.setBounds(10, 258, 309, 14);
		contentPane.add(lblOsBotesAbaixo);
		
		txt_num_do_cartao_recarregavel = new JTextField();
		txt_num_do_cartao_recarregavel.setText("Insira o nº do cartão recarregável para comprar o ticket");
		txt_num_do_cartao_recarregavel.setBounds(10, 46, 333, 20);
		contentPane.add(txt_num_do_cartao_recarregavel);
		txt_num_do_cartao_recarregavel.setColumns(10);
		
		lblTempoDePermanncia = new JLabel("Tempo de permanência:");
		lblTempoDePermanncia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTempoDePermanncia.setBounds(37, 157, 136, 14);
		contentPane.add(lblTempoDePermanncia);
		
		label_permanencia = new JLabel("30");
		label_permanencia.setBounds(186, 157, 46, 14);
		contentPane.add(label_permanencia);
				
		lblDefinaOTempo = new JLabel("Defina o tempo de permanência (30 - 120 min)");
		lblDefinaOTempo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDefinaOTempo.setBounds(10, 86, 263, 14);
		contentPane.add(lblDefinaOTempo);
		
		lblMin = new JLabel("min");
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMin.setBounds(216, 157, 21, 14);
		contentPane.add(lblMin);
				
		lblSelecioneOMtodo = new JLabel("Selecione o método de pagamento");
		lblSelecioneOMtodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelecioneOMtodo.setBounds(379, 293, 196, 14);
		contentPane.add(lblSelecioneOMtodo);
		
		button_plus = new JButton("+");
		button_plus.setBackground(new Color(204, 204, 204));
		//button_plus.setAction(action_plus);
		button_plus.setBounds(82, 111, 45, 35);
		contentPane.add(button_plus);
		
		button_minus = new JButton("-");
		button_minus.setBackground(new Color(204, 204, 204));
		//button_minus.setAction(action_minus);
		button_minus.setBounds(27, 111, 45, 35);
		contentPane.add(button_minus);
		
		btnConfirm_Moedas = new JButton("Confirmar");
		//btnConfirm_Moedas.setAction(action_Confirm_Moedas);
		btnConfirm_Moedas.setBackground(Color.GREEN);
		btnConfirm_Moedas.setBounds(122, 201, 95, 35);
		contentPane.add(btnConfirm_Moedas);
		
		btnCancel_Moedas = new JButton("Cancelar");
		//btnCancel_Moedas.setAction(action_Cancel_Moedas);
		btnCancel_Moedas.setBackground(Color.RED);
		btnCancel_Moedas.setBounds(27, 201, 85, 35);
		contentPane.add(btnCancel_Moedas);
		
		button_1c = new JButton("1 ¢");
		button_1c.setBackground(new Color(204, 204, 204));
		//button_1c.setAction(action_1c);
		button_1c.setBounds(10, 283, 60, 35);
		contentPane.add(button_1c);
		
		button_5c = new JButton("5 ¢");
		button_5c.setBackground(new Color(204, 204, 204));
		//button_5c.setAction(action_5c);
		button_5c.setBounds(80, 283, 60, 35);
		contentPane.add(button_5c);
		
		button_10c = new JButton("10 ¢");
		button_10c.setBackground(new Color(204, 204, 204));
		//button_10c.setAction(action_10c);
		button_10c.setBounds(149, 283, 60, 35);
		contentPane.add(button_10c);
		
		button_25c = new JButton("25 ¢");
		button_25c.setBackground(new Color(204, 204, 204));
		//button_25c.setAction(action_25c);
		button_25c.setBounds(216, 283, 60, 35);
		contentPane.add(button_25c);
		
		button_50c = new JButton("50 ¢");
		button_50c.setBackground(new Color(204, 204, 204));
		//button_50c.setAction(action_50c);
		button_50c.setBounds(283, 283, 60, 35);
		contentPane.add(button_50c);
		
		button_1rs = new JButton("R$ 1");
		button_1rs.setBackground(new Color(204, 204, 204));
		//button_1rs.setAction(action_1rs);
		button_1rs.setBounds(350, 283, 60, 35);
		contentPane.add(button_1rs);
		
		btnOk = new JButton("OK");
		//btnOk.setAction(action_OK);
		btnOk.setBackground(new Color(0, 102, 255));
		btnOk.setForeground(Color.WHITE);
		btnOk.setBounds(347, 40, 60, 35);
		contentPane.add(btnOk);
		
		btnMoedas = new JButton("Moedas");
		//btnMoedas.setAction(action_Moedas);
		btnMoedas.setBackground(new Color(204, 204, 204));
		btnMoedas.setBounds(604, 283, 80, 35);
		contentPane.add(btnMoedas);
		
		btnCartao = new JButton("Cartão");
		//btnCartao.setAction(action_Cartao);
		btnCartao.setBackground(new Color(204, 204, 204));
		btnCartao.setBounds(694, 283, 90, 35);
		contentPane.add(btnCartao);
		
		btnCancel_Cartao = new JButton("Cancelar");
		//btnCancel_Cartao.setAction(action_Cancel_Cartao);
		btnCancel_Cartao.setBackground(Color.RED);
		btnCancel_Cartao.setBounds(27, 201, 85, 35);
		contentPane.add(btnCancel_Cartao);
		
		btnConfirm_Cartao = new JButton("Confirmar");
		//btnConfirm_Cartao.setAction(action_Confirm_Cartao);
		btnConfirm_Cartao.setBackground(Color.GREEN);
		btnConfirm_Cartao.setBounds(122, 201, 95, 35);
		contentPane.add(btnConfirm_Cartao);
		
		this.txt_num_do_cartao_recarregavel.setVisible(false);
		this.btnCancel_Moedas.setVisible(false);
		this.btnConfirm_Moedas.setVisible(false);
		this.btnCancel_Cartao.setVisible(false);
		this.btnConfirm_Cartao.setVisible(false);
		this.btnOk.setVisible(false);
		this.button_10c.setVisible(false);
		this.button_1c.setVisible(false);
		this.button_1rs.setVisible(false);
		this.button_25c.setVisible(false);
		this.button_50c.setVisible(false);
		this.button_5c.setVisible(false);
		this.button_minus.setVisible(false);
		this.button_plus.setVisible(false);
		this.lblDefinaOTempo.setVisible(false);
		this.lblHorarioDeValidade.setVisible(false);
		this.lblMin.setVisible(false);
		this.lblOsBotesAbaixo.setVisible(false);
		this.lblSaldoDisponivel.setVisible(false);
		this.lblTempoDePermanncia.setVisible(false);
		this.lblValorTotalASerPago.setVisible(false);
		label_permanencia.setVisible(false);
		labelValidadeTicket.setVisible(false);
		lblSaldo.setVisible(false);
		lblValorSerPago.setVisible(false);
	}
	
	public void associaController_button_plus(ActionListener c) {
		button_plus.addActionListener(c);
	}
	
	public void associaController_button_minus(ActionListener c) {
		button_minus.addActionListener(c);
	}
	
	public void associaController_btnConfirm_Moedas(ActionListener c) {
		btnConfirm_Moedas.addActionListener(c);
	}
	
	public void associaController_btnCancel_Moedas(ActionListener c) {
		btnCancel_Moedas.addActionListener(c);
	}
	
	public void associaController_button_1c(ActionListener c) {
		button_1c.addActionListener(c);
	}
	
	public void associaController_button_5c(ActionListener c) {
		button_5c.addActionListener(c);
	}
	
	public void associaController_button_10c(ActionListener c) {
		button_10c.addActionListener(c);
	}
	
	public void associaController_button_25c(ActionListener c) {
		button_25c.addActionListener(c);
	}
	
	public void associaController_button_50c(ActionListener c) {
		button_50c.addActionListener(c);
	}
	
	public void associaController_button_1rs(ActionListener c) {
		button_1rs.addActionListener(c);
	}
	
	public void associaController_btnOk(ActionListener c) {
		btnOk.addActionListener(c);
	}
	
	public void associaController_btnMoedas(ActionListener c) {
		btnMoedas.addActionListener(c);
	}
	
	public void associaController_btnCartao(ActionListener c) {
		btnCartao.addActionListener(c);
	}
	
	public void associaController_btnCancel_Cartao(ActionListener c) {
		btnCancel_Cartao.addActionListener(c);
	}
	
	public void associaController_btnConfirm_Cartao(ActionListener c) {
		btnConfirm_Cartao.addActionListener(c);
	}
	
	public void setText_labelValidadeTicket(String text) {
		labelValidadeTicket.setText(text);
	}
	
	public void setText_lblValorSerPago(String text) {
		lblValorSerPago.setText(text);
	}
	
	public void setText_label_permanencia(String text) {
		label_permanencia.setText(text);
	}
	
	public void setText_textPane_visor(String text) {
		textPane_visor.setText(text);
	}
	
	public void setText_lblSaldo(String text) {
		lblSaldo.setText(text);
	}
	
	public void setText_txt_num_do_cartao_recarregavel(String text) {
		txt_num_do_cartao_recarregavel.setText(text);
	}
	
	public void setText_lblHorario(String text) {
		lblHorario.setText(text);
	}
	
	public String getText_lblSaldo() {
		return lblSaldo.getText();
	}
	
	public String getText_lblValorSerPago() {
		return lblValorSerPago.getText();
	}
	
	public String getText_txt_num_do_cartao_recarregavel() {
		return txt_num_do_cartao_recarregavel.getText();
	}
	
	public void setEditable_txt_num_do_cartao_recarregavel(boolean b) {
		txt_num_do_cartao_recarregavel.setEditable(b);
	}
	
	protected void show_and_hide_elements_for_Moedas() {
		txt_num_do_cartao_recarregavel.setVisible(false);
		btnCancel_Moedas.setVisible(true);
		btnConfirm_Moedas.setVisible(true);
		btnOk.setVisible(false);
		button_10c.setVisible(true);
		button_1c.setVisible(true);
		button_1rs.setVisible(true);
		button_25c.setVisible(true);
		button_50c.setVisible(true);
		button_5c.setVisible(true);
		button_minus.setVisible(true);
		button_plus.setVisible(true);
		lblDefinaOTempo.setVisible(true);
		lblHorarioDeValidade.setVisible(true);
		lblMin.setVisible(true);
		lblOsBotesAbaixo.setVisible(true);
		lblSaldoDisponivel.setVisible(true);
		lblTempoDePermanncia.setVisible(true);
		lblValorTotalASerPago.setVisible(true);
		label_permanencia.setVisible(true);
		labelValidadeTicket.setVisible(true);
		lblSaldo.setVisible(true);
		lblValorSerPago.setVisible(true);
		lblSelecioneOMtodo.setVisible(false);
	}
	
	protected void show_and_hide_elements_for_Cartao() {
		txt_num_do_cartao_recarregavel.setVisible(true);
		btnCancel_Moedas.setVisible(false);
		btnConfirm_Moedas.setVisible(false);
		btnOk.setVisible(true);
		button_10c.setVisible(false);
		button_1c.setVisible(false);
		button_1rs.setVisible(false);
		button_25c.setVisible(false);
		button_50c.setVisible(false);
		button_5c.setVisible(false);
		button_minus.setVisible(true);
		button_plus.setVisible(true);
		lblDefinaOTempo.setVisible(true);
		lblHorarioDeValidade.setVisible(true);
		lblMin.setVisible(true);
		lblOsBotesAbaixo.setVisible(false);
		lblSaldoDisponivel.setVisible(true);
		lblTempoDePermanncia.setVisible(true);
		lblValorTotalASerPago.setVisible(true);
		label_permanencia.setVisible(true);
		labelValidadeTicket.setVisible(true);
		lblSaldo.setVisible(true);
		lblValorSerPago.setVisible(true);
		lblSelecioneOMtodo.setVisible(false);
	}
	
	protected void toggle_btnCancel_and_Confirm_Cartao(boolean b) {
		btnCancel_Cartao.setVisible(b);
		btnConfirm_Cartao.setVisible(b);
	}
	
	protected void toggle_btnMoedas_and_Cartao(boolean b) {
		btnMoedas.setVisible(b);
		btnCartao.setVisible(b);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Model model = (Model) arg0;
		label_permanencia.setText(model.getTempo_de_permanencia());
		labelValidadeTicket.setText(model.getValidade_do_ticket());
		lblValorSerPago.setText(model.getValor_total_a_ser_pago());
		lblSaldo.setText(model.getSaldo());
	}

}
