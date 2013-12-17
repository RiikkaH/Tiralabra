
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko implements Keko{

    private Solmu keko;//linkitetty lista juurisolmuista
    
    public Fibonaccikeko(){
        keko=null;
    }
    public Fibonaccikeko(Solmu s){
        keko=s;
    }
    /**
     * Etsii ja palauttaa keon pienimmän arvon. 
     * @return keon pienin arvo, tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        int pienin=Integer.MAX_VALUE;
        if(keko==null){
            return-1;
        }
        Solmu k=keko;
        while(k!=null){
            if(k.getArvo()<pienin){
                pienin=k.getArvo();
            }
            k=k.getSeuraava();
        }
        return pienin;
    }
    //tämäkin on vähän turhaa toistoa --> oikeasti eroon Keko-rajapinnasta?
    public Solmu findMinSolmu(){
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
    
    @Override
    public void deleteMin() {
        Solmu poistettava=findMinSolmu();
    }

    @Override
    public void decreaseKey(int i, int d) {
        //tässäkin olisi kiva jos parametrina olisi Solmu....
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    /**
     * Lisää kekoon uuden solmun arvolla key.
     * @param key kekoon lisättävän solmun arvo
     */
    @Override
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
