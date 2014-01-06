package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Fibonaccikeko;
import keot.Keko;
import keot.Solmu;
import keot.SolmullinenKeko;

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
    
    public int[] etsiReittiBinomiJaFibonacci(SolmullinenKeko keko){
        //alustus
        int[] dist = new int[verkko.length];//etäisyydet
        //int[] edellinen=new int[verkko.length];//edelliset solmut
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 100;
            }
            //edellinen[i]=-1;
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
                        //edellinen[i]=s.getNode();
                        //etsitään solmu i keosta
                        Solmu o = keko.etsiKeosta(i);
                        //keolla ei kyllä ole kovin tehokasta etsiä joku muu 
                        //solmu kuin min...
                        if (o != null) {
                            keko.decreaseKey(o, uusiEtaisyys);
                        }
                    }
                }
            }
        }
        return dist;
    }

    public int[] etsiReittiBinomikeolla() {
        return etsiReittiBinomiJaFibonacci(new Binomikeko());
    }

    public int[] etsiReittiFibonaccikeolla() {
        return etsiReittiBinomiJaFibonacci(new Fibonaccikeko());
    }
    
    public int[] etsiReittiBinaarikeolla(){
        //alustus
        Binaarikeko keko=new Binaarikeko();
        int[] dist = new int[verkko.length];//etäisyydet
        //int[] edellinen=new int[verkko.length];//edelliset solmut
        for (int i = 0; i < dist.length; i++) {
            if (i != alku) {
                dist[i] = Integer.MAX_VALUE - 100;
            }
            //edellinen[i]=-1;
            keko.insertWithNode(dist[i], i);
        }
        //varsinainen työ
        while(keko.getSize()>0){
            int etaisyys=keko.findMin();
            int node=keko.findMinNode();
            keko.deleteMinJaNode();
            for(int i=0;i<verkko.length;i++){
                if(verkko[node][i]>0){
                    int uusietaisyys=dist[node]+verkko[node][i];
                    if(uusietaisyys<dist[i]){
                        dist[i]=uusietaisyys;
                        //vähennetään i:tä vastaavaa key:tä
                        int indeksi=keko.etsiKeosta(i);
                        keko.decreaseKey(indeksi,uusietaisyys);
                    }
                }
            }
        }
        return dist;
    }
}
