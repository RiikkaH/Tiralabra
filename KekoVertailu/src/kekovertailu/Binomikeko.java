package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binomikeko implements Keko {

    public Solmu keko;

    public Binomikeko() {
        keko = null;
    }

    public Binomikeko(Solmu s) {
        keko = s;
    }

    /**
     * Etsii ja palauttaa keon pienimmän arvon tai -1 jos keko on tyhjä.
     *
     * @return keon pienin arvo tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        int pienin = Integer.MAX_VALUE;
        Solmu k = keko;
        if (k == null) {
            pienin = -1;
        }
        while (k != null) {
            if (k.getArvo() < pienin) {
                pienin = k.getArvo();
            }
            k = k.getSeuraava();
        }
        return pienin;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun
     * @return keon pienin solmu
     */
    private Solmu etsiPieninSolmu() {
        int pienin = Integer.MAX_VALUE;
        Solmu p = null;
        Solmu k = keko;
        if (k == null) {
            pienin = -1;
        }
        while (k != null) {
            if (k.getArvo() < pienin) {
                pienin = k.getArvo();
                p = k;
            }
            k = k.getSeuraava();
        }
        return p;
    }

    /**
     * Poistaa keosta pienimmän alkion säilyttäen kekoehdon.
     */
    @Override
    public void deleteMin() { 
        Solmu p = etsiPieninSolmu();
        Solmu uusikeko = null;
        if (p.getLapset() != null) {
            Solmu[] uudetPuut = p.getLapset();
            uusikeko=luoLapsilistastaKeko(uudetPuut);
        }
        Binomikeko uusi = new Binomikeko(uusikeko);
        merge(uusi);
    }

    private Solmu luoLapsilistastaKeko(Solmu[] lista) {
        Solmu nykyinen = null;
        Solmu uusikeko = nykyinen;
        for (int j = 0; j < lista.length; j++) {
            int indeksi = -1;
            int pienin = Integer.MAX_VALUE;
            for (int i = 0; i < lista.length; i++) {
                if (pienin > lista[i].getLapset().length) {
                    pienin = lista[i].getLapset().length;
                    indeksi = i;
                }
            }
            if (nykyinen != null) {
                nykyinen = lista[indeksi];
            } else {
                nykyinen.setSeuraava(lista[indeksi]);
                nykyinen = nykyinen.getSeuraava();
            }
        }
        return uusikeko;
    }

    @Override
    public void decreaseKey(int i, int d) {
        //hmm.... tarvitsisin tässä kyllä parametrina solmun.. :P 
        //olikohan keko-interface hyvä idea?
    }

    /**
     * Lisää kekoon arvon key.
     *
     * @param key kekoon lisättävä arvo
     */
    @Override
    public void insert(int key) {
        Solmu s = new Solmu(key, new Solmu[0]);
        Binomikeko k = new Binomikeko(s);
        merge(k);
    }

    /**
     * Yhdistää kaksi binomikekoa.
     *
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) {
        Solmu p = keko;
        Solmu q = k.keko;
        Solmu nykyinen = null;
        Solmu uusikeko = nykyinen;
        while (p != null && q != null) {
            Solmu uusipuu = mergeTree(p, q);
            if (nykyinen != null) {
                if (nykyinen.getLapset().length != uusipuu.getLapset().length) {
                    uusipuu = mergeTree(uusipuu, nykyinen);
                }
            }
            if (nykyinen != null) {
                nykyinen.setSeuraava(uusipuu);
                nykyinen = nykyinen.getSeuraava();
            } else {
                nykyinen = uusipuu;
            }
            p = p.getSeuraava();
            q = q.getSeuraava();
        }
        if (p != null) {
            nykyinen.setSeuraava(p);
        }
        if (q != null) {
            nykyinen.setSeuraava(q);
        }
        keko = uusikeko;
    }

    /**
     * Yhdistää kaksi keon puuta.
     *
     * @param s ensimmäinen yhdistettävä puu
     * @param t toinen yhdistettävä puu
     * @return palauttaa s:stä ja t:stä yhdistetyn puun
     */
    private Solmu mergeTree(Solmu s, Solmu t) {
        Solmu a = s;
        Solmu b = t;
        a.setSeuraava(null);
        b.setSeuraava(null);
        if (a.getArvo() < b.getArvo()) {
            a.lisaaLapsi(b);
            return a;
        } else {
            b.lisaaLapsi(a);
            return b;
        }
    }

}
