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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import classes.ImagePanel;
import classes.MailSender;
import classes.ModelMessage;
import socket.ClientSocketApplication;

/**
* classe representant un client de la banque 
* @author TIC1.3
*
*/
public class ListeMessage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pan = new JPanel();
	JSplitPane centre = new JSplitPane();
	JPanel gauche = new JPanel();
	JPanel droite = new JPanel();
	private JPanel entete = new JPanel();
	private JPanel bas = new JPanel();
	private JButton boutonSelectionner = new JButton("Selectionner");
	private JButton boutonRetour = new JButton("Retour");
	private JTable table;
	public ModelMessage model;
	private JScrollPane jscroll;
	Icon logo1 = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public JLabel logo = new JLabel(logo1);
	public int idConseille;
	public JLabel home;
	private ImagePanel fond;
	private Image fond1;
	private GridBagConstraints g = new GridBagConstraints();
	private JTextArea objet = new JTextArea();
	private JTextArea message = new JTextArea();
	private JTextArea reponse = new JTextArea();;
	private JButton envoyer = new JButton("Répondre");
	private int selectedRow;
	private int id;
	private String objet1;
	private String mail1;
	public JPanel enteteD = new JPanel();
	public JTextArea user = new JTextArea();
	private ClientSocketApplication s;
	
	/**
	 * Fenêtre pour le traitement des messages
	 * @param idConseiller id du conseiller
	 * @param ss socket associé à la session du  client
	 * @throws IOException exception socket
	 */
	public ListeMessage(int idConseiller, ClientSocketApplication ss) throws IOException
	{
		super("Boite à messages - BankTic");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		this.setMinimumSize(new Dimension (800,500));
		this.s = ss;
		fond1 = ImageIO.read(new File("fondClient.jpg"));
		fond = new ImagePanel(fond1);
		this.setContentPane(fond);
		
		pan = (JPanel) this.getContentPane();
		
		idConseille = idConseiller;
		
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
		
		model = new ModelMessage(s);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model= table.getSelectionModel();
		droite.setLayout(new GridBagLayout());
		model.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if(! model.isSelectionEmpty()) {
					selectedRow = model.getMinSelectionIndex();
					objet.setOpaque(false);
					message.setOpaque(false);
					id = Integer.valueOf((String) table.getValueAt(selectedRow, 0));
					objet1 =(String) table.getValueAt(selectedRow, 1);
					mail1 = (String) table.getValueAt(selectedRow, 2);
					objet.setText("Objet : "+objet1+" \n Expéditeur: <"+ mail1+ ">");
					objet.setEditable(false);
					message.setLineWrap(true);
					message.setWrapStyleWord(true);
					message.setEditable(false);
					message.setText((String) table.getValueAt(selectedRow, 3));
					reponse.setLineWrap(true);
					reponse.setWrapStyleWord(true);
					g.gridy=0;
					g.weightx = 0.8;
					g.weighty = 0.05;
					g.fill = GridBagConstraints.BOTH;
					droite.add(objet,g);
					g.gridy=1;
					g.weighty = 0.3;
					g.fill = GridBagConstraints.BOTH;
					droite.add(message,g);
					g.gridy=2;
					g.weighty = 0.5;
					g.fill = GridBagConstraints.BOTH;
					droite.add(reponse,g);
					g.gridy = 3;
					g.fill = GridBagConstraints.NONE;
					g.weightx = 0.2;
					g.anchor = GridBagConstraints.EAST;
					g.weighty=0.05;
					droite.add(envoyer,g);
				}
			}
			
			
			}
		);
		
		table.setFillsViewportHeight(true);
		table.getModel().addTableModelListener(table);
		jscroll = new JScrollPane(table);		
		jscroll.setOpaque(false);
		
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
		boutonSelectionner.addActionListener(this);
		envoyer.addActionListener(this);

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
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() ==  envoyer) {
			s.deleteMessage(id);
			MailSender sender = new MailSender(mail1, "[Réponse] "+objet1, reponse.getText() + "\n \n \t");
			sender.send();
			model = new ModelMessage(s);
			table.setModel(model);
			((ModelMessage)table.getModel()).fireTableDataChanged();
			
			droite = new JPanel();
			droite.setLayout(new GridBagLayout());
			droite.setOpaque(false);
			centre.setRightComponent(droite);
			
		}
	}
}
