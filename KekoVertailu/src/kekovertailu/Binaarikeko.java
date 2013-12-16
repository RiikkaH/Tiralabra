

package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binaarikeko implements Keko{
    
    private int[] keko;
    private int heapSize;
    
    public Binaarikeko(){
        keko=new int[20];
        heapSize=0;
    }

    @Override
    public void heapify() {
        
    }

    @Override
    public int findMin() {
        
        return 0;
    }

    @Override
    public void deleteMin() {
        
    }

    @Override
    public void decreaseKey() {
        
    }

    @Override
    public void insert(int key) {
        if(heapSize==keko.length){
            int[] uusikeko=new int[keko.length];
            for(int i=0;i<keko.length;i++){
                uusikeko[i]=keko[i];
            }
            keko=uusikeko;
        }
        
        heapSize++;
    }

    @Override
    public Keko merge(Keko k) {
        
        return null;
    }
    
    
}
