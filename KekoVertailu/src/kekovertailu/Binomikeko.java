
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Binomikeko implements Keko{
    
    public Solmu[] keko;
    
    public Binomikeko(){
        keko=new Solmu[0];
    }
    /**
     * Etsii ja palauttaa keon pienimmän arvon ja -1 jos keko on tyhjä.
     * @return keon pienin arvo tai -1 jos keko on tyhjä
     */
    @Override
    public int findMin() {
        int pienin=-1;
        for(int i=0;i<keko.length;i++){
            if(i==0){
                pienin=keko[i].getArvo();
            }else{
                if(keko[i].getArvo()<pienin){
                    pienin=keko[i].getArvo();
                }
            }
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
        Binomikeko k=new Binomikeko();
        k.keko=new Solmu[]{new Solmu(key,null,null)};
        merge(k);
    }
    
    /**
     * Yhdistää kaksi binomikekoa.
     * @param k toinen yhdistettävä keko
     */
    public void merge(Binomikeko k) {
        int i=0;
        int j=0;
        while(i<keko.length && j<k.getLength()){
            Solmu puu=mergeTree(keko[i],k.keko[j]);
            //.......
        }
        
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
    
    public int getLength(){
        return keko.length;
    }
}
