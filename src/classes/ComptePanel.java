package classes;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import socket.ClientSocketApplication;

/**
 * Panel contenant les infos d'un compte
* @author TIC1.3
 *
 */

public class ComptePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * panel info des comptes
	 */
	public JPanel infoCompte = new JPanel();
	/*
	 * table pour la liste des transactions
	 */
	public JTable table = new JTable();
	/*
	 * vecteur de données dans la table des transactions
	 */
	public String data[][];
	/*
	 * entête de la table des transactions
	 */
    private final String[] entetes = {"Date", "Description ", "Emetteur","Destinataire", "Montant"};
    /*
     * panel contenant le type de compte
     */
	public JPanel ligne = new JPanel();
	/*
	 * panel contenant le rib du compte
	 */
	public JPanel codePanel = new JPanel();
	/*
	 * jscrollpane contenant la table des transactions
	 */
	public JScrollPane transacScroll;
	/*
	 * label type de compte
	 */
	public JLabel typeLabel = new JLabel();
	/*
	 * label solde du compte
	 */
	public JLabel soldeLabel = new JLabel();
	/*
	 * label rib du compte
	 */
	public JLabel code = new JLabel();
	/*
	 * chaine de caractères contenant l'iabn du compte
	 */
	public String iban;
	/*
	 * solde du compte
	 */
	public double solde ;
	/*
	 * chaîne de caractère contenant le type du compte
	 */
	public String type = null;
	/*
	 * 
	 */
	public GridBagConstraints g = new GridBagConstraints();
	/*
	 * contrainte du gridBagLayout
	 */

	/**
	 * Constructeur d'un panel pour un compte précis 
	 * @param numCompte numéro du compte
	 * @param s socket client associé
	 */
	public ComptePanel(String numCompte, ClientSocketApplication s){
		int i = 0;
		int a = s.getTransactions(numCompte).size();
		data = new String[a][5];
		Compte c = s.getInfoCompte(numCompte);
		int typeCompte = s.getTypeCompte(numCompte);
		this.setLayout(new BorderLayout());
		infoCompte.setLayout(new BorderLayout());
		ligne.setLayout(new BorderLayout());
		codePanel.setLayout(new BorderLayout());

		
		iban = c.getIban();
		solde = s.getSolde(numCompte);
		soldeLabel.setText("Solde : " + solde);
		code.setText("    IBAN : " + iban + "  clé RIB" + c.getRib() );
		

		switch(typeCompte)
		{
			case  0: ///Compte épargne
			{
				type = "Compte épargne";
				break;
			}
			case 1: ///Compte courant
			{
				type= "Compte courant";
				break;
			}
			case 2: ///Compte titre
			{
				type = "Compte titre";
				break;
			}
		}
		
		typeLabel.setText("    " + type + " N° " + numCompte);
		for(Transaction trans : s.getTransactions(numCompte)) {
			data[i][0] =  String.valueOf(trans.getDate());
			data[i][1] =  trans.getDescp();
			data[i][2] = trans.getNumCpt_src();
			data[i][3] = trans.getIban_cible();
			data[i][4] = String.valueOf(trans.getMontant());
			i++;
		}
		
		
		
		ligne.add(typeLabel, BorderLayout.WEST);
		ligne.add(soldeLabel, BorderLayout.EAST);
		codePanel.add(code, BorderLayout.WEST);
		infoCompte.add(ligne, BorderLayout.NORTH);
		infoCompte.add(codePanel);
		
		table = new JTable(data, entetes);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transacScroll = new JScrollPane(table);
		
		
		this.add(infoCompte, BorderLayout.NORTH);
		this.add(transacScroll, BorderLayout.CENTER);
	}
}

