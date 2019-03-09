package pl.edu.pw.jagiello.rubikscube.view.events;

/**
 * Klasa reprezentujaca zdarzenie na kostce
 *
 * @author Maciej Jagiello
 */
public class Event {
  /**
   * akcja - zdarzenie na kostce
   */
  protected AllowedEvents event;

  /**
   * @param event - typ events
   */
  public Event(final AllowedEvents event) {
    this.event = event;
  }

  /**
   * Zwraca rodzaj akcji danego events
   *
   * @return akcja - typ events
   */
  public AllowedEvents getEvent() {
    return event;
  }

}
