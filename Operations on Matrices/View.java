public class View {

   void show_matrix(Model x) throws InterruptedException
   {
       if(x.getRows()==0)
       {
           System.err.println("Nie wykonales zadnej operacji. Nie masz jeszcze macierzy wynikowej.");
           return;
       }
       
       System.out.println("");
       System.out.println("Macierz wynikowa:");
       for(int i = 0; i < x.getRows(); i++)
       {
           for(int j = 0; j < x.getColumns(); j++)
           {
               System.out.print(x.getMatrix()[i][j] + " ");
           }
           System.out.println("");
       }
       System.out.println("");
        
   }
   
   void show_matrix(Model x, Model y)
   {
       if(x.getRows()==0 && y.getRows()==0)
       {
           System.err.println("Macierze sa puste.");
           return;
       }
       
       System.out.println("");
       System.out.println("Macierz A:");
       
       for(int i = 0; i < x.getRows(); i++)
       {
           for(int j = 0; j < x.getColumns(); j++)
           {
               System.out.print(x.getMatrix()[i][j] + " ");
           }
           System.out.println("");
       }
       
       System.out.println("");
       System.out.println("Macierz B:");
       
        for(int i = 0; i < y.getRows(); i++)
       {
           for(int j = 0; j < y.getColumns(); j++)
           {
               System.out.print(y.getMatrix()[i][j] + " ");
           }
            System.out.println("");
       }
        System.out.println("");
   }
}
           
   