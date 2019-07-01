package classes;

import java.io.Serializable;

/**
 * classe representant un gérant de la banque (conseiller ou manager)
* @author TIC1.3
 *
 */
public class Gerant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6865453521137553706L;
	/**
	 * Id du gerant
	 */
	private int idGerant = 0;
	/**
	 * Email du client
	 */
	private String email = null;
	/**
	 * Mot de passe du client
	 */
	private String mdp;
	/**
	 * Role du client ("conseiller" ou "manager")
	 */
	private String status;
	
	/**
	 * Constructeur pour un gerant déjà présent dans la BDD
	 * @param idGerant id du gerant dans la BDD
	 * @param email email du gerant
	 * @param mdp mot de passe du gerant
	 * @param status le role du gerant
	 */
	public Gerant(int idGerant,String email, String mdp, String status) {
		this.idGerant=idGerant; this.email=email; this.mdp=mdp; this.status=status;
	}
	
	/**
	 * Constructeur pour un gerant déjà présent dans la BDD
	 * @param email email du gerant
	 * @param mdp mot de passe du gerant
	 * @param status le role du gerant
	 */
	public Gerant(String email, String mdp, String status) {
		
		this.email=email; this.mdp=mdp; this.status=status;
	}
	
	/**
	 * Renvoie l'id du conseiller 
	 * @return retourne l''id du conseiller
	 */
	public int getIdGerant() {return idGerant; }
	/**
	 * Renvoie l'email du conseiller 
	 * @return retourne l'email du conseiller
	 */
	public String getEmail() {return email; }
	/**
	 * Renvoie le mot de passe du conseiller 
	 * @return retourne le mot de passe du conseiller
	 */
	public String getMdp() { return mdp; }
	/**
	 * Renvoie le statut du gérant (conseiller ou manager)  
	 * @return retourne le statut du gérant (conseiller ou manager)
	 */
	public String getStatus() {return status; }
	
	/**
	 * modifie le mot de passe du conseiller
	 * @param mdp mot de passe 
	 */
	
	public void setMdp(String mdp) { this.mdp = mdp; }

}
