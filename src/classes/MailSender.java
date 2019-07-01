package classes;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* classe permettant d'envoyer un mail
* @author TIC1.3
*
*/
public class MailSender {
	/**
	 * L'adresse de l'expéditeur
	 */
	private String from = "banktic1.grp3@gmail.com";
	/**
	 * Mot de passe de l'expediteur
	 */
	private final String password = "B@nktic13";
	/**
	 * L'adresse du destinataire
	 */
	private String to;
	/**
	 * L'objet du mail
	 */
	private String objet;
	/**
	 * Le corps du mail
	 */
	private String text;
	
	/**
	 * Definit le contenu du mail
	 * @param to adresse du destinataire du mail
	 * @param objet objet du mail
	 * @param text corps du mail
	 */
	public MailSender(String to, String objet, String text) {
		this.to = to; this.objet = objet; this.text = text;
	}
	
	/**
	 * Envoie le mail
	 * @return booléen true si envoyé false sinon
	 */
	public boolean send() {
		boolean rt = false;
	
		Properties props = new Properties();
	    
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, password);
	                }
	            });

		/* Création du message*/
		MimeMessage msg = new MimeMessage(session);

		try {
		      msg.setFrom(new InternetAddress(from));
		      msg.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(to, false));
		      msg.setSubject(objet);
		      msg.setText(text, "UTF-8", "html");
		      msg.setHeader("X-Mailer", "LOTONtechEmail");
		      Transport.send(msg);
		      rt = true;
		} catch (AddressException e1) {
		      e1.printStackTrace();
		} catch (MessagingException e2) {
		      e2.printStackTrace();
		}
		return rt;
	}
	
	public static void main(String[] args) {
		String contenu = "Ceci est un texte maaaaaaan.\n"
				+"Ligne 1.\n"+"Ligne 2."
						+ "<a href=\"http://google.fr\">Google</a>";
		MailSender m = new MailSender("sediasiabelle@yahoo.fr", "Test", contenu);
		boolean b = m.send();
		if(b) System.out.println("Envoi reussi!!!");
		else System.out.println("Envoi échoué!!!");
	}

}
