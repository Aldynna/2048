/**
 * 
 */
package logika;

/**
 * @author korisnik
 *
 */
public class JednoPolje {
	
	public static final int STATUS_PRAZNO = 0;
	public static final int STATUS_BROJ = 1;
	public static final int AKCIJA_GORE = 1;
	public static final int AKCIJA_DOLJE = 0;
	public static final int AKCIJA_LIJEVO = 2;
	public static final int AKCIJA_DESNO = 3;
	private int statusPolja;
	private boolean broj;
	private int number;
	
	/**
	 * na pocetku definismo JednoPolje i postavljamo da je ono prazno
	 * odnosno da za sad nema nikakvih vrijednosti
	 */
	public JednoPolje() {
		super();
		broj = false;
		statusPolja = STATUS_PRAZNO;
	}
	
	/**
	 * postavljamo vrijednost polja
	 * vazno je da se promijeni i status tog polja, da postavimo da se tu nalazi broj
	 * 
	 * @param num
	 */
	public void postaviBroj(int num) {
		broj = true;
		statusPolja = STATUS_BROJ;
		number=num;
	}
	
	//vraca vrijednost polja
	public int getBroj(){
		return number;
		
	}
	
	/**
	 * prikazujemo sadrzaj polja
	 * ukoliko je polje prazno, vraca  " ", a ukoliko nije vraca vrijednost polja
	 * s obzirom da radio sa string metodom, onda se i vrijednost polja treba vratiti
	 * kao string a  ne kao int, pa cemo izvrsiti konverzije
	 * najlakse je praznom stringu dodati vrijednost polja
	 * @return
	 */
	public String prikaziPolje() {
		
		if (statusPolja == STATUS_PRAZNO) {
			return " ";
		}
		else //(statusPolja == STATUS_BROJ)
			return ""+number; //(string)(number + (int)'0');
		

	
	}

	//sluzi za provjeravanje da li je broj postavljen u tom polju
	public boolean isBroj() {
		// funkcija da ispitamo da li je na tom mjestu, polju trenutno broj
		
		return broj;
	}
	
	/**
	 * ukoliko uklanjamo broj sa nekog polja potrebno potrebno je postaviti da to polje ne sadrzi broj
	 * odnosno da je to polje prazno, promijeniti status polja
	 */
	public void obrisiBroj(){
		broj=false;
		statusPolja = STATUS_PRAZNO;
	}

	public int getStatus() {
		// vraca status polja, da li se nalazi broj ili je prazno
		
		return statusPolja;
	}
	
	@Override
	public String toString() {
		return "" + number;
	}

}
