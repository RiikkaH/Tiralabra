
package kekovertailu;

import keot.Binaarikeko;

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
    
    public void etsiReittiBinaarikeolla(){
        //alustus
        Binaarikeko keko=new Binaarikeko();
        int[] dist =new int[verkko.length];
        int[] edellinen=new int[verkko.length];
        for(int i=0;i<dist.length;i++){
            if(i!=alku){
                dist[i]=Integer.MAX_VALUE;
            }
            edellinen[i]=-1;
            //ongelma: keko pitäisi toimia etäisyyksien perusteella mutta 
            //muistissa pitäisi olla mihin solmuun etäisyys liittyy
        }
        //varsinainen työ
        
    }
}
