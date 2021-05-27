package View;

import Model.Dyrektor;
import Model.Handlowiec;
import Model.Pracownik;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pk44455
 */
public class View {
    
    
    public void showDyrektor(Dyrektor d)
    {
        pesel();
        System.out.println(d.getPesel());
        imie();
        System.out.println(d.getImie());
        nazwisko();
        System.out.println(d.getNazwisko());
        stanowisko();
        System.out.println(d.getStanowisko());
        wynagrodzenie();
        System.out.println(d.getWynagrodzenie());
        numer_tel();
        System.out.println(d.getNumer_tel());
        dodatek();
        System.out.println(d.getDodatek());
        karta();
        System.out.println(d.getKarta());
        limit_kosztow();
        System.out.println(d.getLimit_kosztow());
    }
    
    public void showHandlowiec(Handlowiec h)
    {
        pesel();
        System.out.println(h.getPesel());
        imie();
        System.out.println(h.getImie());
        nazwisko();
        System.out.println(h.getNazwisko());
        stanowisko();
        System.out.println(h.getStanowisko());
        wynagrodzenie();
        System.out.println(h.getWynagrodzenie());
        numer_tel();
        System.out.println(h.getNumer_tel());
        prowizja();
        System.out.println(h.getProwizja());
        limit_prowizji();
        System.out.println(h.getLimit_prowizji());
    }
    
    public void showDyrNoPesel(Dyrektor d)
    {
        imie();
        System.out.println(d.getImie());
        nazwisko();
        System.out.println(d.getNazwisko());
        stanowisko();
        System.out.println(d.getStanowisko());
        wynagrodzenie();
        System.out.println(d.getWynagrodzenie());
        numer_tel();
        System.out.println(d.getNumer_tel());
        dodatek();
        System.out.println(d.getDodatek());
        karta();
        System.out.println(d.getKarta());
        limit_kosztow();
        System.out.println(d.getLimit_kosztow());
    }
    
    public void showHanNoPesel(Handlowiec h)
    {
        imie();
        System.out.println(h.getImie());
        nazwisko();
        System.out.println(h.getNazwisko());
        stanowisko();
        System.out.println(h.getStanowisko());
        wynagrodzenie();
        System.out.println(h.getWynagrodzenie());
        numer_tel();
        System.out.println(h.getNumer_tel());
        prowizja();
        System.out.println(h.getProwizja());
        limit_prowizji();
        System.out.println(h.getLimit_prowizji());
    }
    
    public void menu()
    {
        System.out.println("MENU");
        System.out.println("    1. Lista pracowników");
        System.out.println("    2. Dodaj pracownika");
        System.out.println("    3. Usuń pracownika");
        System.out.println("    4. Kopia zapasowa\n");
    }
    
    public void who()
    {
        System.out.println("2.Dodaj pracownika\n");
        System.out.print("\t[D]yrektor/[H]handlowiec:\t");
    }
    
    public void strips()
    {
         System.out.println("----------------------------------------------------");
    }
    
    public void pesel()
    {
        System.out.print("\tIdentyfikator PESEL\t:\t");
    }
    
    public void imie()
    {
        System.out.print("\tImie\t\t\t:\t");
    }
    
    public void nazwisko()
    {
        System.out.print("\tNazwisko\t\t:\t");
    }
    
    public void stanowisko()
    {
        System.out.print("\tStanowisko\t\t:\t");
    }
    
    public void wynagrodzenie()
    {
        System.out.print("\tWynagrodzenie (zl)\t:\t");
    }
    
    public void numer_tel()
    {
        System.out.print("\tNumer telefonu\t\t:\t");
    }
    
    public void dodatek()
    {
        System.out.print("\tDodatek sluzbowy (zl)\t:\t");
    }
    
    public void karta()
    {
        System.out.print("\tKarta służbowa numer\t:\t");
    }
    
    public void limit_kosztow()
    {
        System.out.print("\tLimit kosztów miesiąc\t:\t");
    }
    
    public void prowizja()
    {
        System.out.print("\tProwizja (%)\t\t:\t");
    }
    
    public void limit_prowizji()
    {
        System.out.print("\tLimit prowizji/miesiac (zl)\t:\t");
    }
    
    public void pesel_exist()
    {
        System.err.println("Osoba z podanym peselem juz na liscie!");
    }
    
    public void wrong_letter()
    {
        System.err.println("Podales zly znak");

    }
    
    public void final_add()
    {
        System.out.println("[Enter]-zapisz");
        System.out.println("[Q]-porzuc");
    }
    
    public void final_workers()
    {
        System.out.println("[Enter]-nastepny");
        System.out.println("[Q]-powrot");
    }
    
    public void cancel()    
    {
        System.out.println("Pracownik nie zostanie dodany");
    }
    
    public void no_person()
    {
        System.err.println("Nie ma takiego pracownika!");
    }
    
    public void final_accept()
    {
        System.out.println("[Enter]-potwierdz");
        System.out.println("[Q]-porzuc");
    }
    
    public void serial_start()
    {
        System.out.print("\t[Z]achowaj/[O]dtworz\t:\t");
    }
   
    public void chose_comp()
    {
        System.out.print("\tKompresja [G]zip/[Z]ip\t:\t");
    }
    
    public void nazwa_pliku()
    {
        System.out.print("\tNazwa pliku\t:\t");
    }
    
    public void position(int i, int j)
    {
        System.out.println("\t\t\t\tPozycja: [" + i + "/" + j + "]");
    }
    
    public void error(Exception e)
    {
        System.err.println("Error: " + e);
    }
    
    public void zero()
    {
        System.out.println("Brak pracownikow na lisice");
    }
    
    public void enter()
    {
        System.out.println("");
    }
    
    public void too_short()
    {
        System.err.println("Za krotki pesel");
    }
}
