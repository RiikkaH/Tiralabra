
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Kekovertailu {

    public static void main(String[] args) {
        mittaaJarjestamisenAikoja();
    }
    
    public static void mittaaJarjestamisenAikoja(){
        int[] taulukko=new int[10000];
        for(int i=0;i<10000;i++){
            taulukko[i]=(int)(Math.random()*100000+1);
        }
        Kekojarjestaminen jarjesta=new Kekojarjestaminen(taulukko);
        
        System.out.println("Kekojärjestämisen viemä aika eri keoilla");
        
        long alku=System.nanoTime();
        jarjesta.jarjestaBinaarikeolla();
        System.out.println("Binäärikeko: "+(System.nanoTime()-alku)+" ms");
        
        alku=System.nanoTime();
        jarjesta.jarjestaDarykeolla(4);
        System.out.println("d-arykeko(4): "+(System.nanoTime()-alku)+" ms");
        
        alku=System.nanoTime();
        jarjesta.jarjestaBinomikeolla();
        System.out.println("Binomikeko: "+(System.nanoTime()-alku)+" ms");
        
        alku=System.nanoTime();
        jarjesta.jarjestaFibonaccikeolla();
        System.out.println("Fibonaccikeko: "+(System.nanoTime()-alku)+" ms");
    }
    
}
