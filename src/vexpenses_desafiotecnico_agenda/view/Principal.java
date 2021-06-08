package vexpenses_desafiotecnico_agenda.view;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;



public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			SwingUtilities.invokeAndWait(JanelaGrafica::new);
			
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
