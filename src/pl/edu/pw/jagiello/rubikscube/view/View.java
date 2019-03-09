package pl.edu.pw.jagiello.rubikscube.view;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.SwingUtilities;

import pl.edu.pw.jagiello.rubikscube.model.Naklejka;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.Zdarzenie;

/**
 * Widok kostki rubika.
 * 
 * @author Maciej Jagiełło
 */
public class View
{
    private final Okienko okienko;
    
    /**
     * tworzy nowe okienko i przekazuje eventQueue do niego
     * 
     * @param eventQueue
     *            - kolejka zdarzen
     */
    public View(final LinkedBlockingQueue<Zdarzenie> eventQueue)
    {
        okienko = new Okienko(eventQueue);
    }
    
    /**
     * zaktualizuj stan na kostce w widoku i wyslij polecenie Swingowi odrysowania okienka
     * 
     * @param tablicaNaklejek
     *            - stan kostki
     */
    public void aktualizujStan(final Naklejka[][][] tablicaNaklejek)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                okienko.aktualizujStan(tablicaNaklejek);
                okienko.repaint();
            }
        });
    }
}
