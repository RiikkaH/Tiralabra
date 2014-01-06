
package kekovertailu;

import keot.Binaarikeko;
import keot.Binomikeko;
import keot.Solmu;

/**
 *
 * @author Riikka
 */
public class Dijkstra {
    
    private final int[][] verkko;
    private final int alku;
    
    
    //verkko vierusmatriisina
    public Dijkstra(int[][] verkko,int alku){
        this.verkko=verkko;
        this.alku=alku;
    }
    
    public void etsiReittiBinomikeolla(){
        //alustus
        Binomikeko keko=new Binomikeko();
        int[] dist =new int[verkko.length];//etäisyydet
        int[] edellinen=new int[verkko.length];//edelliset solmut
        for(int i=0;i<dist.length;i++){
            if(i!=alku){
                dist[i]=Integer.MAX_VALUE;
            }
            edellinen[i]=-1;
            keko.insertWithNode(i,dist[i]);
            //tässä lisätään kekoon solmut
            //ongelma: keko pitäisi toimia etäisyyksien perusteella mutta 
            //muistissa pitäisi olla mihin solmuun etäisyys liittyy
            //Solmussa ei mikään ongelma, mutta pitäiskö binäärikeko ja 
            //d-ary -keko toteuttaa myös Solmun avulla? Taulukko Solmuista?
        }
        //varsinainen työ
        while(keko.findMinSolmu()!=null){
            Solmu s=keko.findMinSolmu();
            keko.deleteMin();
            for(int i=0;i<verkko.length;i++){
                if(verkko[s.getNode()][i]!=0){//käydään läpi kaikki s:n naapurit i
                    int uusiEtaisyys=dist[s.getNode()]+verkko[s.getNode()][i];//uusi etäisyys i:hin
                    if(uusiEtaisyys<dist[i]){
                        dist[i]=uusiEtaisyys;
                        edellinen[i]=s.getNode();
                        //etsitään solmu i keosta
                        //keolla ei kyllä ole kovin tehokasta etsiä joku muu 
                        //solmu kuin min...
                        //decreaseKey(etsittysolmu,uusiEtaisyys);
                    }
                }
            }
        }
        
    }
}
