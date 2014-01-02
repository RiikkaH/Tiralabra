/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kekovertailu.Kekojarjestaminen;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asennus
 */
public class KekojarjestaminenTest {
    
    private Kekojarjestaminen jarjesta;
    
    public KekojarjestaminenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jarjesta=new Kekojarjestaminen(new int[]{2,47,5,12,37,53,9,3,6,38,4,1,35,7,8,0,21});
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaJarjestaminenBinaarikeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta.jarjestaBinaarikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenBinomikeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta.jarjestaBinomikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenDarykeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta.jarjestaDarykeolla(4);
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenUudestaanDarykeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta.jarjestaDarykeolla(5);
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenFibonaccikeolla(){
        boolean jarjestyksessa=true;
        System.out.println("_____ALKAA TÄSTÄ_____");
        int[] t=jarjesta.jarjestaFibonaccikeolla();
        System.out.println("_____LOPPUU TÄHÄN_____");
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);//mitä ihmettä, mikä nullPointerException?
    }
}
