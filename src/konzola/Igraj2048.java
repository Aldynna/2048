/**
 * 
 */
package konzola;

import java.util.Scanner;
import logika.JednoPolje;
import logika.PoljanaZaIgru;


/**
 * @author korisnik
 *
 */
public class Igraj2048 {
	
	public static void main(String[] args) {
		int potez;
		PoljanaZaIgru poljanaZaIgru = new PoljanaZaIgru(PoljanaZaIgru.LEVEL_BEGINER);
		
		
		while(true){
			//ispis trenutnog stanja nasih polja, matrice
			System.out.println(poljanaZaIgru);
			Scanner in = new Scanner(System.in);
		while (poljanaZaIgru.getPobjeda() == poljanaZaIgru.KRAJ_NIJE) {
			
			System.out.format("Unesi potez (Lijevo - %d, Desno - %d, Gore - %d, Dolje - %d): ", PoljanaZaIgru.STRELICA_LIJEVO, PoljanaZaIgru.STRELICA_DESNO, PoljanaZaIgru.STRELICA_GORE, PoljanaZaIgru.STRELICA_DOLJE);
			potez = in.nextInt();
			
			poljanaZaIgru.odigraj(potez);
		
			System.out.println(poljanaZaIgru);
			//System.out.println(prikaz(poljanaZaIgru));
		}
		/*provjeravamo da li smo dosli do kraja
		 * da li je to pobjeda, ukoliko smo dosli do cilja,
		 * ili je ispisujemo odgovarajucu poruku
		 */
		if (poljanaZaIgru.getPobjeda() == PoljanaZaIgru.KRAJ_POBJEDA) {
			System.out.println("Cestitamo, pobijedili ste :)");
			
		} else {
			System.out.println("Zao nam je, nemate vise poteza!!!!");
			
		}
		/* zatim ispisujemo naredbe koje su moguce, u zavisnosti sta zeli korisnik uraditi
		 * da li zeli restartovati igricu ili zavrsiti skroz. kada odabere cancel, nema promjena
		 * exit zavrsava igru
		 * dok restart omogucava ponoovno igranje, vracanje na ppocetne postavke
		 */
		System.out.format("RESTART - %d, EXIT - %d, CANCEL - %d: ", PoljanaZaIgru.RESTART, PoljanaZaIgru.EXIT, PoljanaZaIgru.CANCEL);
		potez = in.nextInt();
		if (potez==PoljanaZaIgru.EXIT){
			System.exit(0);
			break;
		} else poljanaZaIgru.dalje(potez);
		}
	}

	private static String prikaz(PoljanaZaIgru poljana) {
		String str = "   ", linija = "    +";
		for (int i = 0; i < poljana.getSirina(); i++) {
			str = String.format("%s%10d", str, i);
			linija = String.format("%s%s",  linija, "---+");
		}
		str = String.format("%s\n%s", str, linija);
		for (int i = 0; i < poljana.getVisina(); i++) {
			str = String.format("%s\n%3d %s", str, i, "|");
			for (int j = 0; j < poljana.getSirina(); j++) {
				str = String.format("%s 7%s |", str, poljana.getPoljana(i, j)); // str = str + " " + (tabela[i][j]) + " |";
			}
			str = String.format("%s\n%s", str, linija); // str += "\n" + linija
		}

//		str += poljana.mozeSeJosIgrati() ? "\njos uvijek igramo" : "\ngotovo" ;
		return str;
	}

}
