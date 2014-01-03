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
    private Kekojarjestaminen jarjesta2;
    
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
        int[] taulukko=new int[100];
        for(int i=0;i<100;i++){
            taulukko[i]=(int)(Math.random()*100+1);
        }
        jarjesta=new Kekojarjestaminen(new int[]{2,47,5,12,38,53,9,3,6,38,4,1,35,7,8,0,21});
        jarjesta2=new Kekojarjestaminen(taulukko);
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
        int[] t=jarjesta.jarjestaFibonaccikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenSatunnaisellaBinaarikeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta2.jarjestaBinaarikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenSatunnaisellaBinomikeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta2.jarjestaBinomikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenSatunnaisellaDarykeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta2.jarjestaDarykeolla(4);
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenUudestaanSatunnaisellaDarykeolla(){
        boolean jarjestyksessa=true;
        int[] t=jarjesta2.jarjestaDarykeolla(5);
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);
    }
    @Test
    public void testaaJarjestaminenSatunnaisellaFibonaccikeolla(){
        System.out.println("");
        boolean jarjestyksessa=true;
        int[] t=jarjesta2.jarjestaFibonaccikeolla();
        for(int i=0;i<t.length-1;i++){
            if(t[i+1]<t[i]){
                jarjestyksessa=false;
            }
        }
        assertTrue(jarjestyksessa);//mitä ihmettä, mikä nullPointerException?
    }
}
