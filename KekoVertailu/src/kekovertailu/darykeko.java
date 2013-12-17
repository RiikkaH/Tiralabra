
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class darykeko implements Keko{

    private int d;
    private int heapSize;
    private int[] keko;
    
    public darykeko(int d){
        this.d=d;
        this.heapSize=0;
        this.keko=new int[20];
    }
    /**
     * Palauttaa keon pienimmän alkion.
     * @return keon pienin alkio tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        if(heapSize==0){
            return -1;
        }
        return keko[0];   
    }
    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    @Override
    public void deleteMin() {
        keko[0]=keko[heapSize];
        heapSize--;
        heapify(0);
    }

    @Override
    public void decreaseKey(int i, int d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(int key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    /**
     * Korjaa kekoehdon kohdasta key lähtien.
     * @param key kohta, josta lähtien kekoehto korjataan
     */
    public void heapify(int key){
        int[] lapset=new int[d];
        for(int i=0;i<d;i++){
            lapset[i]=d*i+1+i;
        }
        
        /*
        int l=left(key);
        int r=right(key);
        if(r<=heapSize){
            int smallest;
            if(keko[l]<keko[r]){
                smallest=l;
            }else{
                smallest=r;
            }if(keko[key]>smallest){
                int apu=keko[smallest];
                keko[smallest]=keko[key];
                keko[key]=apu;
                heapify(smallest);
            }
        }else if(l==heapSize && keko[key]>keko[l]){
            int apu=keko[l];
            keko[l]=keko[key];
            keko[key]=apu;
        }*/
    }
}
