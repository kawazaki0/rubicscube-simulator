/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch tylna scianka
 *
 * @author Maciej Jagiello
 */
public class BackMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model referencja na model kostki
   */
  public BackMoveApplier(final Model model) {
    this.model = model;
  }

  /* (non-Javadoc)
   * @see pl.edu.pw.jagiello.rubikscube.model.moveapplier.MoveApplier#applyMove(int)
   */
  public void applyMove(final int rot) {
    model.rotateX();
    model.rotateX();
    model.rotateX();
    for (int i = 0; i < rot; i++) {
      model.moveU();
    }
    model.rotateX();
  }
}
