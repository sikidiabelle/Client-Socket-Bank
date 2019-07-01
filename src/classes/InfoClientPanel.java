package classes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* Panel contenant les informations d'un client
* @author TIC1.3
*
*/
public class InfoClientPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	/*
	 * label contenant le nom
	 */
	public JLabel nom = new JLabel();
	/*
	 * label contenant le prénom
	 */
	public JLabel prenom = new JLabel();
	/*
	 * label contenant l'adresse
	 */
	public JLabel adresse = new JLabel();
	/*
	 * label contenant la nationalité
	 */
	public JLabel nat = new JLabel();
	/*
	 * label contenant le numéro de téléphone
	 */
	public JLabel tel = new JLabel();
	/*
	 * label contenant la date
	 */
	public JLabel date = new JLabel();
	/*
	 * label contenant le pays de résidence
	 */
	public JLabel pays = new JLabel();
	/*
	 * label contenant le mail 
	 */
	public JLabel mail = new JLabel();
	GridBagConstraints g = new GridBagConstraints();
	
	/**
	 * Constructeur d'un panel contenant les infos d'un client
	 *  @param c client 
	 */
	public InfoClientPanel(Client c) {
		this.setLayout(new GridBagLayout());
		if(c.getCiv()=='H')
			nom.setText("Nom : Monsieur "+c.getNom());
		else
			nom.setText("Nom : Madame"+c.getNom());
		prenom.setText("Prénom : "+ c.getPrenom());
		adresse.setText("Adresse : "+ c.getAdr());
		nat.setText("Nationalité : "+ c.getNat());
		tel.setText("Téléphone : "+ c.getTel());
		date.setText("Date de naissance : "+c.getBday());
		pays.setText("Ville de résidence : " +c.getCity()+", "+c.getCP()+", "+c.getPays());
		mail.setText("Adresse électronique : " +c.getEmail());

		g.gridy = 0 ;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(nom,g);
		g.gridy = 1;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(prenom,g);
		g.gridy = 2 ;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(date,g);
		g.gridy = 3;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(mail,g);
		g.gridy = 4 ;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(mail,g);
		g.gridy = 5;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(adresse,g);
		g.gridy = 6 ;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(pays ,g);
		g.gridy = 7;
		g.fill = GridBagConstraints.BOTH;
		g.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(nat,g);
	}

}
