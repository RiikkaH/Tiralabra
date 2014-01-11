package keot;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko implements Keko {

    private Solmu keko;//linkitetty lista juurisolmuista, osoittaa ensimmäiseen
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
     * @return keon pienin solmu
     */
    @Override
    public Solmu findMinSolmu() {
        return min;
    }

    /**
     * Palauttaa keon pienimmän solmun arvon.
     *
     * @return keon pienimmän solmun arvo
     */
    @Override
    public int findMin() {
        return min.getArvo();
    }

    /**
     * Poistaa keon pienimmän alkion ja tiivistää keon.
     */
    @Override
    public void deleteMin() {
        poistaMinLisaaLapset();
        yhdistaKeonPuut();
        paivitaMin();
        solmuja--;
    }

    /**
     * Yhdistelee keon puita niin, ettei millään kahdella puulla ole samaa
     * astetta.
     */
    private void yhdistaKeonPuut() {
        Solmu[] lista = new Solmu[solmuja];//listassa kohdassa n on viite kekoon, jonka aste on n
        Solmu edellinen = null;
        Solmu nyk = keko;
        while (nyk != null) {
            //keossa ei ole ennestään tämänasteista solmua
            if (lista[nyk.getAste()] == null) {
                lista[nyk.getAste()] = nyk;
                edellinen = nyk;
                nyk = nyk.getSeuraava();
            }//keossa on tämänasteinen solmu 
            else {
                Solmu s = lista[nyk.getAste()];
                //irrotetaan samanasteinen solmu keosta
                if (s.getEdellinen() != null) {
                    s.getEdellinen().setSeuraava(s.getSeuraava());
                    s.getSeuraava().setEdellinen(s.getEdellinen());
                } else {
                    s.getSeuraava().setEdellinen(null);
                }
                Solmu ed = nyk.getEdellinen();
                Solmu seur = nyk.getSeuraava();
                lista[nyk.getAste()] = null;
                //yhdistetään nyk ja s ja korvataan nyk solmulla uusi
                Solmu uusi = yhdistaPuut(nyk, s);
                uusi.setEdellinen(ed);
                uusi.setSeuraava(seur);
                if (seur != null) {
                    seur.setEdellinen(uusi);
                }
                if (ed != null) {
                    ed.setSeuraava(uusi);
                }
                edellinen = uusi;
                nyk = uusi;
            }
        }
        //päivitetään keko osoittamaan oikeaa kohtaa
        while (edellinen != null && edellinen.getEdellinen() != null) {
            edellinen = edellinen.getEdellinen();
        }
        keko = edellinen;//edellinen==null vain jos keko==null alussa
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
        while (s != null && s.getSeuraava() != null) {
            if (s.getArvo() < uusimin.getArvo()) {
                uusimin = s;
            }
            s = s.getSeuraava();
        }
        //viimeisen solmun tarkistaminen voisi myös olla hyödyllistä
        if (s != null && s.getArvo() < uusimin.getArvo()) {
            uusimin = s;
        }
        min = uusimin;
    }

    /**
     * Poistaa keosta minimialkion ja lisää sen lapset kekoon.
     */
    private void poistaMinLisaaLapset() {
        Solmu uudetpuut = min.getLapsi();
        Solmu s = min;
        //poistetaan min eli päivitetään pointterit sen ohi
        Solmu ed = s.getEdellinen();
        Solmu seur = s.getSeuraava();
        s = null;
        if (ed != null) {
            ed.setSeuraava(seur);
            s = ed;
        }
        if (seur != null) {
            seur.setEdellinen(ed);
            s = seur;
        }
        //jos min oli ainoa solmu, voidaan uudetpuut laittaa suoraan keoksi
        if (s == null) {
            keko = uudetpuut;
            if (uudetpuut != null) {
                //uusillapuilla ei tietenkään ole enää parent:ia.
                uudetpuut.setParent(null);
                while (uudetpuut.getSeuraava() != null) {
                    uudetpuut = uudetpuut.getSeuraava();
                    uudetpuut.setParent(null);
                }
            }
            return;
        }
        Solmu tallennettu = s;//s ei ole null
        //min ei ollut ainoa ja lapsia on
        //min on poistettu, laitetaan lapset listaan
        if (uudetpuut != null) {
            seur=s.getSeuraava();
            s.setSeuraava(uudetpuut);
            uudetpuut.setEdellinen(s);
            while(uudetpuut.getSeuraava()!=null){
                uudetpuut=uudetpuut.getSeuraava();
            }
            uudetpuut.setSeuraava(seur);
            if(seur!=null){
                seur.setEdellinen(uudetpuut);
            }
        }
        //päivitetään kekopointteri osoittamaan oikein
        while (tallennettu.getEdellinen() != null) {
            tallennettu = tallennettu.getEdellinen();
        }
        keko = tallennettu;
    }

    /**
     * Vähentää solmun s arvon d:hen.
     *
     * @param s solmu, jonka arvoa vähennetään
     * @param d solmun uusi arvo
     */
    @Override
    public void decreaseKey(Solmu s, int d) {
        if (d < s.getArvo() && d > 0) {
            s.setArvo(d);
            //jos kekoehto rikkoutuu
            if (s.getParent() != null && s.getParent().getArvo() > d) {
                Solmu p = leikkaaJaLiita(s);
                //jos parent ei ole merkattu, se merkataan. jos se on merkattu, sekin
                //leikataan pois
                boolean jatkuu = true;
                while (p != null && jatkuu) {
                    if (p.getParent() == null) {
                        jatkuu = false;
                    } else if (p.getMarked() == 0) {
                        p.mark();
                        jatkuu = false;
                    } else if (p.getMarked() == 1) {
                        p = leikkaaJaLiita(p);
                    }else{
                        jatkuu=false;
                    }
                }
            }
        }
    }

    /**
     * Irrottaa solmun vanhemmastaan ja lisää kekoon.
     *
     * @param s
     * @return
     */
    private Solmu leikkaaJaLiita(Solmu s) {
        Solmu p = s.getParent();
        //pitää tarkistaa onko ainoa lapsi, pitääkö vanhemman lapsipointteria siirtää
        //parent osoittaa tähän, mutta sisaruksia on: parentin pointteria pitää siirtää
        if (p != null && s.getSeuraava() != null) {
            p.setLapsi(s.getSeuraava());
        } //tämä on ainoa
        else if (p != null && p.getAste() == 1) {
            p.setLapsi(null);
        }
        if (p != null) {
            p.setAste(p.getAste() - 1);
        }
        //jos parent osoittaa muualle tai sitä ei ole, sille ei tarvitse tehdä mitään
        s.setParent(null);
        s.unmark();
        insert(s);//tämä päivittää minin
        return p;
    }

    /**
     * Lisää kekoon uuden solmun. Paivittaa myös minin arvon.
     *
     * @param s kekoon lisättävä solmu
     */
    public void insert(Solmu s) {
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
            if (s.getArvo() < min.getArvo()) {
                min = s;
            }
        }
        solmuja++;
    }

    /**
     * Lisää kekoon uuden solmun arvolla key.
     *
     * @param key uuden solmun arvo
     */
    @Override
    public void insert(int key) {
        Solmu s = new Solmu(key, null, null, 0);
        insert(s);
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
