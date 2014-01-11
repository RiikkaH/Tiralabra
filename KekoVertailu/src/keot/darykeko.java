package keot;

/**
 *
 * @author Riikka
 */
public class darykeko implements Keko{

    private final int d;
    private int heapSize;
    private Solmu[] keko;

    /**
     * Luo uuden d-ary -keon, missä lasten lukumäärä on d
     *
     * @param d lasten lukumäärä
     */
    public darykeko(int d) {
        this.d = d;
        this.heapSize = 0;
        this.keko = new Solmu[20];
    }

    /**
     * Palauttaa keon pienimmän alkion.
     *
     * @return keon pienin alkio tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        if (heapSize == 0) {
            return -1;
        }
        return keko[0].getArvo();
    }

    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    @Override
    public void deleteMin() {
        keko[0] = keko[heapSize - 1];
        keko[0].setIndeksi(0);
        heapSize--;
        heapify(0);
    }

    /**
     * Vähentää indeksin i arvon d:ksi.
     *
     * @param i indeksi, josta arvo muutetaan
     * @param n uusi arvo
     */
    public void decreaseKey(int i, int n) {
        if (keko[i].getArvo() > n && n > 0) {
            keko[i].setArvo(n);
            while (i > 0 && keko[parent(i)].getArvo() > keko[i].getArvo()) {
                Solmu apu = keko[i];
                keko[i] = keko[parent(i)];
                keko[parent(i)] = apu;
                keko[i].setIndeksi(i);
                keko[parent(i)].setIndeksi(parent(i));
                i = parent(i);
            }
        }
    }

    /**
     * Lisää kekoon arvon key säilyttäen kekoehdon.
     *
     * @param key kekoon lisättävä arvo
     */
    @Override
    public void insert(int key) {
        Solmu s=new Solmu(key,null,null,0);
        insert(s);
    }

    /**
     * Palauttaa indeksin key vanhemman indeksin.
     *
     * @param key solmu, jonka vanhempi halutaan selvittää
     * @return vahnemman indeksi
     */
    private int parent(int key) {
        return (int) Math.floor((key - 1) / d);
    }

    /**
     * Korjaa kekoehdon kohdasta key lähtien.
     *
     * @param key kohta, josta lähtien kekoehto korjataan
     */
    private void heapify(int key) {
        int[] lapset = new int[d];
        for (int i = 0; i < d; i++) {
            lapset[i] = d * key + 1 + i;
        }
        int indeksi = etsiPienin(lapset);
        if (indeksi != -1 && keko[indeksi].getArvo() < keko[key].getArvo()) {
            Solmu apu = keko[indeksi];
            keko[indeksi] = keko[key];
            keko[key] = apu;
            keko[indeksi].setIndeksi(indeksi);
            keko[key].setIndeksi(key);
            heapify(indeksi);
        }
    }

    /**
     * Metodi etsii indeksitaulukon indeksin, jolla on keossa pienin arvo.
     *
     * @param taulukko indeksitaulukko
     * @return indeksin, jollaon keossa pienin arvo
     */
    private int etsiPienin(int[] taulukko) {
        int pienin = Integer.MAX_VALUE;
        int pienindeksi = -1;
        for (int i = 0; i < d; i++) {
            if (taulukko[i] < heapSize) {
                if (keko[taulukko[i]].getArvo() < pienin) {
                    pienin = keko[taulukko[i]].getArvo();
                    pienindeksi = taulukko[i];
                }
            }
        }
        return pienindeksi;
    }

    public Solmu[] getKeko() {
        return keko;
    }

    public int getSize() {
        return heapSize;
    }
//
    @Override
    public void insert(Solmu s) {
        if (heapSize == keko.length) {
            Solmu[] uusikeko = new Solmu[keko.length * 2];
            for (int i = 0; i < keko.length; i++) {
                uusikeko[i] = keko[i];
            }
            keko = uusikeko;
        }
        int i = heapSize;
        while (i > 0 && keko[parent(i)].getArvo() > s.getArvo()) {
            keko[i] = keko[parent(i)];
            keko[i].setIndeksi(i);
            i = parent(i);
        }
        keko[i]=s;
        keko[i].setIndeksi(i);
        heapSize++;
    }

    @Override
    public Solmu findMinSolmu() {
        if(heapSize==0){
            return null;
        }
        return keko[0];
    }

    @Override
    public void decreaseKey(Solmu s, int d) {
        decreaseKey(s.getIndeksi(),d);
    }
}
