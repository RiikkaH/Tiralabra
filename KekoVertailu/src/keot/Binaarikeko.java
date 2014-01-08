
package keot;

/**
 *
 * @author Riikka
 */
public class Binaarikeko implements Keko{
    
    private int[] keko;
    //ellen sitten tekisi vierekkäistä int[]:iä johon laittaisin solmujen numerot?
    private int[] node;
    private int heapSize;
    
    public Binaarikeko(){
        keko=new int[20];
        node=new int[20];
        heapSize=0;
    }
    
    private void heapifyWithNode(int key){
        int l=left(key);
        int r=right(key);
        if(r<heapSize){
            int smallest;
            if(keko[l]<keko[r]){
                smallest=l;
            }else{
                smallest=r;
            }if(keko[key]>keko[smallest]){
                int apu=keko[smallest];
                int apunode=node[smallest];
                keko[smallest]=keko[key];
                node[smallest]=node[key];
                keko[key]=apu;
                node[key]=apunode;
                heapifyWithNode(smallest);
            }
        }else if(l==heapSize-1 && keko[key]>keko[l]){
            int apu=keko[l];
            int apunode=node[l];
            keko[l]=keko[key];
            node[l]=node[key];
            keko[key]=apu;
            node[key]=apunode;
        }
    }
    
    /**
     * Korjaa kekoehdon solmusta key alaspäin.
     * @param key somu, josta kekoehto halutaan korjata
     */
    private void heapify(int key) {
        int l=left(key);
        int r=right(key);
        if(r<heapSize){
            int smallest;
            if(keko[l]<keko[r]){
                smallest=l;
            }else{
                smallest=r;
            }if(keko[key]>keko[smallest]){
                int apu=keko[smallest];
                keko[smallest]=keko[key];
                keko[key]=apu;
                heapify(smallest);
            }
        }else if(l==heapSize-1 && keko[key]>keko[l]){
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
    public int findMinNode(){
        if(heapSize>0){
            return node[0];
        }
        return -1;
    }
    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    @Override
    public void deleteMin() {
        keko[0]=keko[heapSize-1];
        heapSize--;
        heapify(0);
    }
    
    public void deleteMinJaNode(){
        keko[0]=keko[heapSize-1];
        node[0]=node[heapSize-1];
        heapSize--;
        heapify(0);
    }
    
    /**
     * Korvaa keon somun i arvon d:llä (jos on pienempi kuin keon i:nnes alkio)
     * ja korjaa kekoehdon.
     * @param i solmu, jonka arvoa pienennetään
     * @param d uusi arvo
     */
    public void decreaseKey(int i,int d) {
        if(keko[i]>d && d>0){
            keko[i]=d;
            while(i>0 && keko[parent(i)]>keko[i]){
                int apu=keko[i];
                keko[i]= keko[parent(i)];
                keko[parent(i)]=apu;
                i=parent(i);
            }
        }
    }
    
    public void insertWithNode(int key, int n){
        if(heapSize==keko.length){
            int[] uusikeko=new int[keko.length*2];
            int[] uusinode=new int[keko.length*2];
            for(int i=0;i<keko.length;i++){
                uusikeko[i]=keko[i];
                uusinode[i]=node[i];
            }
            keko=uusikeko;
            node=uusinode;
        }
        int i=heapSize;
        while(i>0 && keko[parent(i)]>key){
            keko[i]=keko[parent(i)];
            node[i]=node[parent(i)];
            i=parent(i);
        }
        keko[i]=key;
        node[i]=n;
        heapSize++;
    }
    /**
     * Lisää kekoon solmun arvolla key.
     * @param key arvo, joka kekoon halutaan lisätä
     */
    @Override
    public void insert(int key) {
        if(heapSize==keko.length){
            int[] uusikeko=new int[keko.length*2];
            for(int i=0;i<keko.length;i++){
                uusikeko[i]=keko[i];
            }
            keko=uusikeko;
        }
        int i=heapSize;
        while(i>0 && keko[parent(i)]>key){
            keko[i]=keko[parent(i)];
            i=parent(i);
        }
        keko[i]=key;
        heapSize++;
    }
    /**
     * Etsii keosta noden arvoa n vastaavan somun.
     * @param n noden arvo jonka perusteella arvoa etsitään
     * @return keon indeksi, josta n löytyy
     */
    public int etsiKeosta(int n){
        int indeksi=0;
        for(int i=0;i<heapSize;i++){
            if(node[i]==n){
                indeksi=i;
                return indeksi;
            }
        }
        return indeksi;
    }
    
    /**
     * Palauttaa solmun i vanhemman, tai i:n jos se on juuri
     * @param i solmu jonka vanhempi halutaan selvittää
     * @return i:n vanhempi
     */
    private int parent(int i){
        return (int)Math.floor(((double)i-1)/2.0);
    }
    /**
     * Palauttaa solmun i vasemman lapsen indeksin.
     * @param i solmu, jonka vasen lapsi halutaan selvittää
     * @return i:n vasen lapsi
     */
    private int left(int i){
        return 2*i+1;
    }
    /**
     * Palauttaa solmun i oikean lapsen indeksin.
     * @param i solmu, jonka oikea lapsi halutaan selvittää
     * @return i:n oikea lapsi
     */
    private int right(int i){
        return 2*i+2;
    }
    //metodi testejä varten
    public int[] getKeko(){
        return keko;
    }
    //metodi testejä varten
    public int[] getNode(){
        return node;
    }
    //metodi testejä varten
    public int getSize(){
        return heapSize;
    }
}
