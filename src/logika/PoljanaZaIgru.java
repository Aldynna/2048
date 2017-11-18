/**
 * 
 */
package logika;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * @author korisnik
 *
 */
public class PoljanaZaIgru {
	public static final int LEVEL_BEGINER = 4;
	public static final int LEVEL_POCETNIK=6;
	public static final int ODREDISTE = 2048;
	public static final int STRELICA_GORE = 8;
	public static final int STRELICA_DOLJE = 2;
	public static final int STRELICA_LIJEVO = 4;
	public static final int STRELICA_DESNO = 6;
	public static final int KRAJ_NIJE = 0;
	public static final int KRAJ_POBJEDA = 1;
	public static final int KRAJ_IZGUBLJENO = 2;
	public static final int CANCEL = 0;
	public static final int EXIT = 1;
	public static final int RESTART = 2;
	private int visina;
	private int sirina;
	private int score=0;
	private JednoPolje[][] tabela;
	private boolean igraJeAktivna=true;
	public int brojimo;
public int prostor;
public boolean pomjereno=false;
	
	

	
	
	public PoljanaZaIgru(int visina, int sirina) {
		super();
		//postavimo visinu i sirinu matrice
		this.igraJeAktivna = true;
		this.visina = visina;
		this.sirina=sirina;
		/* varijabla prostor prestavlja broj dostupnih mjesta u nasoj matrici, mrezi
		 * na pocetku je to visina*sirina, ali ukoliko dodamo novi broj
		 * znaci da ce se broj dostupnih mjesta smanjiti za jedan, ukoliko spajamo
		 * brojeve, broj dostupnih mjesta se poveca za jedan
		 * sluzi nam za ispitivanje da li smo dosli do kraja
		 * 
		 */
		prostor=visina*sirina;
		brojimo=0;
		this.tabela=new JednoPolje[this.visina][this.sirina];
		//postavimo matricu
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina; j++) {
				tabela[i][j] = new JednoPolje();
				
			}
		}
		postavi();

	}
	/*funkcija za pocetno postavljanje matrice, potrebno
	 * je da dva random broja postavi na dva mjesta u matrici
	 * ovakvu for petlju smo napisali da bismo dobili dva postavljena broja
	 * jer ako se slucajno desi da dva puta dobije istu lokaciju, bez ove petlje
	 * bi samo nastavilo dalje i imali bismo samo jedan broj
	 */
	public void postavi(){
	//na random lokaciji postavljamo dva broja
	for (int count = 0; count < 2;) {
	int d = (int)(Math.random() * this.visina);
	int s = (int)(Math.random() * this.sirina);
	if (!tabela[d][s].isBroj()) {
		tabela[d][s].postaviBroj(2);
		count++;
		prostor--;
	}
	
	
	
}
	}
	//postavljanje visine i sirine na vrijednosti
	public PoljanaZaIgru(int level) {
		this(visina(level), sirina(level));
	}
	/*igra se nastavlja dalje, odnosno restartujemo
	 * ukoliko je korisnik odabrao restart
	 */
	  public void dalje(int result) {
		// TODO Auto-generated method stub
		  if (result ==RESTART ){
			  restart();
	          
	        }
	
	}


	private static int visina(int level) {
	switch (level) {
		case LEVEL_BEGINER:
			return 4;
		case LEVEL_POCETNIK:
			return 6;
		default:
			return 10;
	}
	
	}


private static int sirina(int level) {
	switch (level) {
	case LEVEL_BEGINER:
		return 4;
	case LEVEL_POCETNIK:
		return 6;
	default:
		return 10;
	}
}

/*funkcija kojom provjeravamo da li je moguce
 * napraviti jos poteza, nju pozivamo u slucaju ako vise nema dostupnih mjesta
 * pa provjeravamo da li je moguce jos koje polje spojiti prije obavijesti kraja
 provjeravamo da li se dva ista broja nalaze jedan do drugog,
 jer samo  u tom slucaju je moguce spajanje
 */
