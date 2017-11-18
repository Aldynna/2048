package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.UIManager;

//import gui.Mreza.MojOsluskivac;
import gui.Mreza.MojeDugme;
import logika.PoljanaZaIgru;

public class Mreza extends JPanel implements KeyListener{
	private PoljanaZaIgru pzi;
	private String type;



	JLabel message; 
	
	
	
	public Mreza(PoljanaZaIgru pzi) {
		this(pzi, "text");
	}	
	
	
	
	public Mreza(PoljanaZaIgru pzi, String type) {
		super();
		this.pzi = pzi;
		this.type = type;
	
		Label taskLabel = new Label("SRETNO!");
		
this. setLayout(new BorderLayout()); 
		int visina = pzi.getVisina();
		int sirina = pzi.getSirina();

/*postavljamo mrezu gdje cemo vrsiti prikaz svojih polja
 * takodjes smo postaviti debljinu okolnih linija
 */
		this.setLayout(new GridLayout(visina,sirina,5,5));
	

	       
		 this.setBackground(Color.GRAY);
	    
		for (int i = 0; i < visina; i++) {
			
			for (int j = 0; j < sirina; j++) {
				String tekst=pzi.getPoljana(i, j);
				System.out.println(pzi);
				MojeDugme md = new MojeDugme(tekst,i, j);
				
				md.setOpaque(false);
				
				this.add(md);
				
				
				}
			
			//postavljamo boju pozadine i linije
			setBackground(Color.MAGENTA);
			
			 setBorder(BorderFactory.createLineBorder(Color.PINK,5,false));
			
		}
		
		setBorder(BorderFactory.createLineBorder(Color.MAGENTA,15,false));
	/*dodajemo osluskivac
	 * u nasem slucaju je osluskivac za tastaturu, jer oredjujemo
	 * da li je korisnik kliknuo neku od strelica
	 * pa da na osnovu toga vrsimo odredjena pomjeranja, poteze
	 */
		addKeyListener(this);
	
	
			
	}
	
	  public void addNotify() {
	        super.addNotify();
	        requestFocus();
	    }
	  public void keyPressed(KeyEvent evt) {
		  // sta je korisnik pritisnuo
          int code = evt.getKeyCode();  
          /*ukoliko je pritisnuo lijevu strelicu vrsimo igru vezno 
           * za pomjeranje lijevo i ponovo ispisujemo nasu mrezu
           * sa novim dobivenim vrijednostima
           * isto radimo i ukoliko su pritusnute neke od drugih strelica
           */
          if (code == KeyEvent.VK_LEFT) {
                  // Move left.  
          	System.out.println("Kliknuo si LIJEVO");
				pzi.odigraj(PoljanaZaIgru.STRELICA_LIJEVO);
				
				osvjeziTabelu();
			
				System.out.println(pzi);
          }
          else if (code == KeyEvent.VK_RIGHT) {  
                  // Move right
        	  System.out.println("Kliknuo si desno");
          	pzi.odigraj(PoljanaZaIgru.STRELICA_DESNO);
				osvjeziTabelu();
				System.out.println(pzi);
          }
          else if (code == KeyEvent.VK_DOWN) {
                  // move down
        	  System.out.println("Kliknuo si dolje");
          	pzi.odigraj(PoljanaZaIgru.STRELICA_DOLJE);
				osvjeziTabelu();
				System.out.println(pzi);
				
          }
          else if (code == KeyEvent.VK_UP) { 
        	  //move up
        	  System.out.println("Kliknuo si gore");
				pzi.odigraj(PoljanaZaIgru.STRELICA_GORE);
				osvjeziTabelu();
				System.out.println(pzi);

				
			}
          
          //ispitujemo da li smo dosli do kraja
          if (pzi.getPobjeda() == pzi.KRAJ_POBJEDA) {
        	  //slika koju cemo postaviti da se prikazuje u dialogu
        	 ImageIcon icon = new ImageIcon(type +"/win.png");
        
        	  Object[] options = {"NOVA IGRA",
                      "ZATVORI IGRU",
                      "NAZAD NA IGRU"};	
        	  /* izgled naseg dialoga, postavljali boje*/
        	  UIManager UI=new UIManager();
             		 UI.put("OptionPane.background",new ColorUIResource(255,105,180));
            		 UI.put("Panel.background",new ColorUIResource(255,182,193));
    		
        	//skocni prozor sa porukom ukoliko smo dostigli trazeni broj
        		int result=JOptionPane.showOptionDialog(null,//parent container of JOptionPane
      				    "Cestitamo, pobijedili ste :)!!! ",
      				    "KRAJ",
      				    JOptionPane.YES_NO_CANCEL_OPTION,
      				    JOptionPane.QUESTION_MESSAGE,
      				 icon,//ikonica unutar dialoga
      				    options,//the titles of buttons
      				    options[0]);//default button title
        		//ispis u konzoli
        	
  			System.out.println("Cestitamo, pobijedili ste :)");
  			dalje(result);
  	
  		} else if(pzi.getPobjeda() == pzi.KRAJ_IZGUBLJENO) {
  			ImageIcon icon = new ImageIcon(type +"/lose.png");
  		  Object[] options = {"NOVA IGRA",
                  "ZATVORI IGRU",
                  "NAZAD NA IGRU"};
  	
  		  //skocni prozor sa porukom ukoliko nemamo vise poteza i nismo uspjeli doci do kraja
  			int result=JOptionPane.showOptionDialog(null,//parent container of JOptionPane
  				    "Zao nam je, nemate vise poteza!!! ",
  				    "KRAJ",
  				    JOptionPane.YES_NO_CANCEL_OPTION,
  				    JOptionPane.QUESTION_MESSAGE,
  				    icon,// ikonica unutar dialog
  				    options,//the titles of buttons
  				    options[0]);//default button title
  			dalje(result);
  			//ispis u konzoli
  			System.out.println("Zao nam je, nemate vise poteza!!!!");
  		}
  			
      }
	  
