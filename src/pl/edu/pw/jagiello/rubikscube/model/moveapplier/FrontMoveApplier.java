/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch przednia scianka
 *
 * @author Maciej Jagiello
 */
public class FrontMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public FrontMoveApplier(final Model model) {
    this.model = model;
  }

  public void applyMove(final int rotateCount) {
    model.rotateX();
    for (int i = 0; i < rotateCount; i++) {
      model.moveU();
    }
    model.rotateX();
    model.rotateX();
    model.rotateX();
  }
}
