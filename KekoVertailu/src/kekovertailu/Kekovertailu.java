
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
        
        long alku=System.currentTimeMillis();
        jarjesta.jarjestaBinaarikeolla();
        System.out.println("Binäärikeko: "+(System.currentTimeMillis()-alku)+" ms");
        
        alku=System.currentTimeMillis();
        jarjesta.jarjestaDarykeolla(4);
        System.out.println("d-arykeko(4): "+(System.currentTimeMillis()-alku)+" ms");
        
        alku=System.currentTimeMillis();
        jarjesta.jarjestaBinomikeolla();
        System.out.println("Binomikeko: "+(System.currentTimeMillis()-alku)+" ms");
        
        alku=System.currentTimeMillis();
        jarjesta.jarjestaFibonaccikeolla();//ja miksi ihmeessä ei muka toimi??
        System.out.println("Fibonaccikeko: "+(System.currentTimeMillis()-alku)+" ms");
    }
    
}
