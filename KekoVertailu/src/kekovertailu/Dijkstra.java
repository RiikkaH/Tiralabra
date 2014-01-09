package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
import keot.Solmu;
import keot.SolmullinenKeko;
import keot.SolmutonKeko;
import keot.darykeko;

/**
 *
 * @author Riikka
 */
public class Dijkstra {

    private final int[][] verkko;
    private final int alku;

    //verkko vierusmatriisina
    public Dijkstra(int[][] verkko, int alku) {
        this.verkko = verkko;
        this.alku = alku;
    }

    private int[] etsiReittiBinomiTaiFibonacci(SolmullinenKeko keko) {
        //alustus
        int[] dist = new int[verkko.length];//etäisyydet
        keko.luoPointteritaulukko(verkko.length);
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 100;
            }
            keko.insertWithNode(dist[i], i);
        }
        //varsinainen työ
        while (keko.findMinSolmu() != null) {
            Solmu s = keko.findMinSolmu();
            keko.deleteMin();
            for (int i = 0; i < verkko.length; i++) {
                if (verkko[s.getNode()][i] != 0) {//käydään läpi kaikki s:n naapurit i
                    int uusiEtaisyys = dist[s.getNode()] + verkko[s.getNode()][i];//uusi etäisyys i:hin
                    if (uusiEtaisyys < dist[i]) {
                        dist[i] = uusiEtaisyys;
                        //etsitään solmu i keosta
                        Solmu o = keko.etsiKeosta(i);
                        if (o != null) {
                            keko.decreaseKey(o, uusiEtaisyys);
                        }
                    }
                }
            }
        }
        return dist;
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin binomikeolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiBinomikeolla() {
        return etsiReittiBinomiTaiFibonacci(new Binomikeko());
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin fibonaccikeolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiFibonaccikeolla() {
        return etsiReittiBinomiTaiFibonacci(new Fibonaccikeko());
    }
    
    private int[] etsiReittiBinaariTaiDarykeolla(SolmutonKeko keko){
        //alustus
        int[] dist = new int[verkko.length];//etäisyydet
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 100;
            }
            keko.insertWithNode(dist[i], i);
        }
        //varsinainen työ
        while (keko.getSize() > 0) {
            int etaisyys = keko.findMin();
            int node = keko.findMinNode();
            keko.deleteMinJaNode();
            for (int i = 0; i < verkko.length; i++) {
                if (verkko[node][i] > 0) {
                    int uusietaisyys = dist[node] + verkko[node][i];
                    if (uusietaisyys < dist[i]) {
                        dist[i] = uusietaisyys;
                        //vähennetään i:tä vastaavaa key:tä
                        int indeksi = keko.etsiKeosta(i);
                        keko.decreaseKeyWithNode(indeksi, uusietaisyys,true);
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
        return etsiReittiBinaariTaiDarykeolla(new Binaarikeko());
    }
    /**
     * Etsii lyhyimpien reittien pituudet alkusolmusta kaikkiin solmuihin d-ary -keolla.
     * @return Taulukko, jonka kohdassa i on lyhyimmän matkan pituus siihen solmuun.
     */
    public int[] etsiReittiDarykeolla() {
        return etsiReittiBinaariTaiDarykeolla(new darykeko(4));
    }
}
