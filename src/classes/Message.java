package classes;

import java.io.Serializable;

/**
* classe représentant un mail
* @author TIC1.3
*
*/
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id du message dans la BDD
	 */
	private int id = 0;
	/**
	 * email de l'expediteur
	 */
	private String email;
	/**
	 * objet du message
	 */
	private String objet;
	/**
	 * contenu du message
	 */
	private String textMessage;
		
	/**
	 * Constructeur pour un message déja envoyé 
	 * @param id id du message
	 * @param email email de l'expediteur
	 * @param objet objet du message
	 * @param textMessage contenu du message
	 */
	public Message(int id, String email, String objet, String textMessage) {
		this.id= id; this.email=email; this.objet=objet; this.textMessage=textMessage; 
	}
	
	/**
	 * Renvoie l'id du message
	 * @return retourne l'id du message 
	 */
	public int getId() { return id; }
	/**
	 * Renvoie l'email expéditeur
	 * @return retourne l'email expéditeur 
	 */
	public String getEmail() { return email; }
	/**
	 * Renvoie l'objet du mail 
	 * @return retourne l'objet du mail 
	 */
	public String getObjet() { return objet; }
	/**
	 * Renvoie le corps du mail 
	 * @return retourne le corps du mail 
	 */
	public String getTextMessage() { return textMessage; }

}
