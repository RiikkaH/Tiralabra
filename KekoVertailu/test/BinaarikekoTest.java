

import keot.Binaarikeko;
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
public class BinaarikekoTest {
    
    private Binaarikeko keko;
    
    public BinaarikekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko=new Binaarikeko();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaaLisaamistaParillaArvolla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        assertTrue(keko.getKeko()[0]==1);
        assertTrue(keko.getKeko()[1]==2);
        assertTrue(keko.getKeko()[2]==16);
        assertTrue(keko.getKeko()[3]==4);
        assertTrue(keko.getSize()==4);
    }
    @Test
    public void testaaLisaamistaSuuremmallaMaaralla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        keko.insert(3);
        keko.insert(12);
        keko.insert(8);
        assertTrue(keko.getKeko()[2]==3);
        assertTrue(keko.getKeko()[5]==16);
        assertTrue(keko.getKeko()[7]==8);
        assertTrue(keko.getKeko()[3]==4);
        assertTrue(keko.getSize()==8);
    }
    @Test
    public void testaaLoytaakoPieninta(){
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        keko.insert(3);
        assertTrue(keko.findMin()==2);
    }
    @Test
    public void testaaUudestaanLoytaakoPieninta(){
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        assertTrue(keko.findMin()==2);
    }
    @Test
    public void testaaToimiikoPienimmanPoistaminenPikkuisellaKeolla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.deleteMin();
        assertTrue(keko.getKeko()[0]==2);
        assertTrue(keko.getKeko()[1]==4);
        assertTrue(keko.getKeko()[2]==16);
        assertTrue(keko.getSize()==3);
    }
    @Test
    public void testaaToimiikoPienimmanPoistaminenIsommallaKeolla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        keko.insert(3);
        keko.insert(12);
        keko.insert(8);
        keko.deleteMin();
        assertTrue(keko.getKeko()[0]==2);
        assertTrue(keko.getKeko()[1]==4);
        assertTrue(keko.getKeko()[3]==8);
        assertTrue(keko.getKeko()[2]==3);
        assertTrue(keko.getSize()==7);
    }
    @Test
    public void testaaToimiikoDecreaseKeyPikkuruisellaKeolla(){
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        keko.decreaseKey(3, 1);
        assertTrue(keko.findMin()==1);
        assertTrue(keko.getSize()==4);
        assertTrue(keko.getKeko()[1]==2);
        assertTrue(keko.getKeko()[2]==4);
        assertTrue(keko.getKeko()[3]==9);
    }
    @Test
    public void testaaToimiikoDecreaseKeyVahanIsommallaKeolla(){
        keko.insert(5);
        keko.insert(4);
        keko.insert(16);
        keko.insert(2);
        keko.insert(9);
        keko.insert(3);
        keko.insert(12);
        keko.insert(8);
        keko.decreaseKey(3, 1);
        assertTrue(keko.findMin()==1);
        assertTrue(keko.getSize()==8);
        assertTrue(keko.getKeko()[1]==2);
        assertTrue(keko.getKeko()[3]==4);
        assertTrue(keko.getKeko()[4]==9);
        assertTrue(keko.getKeko()[7]==8);
    }
}
