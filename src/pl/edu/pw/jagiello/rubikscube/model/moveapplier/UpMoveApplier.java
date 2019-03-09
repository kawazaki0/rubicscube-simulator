/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch gorna scianka
 *
 * @author Maciej Jagiello
 */
public class UpMoveApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public UpMoveApplier(final Model model) {
    this.model = model;
  }

  public void applyMove(final int rotateCount) {
    for (int i = 0; i < rotateCount; i++) {
      model.moveU();
    }
  }
}
