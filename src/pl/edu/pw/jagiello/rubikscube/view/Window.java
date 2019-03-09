package pl.edu.pw.jagiello.rubikscube.view;

import pl.edu.pw.jagiello.rubikscube.model.Sticker;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;
import pl.edu.pw.jagiello.rubikscube.view.events.Event;
import pl.edu.pw.jagiello.rubikscube.view.events.CubeEvent;
import pl.edu.pw.jagiello.rubikscube.view.timer.TimerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Klasa z JFrame tworzaca okno i dodajaca panel
 *
 * @author Maciej Jagiello
 */
class Window extends JFrame implements KeyListener {
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;
  /**
   * panel - panel wyswietlajacy kostke rubika
   */
  private final Form panel;

  private final TimerPanel timer;
  /**
   * eventQueue - kolejka zdarzen miedzy kontrolerem a widokiem
   */
  private final BlockingQueue<Event> eventQueue;
  /**
   * keyToEventMap - mapa odwzorowywujaca znak na klawiaturze do events
   */
  private final Map<Integer, Event> keyToEventMap = new HashMap<Integer, Event>();

  /**
   * @param eventQueue - kolejka zdarzen
   */
  Window(final LinkedBlockingQueue<Event> eventQueue) {
    super("Rysowanie");
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    timer = new TimerPanel();
    add(timer);
    setBackground(Color.BLACK);

    // wypelnij mape znak na zdarzenie
    keyToEventMap.put(KeyEvent.VK_ESCAPE, new Event(AllowedEvents.EXIT));
    mapKey(KeyEvent.VK_J, AllowedEvents.MOVE_U, 1);
    mapKey(KeyEvent.VK_F, AllowedEvents.MOVE_U, 3);
    mapKey(KeyEvent.VK_I, AllowedEvents.MOVE_R, 1);
    mapKey(KeyEvent.VK_K, AllowedEvents.MOVE_R, 3);
    mapKey(KeyEvent.VK_D, AllowedEvents.MOVE_L, 1);
    mapKey(KeyEvent.VK_E, AllowedEvents.MOVE_L, 3);
    mapKey(KeyEvent.VK_G, AllowedEvents.MOVE_F, 3);
    mapKey(KeyEvent.VK_H, AllowedEvents.MOVE_F, 1);
    mapKey(KeyEvent.VK_A, AllowedEvents.ROTATE_Y, 3);
    mapKey(KeyEvent.VK_SEMICOLON, AllowedEvents.ROTATE_Y, 1);
    mapKey(KeyEvent.VK_L, AllowedEvents.MOVE_D, 3);
    mapKey(KeyEvent.VK_S, AllowedEvents.MOVE_D, 1);
    mapKey(KeyEvent.VK_O, AllowedEvents.MOVE_B, 3);
    mapKey(KeyEvent.VK_W, AllowedEvents.MOVE_B, 1);


    mapKey(KeyEvent.VK_U, AllowedEvents.MOVE_RW, 1);
    mapKey(KeyEvent.VK_M, AllowedEvents.MOVE_RW, 3);
    mapKey(KeyEvent.VK_R, AllowedEvents.MOVE_LW, 3);
    mapKey(KeyEvent.VK_V, AllowedEvents.MOVE_LW, 1);


    mapKey(KeyEvent.VK_P, AllowedEvents.ROTATE_Z, 1);
    mapKey(KeyEvent.VK_Q, AllowedEvents.ROTATE_Z, 3);
    mapKey(KeyEvent.VK_Y, AllowedEvents.ROTATE_X, 1);
    mapKey(KeyEvent.VK_T, AllowedEvents.ROTATE_X, 1);
    mapKey(KeyEvent.VK_N, AllowedEvents.ROTATE_X, 3);
    mapKey(KeyEvent.VK_B, AllowedEvents.ROTATE_X, 3);
    keyToEventMap.put(KeyEvent.VK_SPACE, new Event(AllowedEvents.SCRAMBLE));

    this.eventQueue = eventQueue;
    panel = new Form();

    add(panel);
    setTitle("Symulator kostki rubika");
    addKeyListener(this);
    pack();
    setMinimumSize(new Dimension(350, 350));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  private void mapKey(int vkJ, AllowedEvents moveU, int i) {
    keyToEventMap.put(vkJ, new CubeEvent(moveU, i));
  }

  /**
   * zaktualizuj stan na kostce na widoku
   *
   * @param stickersArray - stan kostki
   */
  void updateState(final Sticker[][][] stickersArray) {
    panel.updateState(stickersArray);
  }

  /**
   * reaguj na klawisz i wrzuc zdarzenie do blockingqueue (non-Javadoc)
   *
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  public void keyPressed(final KeyEvent arg) {
    // jesli klawisz jest w mapie mozliwych zdarzen to wrzuc zdarzenie do kolejki
    if (keyToEventMap.containsKey(arg.getKeyCode())) {
      try {
        eventQueue.put(keyToEventMap.get(arg.getKeyCode()));
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void keyReleased(final KeyEvent arg0) {

  }

  public void keyTyped(final KeyEvent arg0) {

  }

  public void countDownPreinspectionTimer(int seconds) {
    timer.countDown(seconds);
  }

  public void startTimer() {
    timer.start();
  }

  public long stopTimer() {
    return timer.stop();
  }
}
