
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko {

    private Solmu keko;//linkitetty lista juurisolmuista
    
    public Fibonaccikeko(){
        keko=null;
    }
    public Fibonaccikeko(Solmu s){
        keko=s;
    }
    /**
     * Etsii ja palauttaa keon pienimmän solmun. 
     * @return keon pienin solmu, tai -1 jos keko on tyhjä
     */
    public Solmu findMin(){
        int pienin=Integer.MAX_VALUE;
        Solmu p=null;
        Solmu k=keko;
        while(k!=null){
            if(k.getArvo()<pienin){
                pienin=k.getArvo();
                p=k;
            }
            k=k.getSeuraava();
        }
        return p;
    }
    
    public void deleteMin() {
        Solmu poistettava=findMin();
    }

    public void decreaseKey(Solmu s, int d) {
        
    }
    
    /**
     * Lisää kekoon uuden solmun arvolla key.
     * @param key kekoon lisättävän solmun arvo
     */
    public void insert(int key) {
        Solmu s=new Solmu(key,null,new Solmu[0]);
        Fibonaccikeko uusi=new Fibonaccikeko(s);
        merge(uusi);
    }
    
    public void merge(Fibonaccikeko k){
        if(keko==null){
            keko=k.keko;
        }
        Solmu s=keko;
        while(s.getSeuraava()!=null){
            s=s.getSeuraava();
        }
        s.setSeuraava(k.keko); 
    }
}
