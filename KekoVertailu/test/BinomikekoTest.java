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
        keko.insert(10);
    }
}
