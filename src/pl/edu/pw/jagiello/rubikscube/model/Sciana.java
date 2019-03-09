package pl.edu.pw.jagiello.rubikscube.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klasa zawierająca naklejki i połączenia do ścianek z boku
 * 
 * @author Maciej Jagiełło
 */
class Sciana
{
    /**
     * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi X (osi
     * przechodzacej przez LR)
     * 
     * <pre>
     *   _____
     *  /___ /|
     * |    |-----
     * |____|/
     * </pre>
     */
    private Sciana axisX;
    
    /**
     * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi Y (osi
     * przechodzacej przez UD)
     * 
     * <pre>
     *     |
     *   __|__
     *  /___ /|
     * |    | |
     * |____|/
     * </pre>
     */
    private Sciana axisY;
    
    /**
     * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi Z (osi
     * przechodzacej przez FB)
     * 
     * <pre>
     *   _____
     *  /___ /|
     * |    | |
     * |_/__|/
     *  /
     * </pre>
     */
    private Sciana axisZ;
    
    /**
     * Dwuwymiarowa tablica Naklejek reprezentujaca na ktorej scianie powinna znalezc sie
     * naklejka
     */
    private final Naklejka[][] pola;
    
    /**
     * wypełnij dwuwymiarowa tablice Naklejek naklejkami z argumentu
     * 
     * @param naklejka
     *            naklejka do wypelniania
     */
    Sciana(final Naklejka naklejka)
    {
        pola = new Naklejka[3][3];
        for (final Naklejka[] element : pola)
        {
            Arrays.fill(element, naklejka);
        }
    }
    
    /**
     * Zwraca sciane polozona, wzgledem this, ktora pojawi sie na miejsce poprzedniej po
     * obroceniu wokol osi X (osi przechodzacej przez LR)
     * 
     * @return Sciana
     */
    Sciana getAxisX()
    {
        return axisX;
    }
    
    /**
     * Zwraca sciane polozona wzgledem this, ktora pojawi sie na miejsce poprzedniej po
     * obroceniu wokol osi Y (osi przechodzacej przez UD)
     * 
     * @return Sciana
     */
    Sciana getAxisY()
    {
        return axisY;
    }
    
    /**
     * Zwraca sciane polozona wzgledem this, ktora pojawi sie na miejsce poprzedniej po
     * obroceniu wokol osi Z (osi przechodzacej przez FB)
     * 
     * @return Sciana
     */
    Sciana getAxisZ()
    {
        return axisZ;
    }
    
    /**
     * zwraca stan sciany, czyli pola typu enum
     * 
     * @return pola dwuwymiarowa tablica Naklejek
     */
    Naklejka[][] getStanSciany()
    {
        return pola;
    }
    
    /**
     * zwraca gorny wiersz scianki
     * 
     * @return Row Wiersz zbudowany z tablicy trzech Naklejek
     */
    Wiersz getTopRow()
    {
        return (new Wiersz(pola[0][0], pola[0][1], pola[0][2]));
    }
    
    /**
     * obraca sciane ClockWise czyli wg wskazowek zegara (8-miu Naklejek)
     */
    void obrot()
    {
        final Naklejka temp1 = pola[0][0];
        final Naklejka temp2 = pola[0][1];
        
        // rogi
        pola[0][0] = pola[2][0];
        pola[2][0] = pola[2][2];
        pola[2][2] = pola[0][2];
        pola[0][2] = temp1;
        
        // krawedzie
        pola[0][1] = pola[1][0];
        pola[1][0] = pola[2][1];
        pola[2][1] = pola[1][2];
        pola[1][2] = temp2;
    }
    
    /**
     * zmienia orientacje scianki z góry na dół (nowy punkt początkowy w [2][2] (stary w
     * [0][0]))
     */
    void odwroc()
    {
        final List<Naklejka[]> listaWierszy = Arrays.asList(pola);
        Collections.reverse(listaWierszy);
        
        for (int i = 0; i < listaWierszy.size(); i++)
        {
            final List<Naklejka> wierszWLiscie = Arrays.asList(listaWierszy.get(i));
            Collections.reverse(wierszWLiscie);
            pola[i] = (Naklejka[]) wierszWLiscie.toArray();
        }
    }
    
    /**
     * Setter axisX. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
     * poprzedniej po obroceniu wokol osi X (osi przechodzacej przez RL)
     * 
     * @param axisX
     *            nowa sciana
     */
    void setAxisX(final Sciana axisX)
    {
        this.axisX = axisX;
    }
    
    /**
     * Setter axisY. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
     * poprzedniej po obroceniu wokol osi Y (osi przechodzacej przez UD)
     * 
     * @param axisY
     *            nowa sciana
     */
    void setAxisY(final Sciana axisY)
    {
        this.axisY = axisY;
    }
    
    /**
     * Setter axisZ. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
     * poprzedniej po obroceniu wokol osi Z (osi przechodzacej przez FB)
     * 
     * @param axisZ
     *            nowa sciana
     */
    void setAxisZ(final Sciana axisZ)
    {
        this.axisZ = axisZ;
    }
    
    /**
     * Ustawia nowy wiersz w scianie
     * 
     * @param nowyWiersz
     */
    void setTopRow(final Wiersz nowyWiersz)
    {
        System.arraycopy(nowyWiersz.getWiersz(), 0, pola[0], 0, nowyWiersz.getWiersz().length);
    }
}