public boolean MozeSeIgrati() {
	for (int i = 0; i < this.visina; i++) {
		if(i<this.visina-1)
		{
		for (int j = 0; j < this.sirina-1; j++) {
			
			if((tabela[i][j].getBroj()==tabela[i][j+1].getBroj())||(tabela[i][j].getBroj()==tabela[i+1][j].getBroj()))
return true;
} if(tabela[i][this.sirina-1].getBroj()==tabela[i+1][this.sirina-1].getBroj()) return true;
		}
		else 
		{
			for (int j = 0; j < this.sirina-1; j++) {
			if(tabela[i][j].getBroj()==tabela[i][j+1].getBroj()) return true;
		}
			
		}
	}
	
return false;
}

/*restartovanje igre
 * brisemo brojeve sa polja,gdje su postavljeni
 * prostor postavljamo da je sav dostupan, 
 * da je igra aktivna
 * i naravno vrijednost max polja da je 0
 */
public void restart() {
	for (int i = 0; i < this.visina; i++) {
		for (int j = 0; j < this.sirina; j++) {
			if(tabela[i][j].isBroj())
			{
			tabela[i][j].obrisiBroj();
			}
		
		}
		
			
		}
	
	prostor=visina*sirina;
	postavi();
	brojimo=0;
	this.igraJeAktivna = true;
}

public void odigraj(int potez) {
	
	/**
	 * ukoliko je igra aktivna, i izvrsili smo neku od naredbi za pomjeranje, onda ulazimo u ovaj if blok
	 * i vrsimo dalja ispitivanja da bismo se mogli dalje pomjerati, odnosno
	 * igrati igricu
	 * */
	 
	if (igraJeAktivna && ((potez == STRELICA_LIJEVO) || (potez == STRELICA_DESNO) || (potez == STRELICA_GORE)|| (potez == STRELICA_DOLJE))) {
		
		
		if (potez == STRELICA_DESNO&&mozeSePomjeritiDesno()) {
			/**postavljamo kontrolnu varijablu na true
			*sluzi nam za isitivanje da li smo izvrsili pomjeranje
			**s obzirom da smo u ovom bloku, zadovoljeni uslovi za pomjeranje
			*znaci da tu varijablu mozemo postaviti na true i izvrsiti pomjeranje
			*slicna ispitivanja su i za ostale poteze
			*/
			pomjereno=true;
			pomjeri_desno();
			spoji_desno();
			pomjeri_desno();
			
		}
		if (potez == STRELICA_LIJEVO&&mozeSePomjeritiLijevo()) {
			pomjereno=true;
			pomjeri_lijevo();
			spoji_lijevo();
			pomjeri_lijevo();
		
			
		}
		if (potez == STRELICA_GORE&&mozeSePomjeritiGore()) {
			pomjereno=true;
			pomjeri_gore();
		spoji_gore();
			pomjeri_gore();
			
		}
		if (potez == STRELICA_DOLJE&&mozeSePomjeritiDolje()){
			pomjereno=true;
			pomjeri_dolje();
			spojidolje();
			pomjeri_dolje();
			
		}
		//u slucaju da smo izvrsili pomjeranje, u tom slucaju dodajemo novi broj
		if(pomjereno) update();
		//postavljamo varijablu na false, i idemo opet iz pocetka
	pomjereno=false;
	if(prostor==0&&!MozeSeIgrati()){
		/**
		 * nema vise legalnih poteza, pa aktivnost igre postavljamo na false
		 */
		this.igraJeAktivna = false;
}
		
	}
	/**
	 * ukoliko vise nemamo slobodnog prostora, provjerimo i da li se moze igrati igra
	 * odnosno da li je moguce izvrsiti jos neko spajanje
	 * ukoliko nije moguce tu zavrsava nasa igra, ukoliko jeste, nastavljamo dalje
	 */

	getPobjeda();
}



