package pl.edu.pw.jagiello.rubikscube.view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import pl.edu.pw.jagiello.rubikscube.model.Naklejka;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.MozliweZdarzenia;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.Zdarzenie;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.ZdarzenieJednokrotne;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.ZdarzenieWielokrotne;

/**
 * Klasa z JFrame tworzaca okno i dodajaca panel
 * 
 * @author Maciej Jagiello
 */
class Okienko extends JFrame implements KeyListener
{
    /** panel - panel wyswietlajacy kostke rubika */
    private final Formatka panel;
    /** eventQueue - kolejka zdarzen miedzy kontrolerem a widokiem */
    private final BlockingQueue<Zdarzenie> eventQueue;
    /** charZdarzenie - mapa odwzorowywujaca znak na klawiaturze do zdarzenia */
    private final Map<Integer, Zdarzenie> charZdarzenie = new HashMap<Integer, Zdarzenie>();
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**
     * @param eventQueue
     *            - kolejka zdarzen
     */
    Okienko(final LinkedBlockingQueue<Zdarzenie> eventQueue)
    {
        super("Rysowanie");
        
        // wypelnij mape znak na zdarzenie
        charZdarzenie.put(KeyEvent.VK_ESCAPE, new ZdarzenieJednokrotne(MozliweZdarzenia.EXIT));
        charZdarzenie.put(KeyEvent.VK_J, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_U, 1));
        charZdarzenie.put(KeyEvent.VK_F, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_U, 3));
        charZdarzenie.put(KeyEvent.VK_I, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_R, 1));
        charZdarzenie.put(KeyEvent.VK_K, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_R, 3));
        charZdarzenie.put(KeyEvent.VK_D, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_L, 1));
        charZdarzenie.put(KeyEvent.VK_E, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_L, 3));
        charZdarzenie.put(KeyEvent.VK_G, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_F, 3));
        charZdarzenie.put(KeyEvent.VK_H, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_F, 1));
        charZdarzenie.put(KeyEvent.VK_A, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_Y, 3));
        charZdarzenie.put(KeyEvent.VK_SEMICOLON, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_Y, 1));
        charZdarzenie.put(KeyEvent.VK_L, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_D, 3));
        charZdarzenie.put(KeyEvent.VK_S, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_D, 1));
        charZdarzenie.put(KeyEvent.VK_O, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_B, 3));
        charZdarzenie.put(KeyEvent.VK_W, new ZdarzenieWielokrotne(MozliweZdarzenia.RUCH_B, 1));
        charZdarzenie.put(KeyEvent.VK_P, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_Z, 1));
        charZdarzenie.put(KeyEvent.VK_Q, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_Z, 3));
        charZdarzenie.put(KeyEvent.VK_Y, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_X, 1));
        charZdarzenie.put(KeyEvent.VK_T, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_X, 1));
        charZdarzenie.put(KeyEvent.VK_N, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_X, 3));
        charZdarzenie.put(KeyEvent.VK_B, new ZdarzenieWielokrotne(MozliweZdarzenia.OBROT_X, 3));
        charZdarzenie.put(KeyEvent.VK_SPACE, new ZdarzenieJednokrotne(MozliweZdarzenia.SCRAMBLE));
        
        this.eventQueue = eventQueue;
        panel = new Formatka();
        
        add(panel);
        setTitle("Symulator kostki rubika");
        addKeyListener(this);
        pack();
        setMinimumSize(new Dimension(300, 300));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * zaktualizuj stan na kostce na widoku
     * 
     * @param tablicaNaklejek
     *            - stan kostki
     */
    void aktualizujStan(final Naklejka[][][] tablicaNaklejek)
    {
        panel.aktualizujStan(tablicaNaklejek);
    }
    
    /**
     * reaguj na klawisz i wrzuc zdarzenie do blockingqueue (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(final KeyEvent arg)
    {
        // jesli klawisz jest w mapie mozliwych zdarzen to wrzuc zdarzenie do kolejki 
        if (charZdarzenie.containsKey(arg.getKeyCode()))
        {
            try
            {
                eventQueue.put(charZdarzenie.get(arg.getKeyCode()));
            } 
            catch (final InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void keyReleased(final KeyEvent arg0)
    {
        
    }
    
    public void keyTyped(final KeyEvent arg0)
    {
        
    }
}