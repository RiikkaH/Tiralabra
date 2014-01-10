package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
import keot.Keko;
import keot.Solmu;
import keot.SolmutonKeko;
import keot.darykeko;

/**
 *
 * @author Riikka
 */
public class Dijkstra {
    
    private final Solmu[] verkko;//verkon solmu, paikassa i olevalla solmulla on node=i
    private final int[][] etaisyydet;
    private final int alku;

    //verkko vierusmatriisina
    public Dijkstra(Solmu[] verkko,int[][] etaisyydet, int alku){
        this.verkko=verkko;
        this.etaisyydet = etaisyydet;
        this.alku = alku;
    }

    private int[] etsiReittiBinomiTaiFibonacci(Keko keko) {
        //alustus
        int[] dist = new int[verkko.length];//etäisyydet
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 100;
                verkko[i].setArvo(dist[i]);
            }
            keko.insert(verkko[i]);
        }
        //varsinainen työ
        while (keko.findMinSolmu() != null) {
            Solmu s = keko.findMinSolmu();
            keko.deleteMin();
            for (int i = 0; i < etaisyydet.length; i++) {
                if (etaisyydet[s.getNode()][i] != 0) {//käydään läpi kaikki s:n naapurit i
                    int uusiEtaisyys = dist[s.getNode()] + etaisyydet[s.getNode()][i];//uusi etäisyys i:hin
                    if (uusiEtaisyys < dist[i]) {
                        dist[i] = uusiEtaisyys;
                        //etsitään solmu i keosta
                        Solmu o=verkko[i];
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
        int[] dist = new int[etaisyydet.length];//etäisyydet
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
            for (int i = 0; i < etaisyydet.length; i++) {
                if (etaisyydet[node][i] > 0) {
                    int uusietaisyys = dist[node] + etaisyydet[node][i];
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
