/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        //assertTrue(keko.getKeko()[1]==12);
        //assertTrue(keko.getKeko()[2]==16);
        //assertTrue(keko.getKeko()[3]==4);
        assertTrue(keko.getSize()==8);
    }
}
