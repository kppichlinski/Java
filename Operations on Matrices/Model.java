
public class Model {
    
    private int w;
    private int k;
    private int[][] matrix;
    
    Model(int w,int k,int[][] matrix)
    {
        this.w=w;
        this.k=k;
        this.matrix=matrix;
    }
    
    Model()
    {
        this.w=0;
        this.k=0;
        this.matrix=null;
    }
    
     int getRows()
    {   
        return w;
    }
    
   int getColumns()
    {   
        return k;
    }
   
   int[][] getMatrix()
   {
       return matrix;
   }
   
   boolean getSize()
   {
       if(w==0)
       {
           return false;
       }
       return true;
    }
    
}