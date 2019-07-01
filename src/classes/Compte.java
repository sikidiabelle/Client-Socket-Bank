package classes;

import java.io.Serializable;

/**
 * classe representant un compte de la banque
* @author TIC1.3
 *
 */

public class Compte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8242518526610437715L;
	/**
	 * Numero du compte
	 */
	private String numCompte;
	/*
	 * Rib du Compte
	 */
	private String rib;

	/*
	 * iban du compte
	 */
	private String iban;
	/*
	 * bic
	 */
	private String bic;
	/*
	 * Type du compte soit épargne ou courant ou titre
	 */
	private int ClientID = 0;
	
	/**
	 * Constructeur d'un compte pour un client précis 
	 * @param numCompte numéro du compte
	 * @param rib rib du compte
	 * @param iban iban du compte 
	 * @param ClientID identifiant du client
	 */
	public Compte(String numCompte, String rib, String iban,
			 int ClientID) {
		
		this.numCompte=numCompte; this.rib=rib;
		this.iban=iban; this.ClientID=ClientID;
	}
	
	/**
	 * Constructeur nouveau compte 
	 * @param iban iban du compte 
	 * @param rib rib du compte
	 * @param ClientID identifiant du client
	 */
	public Compte(String rib, String iban,
			 int ClientID) {
		
		this.rib=rib;
		this.iban=iban; this.ClientID=ClientID;
	}
	
	/**
	 * Renvoie le numéro de compte 
	 * @return retourne le numéro de compte dans la BDD
	 */
	public String getNumCompte() {return numCompte;}
	/**
	 * Renvoie le code postal du client 
	 * @return retourne l'id du client dans la BDD
	 */
	public String getRib() {return rib;}
	/**
	 * Renvoie l'IBAN du compte
	 * @return retourne l'IBAN du compte
	 */
	public String getIban() {return iban;}
	/**
	 * Renvoie le BIC du compte 
	 * @return retourne le BIC du compte
	 */
	public String getBic() {return bic;}
	/**
	 * Renvoie l'id du client possédant le compte
	 * @return retourne l'id du client associé au compte dans la BDD
	 */
	public int getClientID() {return ClientID;}
}
