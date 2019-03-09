/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch lewa scianka
 *
 * @author Maciej Jagiello
 */
public class LeftMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public LeftMoveApplier(final Model model) {
    this.model = model;
  }

  public void applyMove(final int rotateCount) {
    model.rotateZ();
    for (int i = 0; i < rotateCount; i++) {
      model.moveU();
    }
    model.rotateZ();
    model.rotateZ();
    model.rotateZ();

  }
}
