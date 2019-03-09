package pl.edu.pw.jagiello.rubikscube.view;

import pl.edu.pw.jagiello.rubikscube.model.Sticker;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;

import javax.swing.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Widok kostki rubika.
 *
 * @author Maciej Jagiełło
 */
public class View {
  private final Window window;

  /**
   * tworzy nowe okienko i przekazuje eventQueue do niego
   *
   * @param eventQueue - kolejka zdarzen
   */
  public View(final LinkedBlockingQueue<Event> eventQueue) {
    window = new Window(eventQueue);
  }

  /**
   * zaktualizuj stan na kostce w widoku i wyslij polecenie Swingowi odrysowania okienka
   *
   * @param stickerArray - stan kostki
   */
  public void updateState(final Sticker[][][] stickerArray) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        window.updateState(stickerArray);
        window.repaint();
      }
    });
  }
}
