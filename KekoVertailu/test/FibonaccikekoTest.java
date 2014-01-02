/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import keot.Fibonaccikeko;
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
        keko.insert(3);
        keko.insert(5);
        keko.insert(1);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testaaLisaysPienellaMaaralla(){
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==1);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==5);
    }
    @Test
    public void testaaLisaysVahanSuuremmallaMaaralla(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==2);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getSeuraava().getSeuraava().getArvo()==1);
    }
    @Test
    public void testaaFindMinPikkuruisellaKeolla(){
        assertTrue(keko.findMin().getArvo()==1);
    }
    @Test
    public void testaaFindMinVahanIsommalla(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        assertTrue(keko.findMin().getArvo()==1);
    }
    @Test
    public void testaaDeleteMinPikkuisellaKeolla(){
        keko.deleteMin();
        assertTrue(keko.getKeko().getAste()==1);
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko().getLapsi().getArvo()==5);
    }
    @Test
    public void testaaDeleteMinAavistuksenIsommalla(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.deleteMin();
        assertTrue(keko.findMin().getArvo()==2);
        assertTrue(keko.getKeko().getArvo()==2);
        assertTrue(keko.getKeko().getAste()==2);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==5);
        assertTrue(keko.getKeko().getSeuraava().getAste()==0);
    }
    @Test
    public void testaaDeleteMinVielaKerran(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.deleteMin();
        keko.deleteMin();
        assertTrue(keko.findMin().getArvo()==3);
        assertTrue(keko.getKeko().getArvo()==3);
        assertTrue(keko.getKeko()==keko.findMin());
        assertTrue(keko.getKeko().getAste()==2);
    }
    //en ole vielä toteuttanut tätä metodia, en ole aivan selvittänyt miten toimii
    @Test
    public void testaaDecreaseKeyPikkukeolla(){
        keko.decreaseKey(keko.getKeko(), 2);
        assertTrue(keko.getKeko().getArvo()==2);
        assertTrue(keko.getKeko().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==1);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==5);
    }
    @Test
    public void testaaDecreaseKeyMonimutkaisemmalla(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.deleteMin();
        keko.decreaseKey(keko.getKeko().getLapsi().getSeuraava().getLapsi(), 1);
        assertTrue(keko.getKeko().getArvo()==2);
        assertTrue(keko.findMin().getArvo()==1);
        assertTrue(keko.getKeko().getAste()==2);
        assertTrue(keko.getKeko().getMarked()==0);
        assertTrue(keko.getKeko().getLapsi().getSeuraava().getMarked()==1);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==1);
        assertTrue(keko.getKeko().getSeuraava().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==5);
    }
    @Test
    public void testaaDecreaseKeyJaSolmujenMerkkaaminen(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.insert(6);
        keko.insert(12);
        keko.insert(10);
        keko.insert(9);
        keko.deleteMin();
        keko.decreaseKey(keko.getKeko().getLapsi().getSeuraava().getSeuraava().getLapsi(), 2);
        assertTrue(keko.getKeko().getLapsi().getSeuraava().getSeuraava().getMarked()==1);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==2);
        assertTrue(keko.getKeko().getMarked()==0);
    }
    @Test
    public void testaaDecreaseKeyTosiMonimutkaisellaKeolla(){
        keko.insert(8);
        keko.insert(4);
        keko.insert(2);
        keko.insert(6);
        keko.insert(12);
        keko.insert(10);
        keko.insert(9);
        keko.deleteMin();
        keko.decreaseKey(keko.getKeko().getLapsi().getSeuraava().getSeuraava().getLapsi(), 2);
        keko.decreaseKey(keko.getKeko().getLapsi().getSeuraava().getSeuraava().getLapsi(), 1);
        assertTrue(keko.findMin().getArvo()==1);
        assertTrue(keko.getKeko().getArvo()==2);
        assertTrue(keko.getKeko().getAste()==2);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==3);
        assertTrue(keko.getKeko().getSeuraava().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getMarked()==0);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==1);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getAste()==1);
    }
}
