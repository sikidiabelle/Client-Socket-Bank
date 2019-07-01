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
	 * Socket associ� au client
	 */
	public Socket socket = null;
	/*
	 * num�ro de port de communication
	 */
	private final static int port = 100;
	/*
	 * adresse 
	 */
	private final static String host = "localhost";
	/*
	 * flux d'entr�e binaire
	 */
	private InputStream input = null;
	/*
	 * flux de sortie bianire
	 */
	private OutputStream output = null;
	/*
	 * Chaine de caract�res dans le flux de sortie
	 */
	private	PrintWriter pw;
	/*
	 * Objet en flux d'entr�e
	 */
	private ObjectInputStream ois;
	
	
	/*
	 * requ�te liste des clients
	 */
	public String listeClients = "LISTE_CLIENTS";
	/*
	 * requ�te liste des messages
	 */
	public String listeMessages = "LISTE_MESSAGES";
	/*
	 * requ�te get message
	 */
	public String getMessage = "GET_MESSAGE_ID = ";
	/*
	 * requ�te delete message
	 */
	public String deleteMessage = "DELETE_MESSAGE_ID = ";
	/*
	 * requ�te de v�rification connexion
	 */
	public String checkLogin = "CHECK_LOGIN_(USER,PASS)";
	/*
	 * requ�te get liste des comptes
	 */
	public String getListeCompte = "GET_LISTE_COMPTE_ID = ";
	/*
	 * requ�te get infos du compte
	 */
	public String getInfoCompte = "GET_INFO_COMPTE = ";
	/*
	 * requ�te get historique des transactions
	 */
	public String historiqueTransaction = "HISTORIQUE_TRANSACTION";
	/*
	 * requ�te get transaction
	 */
	public String getTransactions = "GET_TRANSACTION_COMPTE = ";
	/*
	 * requ�te get solde d'un compte
	 */
	public String getSolde = "GET_SOLDE_COMPTE = ";
	/*
	 * requ�te get type de compte
	 */
	public String getTypeCompte = "GET_TYPE_COMPTE = ";
	/*
	 * requ�te get infos conseiller
	 */
	public String getInfoConseiller = "GET_INFO_CONSEILLER = ";
	/*
	 * requ�te get infos client
	 */
	public String getInfoClient = "GET_INFO_CLIENT = ";



	
	/**
	 * Constructeur du socket cot� client
	 */
	
	public ClientSocketApplication() {
		try {
			socket = new Socket(host, port);
			System.out.println("Connexion �tablie avec le serveur");
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
	 * R�cup�re du serveur le type de transaction 
	 * @param nom mot � rechercher dans la table des clients
	 * @return retourne la liste des clients ayant ce mot cl�
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Client> getListeClients(){
		ArrayList<Client> retour= null;
		pw.println(listeClients);
		System.out.println("Requ�te "+ listeClients + " envoy�e");
		try {
			retour = (ArrayList<Client>) ois.readObject();
			System.out.println("Liste des clients re�us du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur les comptes d'un Client
	 * @param id Identifiant du client
	 * @return liste de ses comptes
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getListeCompte(int id) {
		ArrayList<String> retour = null;
		pw.println(getListeCompte+id);
		System.out.println("Requ�te "+ getListeCompte + id + " envoy�e");
		try {
			retour =  (ArrayList<String>) ois.readObject();
			System.out.println("Liste des comptes re�u du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur les messages dans la boite de r�ception
	 * @return liste des messages
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Message> getListeMessages(){
		ArrayList<Message> retour = new ArrayList<Message>();
		pw.println(listeMessages);
		System.out.println("Requ�te "+ listeMessages + " envoy�e");
		try {
			retour = (ArrayList<Message>) ois.readObject();
			System.out.println("Liste des clients re�us du serveur");
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
		System.out.println("Requ�te "+ historiqueTransaction + " envoy�e");
		try {
			retour = (ArrayList<Transaction>) ois.readObject();
			System.out.println("L'historique des transactions re�us du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur toutes les transactions pour un compte donn�e
	 * @param numCompte numero du compte source dont on recherche les transactions
	 * @return une liste d'objets transaction contenant toutes les infos des transaction du compte 
	 */
	@SuppressWarnings("unchecked")
	public  ArrayList<Transaction> getTransactions(String numCpt) {
		ArrayList<Transaction> retour = null;
		pw.println(getTransactions+numCpt);
		System.out.println("Requ�te "+ getTransactions + numCpt + " envoy�e");
		try {
			retour = (ArrayList<Transaction>) ois.readObject();
			System.out.println("Transaction re�u du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur les infos du compte 
	 * @param numCpt le num�ro du compte dont on requiert les infos
	 * @return un objet compte contenant toutes ses infos
	 */
	public Compte getInfoCompte(String numCpt){
		Compte retour = null;
		pw.println(getInfoCompte+numCpt);
		System.out.println("Requ�te "+ getInfoCompte + numCpt + " envoy�e");
		try {
			retour = (Compte) ois.readObject();
			System.out.println("Les infos du compte re�us du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur un message
	 * @param id identifiant du message
	 * @return retourne le message correspondant � l'id
	 */
	public String getMessage(int id) {
		String retour = null;
		pw.println(getMessage+id);
		System.out.println("Requ�te "+ getMessage + id + " envoy�e");
		try {
			retour = (String) ois.readObject();
			System.out.println("Message re�u du serveur");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * Envoie au serveur une requ�te de suppression d'un message 
	 * @param id du message
	 */
	public int deleteMessage(int id) {
		int retour = 0;
		pw.println(deleteMessage+id);
		System.out.println("Requ�te "+ deleteMessage + id + " envoy�e");
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
	 * Envoie une requ�te de v�rification de l'email et du mot de passe d'un gerant
	 * @param email email � tester
	 * @param pwd mot de passe renseign�
	 * @return l'id du conseiller s'il est pr�sent dans la bdd
	 * et 0 sinon
	 */
	public int checkLogin(String email, String pwd) {
		int retour = 0;
		pw.println(checkLogin + email + "," + pwd);
		System.out.println("Requ�te "+ checkLogin + " envoy�e");
		pw.println(email);
		pw.println(pwd);
		try {
			retour = (int) ois.readObject();
			System.out.println("V�rification de connexion effectu�e");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 *R�cup�re du serveur le solde d'un compte
	 * @param numero mum�ro du compte
	 * @return solde solde du compte
	 */
	public double getSolde(String numero) {
		double retour = 0;
		pw.println(getSolde + numero);
		System.out.println("Requ�te "+ getSolde + numero + " envoy�e");
		try {
			retour = (double) ois.readObject();
			System.out.println("Solde du compte retourn�");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur le type de compte
	 * @param num num�ro du compte
	 * @return entier correspondant au type de compte 0 : �pargne, 1 : courant, 2 : titre
	 */
	public int getTypeCompte(String numero) {
		int retour = 0;
		pw.println(getTypeCompte + numero);
		System.out.println("Requ�te "+ getTypeCompte + numero + " envoy�e");
		try {
			retour = (int) ois.readObject();
			System.out.println("Type du compte retourn�");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur les infos du gerant
	 * @param email l'email du gerant dont on requiert les infos
	 * @return un objet gerant contenant toutes ses infos
	 */
	public Gerant getInfoGerant(int id) {
		Gerant retour = null;
		pw.println(getInfoConseiller + id);
		System.out.println("Requ�te "+ getInfoConseiller + id + " envoy�e");
		try {
			retour = (Gerant) ois.readObject();
			System.out.println("Infos conseiller retourn�");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	/**
	 * R�cup�re du serveur les infos d'un client
	 * @param id l'id du client dont on requiert les infos
	 * @return un objet client contenant toutes ses infos
	 */
	public Client getInfoClient(int id) {
		Client retour = null;
		pw.println(getInfoClient + id);
		System.out.println("Requ�te "+ getInfoClient + id + " envoy�e");
		try {
			retour = (Client) ois.readObject();
			System.out.println("Infos client retourn�");
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
