package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binomikeko {

    public Solmu keko;

    public Binomikeko() {
        keko = null;
    }

    public Binomikeko(Solmu s) {
        keko = s;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun
     * @return keon pienin solmu
     */
    private Solmu findMin() {
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
    public void deleteMin() { 
        Solmu p = findMin();
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

    public void decreaseKey(Solmu s, int d) {
        if(s.getArvo()>d&&d>0){
            s.setArvo(d);
            while(s.getParent()!=null && s.getParent().getArvo()>d){
                int apu=s.getArvo();
                s.setArvo(s.getParent().getArvo());
                s.getParent().setArvo(apu);
            }
        }
    }

    /**
     * Lisää kekoon arvon key.
     *
     * @param key kekoon lisättävä arvo
     */
    public void insert(int key) {
        Solmu s = new Solmu(key,null, new Solmu[0]);
        Binomikeko k = new Binomikeko(s);
        merge(k);
    }

    /**
     * Yhdistää kaksi binomikekoa.
     *
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) { //tämän voisi hajauttaa
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
            b.setParent(a);
            return a;
        } else {
            b.lisaaLapsi(a);
            a.setParent(b);
            return b;
        }
    }

}
