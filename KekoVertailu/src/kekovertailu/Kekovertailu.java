package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Kekovertailu {

    public static void main(String[] args) {
        //mittaaDijkstranAikoja(10);
        //mittaaJarjestamisenAikoja(10000);
    }
    
    /**
     * Mittaa ja tulostaa eri kekojen taulukon järjestämiseen käyttämää keskimääräistä
     * aikaa.
     * @param taulukonpituus järjestettävän taulukon pituus
     */
    public static void mittaaJarjestamisenAikoja(int taulukonpituus) {
        long binaari = 0;
        long dary = 0;
        long binomi = 0;
        long fibo = 0;

        for (int j = 0; j < 10; j++) {

            Kekojarjestaminen jarjesta = new Kekojarjestaminen(luoSatunnainenTaulukko(taulukonpituus));

            long alku = System.nanoTime();
            jarjesta.jarjestaBinaarikeolla();
            binaari += (System.nanoTime() - alku);

            alku = System.nanoTime();
            jarjesta.jarjestaDarykeolla(4);
            dary += (System.nanoTime() - alku);

            alku = System.nanoTime();
            jarjesta.jarjestaBinomikeolla();
            binomi += (System.nanoTime() - alku);

            alku = System.nanoTime();
            jarjesta.jarjestaFibonaccikeolla();
            fibo += (System.nanoTime() - alku);
        }
        binaari/=10*1000000;
        binomi/=10*1000000;
        fibo/=10*1000000;
        dary/=10*1000000;
        System.out.println("Järjestämiseen keskimäärin kuluneita aikoja eri keoilla kun "
                + "\n järjestettävän taulukon pituus oli "+taulukonpituus+":");
        System.out.println("Binäärikeko: "+binaari+" ms");
        System.out.println("d-ary -keko: "+dary+" ms");
        System.out.println("Binomikeko: "+binomi+" ms");
        System.out.println("Fibonaccikeko: "+fibo+" ms");
    }
    
    /*
    public static void mittaaDijkstranAikoja(int matriisinpituus){
        long binaari = 0;
        long dary = 0;
        long binomi = 0;
        long fibo = 0;
        
        for(int i=0;i<10;i++){
            
            Dijkstra dijkstra=new Dijkstra(null,luoSatunnainenMatriisi(matriisinpituus),0);
            
            long alku = System.nanoTime();
            dijkstra.etsiReittiBinaarikeolla();
            binaari += (System.nanoTime() - alku);

            alku = System.nanoTime();
            dijkstra.etsiReittiDarykeolla();
            dary += (System.nanoTime() - alku);

            alku = System.nanoTime();
            dijkstra.etsiReittiBinomikeolla();
            binomi += (System.nanoTime() - alku);

            alku = System.nanoTime();
            dijkstra.etsiReittiFibonaccikeolla();
            fibo += (System.nanoTime() - alku);
            
            System.out.println(i);
        }
        
        binaari/=10*1000000;
        binomi/=10*1000000;
        fibo/=10*1000000;
        dary/=10*1000000;
        System.out.println("Lyhyimpien matkojen etsimiseen kuluneita aikoja eri "
                + "\n keoilla, kun matriisin sivunpituus oli "+matriisinpituus+":");
        System.out.println("Binäärikeko: "+binaari+" ms");
        System.out.println("d-ary -keko: "+dary+" ms");
        System.out.println("Binomikeko: "+binomi+" ms");
        System.out.println("Fibonaccikeko: "+fibo+" ms");
    }
    * */
    private static int[] luoSatunnainenTaulukko(int pituus) {
        int[] taulukko = new int[pituus];
        for (int i = 0; i < pituus; i++) {
            taulukko[i] = (int) (Math.random() * pituus + 1);
        }
        return taulukko;
    }
    
    private static int[][] luoSatunnainenMatriisi(int pituus){
        int[][] matriisi=new int[pituus][pituus];
        for(int i=0;i<pituus;i++){
            matriisi[i]=luoSatunnainenTaulukko(pituus);
        }
        return matriisi;
    }
}
