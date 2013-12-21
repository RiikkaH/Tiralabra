package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Solmu {

    private int arvo;
    private int aste;
    private Solmu lapsi;
    private Solmu parent;
    private Solmu seuraava;

    /**
     * Luo uuden solmun.
     * @param arvo solmun arvo
     * @param parent solmun vanhempi
     * @param lapsi solmun ensimmäinen lapsi
     * @param aste solmun lasten lukumäärä
     */
    public Solmu(int arvo, Solmu parent, Solmu lapsi, int aste) {
        this.arvo = arvo;
        this.aste = aste;
        this.parent = parent;
        this.lapsi = lapsi;
        this.seuraava = null;
    }

    public int getAste() {
        return aste;
    }

    public int getArvo() {
        return arvo;
    }

    public Solmu getParent() {
        return parent;
    }

    public Solmu getLapsi() {
        return lapsi;
    }

    public Solmu getSeuraava() {
        return seuraava;
    }

    public void setParent(Solmu s) {
        parent = s;
    }

    public void setArvo(int a) {
        arvo = a;
    }

    public void setSeuraava(Solmu s) {
        seuraava = s;
    }
    
    public void setLapsi(Solmu s){
        lapsi=s;
    }
}
