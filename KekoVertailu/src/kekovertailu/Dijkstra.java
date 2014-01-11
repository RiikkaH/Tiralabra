package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
import keot.Keko;
import keot.Solmu;
import keot.darykeko;

/**
 *
 * @author Riikka
 */
public class Dijkstra {
    
    private final Solmu[] verkko;//verkon solmu, paikassa i olevalla solmulla on node=i
    private final int[][] etaisyydet;
    private final int alku;

    public Dijkstra(Solmu[] verkko,int[][] etaisyydet, int alku){
        this.verkko=verkko;
        this.etaisyydet = etaisyydet;
        this.alku = alku;
    }

    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin binomikeolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiBinomikeolla() {
        return etsiReitti(new Binomikeko());
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin fibonaccikeolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiFibonaccikeolla() {
        return etsiReitti(new Fibonaccikeko());
    }
    
    private int[] etsiReitti(Keko keko){
        //alustus
        int[] dist = new int[verkko.length];//etäisyydet
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 10000;
                verkko[i].setArvo(dist[i]);
            }
            keko.insert(verkko[i]);
        }
        //varsinainen työ
        while (keko.findMinSolmu() !=null) {
            Solmu s = keko.findMinSolmu();
            keko.deleteMin();
            for (int i = 0; i < etaisyydet.length; i++) {
                if (etaisyydet[s.getNode()][i] > 0) {
                    int uusietaisyys = dist[s.getNode()] + etaisyydet[s.getNode()][i];
                    if (uusietaisyys < dist[i]) {
                        dist[i] = uusietaisyys;
                        //vähennetään i:tä vastaavaa key:tä
                        keko.decreaseKey(verkko[i], uusietaisyys);
                    }
                }
            }
        }
        return dist;
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin binäärikeolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiBinaarikeolla() {
        return etsiReitti(new Binaarikeko());
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin d-ary -keolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiDarykeolla() {
        return etsiReitti(new darykeko(4));
    }
}
