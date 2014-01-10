package keot;

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
    private Solmu edellinen;
    private int marked;
    private int node;//dijkstraan, solmun 'nimi'. arvo=etäisyys

    /**
     * Luo uuden solmun.
     *
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
        this.edellinen = null;
        marked=0;
        node=0;
    }
    public Solmu(int node){
        this.arvo=0;
        this.aste=0;
        this.parent=null;
        this.lapsi=null;
        this.seuraava=null;
        this.edellinen=null;
        this.marked=0;
        this.node=node;
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

    public Solmu getEdellinen() {
        return edellinen;
    }
    
    public int getMarked(){
        return marked;
    }
    
    public int getNode(){
        return node;
    }
    
    public void setNode(int n){
        node=n;
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

    public void setEdellinen(Solmu s) {
        edellinen = s;
    }

    public void setLapsi(Solmu s) {
        lapsi = s;
    }

    public void setAste(int a) {
        aste = a;
    }
    public void mark(){
        marked=1;
    }
    public void unmark(){
        marked=0;
    }
}
