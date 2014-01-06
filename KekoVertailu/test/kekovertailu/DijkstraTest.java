
package kekovertailu;

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
        dijkstra1=new Dijkstra(pieniVerkko(),0);
    }
    
    public int[][] pieniVerkko(){
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
    public void testaaEtaisyyksiaPienellaVerkollaJaFibonaccikeolla(){
        int[] etaisyydet =dijkstra1.etsiReittiFibonaccikeolla();
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }
    @Test
    public void testaaEtaisyyksiaPienellaVerkollaJaBinaarikeolla(){
        int[] etaisyydet =dijkstra1.etsiReittiBinaarikeolla();
        for(int i=0;i<etaisyydet.length;i++){
            System.out.print(etaisyydet[i]+" ");
        }
        System.out.println("");
        assertTrue(etaisyydet[0]==0);
        assertTrue(etaisyydet[1]==7);
        assertTrue(etaisyydet[2]==9);
        assertTrue(etaisyydet[3]==20);
        assertTrue(etaisyydet[4]==20);
        assertTrue(etaisyydet[5]==11);
    }
}
