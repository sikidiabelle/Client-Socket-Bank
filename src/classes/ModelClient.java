package classes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import socket.ClientSocketApplication;

/**
* classe représentant le modèle de données dans la base
* @author TIC1.3
*
*/
public class ModelClient extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * vecteur de données pour la liste des clients 
	 */
	String data[][]; 
	/*
	 * entête du modèle
	 */
    private final String[] entetes = {"Id", "Civilité", "Nom","Prénom", "Email"};
    /*
     * nombre de clients de la banque
     */
    public int n;
    
    /*Constructeur du modèle de données liste de clients
     *  @param ClientSocketApplication socket client associé
     */
    public ModelClient(ClientSocketApplication s)
    {
    	int i = 0;
    	n = s.getListeClients().size();
    	ArrayList<Client> liste = new ArrayList<Client>(); 
    	liste = s.getListeClients();
    	data = new String[n][5];
    	
    	for(Client c : liste)
    	{
    		data[i][0] = String.valueOf(c.getIdClient());
			if(c.getCiv() == 'M'){
				data[i][1] ="Monsieur";
			}
			else {
				data[i][1] ="Monsieur";
			}
			data[i][2] =  c.getNom();
			data[i][3] = c.getPrenom();
			data[i][4] = c.getEmail();
			i++;
    	}
    		
    }
    
    public String getValueAt(int row, int col) {
        return this.data[row][col];
    }
    
    public void setValueAt(String value, int row, int col) {
        this.data[row][col] = value;
    }
    
    public int getColumnCount() {
		return entetes.length;
	}
    
    public int getRowCount()
    {
    	return n;
    }

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

}
