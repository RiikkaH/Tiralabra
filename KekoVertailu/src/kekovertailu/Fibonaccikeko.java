package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko {

    private Solmu keko;//linkitetty lista juurisolmuista
    private Solmu min;

    public Fibonaccikeko() {
        keko = null;
        min = keko;
    }

    public Fibonaccikeko(Solmu s) {
        keko = s;
        min = s;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun.
     *
     * @return keon pienin solmu, tai -1 jos keko on tyhjä
     */
    public Solmu findMin() {
        return min;
    }

    public void deleteMin() {
        poistaMinLisaaLapset();
        paivitaMin();
        yhdistaKeonPuut();
    }
    /**
     * Yhdistelee keon puita niin, ettei millään kahdella puulla ole samaa astetta.
     */
    private void yhdistaKeonPuut(){
        
    }
    /**
     * Päivittää min:in arvon.
     */
    private void paivitaMin(){
        Solmu uusimin=keko;
        Solmu s=keko;
        while(s.getSeuraava()!=null){
            if(s.getArvo()<uusimin.getArvo()){
                uusimin=s;
            }
            s=s.getSeuraava();
        }
        min=uusimin;
    }
    /**
     * Poistaa keosta minimialkion ja lisää sen lapset kekoon.
     */
    private void poistaMinLisaaLapset() {
        Solmu uudetpuut = min.getLapsi();
        if (uudetpuut != null) {
            uudetpuut.setParent(null);
            Solmu s = keko;
            while (s.getSeuraava() != min) {
                s = s.getSeuraava();
            }
            if(s.getSeuraava().getSeuraava()!=null){
                s.setSeuraava(s.getSeuraava().getSeuraava());
                while(s.getSeuraava()!=null){
                    s=s.getSeuraava();
                }
            }
            s.setSeuraava(uudetpuut); 
        }
    }

    public void decreaseKey(Solmu s, int d) {

    }

    /**
     * Lisää kekoon uuden solmun arvolla key.
     *
     * @param key kekoon lisättävän solmun arvo
     */
    public void insert(int key) {
        Solmu s = new Solmu(key, null, null, 0);
        if (keko.getSeuraava() != null) {
            s.setSeuraava(keko.getSeuraava());
            keko.setSeuraava(s);
        } else {
            keko.setSeuraava(s);
        }
        if (key < min.getArvo()) {
            min = s;
        }
    }

    public void merge(Fibonaccikeko k) {
        if (keko == null) {
            keko = k.keko;
        }
        Solmu s = keko;
        while (s.getSeuraava() != null) {
            s = s.getSeuraava();
        }
        s.setSeuraava(k.keko);
    }
}
