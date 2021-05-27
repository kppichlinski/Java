

import Model.Model;
import Model.Dyrektor;
import Model.Handlowiec;
import Model.Pracownik;
import View.View;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pk44455
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        View view = new View();
        Controller con = new Controller();
        Model model = new Model();
        boolean end = true;

        while (end) {

            view.menu();

            switch (con.scanSwitch()) {
                case 1:
                    if(model.getSize()==0)
                    {
                        view.zero();
                        break;
                    }
                    int number = 1;
                    int max = model.getSize();
                    for (Pracownik i : model.getValues()) {
                        if (i instanceof Dyrektor) {
                            view.showDyrektor((Dyrektor)i);
                        } else if (i instanceof Handlowiec) {
                            view.showHandlowiec((Handlowiec)i);
                        }
                        view.enter();
                        view.position(number, max);
                        number++;
                        view.final_workers();
                        String a = con.scanString();
                        if (a.equals("")) {
                            continue;
                        } else if (a.equals("Q")) {
                            break;
                        } else {
                            view.wrong_letter();
                            break;
                        }
                    }
                    break;
                case 2:
                    view.who();
                    String who = con.scanString();
                    if (!who.equals("D") && !who.equals("H")) {
                        view.wrong_letter();
                        break;
                    }
                    view.strips();
                    view.pesel();
                    String pesel = con.scanString();
                    if(pesel.length()!=11)
                    {
                        view.too_short();
                        break;
                    }
                    if (model.containsPesel(pesel)) {
                        view.pesel_exist();
                        break;
                    }
                    view.imie();
                    String imie = con.scanString();
                    view.nazwisko();
                    String nazwisko = con.scanString();
                    view.wynagrodzenie();
                    BigDecimal wynagrodzenie;
                    try {
                        wynagrodzenie = con.scanInt();
                    } catch (Exception e) {
                        view.error(e);
                        break;
                    }
                    view.numer_tel();
                    String numer_tel = con.scanString();
                    if(numer_tel.equals(""))
                    {
                        numer_tel="-brak-";
                    }
                    if (who.equals("D")) {
                        view.dodatek();
                        BigDecimal dodatek;
                        try {
                            dodatek = con.scanInt();
                        } catch (Exception e) {
                            view.error(e);
                            break;
                        }
                        view.karta();
                        String karta = con.scanString();
                        view.limit_kosztow();
                        BigDecimal limit_kosztow;
                        try {
                            limit_kosztow = con.scanInt();
                        } catch (Exception e) {
                            view.error(e);
                            break;
                        }
                        view.strips();
                        view.final_add();
                        String a = con.scanString();
                        if (a.equals("")) {
                            Dyrektor d = new Dyrektor(pesel, imie, nazwisko, "Dyrektor", wynagrodzenie, numer_tel,
                                    dodatek, karta, limit_kosztow);

                            model.put(pesel, d);
                        } else if (a.equals("Q")) {
                            view.cancel();
                            break;
                        } else {
                            view.wrong_letter();
                            break;
                        }

                    } else if (who.equals("H")) {
                        view.prowizja();
                        BigDecimal prowizja;
                        try {
                            prowizja = con.scanInt();
                        } catch (Exception e) {
                            view.error(e);
                            break;
                        }
                        view.limit_prowizji();
                        BigDecimal limit_prowizji;
                        try {
                            limit_prowizji = con.scanInt();
                        } catch (Exception e) {
                            view.error(e);
                            break;
                        }
                        view.strips();
                        view.final_add();
                        String a = con.scanString();
                        if (a.equals("")) {
                            Handlowiec h = new Handlowiec(pesel, imie, nazwisko, "Handlowiec", wynagrodzenie, numer_tel,
                                    prowizja, limit_prowizji);
                            model.put(pesel, h);
                        } else if (a.equals("Q")) {
                            view.cancel();
                            break;
                        } else {
                            view.wrong_letter();
                            break;
                        }
                    }

                    break;
                case 3:
                    view.pesel();
                    String PESEL = con.scanString();
                    view.strips();
                    if (model.containsPesel(PESEL)) {
                        if (model.get(PESEL) instanceof Dyrektor) {
                            view.showDyrNoPesel((Dyrektor) model.get(PESEL));
                        } else {
                            view.showHanNoPesel((Handlowiec) model.get(PESEL));
                        }
                        view.strips();
                        view.final_accept();
                        String a = con.scanString();
                        if (a.equals("")) {
                            model.remove(PESEL);
                        } else if (a.equals("Q")) {
                            break;
                        } else {
                            view.wrong_letter();
                            break;
                        }
                    } else {
                        view.no_person();
                    }
                    break;
                case 4:
                    view.serial_start();
                    String a = con.scanString();
                    view.strips();
                    if (a.equals("Z")) {
                        view.chose_comp();
                        String which = con.scanString();
                        if (which.equals("Z")) {
                            view.nazwa_pliku();
                            String file = con.scanString();
                            view.strips();
                            view.final_accept();
                            String abc = con.scanString();
                            if (abc.equals("")) {
                                try {
                                    model.saveZip(file);
                                } catch (Exception e) {
                                    view.error(e);
                                }
                            } else if (abc.equals("Q")) {
                                break;
                            } else {
                                view.wrong_letter();
                                break;
                            }

                        } else if (which.equals("G")) {
                            view.nazwa_pliku();
                            String file = con.scanString();
                            view.strips();
                            view.final_accept();
                            String abc = con.scanString();
                            if (abc.equals("")) {
                                try {
                                    model.saveGzip(file);
                                } catch (Exception e) {
                                    view.error(e);
                                }
                            } else if (abc.equals("Q")) {
                                break;
                            } else {
                                view.wrong_letter();
                                break;
                            }
                        } else {
                            view.wrong_letter();
                            break;
                        }
                    } else if (a.equals("O")) {
                        view.nazwa_pliku();
                        String file = con.scanString();
                        view.strips();
                        view.final_accept();
                        String abc = con.scanString();
                        if (abc.equals("")) {
                            try {
                                model.openFile(file);
                            } catch (Exception e) {
                                view.error(e);
                            }
                        } else if (abc.equals("Q")) {
                            break;
                        } else {
                            view.wrong_letter();
                            break;
                        }

                    } else {
                        view.wrong_letter();
                        break;
                    }
                    break;
                default:
                    end = false;
            }
        }

    }

    BigDecimal scanInt() {
        Scanner scn = new Scanner(System.in);
        return scn.nextBigDecimal();
    }

    int scanSwitch() {
        Scanner scn = new Scanner(System.in);
        return scn.nextInt();
    }

    String scanString() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine();
    }
    
   

}