//provjeravamo da li se moze pomjeriti dolje
//ukoliko ispod brojeva ima prazno mjesto ili isti broj onda se moze pomjeriti odnosno spojiti
	private boolean mozeSePomjeritiDolje() {
	
		for (int i = 0; i < this.visina-1; i++) {
			for (int j = 0; j < this.sirina; j++) {
				if((tabela[i][j].isBroj()&&tabela[i+1][j].isBroj()&&(tabela[i][j].getBroj()==tabela[i+1][j].getBroj()))||(tabela[i][j].isBroj()&&!tabela[i+1][j].isBroj())) return true;
			}
				
			}
		
	return false;
}

	
	//provjeravamo da li se moze pomjeriti gore
	//ukoliko iznad brojeva ima prazno mjesto ili isti broj onda se moze pomjeriti odnosno spojiti
	private boolean mozeSePomjeritiGore() {
		for (int i = 1; i < this.visina; i++) {
			for (int j = 0; j < this.sirina; j++) {
				if((tabela[i][j].isBroj()&&tabela[i-1][j].isBroj()&&(tabela[i-1][j].getBroj()==tabela[i][j].getBroj()))||(tabela[i][j].isBroj()&&!tabela[i-1][j].isBroj())) return true;
			}
				
			}
	return false;
}

	
	//provjeravamo da li se moze pomjeriti lijevo
	//ukoliko lijevo od brojeva ima prazno mjesto ili isti broj onda se moze pomjeriti odnosno spojiti
	private boolean mozeSePomjeritiLijevo() {
		for (int i = 0; i < this.visina; i++) {
			for (int j = 1; j < this.sirina; j++) {
				if((tabela[i][j].isBroj()&&tabela[i][j-1].isBroj()&&(tabela[i][j].getBroj()==tabela[i][j-1].getBroj()))||(tabela[i][j].isBroj()&&!tabela[i][j-1].isBroj())) return true;
			}
				
			}
	return false;
}

	//provjeravamo da li se moze pomjeriti desno
//ukoliko desno od brojeva ima prazno mjesto ili isti broj onda se moze pomjeriti odnosno spojiti
	private boolean mozeSePomjeritiDesno() {
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina-1; j++) {
				if((tabela[i][j].isBroj()&&tabela[i][j+1].isBroj()&&(tabela[i][j].getBroj()==tabela[i][j+1].getBroj()))||(tabela[i][j].isBroj()&&!tabela[i][j+1].isBroj())) return true;
			}
				
			}
		
	return false;
}
/**
 * vrsimo postavljanje novog broja, pomocu funkcije random, odredimo prvo dva random broja
 * koja su u opsegu nase tabele, visinom i sirinom.
 * ukoliko je to mjesto u tabeli slobodno, postavimo broj, i dostupni prostor smanjimo za 1
 * stavili smo u while petlju da bismo uvijek nakon ivrsenog poteza imali postavljanje broja
 * 
 */
	private void update() {
	
		while(true){
			
		int d = (int)(Math.random() * this.visina);
		int s = (int)(Math.random() * this.sirina);
		if (!tabela[d][s].isBroj()) 
		{
			tabela[d][s].postaviBroj(2);
			prostor--;
			break;
		}
		
		}
	
}

	
	/**
	 * vrsimo spajanje brojeva na desno. 
	 * krecemo sa desne strane i povjeravamo da li lijevo od broja ima isti broj, ukoliko ima
	 * vrsimo spajanje, odnosno sadrzaj desnog mnozimo sa dva, a lijevi brisemo
	 * 
	 */
	private void spoji_desno() {
	
		for (int i = 0; i < this.visina; i++) {
			for (int j = this.sirina-1 ; j >0; j--) {
				if(tabela[i][j].getStatus()==JednoPolje.STATUS_BROJ&&tabela[i][j-1].getStatus()==JednoPolje.STATUS_BROJ){
					
					if(tabela[i][j].getBroj()==tabela[i][j-1].getBroj()){
						tabela[i][j].postaviBroj(tabela[i][j].getBroj()*2);
						score+=tabela[i][j].getBroj();
						ispitaj(tabela[i][j].getBroj());
					
						tabela[i][j-1].obrisiBroj();
						prostor++;
						j--;
					}
					}
				}
				
				
			}
		}
		

