/*
Tato aplikacia bola urobena v marci 2020 pre studenta pod menom
 Martin Repa, ktory za nu nezaplatil .

Zahradnictvo

Hlavny program
- vytvorte a naplnte zoznam rastlin v zahradnictve (aspon 5 poloziek)
- vytvorte prazdny nakupony zoznam pre zakaznika
- privitajte zakaznika a ponuknite mu dostupne rastliny


*/

package sample;

import java.util.Scanner; // ttrieda Scanner sa pouziva pre vstup z klavesnice

public class Main {

    // hlavna metoda
    public static void main(String[] args) {
        Scanner vstup = new Scanner(System.in);
        Rastlina[] zoznam = nacitajRastliny();  // zoznam rastlin
        int pocet = zoznam.length;  // pocet rastlin

        //zoznam darcekov pri nakupe
        String[] darceky = {"Hnojivo", "Metlicka", "Kamienky na zdobenie", "Kvetinac", "Hrablicky"};

        // zaciatok
        System.out.println("Vitame vas v nasom obchode");
        System.out.println("--------------------------");
        System.out.println();
        System.out.println("Zoznam rastlin ktore su v ponuke, cena, pocet kusov a pouzitie: ");

        // vypis rastlin ktore sme nacitali - detailny
        for (int i = 0; i < pocet; i++) {
            System.out.print(" " + (i + 1) + ".  ");
            zoznam[i].vypis();
        }

        System.out.println("Vyberte si rastlinku < 1 ; " + pocet + " > :");
        int cislo = 0;   // vybrane cislo rastliny pre objednanie

        do {
            cislo = vstup.nextInt();
            if (cislo >= 1 && cislo <= pocet) { // ak sme zadali spravne cislo -> vypisem detaily
                System.out.println("Vybrali ste si rastlinku " + zoznam[cislo - 1].getNazov());
                System.out.println("   pocet kusov na sklade: " + zoznam[cislo - 1].getMnozstvo());
            } else {
                // informacia o zlom vybere cisla a ziadost o opakovanie
                System.out.println("Nespravny vstup, vyberte cislo z intervalu < 1 ; " + pocet + " > :");
            }
        } while (cislo < 1 || cislo > pocet); // cyklus skonci ak zadame spravne cislo


        int index = cislo - 1;  // pole je cislovane od 0 a preto vybrane cislo znizime o 1
        vstup.nextLine(); // odstranime enter z buffera

        System.out.println("\nChcete viac ako jeden kus? (a/n)");
        String text = "";
        int pocetks = 0;   // prednastaveny pocet kusov

        do {
            text = vstup.nextLine();    // zadame text a vytiehneme z neho prve pismeno
            if (text.length() > 0 && text.charAt(0) == 'n') {  // ak prve pismeno je n
                pocet = 1;      // chceme objednat 1 ks rastliny
                break;
            } else if (text.length() > 0 && text.charAt(0) == 'a') {  // ak prve pismeno je a, ukoncime cyklus
                break;
            } else
                System.out.println("Zly vstup, zadajte a/n :");
        } while (true);

        if (pocet != 1) {       // ak sme stlacili predtym am tak ideme zadat pocet ks
            System.out.println("Zadajte pocet kusov (max: " + zoznam[index].getMnozstvo() + ") :");

            do {  // cyklus sa opakuje dovtedy, pokial nezadame spravny pocet ks
                pocet = vstup.nextInt();
                if (pocet < 1 || pocet > zoznam[index].getMnozstvo()) {  // pocet musi byt aspon jedna a max tolko kolko je na sklade
                    System.out.println("Zly vstup! Zadaj pocet este raz : ");
                }
            } while (pocet < 1 || pocet > zoznam[index].getMnozstvo());

        }
        int zostatok=zoznam[index].getMnozstvo()-pocet;  // zistime kolko ks rastliny ostalo na sklade
        zoznam[index].setMnozstvo(zostatok);   // aktualizujeme zoznam

        int darcek = -1;  // cislo darceka, -1 znamena ze nie je darcek

        if (pocet > 10) {  // ak pocet ks je viac ako 10, ideme vyberat darcek
            System.out.println("Vybrali ste si viac ako 10 ks a preto si vyberte darcek k objednavke:");

            for (int i = 0; i < darceky.length; i++) {  // vypis darcekov na obrazovku
                System.out.println((i + 1) + ". " + darceky[i]);
            }

            System.out.println("Zadaj cislo darceka < 1 ; " + darceky.length + "> :");

            do { // cyklus sa opakuje dovtedy, pokial nezadame existujuce silo darceka
                cislo = vstup.nextInt();
                darcek = cislo - 1;
                if (cislo < 1 || cislo > darceky.length) {
                    System.out.println("   Nespravne cislo darceka, zadajte znova: ");
                }
            } while (cislo < 1 || cislo > darceky.length);

        }


        double cena = zoznam[index].getCena() * pocet;  // vypocet ceny objednavky
        String cenaText = String.format("%.2f", cena);  // zaokruhlenie ceny na 2 desatinne miesta

        // zaverecny vypis objednavky
        System.out.println("\n---------------------------");
        System.out.println("Dakujeme za Vasu objednavku");
        System.out.println("\nzhrnutie objednavky:");
        System.out.println("  Nazov produktu: " + zoznam[index].getNazov());
        System.out.println("  Pocet kusov: " + pocet);
        System.out.println("  Celkova cena: " + cenaText + " Eur");

        // ak bol narok na darcek, vypiseme co si zakaznik vybral
        if (darcek >= 0) {
            System.out.println("\nK vasej objednavke Vam pribalime darcek: " + darceky[darcek]);
        }

    }
    // koniec hlavnej metody main

