package pl.edu.pw.jagiello.rubikscube.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.MozliweZdarzenia;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.Zdarzenie;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.ZdarzenieWielokrotne;

/**
 * Kontroler kostki rubika.
 * 
 * @author Maciej Jagiełło
 */
public class Controller
{
    /** Model kostki rubika */
    final private Model model;
     /** Widok. Okno do wyswietlania symulacji */
    final private View view;
    /** Kolekcja dozwolonych ruchów na kostce */
    final private Set<MozliweZdarzenia> dozwoloneRuchy = new HashSet<MozliweZdarzenia>();
    /** kolejka zdarzeń wysyłanych z widoku do kontrolera */
    final private LinkedBlockingQueue<Zdarzenie> eventQueue = new LinkedBlockingQueue<Zdarzenie>();
    
    /**
     * Konstruktor wypełnia Set dozwolonych ruchów. Tworzy obiekt modelu i widoku (z
     * blockingqueue). Inicjuje stan kostki na widoku.
     */
    public Controller()
    {
        dozwoloneRuchy.add(MozliweZdarzenia.OBROT_X);
        dozwoloneRuchy.add(MozliweZdarzenia.OBROT_Y);
        dozwoloneRuchy.add(MozliweZdarzenia.OBROT_Z);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_B);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_F);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_L);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_R);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_D);
        dozwoloneRuchy.add(MozliweZdarzenia.RUCH_U);
        
        model = new Model();
        view = new View(eventQueue);
        view.aktualizujStan(model.getStanKostkiView());
    }
    
    /**
     * Funkcja z pętlą nieskończoną czekającą na zdarzenie z widoku.
     */
    public void robCos()
    {
        while (true)
        {
            Zdarzenie zdarzenieZWidoku;
            try
            {
                zdarzenieZWidoku = eventQueue.take();
                
                // obsluz akcje zamkniecia
                if (zdarzenieZWidoku.getAkcja() == MozliweZdarzenia.EXIT)
                {
                    System.exit(0);
                }
                // obsluz akcje mieszania kostki
                else if (zdarzenieZWidoku.getAkcja() == MozliweZdarzenia.SCRAMBLE)
                {
                    model.scramble();
                }
                // obsluz zdarzenie, ktore jest zdarzeniem ruchu na kostce
                else if (zdarzenieZWidoku instanceof ZdarzenieWielokrotne)
                {
                    model.zrobRuch(zdarzenieZWidoku.getAkcja(), ((ZdarzenieWielokrotne) zdarzenieZWidoku).getKrotnosc());
                }
            } 
            catch (final InterruptedException e1)
            {
                e1.printStackTrace();
            }
            
            view.aktualizujStan(model.getStanKostkiView());
        }
    }
}
