
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binomikeko implements Keko{
    
    public Solmu keko;
    
    public Binomikeko(){
        keko=null;
    }
    public Binomikeko(Solmu s){
        keko=s;
    }
    /**
     * Etsii ja palauttaa keon pienimmän arvon tai -1 jos keko on tyhjä.
     * @return keon pienin arvo tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        int pienin=Integer.MAX_VALUE;
        Solmu k=keko;
        if(k==null){
            pienin=-1;
        }
        while(k!=null){
            if(k.getArvo()<pienin){
                pienin=k.getArvo();
            }
            k=k.getSeuraava();
        }
        return pienin;
    }

    @Override
    public void deleteMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreaseKey(int i, int d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Lisää kekoon arvon key.
     * @param key kekoon lisättävä arvo
     */
    @Override
    public void insert(int key) {
        Solmu s=new Solmu(key,null,new Solmu[0]);
        Binomikeko k=new Binomikeko(s);
        merge(k);
    }
    
    /**
     * Yhdistää kaksi binomikekoa.
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) {
        
        
    }
    /**
     * Yhdistää kaksi keon puuta. 
     * @param a ensimmäinen yhdistettävä puu
     * @param b toinen yhdistettävä puu
     * @return palauttaa a:sta ja b:stä yhdistetyn puun
     */
    private Solmu mergeTree(Solmu a,Solmu b){
        if(a.getArvo()<b.getArvo()){
            a.lisaaLapsi(b);
            return a;
        }else{
            b.lisaaLapsi(a);
            return b;
        }
    }
    
}
