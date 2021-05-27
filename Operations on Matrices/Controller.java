
import java.util.Scanner;

public class Controller {
   
   public static void main(String[] args) throws InterruptedException
 {
    View view = new View();
    Controller con = new Controller();
    
    Model m1 = new Model();
    Model m2 = new Model();
    Model m3 = new Model();
            
    boolean x=true;
    
    while(x)
    {
    
       System.out.println("MENU:");
       System.out.println("1: Wprowadz macierze wejsciowe");
       System.out.println("2: Operacje arytmetyczne");
       System.out.println("3: Wyswietl macierze");
       System.out.println("4: Zakoncz program");
        
    switch(con.getSwitch())
    {
        case 1:
            
            System.out.println("Podaj wymiary macierzy A: ");
            int w = con.scanRows();
            int k = con.scanColumns();
            int[][] numbers = con.scanNumbers(w, k);
            Model mA = new Model(w,k,numbers);
            m1 = mA;
            System.out.println("Podaj wymiary macierzy B: ");
            w = con.scanRows();
            k = con.scanColumns();
            numbers = con.scanNumbers(w, k);
            Model mB = new Model(w,k,numbers);
            m2 = mB;
            break;
            
        case 2:
            
            System.out.println("Operacje arytmetyczne: ");
            System.out.println("1: Transponuj macierze wejsciowe");
            System.out.println("2: Pomnoz macierze wejsciowe");
            System.out.println("3: Transponuj macierz wynikowa");
            System.out.println("4: Powrot");
            
            switch(con.getSwitch())
            {
                case 1:

                    if(m1.getSize() && m2.getSize())
                    {
                    m1 = con.transpose(m1);
                    m2 = con.transpose(m2);
                    }
                    else
                    {
                        System.err.println("Nie podales macierzy wejsciowych lub sa one puste. Nie mam jak ich transponowac.");
                    }
                    break;
                
                case 2:
                    
                   if(m1.getSize() && m2.getSize())
                   {
                   m3 = con.multiply(m1,m2);
                   }
                   else
                   {
                      System.err.println("Nie podales macierzy wejsciowych. Nie mam czego pomnozyc.");  
                   }
                   break;
                   
                case 3:
                    
                   if(m3.getSize())
                   {
                   m3 = con.transpose(m3);
                   }
                   else
                   {
                       System.err.println("Nie wykonales zadnej operacji. Nie masz jeszcze macierzy wynikowej.");
                   }
                   break;
                   
                case 4:
                    
                   break;
                   
                default:
                    
                    System.err.println("Zly numer");
                    break;
            }
            break;
         
        case 3:
            
            System.out.println("Wyswietl macierze:");
            System.out.println("1: Wyswietl macierze wejsciowe");
            System.out.println("2: Wyswietl macierz wynikowa");
            System.out.println("3: Powrot");
            
             switch(con.getSwitch())
            {
                 case 1:
                     
                    view.show_matrix(m1,m2);
                    break;
                    
                 case 2:
                    
                    view.show_matrix(m3);
                    break;
                    
                 case 3:
                    break;
                     
                 default:
                    
                    System.err.println("Zly numer");
                    break;
            
             }
            break;
            
        case 4:
           
            x=false;
            break;
            
        default:
            
            System.err.println("Zly numer");
            break;
            
    } 
    }
 }
    
   Model transpose(Model m)
   {
        int trans[][] = new int[m.getColumns()][m.getRows()];
        for (int i = 0; i < m.getRows(); i++)
        {
            for (int j = 0; j < m.getColumns(); j++)   
            {
                trans[j][i] = m.getMatrix()[i][j];
            }
        }
        
        Model fin = new Model(m.getColumns(),m.getRows(), trans);
        return fin;
   }
   
   Model multiply(Model mA,Model mB) throws InterruptedException
   {
      // Cm x n = A m x k * B k x n
       int first[][] = mA.getMatrix();
       int second[][] = mB.getMatrix();
       int fin[][] = new int[mA.getRows()][mB.getColumns()];
       if(mA.getColumns()!=mB.getRows())
       {
          System.err.println("Nie mozna pomnozyc tych macierzy");
       }
       else
       {
       int sum=0;
     
       for (int i = 0; i < mA.getRows(); i++)
       {
           for (int j = 0; j < mB.getColumns(); j++)
           {
               for (int z = 0; z < mB.getRows(); z++)
               {
                  sum += first[i][z]*second[z][j];
               }
               fin[i][j]=sum;
               sum=0;
           }
       }
       }
       Model multi = new Model(mA.getRows(),mB.getColumns(),fin);
       return multi;
       
   }
   
    int scanRows()
    {
        System.out.println("Podaj liczbe wierszy: ");
        Scanner scn = new Scanner(System.in);
        int w = scn.nextInt();
        return w;
    }
    
    int scanColumns()
    {   
        System.out.println("Podaj liczbe kolumn: ");
        Scanner scn = new Scanner(System.in);
        int k = scn.nextInt();
        return k;
    }
    
    int[][] scanNumbers(int a, int b)
    {
        System.out.println("Podaj po kolei liczby znajdujace sie w macierzy (przechodzenie po wierszach): ");
        int[][] d = new int[a][b];
        Scanner data = new Scanner(System.in);
        for(int i=0;i<a;i++)
        {
            for(int j=0;j<b;j++)
            {
                d[i][j] = data.nextInt();
            }
        }
        return d;
    }
    
    
    int getSwitch()
    {
     Scanner scn = new Scanner(System.in);
     System.out.println("Podaj numer polecenia: ");
     int s = scn.nextInt();
     return s;
    }
     
}
   

    
