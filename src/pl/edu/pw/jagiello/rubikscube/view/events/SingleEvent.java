/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.view.events;

/**
 * Klasa reprezentujaca zdarzenie jednokrotne typu MozliweZdarzenia
 *
 * @author Maciej Jagiello
 */
public class SingleEvent extends Event {

  /**
   * @param event
   *            - typ events
   */
  public SingleEvent(final AllowedEvents event) {
    super(event);
  }

}
