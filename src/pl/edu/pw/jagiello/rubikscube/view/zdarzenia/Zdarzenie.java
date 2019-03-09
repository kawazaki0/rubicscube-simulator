package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Klasa reprezentujaca zdarzenie na kostce
 * 
 * @author Maciej Jagiello
 */
public class Zdarzenie
{
    /** akcja - zdarzenie na kostce */
    protected MozliweZdarzenia akcja;
    
    /**
     * @param akcja
     *            - typ zdarzenia
     */
    public Zdarzenie(final MozliweZdarzenia akcja)
    {
        this.akcja = akcja;
    }
    
    /**
     * Zwraca rodzaj akcji danego zdarzenia
     * 
     * @return akcja - typ zdarzenia
     */
    public MozliweZdarzenia getAkcja()
    {
        return akcja;
    }
    
}
