package classes;

import java.io.Serializable;

/**
* classe représentant une transaction
* @author TIC1.3
*
*/
public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2756326038178020546L;
	
	/**
	 * id de la transaction dans la BDD
	 */
	private int idTrans;
	/**
	 * Description de la transaction (dépot, retrait, virement, achat en ligne...)
	 */
	private String descp;
	/**
	 * numero du compte source
	 */
	private String numCpt_src;
	/**
	 * iban du compte cible
	 */
	private String iban_cible;
	/**
	 * montant de la transaction
	 */
	private double montant;
	/**
	 * type d'operation (false pour un debit et true pour un credit)
	 */
	private boolean type;
	/**
	 * date et heure de l'opération
	 */
	private java.sql.Timestamp date = null;
	
	public Transaction(int idTrans, String descp, String numCpt_src, String iban_cible,
			double montant, boolean type, java.sql.Timestamp date) {
		this.idTrans = idTrans; this.numCpt_src = numCpt_src; this.iban_cible = iban_cible;
		this.montant = montant; this.type = type; this.descp = descp; this.date = date; 
	}
	
	public Transaction(String descp, String numCpt_src, String iban_cible,
			double montant, boolean type) {
		this.numCpt_src = numCpt_src; this.iban_cible = iban_cible;
		this.montant = montant; this.type = type; this.descp = descp;
	}
	
	/**
	 * Renvoie l'id de la transaction 
	 * @return retourne l'id de la transaction 
	 */
	public int getIdTrans() { return idTrans; }
	/**
	 * Renvoie la description de la transaction
	 * @return retourne la description de la transaction 
	 */
	public String getDescp() { return descp; }
	/**
	 * Renvoie le numéro de compte source
	 * @return retourne le numéro de compte source 
	 */
	public String getNumCpt_src() { return numCpt_src; }/**
	 * Renvoie l'iban du compte destinataire 
	 * @return retourne l'iban du compte destinataire 
	 */
	
	public String getIban_cible() { return iban_cible; }
	/**
	 * Renvoie le montant de la transaction 
	 * @return retourne le montant de la transaction 
	 */
	public double getMontant() { return montant; }
	/**
	 * Renvoie le type de transaction 
	 * @return retourne le type de transaction 
	 */
	public boolean getType() { return type; }
	/**
	 * Renvoie la date de la transaction 
	 * @return retourne la date de la transaction 
	 */
	public java.sql.Timestamp getDate() { return date; }

}
