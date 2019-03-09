/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch prawa scianka
 *
 * @author Maciej Jagiello
 */
public class RightMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public RightMoveApplier(final Model model) {
    this.model = model;
  }

  public void applyMove(final int rotateCount) {
    model.rotateZ();
    model.rotateZ();
    model.rotateZ();
    for (int i = 0; i < rotateCount; i++) {
      model.moveU();
    }
    model.rotateZ();
  }
}
