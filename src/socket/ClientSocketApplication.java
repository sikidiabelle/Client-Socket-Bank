package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import classes.Client;
import classes.Compte;
import classes.Gerant;
import classes.Message;
import classes.Transaction;

public class ClientSocketApplication {
	/*
	 * Socket associé au client
	 */
	public Socket socket = null;
	/*
	 * numéro de port de communication
	 */
	private final static int port = 100;
	/*
	 * adresse 
	 */
	private final static String host = "localhost";
	/*
	 * flux d'entrée binaire
	 */
	private InputStream input = null;
	/*
	 * flux de sortie bianire
	 */
	private OutputStream output = null;
	/*
	 * Chaine de caractères dans le flux de sortie
	 */
	private	PrintWriter pw;
	/*
	 * Objet en flux d'entrée
	 */
	private ObjectInputStream ois;
	
	
	/*
	 * requête liste des clients
	 */
	public String listeClients = "LISTE_CLIENTS";
	/*
	 * requête liste des messages
	 */
	public String listeMessages = "LISTE_MESSAGES";
	/*
	 * requête get message
	 */
	public String getMessage = "GET_MESSAGE_ID = ";
	/*
	 * requête delete message
	 */
	public String deleteMessage = "DELETE_MESSAGE_ID = ";
	/*
	 * requête de vérification connexion
	 */
	public String checkLogin = "CHECK_LOGIN_(USER,PASS)";
	/*
	 * requête get liste des comptes
	 */
	public String getListeCompte = "GET_LISTE_COMPTE_ID = ";
	/*
	 * requête get infos du compte
	 */
	public String getInfoCompte = "GET_INFO_COMPTE = ";
	/*
	 * requête get historique des transactions
	 */
	public String historiqueTransaction = "HISTORIQUE_TRANSACTION";
	/*
	 * requête get transaction
	 */
	public String getTransactions = "GET_TRANSACTION_COMPTE = ";
	/*
	 * requête get solde d'un compte
	 */
	public String getSolde = "GET_SOLDE_COMPTE = ";
	/*
	 * requête get type de compte
	 */
	public String getTypeCompte = "GET_TYPE_COMPTE = ";
	/*
	 * requête get infos conseiller
	 */
	public String getInfoConseiller = "GET_INFO_CONSEILLER = ";
	/*
	 * requête get infos client
	 */
	public String getInfoClient = "GET_INFO_CLIENT = ";



	
	/**
	 * Constructeur du socket coté client
	 */
	
