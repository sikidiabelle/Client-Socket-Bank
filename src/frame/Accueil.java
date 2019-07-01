package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import classes.ImagePanel;
import socket.ClientSocketApplication;

/**
* classe representant un client de la banque 
* @author TIC1.3
*
*/
public class Accueil extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel container = new JPanel();
	public JPanel entete = new JPanel();
	public JPanel centre = new JPanel();
	public JPanel bas = new JPanel();
	public JPanel enteteD = new JPanel();
	public JLabel clients = new JLabel("Clients",new ImageIcon(new ImageIcon("client.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)), JLabel.CENTER);
	public JLabel messages = new JLabel("Boîte de réception",new ImageIcon(new ImageIcon("message.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)), JLabel.CENTER);
	Icon logo1 = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public JLabel logo;
	public JLabel deco;
	public JTextArea user = new JTextArea();
	public int idConseiller;
	GridBagConstraints g = new GridBagConstraints();
	private ImagePanel fond;
	private Image fond1;
	ClientSocketApplication s;
	
	/**
	 * Constructeur de la fenêtre d'accueil
	 * @param idConseiller id du conseiller
	 * @param ss socket associé à la session du  client
	 * @throws IOException exception socket
	 */
	public Accueil(int idConseiller, ClientSocketApplication ss) throws IOException
	{
		super("Espace Conseiller Front Office - BankTic ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setMinimumSize(new Dimension (500,500));
		this.idConseiller = idConseiller;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.s = ss;
		
		
		fond1 = ImageIO.read(new File("fondAccueil.jpg"));
		fond = new ImagePanel(fond1);
		this.setContentPane(fond);
		
		container = (JPanel) this.getContentPane();
		container.setLayout(new BorderLayout());
		
		entete.setOpaque(false);
		entete.setLayout(new BorderLayout());
		logo = new JLabel(logo1);
		entete.add(logo, BorderLayout.WEST);
		Font police = new Font("Times New Roman", Font.ITALIC, 28);
		enteteD.setForeground(Color.white);
		enteteD.setFont(police);
		user.setOpaque(false);
		enteteD.setOpaque(false);
		user.setText(s.getInfoGerant(idConseiller).getEmail()+"\n Conseiller");
		user.setEditable(false);
		enteteD.add(user);
		entete.add(enteteD, BorderLayout.EAST);
		
		
		centre.setOpaque(false);
		centre.setLayout(new GridBagLayout());
		g.gridx = 0;
		g.gridy = 0 ;
		clients.setVerticalTextPosition(JLabel.BOTTOM);
		clients.setHorizontalTextPosition(JLabel.CENTER);
		centre.add(clients,g);
		
		g.gridx = 2;
		messages.setVerticalTextPosition(JLabel.BOTTOM);
		messages.setHorizontalTextPosition(JLabel.CENTER);
		centre.add(messages,g);
		
		bas.setOpaque(false);
		bas.setLayout(new BorderLayout());
		deco = new JLabel("Déconnexion",new ImageIcon(new ImageIcon("deco.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)), JLabel.CENTER);
		deco.setVerticalTextPosition(JLabel.TOP);
		deco.setHorizontalTextPosition(JLabel.CENTER);
		bas.add(deco, BorderLayout.EAST);
		
		
		container.add(entete, BorderLayout.NORTH );
		container.add(centre, BorderLayout.CENTER);
		container.add(bas, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		deco.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				dispose();
				try {
					new Connexion(s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		clients.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				dispose();
				try {
					new ListeClients(idConseiller, s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		messages.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				dispose();
				try {
					new ListeMessage(idConseiller,s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
