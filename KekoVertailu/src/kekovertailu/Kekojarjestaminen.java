
package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
import keot.Keko;
import keot.darykeko;

/**
 *
 * @author Riikka
 */
public class Kekojarjestaminen {
    
    private final int[] taulukko;
    
    public Kekojarjestaminen(int[] taulukko){
        this.taulukko=taulukko;
    }
    
    private int[] jarjesta(Keko keko){
        int[] uusi=new int[taulukko.length];
        for(int i=0;i<taulukko.length;i++){
            keko.insert(taulukko[i]);
        }
        for(int j=0;j<taulukko.length;j++){
            uusi[j]=keko.findMin();
            keko.deleteMin();
        }
        return uusi;
    }
    /**
     * Järjestää taulukon binäärikeon avulla.
     * @return järjestetty taulukko
     */
    public int[] jarjestaBinaarikeolla(){
        return jarjesta(new Binaarikeko());
    }
    /**
     * Järjestää taulukon binomikeon avulla.
     * @return järjestetty taulukko
     */
    public int[] jarjestaBinomikeolla(){
        return jarjesta(new Binomikeko());
    }
    /**
     * Järjestää taulukon d-ary -keon avulla.
     * @param aste d-ary -keon aste
     * @return järjestetty taulukko
     */
    public int[] jarjestaDarykeolla(int aste){
        return jarjesta(new darykeko(aste));
    }
    /**
     * Järjestää taulukon fibonaccikeon avulla.
     * @return järjestetty taulukko
     */
    public int[] jarjestaFibonaccikeolla(){
        return jarjesta(new Fibonaccikeko());
    }
}
