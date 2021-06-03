import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Harjutaja {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/tulemuskaartideinfo.txt"));
        Scanner scanner1 = new Scanner(System.in); // uus scanner, mis lugeb seda, mida kasutaja sisestab
        String[][] tulemuskaartideinfo = new String[7][5];
        String[][] kontrollimiseks = new String[6][4]; //mida kasutaja sisestab 
        String[][] kasoletevalmis = new String[1][4];
        String sisestage = "0";
        int õiged = 0, kokku = 0, x = 1, y = 1, vigadejärjekorranumber = 0;
        while (scanner.hasNext()) { //õigete vastuste scanner
            for (x = 0; x < 7; x++) {
                String line = scanner.nextLine();
                String kuski[] = line.split(",");
                for (y = 0; y < 5; y++) {
                    tulemuskaartideinfo[x][y] = kuski[y];
                }
            }
        }
        System.out.println("Tere! Siin saate harjutada oma tulemuste sisestamisoskusi.\nLõpus saate teada kui palju ja kus tegite vigu.\nSoovi puhul saate ise oma katse aja ära mõõta.\n");
        System.out.println("Enne esimest korda programmi kasutamist on väga soovitatav õpetuse saada.\nKas soovite õpetuse saada? (sisestage “Jah” või “Ei”)");
        do {
            sisestage = scanner1.next().toLowerCase();
            if (sisestage.equals("jah")) {
                System.out.println("\n1. Avage kõik fotod, mis asuvad kaustas “tulemuskaardid” või avage ühe aga pärast liikuge nende vahel kiiresti");
                System.out.println("2. Alustage tulemuskaardist madalaima ID-ga ning liikuge edasi suureima ID-ga kaardile. Foto nimi ongi tulemuskaardi ID");
                System.out.println("3. Alguses sisestage võistleja ID, vajutage “Enter”, pärast mida ekraanile väljastatakse võistleja nimi,\n   sisestage esimese tulemuse, vajutage “Enter”, teise tulemuse, vajutage “Enter”, kolmanda tulemuse ja vajutage “Enter”\n   Kordage selle protseduuri kõikide tulemuskaartidega\n");
                System.out.println("    * Kõik tulemused peavad olema sisestatud ILMA mingite sümboliteta, ehk ainult numbritega");
                System.out.println("    * Kõik tulemused (praegu erandeid pole) peavad olema lõigatud sajandikuni\n    Näiteks “3:02,119” muutub “3:02,11”ks ning peab olema sisestatud nagu “30211”");
                System.out.println("    * “DNF” asemel sisestatakse “/”. Kui “DNF” juurde on aeg kirjutatud, siis ikkagi sisestatakse ainult “/”");
                System.out.println("    * Sama asi “DNS” kohta, aga selle asemel sisestatakse “*”");
                System.out.println("    * Lisaks võite seda videot vaadata “https://youtu.be/BhqUptSiKus?t=1m50s”");
                System.out.println("\nSelleks, et teid testida, sisestage järgmise tulemuskaardi info vastavalt juhendile ja vajutage “Enter”\n\nid: 13.   Vasya Pupkin\n1:59,136\nDNF\n2:20,89");
                kasoletevalmis[0][0] = scanner1.next();
                while (!kasoletevalmis[0][0].equals("13")) {
                    System.out.println("\nInimese selle id-ga pole andmebaasis. Proovige uuesti.");
                    kasoletevalmis[0][0] = scanner1.next();
                }
                System.out.println("Max Mustermann");
                do {
                    kasoletevalmis[0][1] = scanner1.next();
                    kasoletevalmis[0][2] = scanner1.next();
                    kasoletevalmis[0][3] = scanner1.next();
                    if (kasoletevalmis[0][1].equals("15913") && kasoletevalmis[0][2].equals("/") && kasoletevalmis[0][3].equals("22089")) {
                        System.out.println("\nÕige! Alustame.");
                    } else {
                        System.out.println("\nVale. Lugege veel kord juhend läbi ja sisestage ainult tulemusi\n1:59,136\nDNF\n2:20,89");
                    }
                } while ((!kasoletevalmis[0][1].equals("15913") || !kasoletevalmis[0][2].equals("/") || !kasoletevalmis[0][3].equals("22089")));
            } else if (sisestage.equals("ei")) {
                System.out.println("Lahe. Alustame.");
            } else {
                System.out.println("Sisestasiste midagi muud. Palun sisestage “Jah” või “Ei”");
            }
        } while (!sisestage.equals("jah") && !sisestage.equals("ei"));
        System.out.println("Hakake sisestama inimeste tulemuskaarte.\n");
        for (x = 0; x < 6; x++) {
            for (y = 0; y < 4; y++) {
                kontrollimiseks[x][y] = scanner1.next();
                if (y == 0) {
                    while (!kontrollimiseks[x][y].equals(tulemuskaartideinfo[x + 1][y + 1])) {
                        System.out.println("\nInimene selle id-ga pole andmebaasis järgmine. Proovige uuesti.");
                        kontrollimiseks[x][y] = scanner1.next();
                    }
                    System.out.println(tulemuskaartideinfo[x + 1][0]);
                } else if (kontrollimiseks[x][y].equals(tulemuskaartideinfo[x + 1][y + 1])) {
                    õiged++; kokku++;
                } else {
                    kokku++;
                }
            }
            System.out.println("\n");
        }
        System.out.printf("\n\n\n\n\nKatse on lõppenud. Teie tulemus on %d/%d.", õiged, kokku);
        if (õiged == kokku) {
            System.out.println(" Olete tubli!");
        } else {
            System.out.println(" Vead olid järgmised:");
            for (x = 0; x < 6; x++) {
                for (y = 0; y < 4; y++) {
                    if (!kontrollimiseks[x][y].equals(tulemuskaartideinfo[x + 1][y + 1])) {
                        vigadejärjekorranumber++;
                        System.out.printf("\n%d. Võistleja nimi: %s\n   Võistleja id: %s\n   Kus tekis viga: %s\n   Teie sisestasite: %s\n   Õige vastus: %s\n", vigadejärjekorranumber, tulemuskaartideinfo[x + 1][0], tulemuskaartideinfo[x + 1][1], tulemuskaartideinfo[0][y + 1], kontrollimiseks[x][y], tulemuskaartideinfo[x + 1][y + 1]);
                    }
                }
            }
        }
    }
}