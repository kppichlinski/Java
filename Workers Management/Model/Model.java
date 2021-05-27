package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pk44455
 */
public class Model implements Serializable {
    
    private HashMap<String, Pracownik> map = new HashMap<String, Pracownik>();
    
    public int getSize()
    {
        return map.size();
    }
    
    public Collection<Pracownik> getValues()
    {
        return map.values();
    }
    
    public boolean containsPesel(String pesel)
    {
        return map.keySet().contains(pesel);
    }
    
    public void put(String s, Pracownik p)
    {
        map.put(s, p);
    }
    
    public Pracownik get(String s)
    {
        return map.get(s);
    }
    
    public void remove(String s)
    {
        map.remove(s);
    }
    
    public HashMap<String, Pracownik> returnMap()
    {
        return map;
    }
    
    public void saveZip(String file) throws FileNotFoundException, IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        fos = new FileOutputStream(file+".zip");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();
        fos.close();
    }

    public void saveGzip(String file) throws FileNotFoundException, IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        fos = new FileOutputStream(file+".gzip");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();
        fos.close();

    }
    
    public void openFile(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
        map = (HashMap<String, Pracownik>) ois.readObject();
        ois.close();
        fis.close();
    }

}
