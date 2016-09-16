package ui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Programa {
	private static void criarGUI() {
		Model m = new Model();
		View v = new View();
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
