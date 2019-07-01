package classes;

import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
* classe permettant d'ajouter une image de fond
* @author TIC1.3
*
*/
public class ImagePanel extends JPanel implements Serializable {
    /**
	 * Constructeur pour un JPanel avec une image de fond 
	 * @param image image de fond
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * image à ajouter en fond
	 */
	Image image = null;
    public ImagePanel(Image image) {
        this.image = image;
    }
    public ImagePanel() {
    }
    /**
	 * Renvoie le code postal du client 
	 * @param image image de fond
	 */
    public void setImage(Image image){
        this.image = image;
    }
    /**
	 * Renvoie l'image
	 * @param image Image de fond
	 * @return retourne l'image
	 */
    public Image getImage(Image image){
        return image;
    }
    
    /**
	 * Paint l'image sur le panel
	 * @param g outil graphique 
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (image != null) { 
            int height = this.getSize().height;
            int width = this.getSize().width;
            g.drawImage(image,0,0, width, height, this);
        }
    }
}
