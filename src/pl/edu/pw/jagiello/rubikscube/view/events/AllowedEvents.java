package pl.edu.pw.jagiello.rubikscube.view.events;

/**
 * Element events przekazanego od użytkownika (tutaj z klawiatury) określający co ma
 * zostać wykonane na kostce.
 *
 * @author Maciej Jagiełło
 */
public enum AllowedEvents {
  /**
   * Ruch kostki sciana gorna
   */
  MOVE_U(Group.MOVE),
  /**
   * Ruch kostki sciana dolna
   */
  MOVE_D(Group.MOVE),
  /**
   * Ruch kostki sciana przednia
   */
  MOVE_F(Group.MOVE),

  /**
   * Ruch kostki sciana tylna
   */
  MOVE_B(Group.MOVE),

  /**
   * Ruch kostki sciana prawa
   */
  MOVE_R(Group.MOVE),

  /**
   * Ruch kostki sciana lewa
   */
  MOVE_L(Group.MOVE),

  /**
   * Obrot wzgledem osi X
   */
  ROTATE_X(Group.ROTATE),

  /**
   * Obrot wzgledem osi Y
   */
  ROTATE_Y(Group.ROTATE),

  /**
   * Obrot wzgledem osi Z
   */
  ROTATE_Z(Group.ROTATE),

  /**
   * Mieszanie kostki
   */
  SCRAMBLE(Group.OTHER),

  /**
   * Wyjscie z programu
   */
  EXIT(Group.OTHER);

  private Group group;

  AllowedEvents(Group group) {
    this.group = group;
  }

  public boolean isInGroup(Group group) {
    return this.group == group;
  }

  public enum Group {
    MOVE,
    ROTATE,
    OTHER
  }
}
