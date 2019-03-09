package pl.edu.pw.jagiello.rubikscube.controller;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.Event;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.MultipleEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Kontroler kostki rubika.
 *
 * @author Maciej Jagiełło
 */
public class Controller {
  /**
   * Model kostki rubika
   */
  final private Model model;
  /**
   * Widok. Okno do wyswietlania symulacji
   */
  final private View view;
  /**
   * Kolekcja dozwolonych ruchów na kostce
   */
  final private Set<AllowedEvents> allowedMoves = new HashSet<AllowedEvents>();
  /**
   * kolejka zdarzeń wysyłanych z widoku do kontrolera
   */
  final private LinkedBlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();

  /**
   * Konstruktor wypełnia Set dozwolonych ruchów. Tworzy obiekt modelu i widoku (z
   * blockingqueue). Inicjuje stan kostki na widoku.
   */
  public Controller() {
    allowedMoves.add(AllowedEvents.ROTATE_X);
    allowedMoves.add(AllowedEvents.ROTATE_Y);
    allowedMoves.add(AllowedEvents.ROTATE_Z);
    allowedMoves.add(AllowedEvents.MOVE_B);
    allowedMoves.add(AllowedEvents.MOVE_F);
    allowedMoves.add(AllowedEvents.MOVE_L);
    allowedMoves.add(AllowedEvents.MOVE_R);
    allowedMoves.add(AllowedEvents.MOVE_D);
    allowedMoves.add(AllowedEvents.MOVE_U);

    model = new Model();
    view = new View(eventQueue);
    view.updateState(model.getCubeStateView());
  }

  /**
   * Funkcja z pętlą nieskończoną czekającą na zdarzenie z widoku.
   */
  public void doSomething() {
    while (true) {
      Event viewEvent;
      try {
        viewEvent = eventQueue.take();

        // obsluz akcje zamkniecia
        if (viewEvent.getEvent() == AllowedEvents.EXIT) {
          System.exit(0);
        }
        // obsluz akcje mieszania kostki
        else if (viewEvent.getEvent() == AllowedEvents.SCRAMBLE) {
          model.scramble();
        }
        // obsluz zdarzenie, ktore jest zdarzeniem ruchu na kostce
        else if (viewEvent instanceof MultipleEvent) {
          model.makeMove(viewEvent.getEvent(), ((MultipleEvent) viewEvent).getCount());
        }
      } catch (final InterruptedException e1) {
        e1.printStackTrace();
      }

      view.updateState(model.getCubeStateView());
    }
  }
}
