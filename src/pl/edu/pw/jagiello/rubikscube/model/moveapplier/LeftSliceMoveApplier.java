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
public class LeftSliceMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public LeftSliceMoveApplier(final Model model) {
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
    for (int i = 0; i < 4 - rotateCount; i++) {
      model.rotateX();
    }
  }
}
