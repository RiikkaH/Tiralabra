package keot;

/**
 *
 * @author Riikka
 */
public class Binaarikeko implements Keko {

    private Solmu[] keko;
    private int heapSize;

    public Binaarikeko() {
        keko = new Solmu[20];
        heapSize = 0;
    }

    private void heapify(int key) {
        int l = left(key);
        int r = right(key);
        if (r < heapSize) {
            int smallest;
            if (keko[l].getArvo() < keko[r].getArvo()) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (keko[key].getArvo() > keko[smallest].getArvo()) {
                Solmu apu = keko[smallest];
                keko[smallest] = keko[key];
                keko[key] = apu;
                keko[key].setIndeksi(key);
                keko[smallest].setIndeksi(smallest);
                heapify(smallest);
            }
        } else if (l == heapSize - 1 && keko[key].getArvo() > keko[l].getArvo()) {
            Solmu apu = keko[l];
            keko[l] = keko[key];
            keko[key] = apu;
            keko[l].setIndeksi(l);
            keko[key].setIndeksi(key);
        }
    }

    /**
     * Palauttaa keon pienimmän alkion, tai jos keko on tyhjä, palauttaa metodi
     * arvon -1.
     *
     * @return keon pienin alkio tai -1, jos keko on tyhjä
     */
    @Override
    public int findMin() {
        if (heapSize > 0) {
            return keko[0].getArvo();
        }
        return -1;
    }

    /**
     * Poistaa keon pienimmän alkion ja korjaa kekoehdon.
     */
    @Override
    public void deleteMin() {
        if (heapSize == 1) {
            heapSize = 0;
        } else if (heapSize > 1) {
            keko[0] = keko[heapSize - 1];
            keko[0].setIndeksi(0);
            heapSize--;
            heapify(0);
        }
    }

    /**
     * Korvaa keon somun i arvon d:llä (jos on pienempi kuin keon i:nnes alkio)
     * ja korjaa kekoehdon.
     *
     * @param i solmu, jonka arvoa pienennetään
     * @param d uusi arvo
     */
    public void decreaseKey(int i, int d) {
        if (keko[i].getArvo() > d && d > 0) {
            keko[i].setArvo(d);
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
     * Lisää kekoon solmun arvolla key.
     *
     * @param key arvo, joka kekoon halutaan lisätä
     */
    @Override
    public void insert(int key) {
        Solmu lisattava = new Solmu(key, null, null, 0);
        insert(lisattava);
    }

    /**
     * Palauttaa solmun i vanhemman, tai i:n jos se on juuri
     *
     * @param i solmu jonka vanhempi halutaan selvittää
     * @return i:n vanhempi
     */
    private int parent(int i) {
        return (int) Math.floor(((double) i - 1) / 2.0);
    }

    /**
     * Palauttaa solmun i vasemman lapsen indeksin.
     *
     * @param i solmu, jonka vasen lapsi halutaan selvittää
     * @return i:n vasen lapsi
     */
    private int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Palauttaa solmun i oikean lapsen indeksin.
     *
     * @param i solmu, jonka oikea lapsi halutaan selvittää
     * @return i:n oikea lapsi
     */
    private int right(int i) {
        return 2 * i + 2;
    }
    //metodi testejä varten

    public Solmu[] getKeko() {
        return keko;
    }

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
        keko[i] = s;
        s.setIndeksi(i);
        heapSize++;
    }

    @Override
    public Solmu findMinSolmu() {
        if(heapSize==0){
            return null;
        }
        return keko[0];
    }

    public int getSize() {
        return heapSize;
    }

    @Override
    public void decreaseKey(Solmu s, int d) {
        decreaseKey(s.getIndeksi(), d);
    }
}
