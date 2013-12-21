package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binomikeko {

    public Solmu keko;
    
    /**
     * Luo uuden tyhjän binomikeon.
     */
    public Binomikeko() {
        keko = null;
    }
    
    /**
     * Luo uuden binomikeon, aloitussolmuna s.
     * @param s uuden binomikeon aloitussolmu
     */
    public Binomikeko(Solmu s) {
        keko = s;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun
     * @return keon pienin solmu
     */
    private Solmu findMin() {
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
     * Poistaa keosta pienimmän alkion säilyttäen kekoehdon.
     */
    public void deleteMin() { 
        Solmu p = findMin();
        if (p.getAste()>0) {
            Solmu uusikeko=p.getLapsi();
            Binomikeko uusi =new Binomikeko(uusikeko);
            merge(uusi);
        }
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
        Solmu s = new Solmu(key,null,null,0);
        Binomikeko k = new Binomikeko(s);
        merge(k);
    }

    /**
     * Yhdistää kaksi binomikekoa.
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) { //tämän voisi hajauttaa, pitkä metodi
        
    }

    /**
     * Yhdistää kaksi keon puuta.
     *
     * @param s ensimmäinen yhdistettävä puu
     * @param t toinen yhdistettävä puu
     * @return palauttaa s:stä ja t:stä yhdistetyn puun
     */
    private Solmu mergeTree(Solmu s, Solmu t) {
        if(s.getArvo()<t.getArvo()){
            Solmu apu=s;
            s=t;
            t=apu;
        }
        lisaaLapsi(t,s);
        return t;
    }
    /**
     * Sisää solmulle p lapsen l
     * @param p solmu, johon lapsi lisätään
     * @param l lisättävä lapsi
     */
    //lisättävä tulee aina lapsista viimeiseksi
    private void lisaaLapsi(Solmu p,Solmu l){
        if(p.getAste()==0){
            l.setParent(p);
            p.setLapsi(l);
        }
        else {
            Solmu s=p.getLapsi();
            while(s.getSeuraava()!=null){
                s=s.getSeuraava();
            }
            s.setSeuraava(l);
        }
    }
    //metodi testejä varten
    public Solmu getKeko(){
        return keko;
    }
}
