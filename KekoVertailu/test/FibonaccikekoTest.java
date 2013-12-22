/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kekovertailu.Fibonaccikeko;
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
public class FibonaccikekoTest {
    
    private Fibonaccikeko keko;
    
    public FibonaccikekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko=new Fibonaccikeko();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testaaLisaysPienellaMaaralla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==5);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==1);
    }
    @Test
    public void testaaLisaysVahanSuuremmallaMaaralla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getSeuraava().getSeuraava().getArvo()==4);
    }
    @Test
    public void testaaFindMinPikkuruisellaKeolla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        assertTrue(keko.findMin().getArvo()==1);
    }
    @Test
    public void testaaFindMinVahanIsommalla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        assertTrue(keko.findMin().getArvo()==1);
    }
    @Test
    public void testaaDeleteMinPikkuisellaKeolla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        keko.deleteMin();
        assertTrue(keko.getKeko().getAste()==1);
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko().getLapsi().getArvo()==5);
    }
    @Test
    public void testaaDeleteMinAavistuksenIsommalla(){
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.deleteMin();
        assertTrue(keko.findMin().getArvo()==2);
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko().getAste()==2);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==2);
        assertTrue(keko.getKeko().getSeuraava().getAste()==0);
    }
    //en ole vielä toteuttanut tätä metodia, en ole aivan selvittänyt miten toimii
    @Test
    public void testaaDecreaseKeyPikkukeolla(){
        
    }
    @Test
    public void testaaDecreaseKeyIsommalla(){
        
    }
}