/**
 * ovdje ispitujemo za trenutni postavljeni broj, koji je nastao spajanje
 * da li je on veci od takvog prethodnog, postavljenog
 * ukoliko jeste, njega postavljamo da bude najveci
 * sluzi za provjeravanje da li smo dosli do cilja, u nasem slucaju do broja 2048
 * @param a
 */
	private void ispitaj(int a) {
		// ispitujemo vrijednost novog broja da l je vece od do sad najveceg,ukoliko jeste to je nas novi max broj
		if(a>brojimo) brojimo=a;
		
	}



	private void spoji_lijevo() {
	// TODO Auto-generated method stub
		
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina-1; j++) {
				if(tabela[i][j].getStatus()==JednoPolje.STATUS_BROJ&&tabela[i][j+1].getStatus()==JednoPolje.STATUS_BROJ){
					
					if(tabela[i][j].getBroj()==tabela[i][j+1].getBroj()){
						tabela[i][j].postaviBroj(tabela[i][j].getBroj()*2);
						score+=tabela[i][j].getBroj();
						ispitaj(tabela[i][j].getBroj());
						
						tabela[i][j+1].obrisiBroj();
						prostor++;
						j++;
					}
						
					
				}
				
				
			}
		}
}

	
	private void spojidolje() {
	// TODO Auto-generated method stub
		for (int j = 0; j < this.sirina; j++) {
			for (int i = this.visina-1; i >0 ; i--) {	
			if(tabela[i][j].getStatus()==JednoPolje.STATUS_BROJ&&tabela[i-1][j].getStatus()==JednoPolje.STATUS_BROJ){
			{
				
				if(tabela[i][j].getBroj()==tabela[i-1][j].getBroj()){
					
					tabela[i][j].postaviBroj(tabela[i][j].getBroj()*2);
					score+=tabela[i][j].getBroj();
					ispitaj(tabela[i][j].getBroj());
					
					tabela[i-1][j].obrisiBroj();
					prostor++;
					i--;
				}
			
		}	
	}
	
		
	}
		}
		
}

	private void spoji_gore() {
	// TODO Auto-generated method stub
		for (int j = 0; j < this.sirina; j++) {
			for (int i = 0; i < this.visina-1; i++) {	
			if(tabela[i][j].getStatus()==JednoPolje.STATUS_BROJ&&tabela[i+1][j].getStatus()==JednoPolje.STATUS_BROJ){
				
				if(tabela[i][j].getBroj()==tabela[i+1][j].getBroj()){
					tabela[i][j].postaviBroj(tabela[i][j].getBroj()*2);
					score+=tabela[i][j].getBroj();
					ispitaj(tabela[i][j].getBroj());
					
					tabela[i+1][j].obrisiBroj();
					prostor++;
					i++;
				}
			
		}	
	}
	
		
	}
}


	
	private void pomjeri_gore() {
	// TODO Auto-generated method stub
		
		
		for (int j = 0; j < this.sirina; j++) {
				for (int i = 0; i < this.visina-1; i++) {	
				if(tabela[i][j].getStatus()==JednoPolje.STATUS_PRAZNO)
				{
					for (int k = i+1; k < this.visina; k++) {
						if(tabela[k][j].getStatus()==JednoPolje.STATUS_BROJ){
							tabela[i][j].postaviBroj(tabela[k][j].getBroj());
							tabela[k][j].obrisiBroj();
							break;
						
					}
				}
				
				
			}	
		}
		
			
		}
}

	private void pomjeri_dolje() {
	// TODO Auto-generated method stub
		for (int j = 0; j < this.sirina; j++) {
			for (int i = this.visina-1; i >0 ; i--) {	
			if(tabela[i][j].getStatus()==JednoPolje.STATUS_PRAZNO)
			{
				for (int k = i-1; k >=0; k--) {
					if(tabela[k][j].getStatus()==JednoPolje.STATUS_BROJ){
						tabela[i][j].postaviBroj(tabela[k][j].getBroj());
						tabela[k][j].obrisiBroj();
						break;
					
				}
			}
			
			
		}	
	}
	
		
	}
	
}

