package classes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import socket.ClientSocketApplication;

/**
* classe représentant le modèle de données dans la base
* @author TIC1.3
*
*/
public class ModelMessage extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * vecteur de données pour la liste des messages 
	 */
	String data[][]; 
	/*
	 * vecteur de données pour la liste des messages
	 */
    private final String[] entetes = {"N°", "Objet",  "Email","Message"}; 
    /*
     * nombre de message dans la boite de réception de la banque
     */
    public int n;
    
    public ModelMessage(ClientSocketApplication s)
    {
    	int i = 0;
    	n = s.getListeMessages().size();
    	ArrayList<Message> liste = new ArrayList<Message>(); 
    	liste = s.getListeMessages();
    	data = new String[n][4];
    	for(Message c : liste)
    	{
    		data[i][0] = String.valueOf(c.getId());
			data[i][1] =  c.getObjet();
			data[i][2] = c.getEmail();
			data[i][3] = c.getTextMessage();
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
