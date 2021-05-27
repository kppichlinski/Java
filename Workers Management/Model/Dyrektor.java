/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import Model.Pracownik;

/**
 *
 * @author pk44455
 */
public class Dyrektor extends Pracownik implements Serializable {

        BigDecimal dodatek;
        String karta;
        BigDecimal limit_kosztow;
       
        public Dyrektor(String pesel, String imie, String nazwisko, String stanowisko, BigDecimal wynagrodzenie,
                String numer_tel, BigDecimal dodatek, String karta, BigDecimal limit_kosztow) {
            super(pesel, imie, nazwisko, stanowisko, wynagrodzenie, numer_tel);
            this.dodatek = dodatek;
            this.karta = karta;
            this.limit_kosztow = limit_kosztow;
        }
        
        public BigDecimal getDodatek()
        {
            return dodatek;
        }
        
        public String getKarta()
        {
            return karta;
        }
        
        public BigDecimal getLimit_kosztow()
        {
            return limit_kosztow;
        }
        
    }