/**
 * pomjeranje brojeva, ukoliko desno od broja postoji prazno mjesto, pomjeramo taj broj 
 * ovdje smo koristili jos jednu dodatnu petlju
 * krecemo sa desna, ukoliko naidjemo na prazno polje pokrecemo petlju sa k
 * krece od polja j, prethodne petlje, pa prema lijevo i trazi prvo polje koje je broj
 * kada pronadje takvo polje njega obrise sa tog polja i postavi na polje koje smo oznacili
 * sa j petljom i prekidamo k petlju, nastavljamo dalje j,i petlje
 */
	private void pomjeri_desno() {
	// TODO Auto-generated method stub
		for (int i = 0; i < this.visina; i++) {
			for (int j = this.sirina-1 ; j >0; j--) {
				if(tabela[i][j].getStatus()==JednoPolje.STATUS_PRAZNO)
				{
					for (int k = j-1; k >=0; k--) {
						if(tabela[i][k].getStatus()==JednoPolje.STATUS_BROJ){
							tabela[i][j].postaviBroj(tabela[i][k].getBroj());
							tabela[i][k].obrisiBroj();
							break;
						}
					}
				}
				
				
			}
		}
	
}

	private void pomjeri_lijevo() {
	// TODO Auto-generated method stub
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina-1; j++) {
				if(tabela[i][j].getStatus()==JednoPolje.STATUS_PRAZNO)
				{
					for (int k = j+1; k < this.sirina; k++) {
						if(tabela[i][k].getStatus()==JednoPolje.STATUS_BROJ){
							tabela[i][j].postaviBroj(tabela[i][k].getBroj());
							tabela[i][k].obrisiBroj();
							break;
						}
					}
				}
				
				
			}
		}
	
}
/*ispitujemo dokle je korisnik stigao, odnosno da li je stigao do kraja
 * za slucaj da je do sada maksimalni postignuti broj jednak nasem cilju
 * onda znaci da je korisnik pobijedio
 * ukoliko igra nije aktivna znaci da je stigao do kraja i da je izgubio
 * u protivnom korisnik moze i dalje igrati, pa ispisujemo trenutno
 * stanje u matrici
 */
	public int getPobjeda() {
		int trenutno = 0;
		
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				if (tabela[i][j].getStatus() == JednoPolje.STATUS_BROJ) {
					trenutno++;
					
				}
			}
		}
	
		
		if (brojimo==ODREDISTE) {
			igraJeAktivna=false;
			return KRAJ_POBJEDA;
		}
		if (igraJeAktivna) {
			ispisi();
			return KRAJ_NIJE;
			
			
		}
		return KRAJ_IZGUBLJENO;

}
	
	
	
	private void ispisi() {
		// TODO Auto-generated method stub
		System.out.println("Score: "+score);
		System.out.println("Slobodniih mjesta: "+prostor);
		System.out.println("Trenutno max: "+brojimo);
	}

	//vraca visinu poljanje
	public int getVisina() {
		return visina;
	}
	
	public int getScore() {
		return score;
	}

	//vraca sirinu poljanje
	public int getSirina() {
		return sirina;
	}
	
	//vraca vrijednost polja i,j
	public String getPoljana(int i, int j) {
		return tabela[i][j].prikaziPolje();
	}
	
	//vraca poljanu, sve vrijednosti polja
	public String[][] getPoljana() {
		String[][] poljana = new String[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				poljana[i][j] = tabela[i][j].prikaziPolje();
			}
		}
		return poljana;
	}
	
	
	
	
	
	/**
	 * nasa funkcija za ispis.
	 * sada mozemo direktno ispisivati pomocu naredni println
	 */
	@Override
	public String toString() {
		String str = "   ", linija = "    +";
		for (int i = 0; i < sirina; i++) {
			str = String.format("%s%10d", str, i);
			linija = String.format("%s%s",  linija, "---------+");
		}
		str = String.format("%s\n%s", str, linija);
		for (int i = 0; i < visina; i++) {
			str = String.format("%s\n%3d %s", str, i, "|");
			for (int j = 0; j <sirina; j++) {
				str = String.format("%s %7s |", str,tabela[i][j].prikaziPolje()); // str = str + " " + (tabela[i][j]) + " |";
			}
			str = String.format("%s\n%s", str, linija); // str += "\n" + linija
		}

//		str += poljana.mozeSeJosIgrati() ? "\njos uvijek igramo" : "\ngotovo" ;
		return str;
	
	}
	}
	
	
