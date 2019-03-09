package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Klasa reprezentujaca zdarzenie na kostce wraz z jego krotnoscia.
 * 
 * @author Maciej Jagiello
 */
public class ZdarzenieWielokrotne extends Zdarzenie
{
    /** krotnosc ruchu */
    private final int krotnosc;
    
    /**
     * @param ruch
     *            ruch do wykonania
     * @param krot
     *            krotnosc ruchu
     */
    public ZdarzenieWielokrotne(final MozliweZdarzenia ruch, final int krot)
    {
        super(ruch);
        krotnosc = krot;
    }
    
    /**
     * Zwraca krotnosc ruchu
     * 
     * @return Krotnosc ruchu
     */
    public int getKrotnosc()
    
    {
        return krotnosc;
    }
    
}
