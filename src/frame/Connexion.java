package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import classes.ImagePanel;
import socket.ClientSocketApplication;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
* classe representant un client de la banque 
* @author TIC1.3
*
*/
public class Connexion extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JPasswordField passField = new JPasswordField(); 
	public JPanel enteteD = new JPanel();
	private JLabel passLabel = new JLabel("Mot de passe");
	private JTextField userField = new JTextField();;
	private JLabel userLabel = new JLabel("Identifiant");
	private JButton connectButton = new JButton("Se connecter");
	private ImagePanel fond;
	private JLabel logo = new JLabel();
	Image fond1;
	ImageIcon logo1 = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	ClientSocketApplication s;
	
	/**
	 * Constructeur de la fenêtre de connexion
	 * @param ss socket associé à la session du  client
	 * @throws IOException exception socket
	 */
	public Connexion(ClientSocketApplication ss) throws IOException
	{
		
		super("Connexion - BankTic");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.s = ss;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(  new Dimension(500,500));
		this.setLocationRelativeTo(null);
		
		fond1 = ImageIO.read(new File("fond.jpg"));
		fond = new ImagePanel(fond1);
	
		this.setContentPane(fond);
		container = (JPanel) this.getContentPane();
		container.setLayout(null);
		logo.setIcon(logo1);
		
		
		
		Font police = new Font("Times New Roman", Font.ITALIC, 16);
		userLabel.setForeground(Color.black);
		passLabel.setForeground(Color.black);
		userLabel.setFont(police);
		passLabel.setFont(police);
		
		logo.setBounds(200, 50, 100, 100);
		userLabel.setBounds(100,200, 100, 30);
		userField.setBounds(225,200, 175, 30);
		userField.setBackground(Color.white);
		passField.setBackground(Color.white);
		passLabel.setBounds(100,250, 100,30);
		passField.setBorder(new BevelBorder(BevelBorder.LOWERED));
		connectButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passField.setBounds(225,250, 175,30);
		connectButton.setBounds(275,300, 125,30);
		
		connectButton.setToolTipText("Cliquez ici pour vous connecter");
		
	
		container.add(logo);
		container.add(userLabel);
		container.add(userField);
		container.add(passLabel);
		container.add(passField);
		container.add(connectButton);
		connectButton.addActionListener(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String user = userField.getText();
		String mdp = String.valueOf(passField.getPassword());
		try {
			if(ae.getSource() ==  connectButton) {
				if (s.checkLogin(user,mdp)!= 0)
				{
					dispose();
					new Accueil(s.checkLogin(user, mdp), s);
				}
				else {
					JOptionPane.showMessageDialog(this,
							"Identifiants et/ou mot de passe incorrects", "Erreur de connexion",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this,
					"Veuillez contrôler vos saisies", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}	
	}
	
	
	public static void main(String[] args) throws Exception {
		
		UIManager.setLookAndFeel(new NimbusLookAndFeel());		
	}
}
