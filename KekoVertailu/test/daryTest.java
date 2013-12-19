

import kekovertailu.darykeko;
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
public class daryTest {
    
    private darykeko keko;
    
    public daryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko=new darykeko(4);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaLisaysPienellaMaaralla(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        assertTrue(keko.getKeko()[0]==2);
        assertTrue(keko.getKeko()[1]==12);
        assertTrue(keko.getKeko()[2]==16);
        assertTrue(keko.getKeko()[3]==4);
        assertTrue(keko.getSize()==4);
    }
    @Test
    public void testaaLisaysVahanSuuremmallaMaaralla(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(8);
        keko.insert(1);
        keko.insert(9);
        keko.insert(6);
        assertTrue(keko.getKeko()[0]==1);
        assertTrue(keko.getKeko()[1]==2);
        assertTrue(keko.getKeko()[5]==12);
        assertTrue(keko.getKeko()[6]==9);
        assertTrue(keko.getSize()==8);
    }
    @Test
    public void testaaLoytyykoMinPikkuruisestaKeosta(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        assertTrue(keko.findMin()==2);
    }
    @Test
    public void testaaLoytyykoMinVahanIsommasta(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(8);
        keko.insert(1);
        keko.insert(9);
        keko.insert(6);
        assertTrue(keko.findMin()==1);
    }
    @Test
    public void testaaToimiikoPikkuisestaKeostaPienimmanPoistaminen(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.deleteMin();
        for(int i=0;i<3;i++){
            System.out.println(keko.getKeko()[i]);
        }
        assertTrue(keko.getKeko()[0]==4);
        assertTrue(keko.getKeko()[1]==12);
        assertTrue(keko.getKeko()[2]==16);
        assertTrue(keko.getSize()==3);
    }
    public void testaaToimiikoVahanIsommastaKeostaPienimmanPoistamine(){
        keko.insert(12);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(8);
        keko.insert(1);
        keko.insert(9);
        keko.insert(6);
        keko.deleteMin();
        assertTrue(keko.findMin()==2);
        assertTrue(keko.getKeko()[1]==6);
        assertTrue(keko.getKeko()[2]==16);
        assertTrue(keko.getKeko()[6]==9);
        assertTrue(keko.getSize()==7);
    }
}
