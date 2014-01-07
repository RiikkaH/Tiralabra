package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Kekovertailu {

    public static void main(String[] args) {
        mittaaJarjestamisenAikoja(1000);
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
        binaari/=10*100000;
        binomi/=10*100000;
        fibo/=10*100000;
        dary/=10*100000;
        System.out.println("Järjestämiseen keskimäärin kuluneita aikoja eri keoilla kun "
                + "\n järjestettävän taulukon pituus oli "+taulukonpituus+":");
        System.out.println("Binäärikeko: "+binaari+" ms");
        System.out.println("d-ary -keko: "+dary+" ms");
        System.out.println("Binomikeko: "+binomi+" ms");
        System.out.println("Fibonaccikeko: "+fibo+" ms");
    }

    public static int[] luoSatunnainenTaulukko(int pituus) {
        int[] taulukko = new int[pituus];
        for (int i = 0; i < pituus; i++) {
            taulukko[i] = (int) (Math.random() * pituus + 1);
        }
        return taulukko;
    }
}
