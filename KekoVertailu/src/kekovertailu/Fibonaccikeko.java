package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko {

    private Solmu keko;//linkitetty lista juurisolmuista
    private Solmu min;
    private int solmuja;

    public Fibonaccikeko() {
        keko = null;
        min = keko;
        solmuja = 0;
    }

    public Fibonaccikeko(Solmu s) {
        keko = s;
        min = s;
        solmuja = 1;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun.
     *
     * @return keon pienin solmu, tai -1 jos keko on tyhjä
     */
    public Solmu findMin() {
        return min;
    }

    /**
     * Poistaa keon pienimmän alkion ja tiivistää keon.
     */
    public void deleteMin() {
        poistaMinLisaaLapset();
        paivitaMin();
        yhdistaKeonPuut();
    }

    /**
     * Yhdistelee keon puita niin, ettei millään kahdella puulla ole samaa
     * astetta.
     */
    private void yhdistaKeonPuut() {
        Solmu[] lista = new Solmu[solmuja];
        Solmu edellinen=null;
        Solmu nyk = keko;
        while (nyk != null) {
            if (lista[nyk.getAste()] == null) {
                lista[nyk.getAste()] = nyk;
                edellinen=nyk;
                nyk = nyk.getSeuraava();
            } else {
                Solmu s=lista[nyk.getAste()];
                if(s.getEdellinen()!=null){
                    s.getEdellinen().setSeuraava(s.getSeuraava());
                    s.getSeuraava().setEdellinen(s.getEdellinen());
                }else{
                    s.getSeuraava().setEdellinen(null);
                }
                Solmu ed=nyk.getEdellinen();
                Solmu seur=nyk.getSeuraava();
                lista[nyk.getAste()]=null;
                //nyt yhdistetään nyk ja s, ja laitetaan yhdistetty ed:istä 
                //seuraavaksi. s on jo poistettu nätisti, siitä vois tehdä oman metodin.
                //sitten uusi yhdistetty solmu laitetaan nyk:in paikalle,
                //ja se on seuraava nyk.
                Solmu uusi=yhdistaPuut(nyk,s);
                edellinen=uusi;
                uusi.setEdellinen(ed);
                uusi.setSeuraava(seur);
                if(seur!=null){
                    seur.setEdellinen(uusi);
                }if(ed!=null){
                    ed.setSeuraava(uusi);
                }
                nyk=uusi;
            }
        }
        
        while(edellinen!=null&&edellinen.getEdellinen()!=null){
            edellinen=edellinen.getEdellinen();
        }
        keko=edellinen;
    }

    private Solmu yhdistaPuut(Solmu s, Solmu t) {
        if (s.getArvo() < t.getArvo()) {
            Solmu apu = s;
            s = t;
            t = apu;
        }
        lisaaLapsi(t, s);
        return t;
    }

    /**
     * Lisää solmulle p lapsen l
     *
     * @param p solmu, johon lapsi lisätään
     * @param l lisättävä lapsi
     */
    //lisättävä tulee aina lapsista viimeiseksi
    private void lisaaLapsi(Solmu p, Solmu l) {
        if (p.getAste() == 0) {
            l.setParent(p);
            p.setLapsi(l);
            l.setEdellinen(null);
            l.setSeuraava(null);
        } else {
            Solmu s = p.getLapsi();
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
            }
            l.setParent(p);
            s.setSeuraava(l);
            l.setEdellinen(s);
            l.setSeuraava(null);
        }
        p.setAste(p.getAste() + 1);
    }

    public Solmu getKeko() {
        return keko;
    }

    /**
     * Päivittää min:in arvon.
     */
    private void paivitaMin() {
        Solmu uusimin = keko;
        Solmu s = keko;
        while (s.getSeuraava() != null) {
            if (s.getArvo() < uusimin.getArvo()) {
                uusimin = s;
            }
            s = s.getSeuraava();
        }
        min = uusimin;
    }

    /**
     * Poistaa keosta minimialkion ja lisää sen lapset kekoon.
     */
    private void poistaMinLisaaLapset() {
        Solmu uudetpuut = min.getLapsi();
        Solmu s = keko;
        while (s != min) {
            s = s.getSeuraava();
        }
        Solmu ed=s.getEdellinen();
        Solmu seur=s.getSeuraava();
        s=null;
        if(ed!=null){
            ed.setSeuraava(seur);
            s=ed;
        }if(seur!=null){
            seur.setEdellinen(ed);
            s=seur;
        }
        //jos min oli ainoa solmu
        if(s==null){
            keko=uudetpuut;
            return;
        }
        //min on poistettu, siirrytään listan loppuun ja lisätään uudetpuut sinne
        if (uudetpuut != null) {
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
            }
            uudetpuut.setParent(null);
            uudetpuut.setEdellinen(s);
            s.setSeuraava(uudetpuut);
            while(s!=null){
                s.setParent(null);
                s=s.getSeuraava();
            }
            while(uudetpuut.getEdellinen()!=null){
                uudetpuut=uudetpuut.getEdellinen();
            }
            keko=uudetpuut;
        }
    }

    /**
     * Vähentää solmun s arvon d:hen.
     *
     * @param s solmu, jonka arvoa vähennetään
     * @param d solmun uusi arvo
     */
    public void decreaseKey(Solmu s, int d) {

    }

    /**
     * Lisää kekoon uuden solmun arvolla key.
     *
     * @param key kekoon lisättävän solmun arvo
     */
    public void insert(int key) {
        Solmu s = new Solmu(key, null, null, 0);
        if (keko == null) {
            keko = s;
            min = s;
        } else {
            if (keko.getSeuraava() != null) {
                Solmu apu = keko.getSeuraava();
                keko.setSeuraava(s);
                s.setSeuraava(apu);
                apu.setEdellinen(s);
                s.setEdellinen(keko);
            } else {
                keko.setSeuraava(s);
                s.setEdellinen(keko);
            }
            if (key < min.getArvo()) {
                min = s;
            }
        }
        solmuja++;
    }

    /**
     * Yhdistää kaksi fibonaccikekoa.
     *
     * @param k toinen yhdistettävä keko
     */
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
