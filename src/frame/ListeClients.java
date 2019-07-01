package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import classes.ComptePanel;
import classes.ImagePanel;
import classes.ModelClient;
import classes.InfoClientPanel;
import socket.ClientSocketApplication;

/**
* classe representant un client de la banque 
* @author TIC1.3
*
*/
public class ListeClients extends JFrame implements ActionListener{
	
	/**
	 * Fenetre liste Personne
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel pan = new JPanel();
	public JPanel enteteD = new JPanel();
	JSplitPane centre = new JSplitPane();
	private JTabbedPane droite = new JTabbedPane();
	private JPanel info; 
	private JPanel gauche = new JPanel();
	private JPanel entete = new JPanel();
	private JPanel bas = new JPanel();
	private ComptePanel comptePanel;
	private JButton boutonRetour = new JButton("Retour");
	private JTable table;
	public ModelClient model;
	private JScrollPane jscroll;
	Icon logo1 = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public JLabel logo = new JLabel(logo1);
	public int idConseiller;
	private ImagePanel fond;
	private Image fond1;
	public JLabel home;
	private int id;
	public JTextArea user = new JTextArea();
	ClientSocketApplication s;
	
	/**
	 * Fenêtre pour le traitement des clients et des comptes associés
	 * @param idConseiller id du conseiller
	 * @param s socket associé à la session du  client
	 * @throws IOException exception socket
	 */
	public ListeClients(int idConseiller, ClientSocketApplication s) throws IOException { 
		super("Liste des clients - BankTic");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setMinimumSize(new Dimension (1000,500));
		this.s = s;
			
		fond1 = ImageIO.read(new File("fondClient.jpg"));
		fond = new ImagePanel(fond1);
		this.setContentPane(fond);
		
		pan = (JPanel) this.getContentPane();
		
		this.idConseiller = idConseiller;
		
		
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
		
		model = new ModelClient(s);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model= table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(! model.isSelectionEmpty()) {
					droite = new JTabbedPane();
					int selectedRow = model.getMinSelectionIndex();
					id = Integer.valueOf((String) table.getValueAt(selectedRow, 0));
					info = new InfoClientPanel(s.getInfoClient(id));
					droite.add(info, " Informations personnelles ");
					for(String num : s.getListeCompte(id)) {
						comptePanel = new ComptePanel(num,s); 
						droite.add(comptePanel, comptePanel.type);
					}
					droite.setOpaque(false);
					centre.setRightComponent(droite);
				}
			}
		 }
		);
		
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getModel().addTableModelListener(table);
		jscroll = new JScrollPane(table);
		
		gauche.setLayout(new GridLayout(2,1));
		
		centre.setOpaque(false);
		centre.setLeftComponent(jscroll);
		droite.setOpaque(false);
		centre.setRightComponent(droite);
		
		bas.setOpaque(false);
		bas.setLayout(new BorderLayout());
		
		home= new JLabel("Retourner à l'accueil",new ImageIcon(new ImageIcon("home.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)), JLabel.CENTER);
		home.setVerticalTextPosition(JLabel.TOP);
		home.setHorizontalTextPosition(JLabel.CENTER);
		bas.add(home, BorderLayout.EAST);
		
		
		pan.setLayout(new BorderLayout());
		
				
		
		pan.add(entete, BorderLayout.NORTH );
		pan.add(centre, BorderLayout.CENTER);
		pan.add(bas, BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boutonRetour.addActionListener(this);
		home.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				dispose();
				try {
					new Accueil(idConseiller,s);
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
