import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import frame.Connexion;
import socket.ClientSocketApplication;

public class Run {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(new NimbusLookAndFeel());		
		ClientSocketApplication s = new ClientSocketApplication();
		try {
			new Connexion(s);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
