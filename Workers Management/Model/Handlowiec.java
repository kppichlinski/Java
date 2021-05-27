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
public class Handlowiec extends Pracownik implements Serializable {

        BigDecimal prowizja;
        BigDecimal limit_prowizji;

        public Handlowiec(String pesel, String imie, String nazwisko, String stanowisko, BigDecimal wynagrodzenie,
                String numer_tel, BigDecimal prowizja, BigDecimal limit_prowizji) {
            super(pesel, imie, nazwisko, stanowisko, wynagrodzenie, numer_tel);
            this.prowizja = prowizja;
            this.limit_prowizji = limit_prowizji;
        }
        
        public BigDecimal getProwizja()
        {
            return prowizja;
        }
        
        public BigDecimal getLimit_prowizji()
        {
            return limit_prowizji;
        }
    }
