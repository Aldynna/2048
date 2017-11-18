/**
 * 
 */
package gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JFrame;

import logika.PoljanaZaIgru;

/**
 * @author korisnik
 *
 */
public class Igrica extends JFrame {
	
	public static void main(String[] args) {
		Igrica igrica = new Igrica(new PoljanaZaIgru(PoljanaZaIgru.LEVEL_BEGINER));
		

	}
	
	public Igrica(PoljanaZaIgru pzi) throws HeadlessException {
		super();
		this.setTitle("2048 verzija Aldina");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		  Object[] options = {"boho",
                  "classic",
                  };	
		  UIManager UI=new UIManager();
         		 UI.put("OptionPane.background",new ColorUIResource(255,105,180));
        		 UI.put("Panel.background",new ColorUIResource(255,182,193));
        		 String type="classic";
		int result=JOptionPane.showOptionDialog(null,//parent container of JOptionPane
				    "Izaberite tip igre :)!!! ",
				    "KRAJ",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				 null,//ikonica unutar dialoga
				    options,//the titles of buttons
				    options[0]);//default button title
		 if (result == JOptionPane.YES_OPTION){
	          type = (String) options[0];
	        } else  if (result == JOptionPane.NO_OPTION){
	        	 type=(String) options[1];
		        }
		this.setContentPane(new Mreza(pzi,type));
		
		this.setVisible(true);
		
		this.setLocation(270,150);
		this.setSize(700, 580);
		  this.setResizable(false);
		
		//this.setSize(this.getMaximumSize());
	}

}
