/**
 * 
 */
package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Klasa reprezentujaca zdarzenie jednokrotne typu MozliweZdarzenia
 * 
 * @author Maciej Jagiello
 */
public class SingleEvent extends Event
{
    
    /**
     * @param event
     *            - typ zdarzenia
     */
    public SingleEvent(final AllowedEvents event)
    {
        super(event);
    }
    
}
