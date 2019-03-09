package pl.edu.pw.jagiello.rubikscube.view;

/**
 * Klasa reprezentujaca naklejke na kostce wraz z wszystkimi wspolrzednymi pikseli oraz
 * wspolrzednymi w tablicy naklejek stanu kostki
 * 
 * @author Maciej Jagiello
 */
public class NaklejkaNaKostce
{
    /** x1 - 1. wspolrzedna x-owa czworokata */
    private final int x1;
    
    /** y1 - 1. wspolrzedna y-owa czworokata */
    private final int y1;
    
    /** x2 - 2. wspolrzedna x-owa czworokata */
    private final int x2;
    
    /** y2 - 2. wspolrzedna y-owa czworokata */
    private final int y2;
    
    /** x3 - 3. wspolrzedna x-owa czworokata */
    private final int x3;
    
    /** y3 - 3. wspolrzedna y-owa czworokata */
    private final int y3;
    
    /** x4 - 4. wspolrzedna x-owa czworokata */
    private final int x4;
    
    /** y4 - 4. wspolrzedna y-owa czworokata */
    private final int y4;
    
    /** numerSciany nr sciany z tablicy naklejek */
    private final int numerSciany;
    
    /** wspolrzednaNaklejekX wspolrzedna x w tablicy naklejek */
    private final int wspolrzednaNaklejekX;
    
    /** wspolrzednaNaklejekY wspolrzedna y w tablicy naklejek */
    private final int wspolrzednaNaklejekY;
    
    /**
     * @param x1
     *            - 1. wspolrzedna x-owa czworokata
     * @param y1
     *            - 1. wspolrzedna y-owa czworokata
     * @param x2
     *            - 2. wspolrzedna x-owa czworokata
     * @param y2
     *            - 2. wspolrzedna y-owa czworokata
     * @param x3
     *            - 3. wspolrzedna x-owa czworokata
     * @param y3
     *            - 3. wspolrzedna y-owa czworokata
     * @param x4
     *            - 4. wspolrzedna x-owa czworokata
     * @param y4
     *            - 4. wspolrzedna y-owa czworokata
     * @param nrSciany
     *            - nr sciany z tablicy naklejek
     * @param wspX
     *            - wspolrzedna x w tablicy naklejek
     * @param wspY
     *            - wspolrzedna y w tablicy naklejek
     */
    public NaklejkaNaKostce(final int x1, final int y1, final int x2, final int y2, final int x3, final int y3, final int x4, final int y4,
            final int nrSciany, final int wspX, final int wspY)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.numerSciany = nrSciany;
        this.wspolrzednaNaklejekX = wspX;
        this.wspolrzednaNaklejekY = wspY;
    }
    
    /**
     * Zwraca 4 wspolrzedne x-owe danej naklejki na kostce
     * 
     * @return 4-elementowa tablica wspolrzednych x-owych
     */
    public int[] getPunktyX()
    {
        final int[] result = new int[4];
        
        result[0] = x1;
        result[1] = x2;
        result[2] = x3;
        result[3] = x4;
        
        return result;
    }
    
    /**
     * Zwraca 4 wspolrzedne y-owe danej naklejki na kostce
     * 
     * @return 4-elementowa tablice wspolrzednych y-owych
     */
    public int[] getPunktyY()
    {
        final int[] result = new int[4];
        
        result[0] = y1;
        result[1] = y2;
        result[2] = y3;
        result[3] = y4;
        
        return result;
    }
    
    /**
     * zwroc index sciany z tablicy odpowiadajacej naklejkom w tablicy stanu kostki
     * 
     * @return numerSciany - index sciany
     */
    public int getSciana()
    {
        return numerSciany;
    }
    
    /**
     * zwroc wspolrzedna X z tablicy odpowiadajacej naklejkom
     * 
     * @return wspolrzednaNaklejekX - wartosc wspolrzednej w tablicy naklejek stanu kostki
     */
    public int getWspolrzednaX()
    {
        return wspolrzednaNaklejekX;
    }
    
    /**
     * zwroc wspolrzedna Y z tablicy odpowiadajacej naklejkom
     * 
     * @return wspolrzednaNaklejekY - wartosc wspolrzednej w tablicy naklejek stanu kostki
     */
    public int getWspolrzednaY()
    {
        return wspolrzednaNaklejekY;
    }
    
}