package pl.edu.pw.jagiello.rubikscube.view;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.SwingUtilities;

import pl.edu.pw.jagiello.rubikscube.model.Sticker;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.Event;

/**
 * Widok kostki rubika.
 * 
 * @author Maciej Jagiełło
 */
public class View
{
    private final Window window;
    
    /**
     * tworzy nowe okienko i przekazuje eventQueue do niego
     * 
     * @param eventQueue
     *            - kolejka zdarzen
     */
    public View(final LinkedBlockingQueue<Event> eventQueue)
    {
        window = new Window(eventQueue);
    }
    
    /**
     * zaktualizuj stan na kostce w widoku i wyslij polecenie Swingowi odrysowania okienka
     * 
     * @param tablicaNaklejek
     *            - stan kostki
     */
    public void updateState(final Sticker[][][] tablicaNaklejek)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                window.updateState(tablicaNaklejek);
                window.repaint();
            }
        });
    }
}
