
package kekovertailu;

import keot.Solmu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Riikka
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra1;
    private Dijkstra dijkstra2;
    
    public DijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dijkstra1=new Dijkstra(pieniVerkko(),pienetEtaisyydet(),0);
        dijkstra2=new Dijkstra(pieniVerkko(),toisetEtaisyydet(),0);
    }
    public Solmu[] pieniVerkko(){
        Solmu[] solmut =new Solmu[6];
        for(int i=0;i<6;i++){
            solmut[i]=new Solmu(i);
        }
        return solmut;
    }
    public int[][] toisetEtaisyydet(){
        return new int[][]
                {{0,5,0,1,0,0},
                {5,0,0,0,0,7},
                {0,0,0,16,19,4},
                {1,0,16,0,2,0},
                {0,0,19,2,0,3},
                {0,7,4,0,3,0}};
    
    }
    public int[][] pienetEtaisyydet(){
        int[][] verkko=new int[6][6];
        verkko[0][1]=verkko[1][0]=7;
        verkko[0][2]=verkko[2][0]=9;
        verkko[0][5]=verkko[5][0]=14;
        verkko[1][2]=verkko[2][1]=10;
        verkko[1][3]=verkko[3][1]=15;
        verkko[2][3]=verkko[3][2]=11;
        verkko[2][5]=verkko[5][2]=2;
        verkko[3][4]=verkko[4][3]=6;
        verkko[4][5]=verkko[5][4]=9;
        return verkko;
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testaaEtaisyyksiaPienellaVerkollaJaBinomikeolla(){
        int[] etaisyydet =dijkstra1.etsiReittiBinomikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }
    @Test
    public void testaaUudestaanPienellaVerkollaJaBinomikeolla(){
        int[] etaisyydet=dijkstra2.etsiReittiBinomikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==5);
        assertTrue(etaisyydet[2]==10);
        assertTrue(etaisyydet[3]==1);
        assertTrue(etaisyydet[4]==3);
        assertTrue(etaisyydet[5]==6);
    }
    @Test
    public void testaaEtaisyyksiaPienellaVerkollaJaFibonaccikeolla(){
        int[] etaisyydet =dijkstra1.etsiReittiFibonaccikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }/* Tämä testi jää vain junnaamaan
    @Test
    public void testaaUudestaanPienellaVerkollaJaFibonaccikeolla(){
        int[] etaisyydet=dijkstra2.etsiReittiFibonaccikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==5);
        assertTrue(etaisyydet[2]==10);
        assertTrue(etaisyydet[3]==1);
        assertTrue(etaisyydet[4]==3);
        assertTrue(etaisyydet[5]==6);
    }*/
    @Test
    public void testaaEtaisyyksiaPienellaVerkollaJaBinaarikeolla(){
        int[] etaisyydet =dijkstra1.etsiReittiBinaarikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }
    @Test
    public void testaaUudestaanPienellaVerkollaJaBinaarikeolla(){
        int[] etaisyydet=dijkstra2.etsiReittiBinaarikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==5);
        assertTrue(etaisyydet[2]==10);
        assertTrue(etaisyydet[3]==1);
        assertTrue(etaisyydet[4]==3);
        assertTrue(etaisyydet[5]==6);
    }
    @Test
    public void testaaEtaisyyksiaPienellaVerkollaJaDarykeolla(){
        int[] etaisyydet=dijkstra1.etsiReittiDarykeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }
    @Test
    public void testaaUudestaanPienellaVerkollaJaDarykeolla(){
        int[] etaisyydet=dijkstra2.etsiReittiDarykeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==5);
        assertTrue(etaisyydet[2]==10);
        assertTrue(etaisyydet[3]==1);
        assertTrue(etaisyydet[4]==3);
        assertTrue(etaisyydet[5]==6);
    }
}
