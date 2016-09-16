package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {
	private Model model;
	private View view;
	
	private static class Now implements Runnable {
		private SimpleDateFormat sdf;
		private Date now;
		private View v;
		
		public Now(View v) {
			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			this.v = v;
		}
		
		@Override
		public void run() {
			while (true) {
				now = GregorianCalendar.getInstance().getTime();
				v.setText_lblHorario(sdf.format(now));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.model.addObserver(this.view);
		this.view.associaController_btnMoedas(new Action_Moedas());
		this.view.associaController_btnCartao(new Action_Cartao());
		this.view.associaController_btnCancel_Cartao(new Action_Cancel_Cartao());
		this.view.associaController_btnCancel_Moedas(new Action_Cancel_Moedas());
		this.view.associaController_btnConfirm_Cartao(new Action_Confirm_Cartao());
		this.view.associaController_btnConfirm_Moedas(new Action_Confirm_Moedas());
		this.view.associaController_btnOk(new Action_OK());
		this.view.associaController_button_10c(new Action_10c());
		this.view.associaController_button_1c(new Action_1c());
		this.view.associaController_button_1rs(new Action_1rs());
		this.view.associaController_button_25c(new Action_25c());
		this.view.associaController_button_50c(new Action_50c());
		this.view.associaController_button_5c(new Action_5c());
		this.view.associaController_button_minus(new Action_minus());
		this.view.associaController_button_plus(new Action_plus());
		
		Thread task_now = new Thread(new Now(this.view));
		task_now.start();
	}

	private class Action_plus implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.incrementa_tempo_de_permanencia();
			model.validade_do_ticket();
			model.valor_total_a_ser_pago();
		}	
	}
	
	private class Action_minus implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.decrementa_tempo_de_permanencia();
			model.validade_do_ticket();
			model.valor_total_a_ser_pago();	
		}
	}
	
	private class Action_Confirm_Moedas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String troco;
			try {
				troco = model.confirma_e_retorna_o_troco();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.setText_textPane_visor(String.format("Total:\t%s\nDinheiro:\t%s\nTroco:\t%s\n- - - - - - - - - -  TICKET - - - - - - - - - -\n%s", view.getText_lblValorSerPago(), view.getText_lblSaldo(), troco, model.imprime_ticket()));
			model.saldo_disponivel_repositorio_moedas();
			view.toggle_btnMoedas_and_Cartao(true);
		}
	}

	
	private class Action_Cancel_Moedas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String troco;
			try {
				troco = model.cancela_e_devolve_o_dinheiro();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.setText_textPane_visor(String.format("Dinheiro devolvido: %s", troco));
			model.saldo_disponivel_repositorio_moedas();
			view.toggle_btnMoedas_and_Cartao(true);
		}
	}
	
	private class Action_1c implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(1);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_5c implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(5);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}	
	}
	
	private class Action_10c implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(10);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_25c implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(25);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_50c implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(50);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_1rs implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.inserir_moeda(100);
				model.saldo_disponivel_repositorio_moedas();
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_Moedas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.show_and_hide_elements_for_Moedas();
		}	
	}
	
	private class Action_Cartao implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.show_and_hide_elements_for_Cartao();
		}
	}
	
	private class Action_OK implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.pesquisa_num_cartao(view.getText_txt_num_do_cartao_recarregavel());
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			view.setText_textPane_visor(String.format("Cartão identificado com sucesso!\nTitular:\t%s", model.titular_nome_cartao(view.getText_txt_num_do_cartao_recarregavel())));
			model.saldo_disponivel_cartao(view.getText_txt_num_do_cartao_recarregavel());
			view.toggle_btnCancel_and_Confirm_Cartao(true);
			view.setEditable_txt_num_do_cartao_recarregavel(false);
			view.toggle_btnMoedas_and_Cartao(false);
		}
	}
	
	private class Action_Cancel_Cartao implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.setText_txt_num_do_cartao_recarregavel("Insira o nº do cartão recarregável para comprar o ticket");
			view.setText_lblSaldo("R$ 0,00");
			view.setText_textPane_visor("");
			view.toggle_btnCancel_and_Confirm_Cartao(false);
			view.setEditable_txt_num_do_cartao_recarregavel(true);
			view.toggle_btnMoedas_and_Cartao(true);
		}
	}
	
	private class Action_Confirm_Cartao implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.confirmar_cartao(view.getText_txt_num_do_cartao_recarregavel());
			} catch (Exception e) {
				view.setText_textPane_visor(e.getMessage());
				return;
			}
			model.saldo_disponivel_cartao(view.getText_txt_num_do_cartao_recarregavel());
			view.setText_textPane_visor(String.format("Total:\t%s\n- - - - - - - - - -  TICKET - - - - - - - - - -\n%s", view.getText_lblValorSerPago(), model.imprime_ticket()));
			view.setText_txt_num_do_cartao_recarregavel("Insira o nº do cartão recarregável para comprar o ticket");
			view.setText_lblSaldo("R$ 0,00");
			view.toggle_btnCancel_and_Confirm_Cartao(false);
			view.setEditable_txt_num_do_cartao_recarregavel(true);
			view.toggle_btnMoedas_and_Cartao(true);
		}
	}
}