	  /*funkcija koju pozivamo prilikom zavrsetka igre
	   * ispitujemo sta korisnik zeli dalje ciniti, da li zeli zavrsiti igru
	   * ili restartovati, pa poceti novu
	   */
	  private void dalje(int result) {
		// TODO Auto-generated method stub
		  if (result == JOptionPane.YES_OPTION){
	           pzi.restart();
	           osvjeziTabelu();
	        }
		  
		  if (result == JOptionPane.NO_OPTION){
		  System.exit(0);
		  }
	}



	public void keyReleased(KeyEvent e) { }
	    public void keyTyped(KeyEvent e) {
	
	    }


	public void osvjeziTabelu(){
		for (int i = 0; i < pzi.getVisina(); i++) {
			for (int j = 0; j < pzi.getSirina(); j++) {
				String tekst=(pzi.getPoljana(i, j));
				
			
					 
					//ako trazimo npr deseti dugmic u nasem gridlayout, (na poziciji 2,1) do njega
					 //cemo doci tako sto cemo preci citav prvi red, citav drugi, a u trecem redu je na mjestu 2
					 //odnosno gledano nase x,y kordinate, x puta ce preci sirinu, a onda ce se u narednom redu samo pomjeriti za y mjesta
					 //tako smo nasli odgovarajuci sadrzaj koji nam treba
					// ((MojeDugme)(this.getComponent(i* pzi.getSirina() + j))).setText(tekst);
					 try {
						 if(tekst.equals(" ")) tekst="prazno";
					 ((MojeDugme)(this.getComponent(i* pzi.getSirina() + j))).setIcon(new ImageIcon(ImageIO.read(new File(type + "/" + tekst + ".png"))));
						((MojeDugme)(this.getComponent(i* pzi.getSirina() + j))).setDisabledIcon(new ImageIcon(ImageIO.read(new File(type + "/" + tekst + ".png"))));
					 } catch (IOException e) {
							System.out.println("GRESKA!!! ovdje je tekst = " + tekst);
							e.printStackTrace();
						}
//	}
	
	}}}
	
	


	
	

	class MojeDugme extends JButton {
		int x;
		int y;

		public MojeDugme(String tekst,int i,int j) {
			//postavljamo dugme na poziciju i, j 
			//tekst je tekst koji se prikazuje na dugmicu
			x = i;
			y = j;
			//postavljamo font za brojeve koje ispisujemo na svakom polju
			//font halvetica, boldirano, velicina 30
			setFont(new Font("Halvetica",Font.BOLD,30));
	
	
			
			 try {
				 /*u zavisnosti od vrijednosti polja, postavljamo sliku
				  * slike smo nazvali isto kao ova vrijednost, tako da nije potrebno za sve
				  * dodatno ispitivati postavljati
				  * nego jednosttavno puttanja nam je
				  * tipigre/vrijednostpolja.png
				  * za slucaj da pojenema vrijednosti, odnosno da je prazno, 
				  * vrijednost teksta postavljamo na "prazno", jer imamo slike sa 
				  * tim nazivom, koje postavljamo na prazna mjesta, koja nemaju vrijednosti
				  */
				 
				 if(tekst.equals(" ")) tekst="prazno";
			setIcon(new ImageIcon(ImageIO.read(new File(type + "/" + tekst + ".png"))));
			/*i vazno je da postavimo da se ne moze klikati, jer nasa igra ne
			 * ukljucuje klikanje dugmica, nego pomjeranje pomocu strelica
			 */
			setDisabledIcon(new ImageIcon(ImageIO.read(new File(type + "/" + tekst + ".png"))));
			 } catch (IOException e) {
					System.out.println("GRESKA!!! ovdje je tekst = " + tekst);
					e.printStackTrace();
				}
			//postavljamo da se ne moze klikati
			
			this.setEnabled(false);
			
			
		}
		
	}
	


}
	
	
			 