    // metoda vrati zoznam rastlin vo forme jednorozmerneho pola
    private static Rastlina[] nacitajRastliny() {
        Rastlina[] zoznam = new Rastlina[8];
        zoznam[0] = new Rastlina("Ruza", Pouzitie.VONKAJSIE, 3.10, 57);
        zoznam[1] = new Rastlina("Orchidea", Pouzitie.VNUTORNE, 8.50, 5);
        zoznam[2] = new Rastlina("Fikus", Pouzitie.VNUTORNE, 12, 10);
        zoznam[3] = new Rastlina("Lekno biele", Pouzitie.VODNE_RASTLINY, 4.30, 2);

        zoznam[4] = new Rastlina("Brectan popinavy", Pouzitie.VONKAJSIE, 12.10, 41);
        zoznam[5] = new Rastlina("Svokrin jazyk", Pouzitie.VNUTORNE, 10.40, 60);
        zoznam[6] = new Rastlina("Potosovec zlaty", Pouzitie.VONKAJSIE, 25, 6);
        zoznam[7] = new Rastlina("Vova", Pouzitie.VODNE_RASTLINY, 18.50, 4);
        // tu sa mozu pridat dalsie rastliny
        return zoznam;
    }
}

enum Pouzitie {  // enumeracny typ, na vyber z 3 moznosti
    VONKAJSIE, VNUTORNE, VODNE_RASTLINY
}

//trieda Rastlina - reprezentuje jednu konkretnu rastlinu
class Rastlina {
    //clenske premenne
    private String nazov;
    private Pouzitie pouzitie;   // Enum typ, mame na vyber len z uvedenych moznosti
    private double cena;         // cena je desatinne cislo
    private int mnozstvo;

    // konstruktor
    public Rastlina(String nazov, Pouzitie pouzitie, double cena, int mnozstvo) {
        this.nazov = nazov;
        this.pouzitie = pouzitie;
        this.cena = cena;
        this.mnozstvo = mnozstvo;
    }

    public String getNazov() {
        return nazov;
    }

    public Pouzitie getPouzitie() {
        return pouzitie;
    }

    public double getCena() {
        return cena;
    }

    public int getMnozstvo() {
        return mnozstvo;
    }

    // metoda sluzi na aktualizaciu mnozstva ks po vykonani objednavky
    public void setMnozstvo(int mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    // vypis rastliny - s detailami
    public void vypis() {
        System.out.println("  Nazov: " + nazov + "   Cena: " + cena + "   Mnozstvo: " + mnozstvo + "   Pouzitie :" + pouzitie);
    }
}
// koniec triedy Rastlina
