
package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
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
    
    public int[] jarjestaBinaarikeolla(){
        int[] uusi=new int[taulukko.length];
        Binaarikeko keko=new Binaarikeko();
        for(int i=0;i<taulukko.length;i++){
            keko.insert(taulukko[i]);
        }
        for(int j=0;j<taulukko.length;j++){
            uusi[j]=keko.findMin();
            keko.deleteMin();
        }
        return uusi;
    }
    
    public int[] jarjestaBinomikeolla(){
        int[] uusi=new int[taulukko.length];
        Binomikeko keko=new Binomikeko();
        for(int i=0;i<taulukko.length;i++){
            keko.insert(taulukko[i]);
        }
        for(int j=0;j<taulukko.length;j++){
            uusi[j]=keko.findMin().getArvo();
            keko.deleteMin();
        }
        return uusi;
    }
    
    public int[] jarjestaDarykeolla(int aste){
        int[] uusi=new int[taulukko.length];
        darykeko keko=new darykeko(aste);
        for(int i=0;i<taulukko.length;i++){
            keko.insert(taulukko[i]);
        }
        for(int j=0;j<taulukko.length;j++){
            uusi[j]=keko.findMin();
            keko.deleteMin();
        }
        return uusi;
    }
    
    public int[] jarjestaFibonaccikeolla(){
        int[] uusi=new int[taulukko.length];
        Fibonaccikeko keko=new Fibonaccikeko();
        for(int i=0;i<taulukko.length;i++){
            keko.insert(taulukko[i]);
        }
        for(int j=0;j<taulukko.length;j++){
            uusi[j]=keko.findMin().getArvo();
            keko.deleteMin();
        }
        return uusi;
    }
}
