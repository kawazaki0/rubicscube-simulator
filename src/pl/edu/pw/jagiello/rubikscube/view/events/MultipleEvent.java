package pl.edu.pw.jagiello.rubikscube.view.events;

/**
 * Klasa reprezentujaca zdarzenie na kostce wraz z jego krotnoscia.
 *
 * @author Maciej Jagiello
 */
public class MultipleEvent extends Event {
  /**
   * krotnosc ruchu
   */
  private final int count;

  /**
   * @param event event do wykonania
   * @param count krotnosc ruchu
   */
  public MultipleEvent(final AllowedEvents event, final int count) {
    super(event);
    this.count = count;
  }

  /**
   * Zwraca krotnosc ruchu
   *
   * @return Krotnosc ruchu
   */
  public int getCount() {
    return count;
  }

}
