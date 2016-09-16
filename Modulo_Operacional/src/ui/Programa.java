package ui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Programa {
	
	private static void criarGUI() {
		Model m = new Model();
		m.validade_do_ticket();
		m.valor_total_a_ser_pago();
		m.decrementa_tempo_de_permanencia();
		View v = new View(m.getValidade_do_ticket(), m.getValor_total_a_ser_pago());
		new Controller(m, v);
		v.setVisible(true);
		
	}
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					criarGUI();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
