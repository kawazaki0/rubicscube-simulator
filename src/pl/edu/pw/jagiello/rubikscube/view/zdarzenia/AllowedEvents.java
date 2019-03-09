package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Element zdarzenia przekazanego od użytkownika (tutaj z klawiatury) określający co ma
 * zostać wykonane na kostce.
 *
 * @author Maciej Jagiełło
 */
public enum AllowedEvents {
  /**
   * Ruch kostki sciana gorna
   */
  MOVE_U,
  /**
   * Ruch kostki sciana dolna
   */
  MOVE_D,
  /**
   * Ruch kostki sciana przednia
   */
  MOVE_F,

  /**
   * Ruch kostki sciana tylna
   */
  MOVE_B,

  /**
   * Ruch kostki sciana prawa
   */
  MOVE_R,

  /**
   * Ruch kostki sciana lewa
   */
  MOVE_L,

  /**
   * Obrot wzgledem osi X
   */
  ROTATE_X,

  /**
   * Obrot wzgledem osi Y
   */
  ROTATE_Y,

  /**
   * Obrot wzgledem osi Z
   */
  ROTATE_Z,

  /**
   * Mieszanie kostki
   */
  SCRAMBLE,

  /**
   * Wyjscie z programu
   */
  EXIT
}
