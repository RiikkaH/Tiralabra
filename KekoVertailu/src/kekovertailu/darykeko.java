
package kekovertailu;

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
        keko[0]=keko[heapSize];
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
            heapify(i);
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
        heapSize++;
        int i=heapSize;
        while(i>0 && parent(i)>key){
            keko[i]=keko[parent(i)];
            i=parent(i);
        }
        keko[i]=key;
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
    public void heapify(int key){
        int[] lapset=new int[d];
        for(int i=0;i<d;i++){
            lapset[i]=d*key+1+i;
        }
        int indeksi = etsiSuurin(lapset);
        if(keko[indeksi]<keko[key]){
            int apu=keko[indeksi];
            keko[indeksi]=keko[key];
            keko[key]=apu;
            heapify(indeksi);
        }
        
    }

    private int etsiSuurin(int[] taulukko){
        int suurin=0;
        int suurinindeksi=taulukko[0];
        //taulukossa on siis keon indeksejä
        for(int i=0;i<taulukko.length;i++){
            if(taulukko[i]<heapSize){
                if(keko[taulukko[i]]>suurin){
                    suurin=keko[taulukko[i]];
                    suurinindeksi=taulukko[i];
                }
            }
        }
        return suurinindeksi;
    }
}
