/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author pk44455
 */
public abstract class Pracownik implements Serializable {

        private String pesel;
        private String imie;
        private String nazwisko;
        private String stanowisko;
        private BigDecimal wynagrodzenie;
        private String numer_tel;
        
        Pracownik(String pesel, String imie, String nazwisko, String stanowisko, BigDecimal wynagrodzenie, String numer_tel)
        {
            this.pesel = pesel;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.stanowisko = stanowisko;
            this.wynagrodzenie = wynagrodzenie;
            this.numer_tel = numer_tel;
        }

        public String getPesel()
        {
            return pesel;
        }
        
        public String getImie()
        {
            return imie;
        }
        
        public String getNazwisko()
        {
            return nazwisko;
        }
        
        public String getStanowisko()
        {
            return stanowisko;
        }
        
        public BigDecimal getWynagrodzenie()
        {
            return wynagrodzenie;
        }
        
        public String getNumer_tel()
        {
            return numer_tel;
        }
    }
