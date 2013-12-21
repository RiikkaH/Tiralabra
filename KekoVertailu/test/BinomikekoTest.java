/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kekovertailu.Binomikeko;
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
public class BinomikekoTest {
    
    private Binomikeko keko;
    
    public BinomikekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko=new Binomikeko();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaLisaysPienellaMaaralla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(6);
        keko.insert(2);
        //yksi kahden asteen puu, juuressa yksi, lapsina 4 ja 2, jolla vielä lapsi 6
        assertTrue(keko.getKeko().getAste()==2);
        assertTrue(keko.getKeko().getArvo()==1);
        assertTrue(keko.getKeko().getSeuraava()==null);
        assertTrue(keko.getKeko().getLapsi().getArvo()==4);
        assertTrue(keko.getKeko().getLapsi().getAste()==0);
        assertTrue(keko.getKeko().getLapsi().getSeuraava().getArvo()==2);
        assertTrue(keko.getKeko().getLapsi().getSeuraava().getAste()==1);
        assertTrue(keko.getKeko().getLapsi().getSeuraava().getLapsi().getArvo()==6);
    }
    @Test
    public void testaaLisaysVahanSuuremmallaMaaralla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(6);
        keko.insert(2);
        keko.insert(14);
        keko.insert(8);
        keko.insert(5);
        assertTrue(keko.getKeko().getArvo()==5);
        assertTrue(keko.getKeko().getAste()==0);
        assertTrue(keko.getKeko().getSeuraava().getAste()==1);
        assertTrue(keko.getKeko().getSeuraava().getArvo()==8);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getAste()==2);
        assertTrue(keko.getKeko().getSeuraava().getSeuraava().getArvo()==1);
    }
    @Test
    public void testaaFindMinPienella(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(6);
        keko.insert(2);
        assertTrue(keko.findMin().getArvo()==1);
    }
    @Test
    public void testaaFindMinVahanIsommalla(){
        keko.insert(1);
        keko.insert(4);
        keko.insert(6);
        keko.insert(2);
        keko.insert(14);
        keko.insert(8);
        keko.insert(5);
        assertTrue(keko.findMin().getArvo()==1);
    }
    
}
