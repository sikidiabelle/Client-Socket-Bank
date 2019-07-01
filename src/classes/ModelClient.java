package classes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import socket.ClientSocketApplication;

/**
* classe repr�sentant le mod�le de donn�es dans la base
* @author TIC1.3
*
*/
public class ModelClient extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * vecteur de donn�es pour la liste des clients 
	 */
	String data[][]; 
	/*
	 * ent�te du mod�le
	 */
    private final String[] entetes = {"Id", "Civilit�", "Nom","Pr�nom", "Email"};
    /*
     * nombre de clients de la banque
     */
    public int n;
    
    /*Constructeur du mod�le de donn�es liste de clients
     *  @param ClientSocketApplication socket client associ�
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
