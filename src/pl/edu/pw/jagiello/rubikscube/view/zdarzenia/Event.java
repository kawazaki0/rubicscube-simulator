package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Klasa reprezentujaca zdarzenie na kostce
 * 
 * @author Maciej Jagiello
 */
public class Event
{
    /** akcja - zdarzenie na kostce */
    protected AllowedEvents event;
    
    /**
     * @param event
     *            - typ zdarzenia
     */
    public Event(final AllowedEvents event)
    {
        this.event = event;
    }
    
    /**
     * Zwraca rodzaj akcji danego zdarzenia
     * 
     * @return akcja - typ zdarzenia
     */
    public AllowedEvents getEvent()
    {
        return event;
    }
    
}
