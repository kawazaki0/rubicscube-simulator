package pl.edu.pw.jagiello.rubikscube.controller;

import pl.edu.pw.jagiello.rubikscube.model.Model;
import pl.edu.pw.jagiello.rubikscube.model.state.FreeState;
import pl.edu.pw.jagiello.rubikscube.model.state.State;
import pl.edu.pw.jagiello.rubikscube.model.state.StateContext;
import pl.edu.pw.jagiello.rubikscube.view.View;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.MultipleEvent;

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
   * 
   */
  final private StateContext state;
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
    model = new Model();
    state = new StateContext(model);
    view = new View(eventQueue);
    view.updateState(model.getCubeStateView());
  }

  /**
   * Funkcja z pętlą nieskończoną czekającą na zdarzenie z widoku.
   */
  public void runLoop() {
    while (true) {
      Event viewEvent;
      try {
        viewEvent = eventQueue.take();
        state.handle(viewEvent);
      } catch (final InterruptedException e1) {
        e1.printStackTrace();
      }

      view.updateState(model.getCubeStateView());
    }
  }
}
