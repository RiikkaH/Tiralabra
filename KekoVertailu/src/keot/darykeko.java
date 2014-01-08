package keot;

/**
 *
 * @author Riikka
 */
public class darykeko implements Keko, SolmutonKeko {

    private final int d;
    private int heapSize;
    private int[] keko;
    private int[] node;

    /**
     * Luo uuden d-ary -keon, missä lasten lukumäärä on d
     *
     * @param d lasten lukumäärä
     */
    public darykeko(int d) {
        this.d = d;
        this.heapSize = 0;
        this.keko = new int[20];
        this.node = new int[20];
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
        return keko[0];
    }

    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    @Override
    public void deleteMin() {
        keko[0] = keko[heapSize - 1];
        heapSize--;
        heapify(0);
    }

    /**
     * Vähentää indeksin i arvon d:ksi.
     *
     * @param i indeksi, josta arvo muutetaan
     * @param d uusi arvo
     */
    public void decreaseKey(int i, int n) {
        decreaseKeyWithNode(i,n,false);
    }

    /**
     * Lisää kekoon arvon key säilyttäen kekoehdon.
     *
     * @param key kekoon lisättävä arvo
     */
    @Override
    public void insert(int key) {
        insertWithNode(key, -1);
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
        heapifyWithNode(key, false);
    }

    /**
     * Korjaa kekoehdon kohdasta key lähtien ja mahdollisesti pitää nodelistan
     * ajan tasalla.
     *
     * @param key kohta, josta lähtien kekoehto korjataan
     * @param onNode pitää listan ajantasalla
     */
    private void heapifyWithNode(int key, boolean onNode) {
        int[] lapset = new int[d];
        for (int i = 0; i < d; i++) {
            lapset[i] = d * key + 1 + i;
        }
        int indeksi = etsiPienin(lapset);
        if (indeksi != -1 && keko[indeksi] < keko[key]) {
            int apu = keko[indeksi];
            keko[indeksi] = keko[key];
            keko[key] = apu;
            if (onNode) {
                int apunode = node[indeksi];
                node[indeksi] = node[key];
                node[key] = apunode;
                heapifyWithNode(indeksi, true);
            } else {
                heapifyWithNode(indeksi, false);
            }
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
                if (keko[taulukko[i]] < pienin) {
                    pienin = keko[taulukko[i]];
                    pienindeksi = taulukko[i];
                }
            }
        }
        return pienindeksi;
    }
    public int[] getKeko() {
        return keko;
    }
    @Override
    public int getSize() {
        return heapSize;
    }
    /**
     * Lisää kekoon arvon key ja sitä vastaavan noden n. Jos n==-1, nodea ei 
     * lisätä.
     * @param key Kekoon lisättävä arvo
     * @param n noden arvo
     */
    @Override
    public void insertWithNode(int key, int n) {
        if (heapSize == keko.length) {
            if (n == -1) {
                int[] uusikeko = new int[keko.length * 2];
                for (int i = 0; i < keko.length; i++) {
                    uusikeko[i] = keko[i];
                }
                keko = uusikeko;
            } else {
                int[] uusikeko = new int[keko.length * 2];
                int[] uusinode = new int[node.length * 2];
                for (int i = 0; i < keko.length; i++) {
                    uusikeko[i] = keko[i];
                    uusinode[i] = node[i];
                }
                keko = uusikeko;
                node = uusinode;
            }
        }
        int i = heapSize;
        while (i > 0 && keko[parent(i)] > key) {
            keko[i] = keko[parent(i)];
            if (n != -1) {
                node[i] = node[parent(i)];
            }
            i = parent(i);
        }
        keko[i] = key;
        if (n != -1) {
            node[i] = n;
        }
        heapSize++;
    }
    /**
     * Palauttaa keon pienintä arvoa vastaavan noden.
     * @return pienintä arvoa vastaava node
     */
    @Override
    public int findMinNode() {
        return node[0];
    }
    /**
     * Poistaa keosta pienimmän alkion ja sitä vastaavan noden.
     */
    @Override
    public void deleteMinJaNode() {
        keko[0] = keko[heapSize - 1];
        node[0] = node[heapSize - 1];
        heapSize--;
        heapifyWithNode(0, true);
    }

    /**
     * Etsii keosta indeksin, jossa noden arvo on n. Tätä käytetään Dijkstrassa
     * ja tiedetään, että indeksi on olemassa.
     *
     * @param n noden arvo, jolla etsitään
     * @return indeksi jossa n on
     */
    @Override
    public int etsiKeosta(int n) {
        int indeksi = 0;
        for (int i = 0; i < heapSize; i++) {
            if (node[i] == n) {
                indeksi = i;
                return indeksi;
            }
        }
        return indeksi;
    }
    /**
     * Vähentää indeksin i arvon n:ksi ja päivittää nodelistan jos onNode==true.
     * @param i indeksi, josta vähennetään
     * @param n uusi arvo
     * @param onNode kertoo päivitetäänkö nodelista
     */
    @Override
    public void decreaseKeyWithNode(int i, int n, boolean onNode) {
        if (keko[i] > n && n > 0) {
            keko[i] = n;
            while (i > 0 && keko[parent(i)] > keko[i]) {
                int apu = keko[i];
                keko[i] = keko[parent(i)];
                keko[parent(i)] = apu;
                if (onNode) {
                    int apunode = node[i];
                    node[i] = node[parent(i)];
                    node[parent(i)] = apunode;
                }
                i = parent(i);
            }
        }
    }
}
