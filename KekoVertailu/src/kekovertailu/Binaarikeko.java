
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
    /**
     * Korjaa kekoehdon solmusta key alaspäin.
     * @param key somu, josta kekoehto halutaan korjata
     */
    private void heapify(int key) {
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
        }
    }
    /**
     * Palauttaa keon pienimmän alkion, tai jos keko on tyhjä, palauttaa metodi
     * arvon -1.
     * @return keon pienin alkio tai -1, jos keko on tyhjä
     */
    @Override
    public int findMin() {
        if(heapSize>0){
            return keko[0];
        }
        return -1;
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
    /**
     * Korvaa keon somun i arvon d:llä (jos on pienempi kuin keon i:nnes alkio)
     * ja korjaa kekoehdon.
     * @param i solmu, jonka arvoa pienennetään
     * @param d uusi arvo
     */
    @Override
    public void decreaseKey(int i,int d) {
        if(keko[i]>d && d>0){
            keko[i]=d;
            while(i>0 && keko[parent(i)]>keko[i]){
                int apu=keko[i];
                keko[i]= keko[parent(i)];
                keko[parent(i)]=keko[i];
                i=parent(i);
            }
        }
    }
    /**
     * Lisää kekoon solmun arvolla key.
     * @param key arvo, joka kekoon halutaan lisätä
     */
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
        int i=heapSize;
        while(i>0 && parent(i)<key){
            keko[i]=keko[parent(i)];
            i=parent(i);
        }
        keko[i]=key;
    }

    /**
     * Yhdistää kekoon toisen keon k.
     * @param k keko joka halutaan yhtistää
     */
    
    public void merge(Keko k) {
        
    }
    /**
     * Palauttaa solmun i vanhemman, tai i:n jos se on juuri
     * @param i solmu jonka vanhempi halutaan selvittää
     * @return i:n vanhempi
     */
    private int parent(int i){
        return (int)Math.floor(((double)i)/2.0);
    }
    /**
     * Palauttaa solmun i vasemman lapsen, tai arvon -1 jos sitä ei ole.
     * @param i solmu, jonka vasen lapsi halutaan selvittää
     * @return i:n vasen lapsi tai -1 jos sitä ei ole
     */
    private int left(int i){
        if(2*i<=heapSize){
            return 2*i;
        }
        return -1;
    }
    /**
     * Palauttaa solmun i oikean lapsen, tai arvon -1 jos sitä ei ole.
     * @param i solmu, jonka oikea lapsi halutaan selvittää
     * @return i:n oikea lapsi tai -1 jos sitä ei ole
     */
    private int right(int i){
        if(2*i+1<=heapSize){
            return 2*i+1;
        }
        return -1;
    }
}
