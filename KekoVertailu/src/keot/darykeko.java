
package keot;

/**
 *
 * @author Riikka
 */
public class darykeko {

    private int d;
    private int heapSize;
    private int[] keko;
    
    /**
     * Luo uuden d-ary -keon, missä lasten lukumäärä on d
     * @param d lasten lukumäärä
     */
    public darykeko(int d){
        this.d=d;
        this.heapSize=0;
        this.keko=new int[20];
    }
    /**
     * Palauttaa keon pienimmän alkion.
     * @return keon pienin alkio tai -1 jos keko on tyhjä
     */
    public int findMin() {
        if(heapSize==0){
            return -1;
        }
        return keko[0];   
    }
    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    public void deleteMin() {
        keko[0]=keko[heapSize-1];
        heapSize--;
        heapify(0);
    }
    /**
     * Vähentää indeksin i arvon d:ksi.
     * @param i indeksi, josta arvo muutetaan
     * @param d uusi arvo
     */
    public void decreaseKey(int i, int d) {
        if(keko[i]>d && d>0){
            keko[i]=d;
            while(i>0&&keko[parent(i)]>keko[i]){
                int apu=keko[i];
                keko[i]=keko[parent(i)];
                keko[parent(i)]=apu;
                i=parent(i);
            }
        }
    }
    /**
     * Lisää kekoon arvon key säilyttäen kekoehdon.
     * @param key kekoon lisättävä arvo
     */
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
     * Palauttaa indeksin key vanhemman indeksin.
     * @param key solmu, jonka vanhempi halutaan selvittää
     * @return vahnemman indeksi
     */
    private int parent(int key){
        return (int)Math.floor((key-1)/d);
    }
    /**
     * Korjaa kekoehdon kohdasta key lähtien.
     * @param key kohta, josta lähtien kekoehto korjataan
     */
    private void heapify(int key){
        int[] lapset=new int[d];
        for(int i=0;i<d;i++){
            lapset[i]=d*key+1+i;
        }
        int indeksi = etsiPienin(lapset);
        if(indeksi!=-1 && keko[indeksi]<keko[key]){
            int apu=keko[indeksi];
            keko[indeksi]=keko[key];
            keko[key]=apu;
            heapify(indeksi);
        }
        
    }
    /**
     * Metodi etsii indeksitaulukon indeksin, jolla on keossa pienin arvo.
     * @param taulukko indeksitaulukko
     * @return indeksin, jollaon keossa pienin arvo
     */
    private int etsiPienin(int[] taulukko){
        int pienin=Integer.MAX_VALUE;
        int pienindeksi=-1;
        for(int i=0;i<d;i++){
            if(taulukko[i]<heapSize){
                if(keko[taulukko[i]]<pienin){
                    pienin=keko[taulukko[i]];
                    pienindeksi=taulukko[i];
                }
            }
        }
        return pienindeksi;
    }
    //metodi testejä varten
    public int[] getKeko(){
        return keko;
    }
    //metodi testejä varten
    public int getSize(){
        return heapSize;
    }
}
