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
    public Solmu findMin() {
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
            p.getLapsi().setParent(null);
            Solmu uusikeko=p.getLapsi();
            Binomikeko uusi =new Binomikeko(uusikeko);
            poistaKeostaPuu(p);
            merge(uusi);
        }
    }
    
    private void poistaKeostaPuu(Solmu s){
        Solmu p=keko;
        while(p.getSeuraava()!=s){
            p=s.getSeuraava();
        }
        p.setSeuraava(s.getSeuraava());
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
    public void merge(Binomikeko k) { //huhhuh, järkyttävän pitkä, voisi hajauttaa
        Solmu s1=keko;
        Solmu s2=k.getKeko();
        Solmu uusipuu=null;
        Solmu ed=null; //HUOM AINAKIN TÄMÄ VIELÄ VÄÄRIN, KEKSI PAREMPI JUTTU
        //kahteen suuntaan linkitetty lista?
        Binomikeko uusikeko=new Binomikeko(uusipuu);
        while(s1!=null && s2!=null && s1.getSeuraava()!=null && s2.getSeuraava()!=null){
            if(s2.getAste()<s1.getAste()){
                if(uusipuu==null){
                    uusipuu=s2;
                }else if(uusipuu.getAste()==s2.getAste()){
                    Solmu uusi=mergeTree(uusipuu,s2);
                    ed.setSeuraava(uusi);
                    uusipuu=uusi;
                }else{
                    uusipuu.setSeuraava(s2);
                    uusipuu=uusipuu.getSeuraava();
                }
                s2=s2.getSeuraava();
            }else if(s2.getAste()>s1.getAste()){
                if(uusipuu==null){
                    uusipuu=s1;
                }else if(uusipuu.getAste()==s1.getAste()){
                    Solmu uusi=mergeTree(uusipuu,s1);
                    ed.setSeuraava(uusi);
                    uusipuu=uusi;
                }else{
                    uusipuu.setSeuraava(s1);
                    uusipuu=uusipuu.getSeuraava();
                }
                s1=s1.getSeuraava();
            }else{
                Solmu uusi=mergeTree(s1,s2);
                if(uusipuu.getAste()==uusi.getAste()){
                    Solmu vielauusi=mergeTree(uusipuu,uusi);
                    ed.setSeuraava(vielauusi);
                    uusipuu=vielauusi;
                }else{
                    uusipuu.setSeuraava(uusi);
                    uusipuu=uusipuu.getSeuraava();
                }
                s1=s1.getSeuraava();
                s2=s2.getSeuraava();
            }
            ed=uusipuu;
        }
        
        if(s1!=null && s1.getSeuraava()!=null){
            if(uusipuu!=null){
                uusipuu.setSeuraava(s1);
            }else{
                uusipuu=s1;
            }
        }else if(s2!=null && s2.getSeuraava()!=null){
            if(uusipuu!=null){
                uusipuu.setSeuraava(s2);
            }else{
                uusipuu=s2;
            }
        }

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
     * Lisää solmulle p lapsen l
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
        p.setAste(p.getAste()+1);
    }
    public Solmu getKeko(){
        return keko;
    }
}
