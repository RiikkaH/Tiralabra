package keot;

/**
 *
 * @author Riikka
 */
public class Binomikeko implements Keko{

    public Solmu keko;

    /**
     * Luo uuden tyhjän binomikeon.
     */
    public Binomikeko() {
        keko = null;
    }

    /**
     * Luo uuden binomikeon, aloitussolmuna s.
     *
     * @param s uuden binomikeon aloitussolmu
     */
    public Binomikeko(Solmu s) {
        keko = s;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun
     *
     * @return keon pienin solmu
     */
    public Solmu findMinSolmu() {
        int pienin = Integer.MAX_VALUE;
        Solmu k = keko;
        if (k == null) {
            pienin = -1;
        }
        Solmu p = keko;
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
     * Etsii ja palauttaa keon pienimmän solmun arvon.
     * @return keon pienimmän solmun arvo
     */
    @Override
    public int findMin(){
        return findMinSolmu().getArvo();
    }

    /**
     * Poistaa keosta pienimmän alkion säilyttäen kekoehdon.
     */
    @Override
    public void deleteMin() {
        Solmu p = findMinSolmu();
        if (p.getAste() > 0) {
            p.getLapsi().setParent(null);
            Solmu uusikeko = p.getLapsi();
            Binomikeko uusi = new Binomikeko(uusikeko);
            poistaKeostaPuu(p);
            merge(uusi);
        } else {
            poistaKeostaPuu(p);
        }
    }

    private void poistaKeostaPuu(Solmu s) {
        Solmu ed = s.getEdellinen();
        Solmu se = s.getSeuraava();
        if (ed == null) {
            keko = se;
            if (se != null) {
                se.setEdellinen(null);
            }
        } else {
            ed.setSeuraava(se);
            if (se != null) {
                se.setEdellinen(ed);
            }
        }
    }

    public void decreaseKey(Solmu s, int d) {
        if (s.getArvo() > d && d > 0) {
            s.setArvo(d);
            while (s.getParent() != null && s.getParent().getArvo() > d) {
                int apu = s.getArvo();
                s.setArvo(s.getParent().getArvo());
                s.getParent().setArvo(apu);
                s=s.getParent();
            }
        }
    }

    /**
     * Lisää kekoon arvon key.
     *
     * @param key kekoon lisättävä arvo
     */
    @Override
    public void insert(int key) {
        Solmu s = new Solmu(key, null, null, 0);
        Binomikeko k = new Binomikeko(s);
        merge(k);
    }
    /**
     * Lisää kekoon arvon key. Käytetään Dijkstran algoritmin kanssa, node
     * merkitsee lisättävää solmua ja key etäisyyttä.
     * @param key arvo, joka kekoon lisätään
     * @param node kekoon lisättävän solmun arvo node
     */
    public void insertWithNode(int key, int node){
        Solmu s=new Solmu(key,null,null,0);
        s.setNode(node);
        Binomikeko k=new Binomikeko(s);
        merge(k);
    }

    /**
     * Yhdistää kaksi binomikekoa.
     *
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) {
        Solmu a = keko;
        Solmu b = k.getKeko();
        Solmu uusi = null;
        while (a != null || b != null) {
            Solmu lisattava = null;
            //sekä a että b ovat olemassa
            if (a != null && b != null) {
                //a on pienempi kuin b
                if (a.getAste() < b.getAste()) {
                    lisattava = a;
                    a = a.getSeuraava();
                } //b on pienempi kuin a
                else if (b.getAste() < a.getAste()) {
                    lisattava = b;
                    b = b.getSeuraava();
                } //a ja b ovat samankokoisia, pitää yhdistää
                else {
                    Solmu aa = a;
                    Solmu bb = b;
                    a = a.getSeuraava();
                    b = b.getSeuraava();
                    lisattava = mergeTree(aa, bb);
                }
            } //vain a:ssa on enää solmuja jäljellä
            else if (a != null) {
                lisattava = a;
                a = a.getSeuraava();
            } //enää b:ssä on solmuja jäljellä
            else if (b != null) {
                lisattava = b;
                b = b.getSeuraava();
            }
            //nyt on selvillä mikä solmu uuteen kekoon pitää lisätä
            //uusi on tyhjä; lisätään ensimmäiseksi alkioksi
            if (uusi == null) {
                lisattava.setEdellinen(null);
                lisattava.setSeuraava(null);
                uusi = lisattava;
            } //entä jos lisattava on samanasteinen kuin uusi?
            else if (lisattava.getAste() == uusi.getAste()) {
                Solmu s = uusi;
                if(uusi.getEdellinen()!=null){
                    uusi=uusi.getEdellinen();
                }else{
                    uusi=null;
                }
                lisattava = mergeTree(lisattava, s);
                if(uusi==null){
                    uusi=lisattava;
                    uusi.setEdellinen(null);
                    uusi.setSeuraava(null);
                }else{
                    uusi.setSeuraava(lisattava);
                    lisattava.setEdellinen(uusi);
                    lisattava.setSeuraava(null);
                    uusi=uusi.getSeuraava();
                }
            } //lisättävä on isompi, voidaan vain laittaa jatkoksi
            else {
                lisattava.setEdellinen(uusi);
                lisattava.setSeuraava(null);
                uusi.setSeuraava(lisattava);
                uusi=uusi.getSeuraava();
            }
        }
        //looppi loppui, enää ei ole lisättäviä solmuja
        //nyt siirrytään uuden ensimmäiseen solmuun ja asetetaan se keoksi
        while (uusi != null && uusi.getEdellinen() != null) {
            uusi = uusi.getEdellinen();
        }
        keko = uusi;
    }

    /**
     * Yhdistää kaksi keon puuta.
     *
     * @param s ensimmäinen yhdistettävä puu
     * @param t toinen yhdistettävä puu
     * @return palauttaa s:stä ja t:stä yhdistetyn puun
     */
    private Solmu mergeTree(Solmu s, Solmu t) {
        if (s.getArvo() < t.getArvo()) {
            Solmu apu = s;
            s = t;
            t = apu;
        }
        lisaaLapsi(t, s);
        return t;
    }

    /**
     * Lisää solmulle p lapsen l
     *
     * @param p solmu, johon lapsi lisätään
     * @param l lisättävä lapsi
     */
    //lisättävä tulee aina lapsista viimeiseksi, koska tämä on apumetodi
    //kahden samankokoisen puun yhdistämiseen
    private void lisaaLapsi(Solmu p, Solmu l) {
        if (p.getAste() == 0) {
            l.setParent(p);
            p.setLapsi(l);
            l.setEdellinen(null);
            l.setSeuraava(null);
        } else {
            Solmu s = p.getLapsi();
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
            }
            l.setParent(p);
            s.setSeuraava(l);
            l.setEdellinen(s);
            l.setSeuraava(null);
        }
        p.setAste(p.getAste() + 1);
    }

    public Solmu getKeko() {
        return keko;
    }
    
    public Solmu etsiKeosta(int node){
        //huom tämä ei ole vielä oikein!
        Solmu s=keko;
        while(s!=null){//käydään läpi jokainen keko
            if(s.getNode()==node){
                return s;
            }
            Solmu l=s.getLapsi();//jokainen taso
            while(l!=null){
                if(l.getNode()==node){
                    return l;
                }
                Solmu p=l.getSeuraava();
                while(p!=null){//jokainen solmu tasolla
                    if(p.getNode()==node){
                        return p;
                    }
                    p=p.getSeuraava();
                }
                l=l.getLapsi();
            }
            s=s.getSeuraava();
        }
        return null;
    }
}
