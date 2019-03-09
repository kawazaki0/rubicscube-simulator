package pl.edu.pw.jagiello.rubikscube.model;

/**
 * @author Maciej Jagiełło 3 kolejne naklejki zgrupowane w wiersz
 */
class Wiersz
{
    /** tablica jednowymiarowa naklejek */
    private final Naklejka[] wierszNaklejek;
    
    /**
     * Konstruktor kopiujacy wiersz z naklejek
     * 
     * @param naklejka1
     *            Naklejka pierwsza
     * @param naklejka2
     *            Naklejka druga
     * @param naklejka3
     *            Naklejka trzecia
     */
    Wiersz(final Naklejka naklejka1, final Naklejka naklejka2, final Naklejka naklejka3)
    {
        wierszNaklejek = new Naklejka[3];
        
        wierszNaklejek[0] = naklejka1;
        wierszNaklejek[1] = naklejka2;
        wierszNaklejek[2] = naklejka3;
    }
    
    /**
     * konstruktor kopiujący
     * 
     * @param nowyWiersz
     *            wiersz do skopiowania
     */
    Wiersz(final Wiersz nowyWiersz)
    {
        wierszNaklejek = new Naklejka[3];
        System.arraycopy(nowyWiersz.getWiersz(), 0, wierszNaklejek, 0, nowyWiersz.getWiersz().length);
    }
    
    /**
     * Zwraca jeden gorny wiersz sciany
     * 
     * @return zwroc 3-elementowa tablice Naklejek
     */
    Naklejka[] getWiersz()
    {
        return wierszNaklejek;
    }
}
