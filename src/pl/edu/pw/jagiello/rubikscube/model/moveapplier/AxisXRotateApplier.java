/**
 *
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch calej kostki wzdluz osi Z
 *
 * @author Maciej Jagiello
 */
public class AxisXRotateApplier implements MoveApplier {
  /** model kostki rubika */
  private final Model model;

  /**
   * Konstruktor przyjmujacy w argumencie model
   *
   * @param model
   *            referencja na model kostki
   */
  public AxisXRotateApplier(final Model model) {
    this.model = model;
  }

  public void applyMove(final int rotateCount) {
    for (int i = 0; i < rotateCount; i++) {
      model.rotateX();
    }
  }
}