	public ClientSocketApplication() {
		try {
			socket = new Socket(host, port);
			System.out.println("Connexion établie avec le serveur");
			input = socket.getInputStream();
			output = socket.getOutputStream();
			pw = new PrintWriter(output, true);
			ois = new ObjectInputStream(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Récupère du serveur le type de transaction 
	 * @param nom mot à rechercher dans la table des clients
	 * @return retourne la liste des clients ayant ce mot clé
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Client> getListeClients(){
		ArrayList<Client> retour= null;
		pw.println(listeClients);
		System.out.println("Requête "+ listeClients + " envoyée");
		try {
			retour = (ArrayList<Client>) ois.readObject();
			System.out.println("Liste des clients reçus du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur les comptes d'un Client
	 * @param id Identifiant du client
	 * @return liste de ses comptes
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getListeCompte(int id) {
		ArrayList<String> retour = null;
		pw.println(getListeCompte+id);
		System.out.println("Requête "+ getListeCompte + id + " envoyée");
		try {
			retour =  (ArrayList<String>) ois.readObject();
			System.out.println("Liste des comptes reçu du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur les messages dans la boite de réception
	 * @return liste des messages
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Message> getListeMessages(){
		ArrayList<Message> retour = new ArrayList<Message>();
		pw.println(listeMessages);
		System.out.println("Requête "+ listeMessages + " envoyée");
		try {
			retour = (ArrayList<Message>) ois.readObject();
			System.out.println("Liste des clients reçus du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> getHistoriqueTransaction(){
		ArrayList<Transaction> retour = new ArrayList<Transaction>();
		pw.println(historiqueTransaction);
		System.out.println("Requête "+ historiqueTransaction + " envoyée");
		try {
			retour = (ArrayList<Transaction>) ois.readObject();
			System.out.println("L'historique des transactions reçus du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur toutes les transactions pour un compte donnée
	 * @param numCompte numero du compte source dont on recherche les transactions
	 * @return une liste d'objets transaction contenant toutes les infos des transaction du compte 
	 */
	@SuppressWarnings("unchecked")
	public  ArrayList<Transaction> getTransactions(String numCpt) {
		ArrayList<Transaction> retour = null;
		pw.println(getTransactions+numCpt);
		System.out.println("Requête "+ getTransactions + numCpt + " envoyée");
		try {
			retour = (ArrayList<Transaction>) ois.readObject();
			System.out.println("Transaction reçu du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur les infos du compte 
	 * @param numCpt le numéro du compte dont on requiert les infos
	 * @return un objet compte contenant toutes ses infos
	 */
	public Compte getInfoCompte(String numCpt){
		Compte retour = null;
		pw.println(getInfoCompte+numCpt);
		System.out.println("Requête "+ getInfoCompte + numCpt + " envoyée");
		try {
			retour = (Compte) ois.readObject();
			System.out.println("Les infos du compte reçus du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur un message
	 * @param id identifiant du message
	 * @return retourne le message correspondant à l'id
	 */
	public String getMessage(int id) {
		String retour = null;
		pw.println(getMessage+id);
		System.out.println("Requête "+ getMessage + id + " envoyée");
		try {
			retour = (String) ois.readObject();
			System.out.println("Message reçu du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Envoie au serveur une requête de suppression d'un message 
	 * @param id du message
	 */
	public int deleteMessage(int id) {
		int retour = 0;
		pw.println(deleteMessage+id);
		System.out.println("Requête "+ deleteMessage + id + " envoyée");
		try {
			retour = (int) ois.readObject();
			System.out.println("Retour sur suppression du message");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Envoie une requête de vérification de l'email et du mot de passe d'un gerant
	 * @param email email à tester
	 * @param pwd mot de passe renseigné
	 * @return l'id du conseiller s'il est présent dans la bdd
	 * et 0 sinon
	 */
	public int checkLogin(String email, String pwd) {
		int retour = 0;
		pw.println(checkLogin + email + "," + pwd);
		System.out.println("Requête "+ checkLogin + " envoyée");
		pw.println(email);
		pw.println(pwd);
		try {
			retour = (int) ois.readObject();
			System.out.println("Vérification de connexion effectuée");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 *Récupère du serveur le solde d'un compte
	 * @param numero muméro du compte
	 * @return solde solde du compte
	 */
	public double getSolde(String numero) {
		double retour = 0;
		pw.println(getSolde + numero);
		System.out.println("Requête "+ getSolde + numero + " envoyée");
		try {
			retour = (double) ois.readObject();
			System.out.println("Solde du compte retourné");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur le type de compte
	 * @param num numéro du compte
	 * @return entier correspondant au type de compte 0 : épargne, 1 : courant, 2 : titre
	 */
	public int getTypeCompte(String numero) {
		int retour = 0;
		pw.println(getTypeCompte + numero);
		System.out.println("Requête "+ getTypeCompte + numero + " envoyée");
		try {
			retour = (int) ois.readObject();
			System.out.println("Type du compte retourné");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur les infos du gerant
	 * @param email l'email du gerant dont on requiert les infos
	 * @return un objet gerant contenant toutes ses infos
	 */
	public Gerant getInfoGerant(int id) {
		Gerant retour = null;
		pw.println(getInfoConseiller + id);
		System.out.println("Requête "+ getInfoConseiller + id + " envoyée");
		try {
			retour = (Gerant) ois.readObject();
			System.out.println("Infos conseiller retourné");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Récupère du serveur les infos d'un client
	 * @param id l'id du client dont on requiert les infos
	 * @return un objet client contenant toutes ses infos
	 */
	public Client getInfoClient(int id) {
		Client retour = null;
		pw.println(getInfoClient + id);
		System.out.println("Requête "+ getInfoClient + id + " envoyée");
		try {
			retour = (Client) ois.readObject();
			System.out.println("Infos client retourné");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	public static void main(String[] args) {
		ClientSocketApplication c = new ClientSocketApplication();
		System.out.println(c.getSolde("BDAEED424235"));
	  /*System.out.println(c.getListeMessages().get(0).getObjet());
		System.out.println(c.getMessage(18));
		System.out.println(c.deleteMessage(18));
		System.out.println(c.checkLogin("sediasiabelle@yahoo.fr", "123"));
		System.out.println(c.getListeCompte(1).get(0));
		System.out.println("L'iban du compte:" + (c.getInfoCompte("ADDAAE113551")).getIban() +" et le rib" + (c.getInfoCompte("ADDAAE113551")).getRib());
		System.out.println(c.getHistoriqueTransaction().get(0).getDescp());
		System.out.println(c.getInfoClient(1));*/
		
		
	}

	

}
