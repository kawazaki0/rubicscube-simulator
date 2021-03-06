package pl.edu.pw.jagiello.rubikscube.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klasa zawierająca naklejki i połączenia do ścianek z boku
 *
 * @author Maciej Jagiełło
 */
class Face {
  /**
   * Dwuwymiarowa tablica Naklejek reprezentujaca na ktorej scianie powinna znalezc sie
   * naklejka
   */
  private final Sticker[][] stickers;
  /**
   * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi X (osi
   * przechodzacej przez LR)
   *
   * <pre>
   *   _____
   *  /___ /|
   * |    |-----
   * |____|/
   * </pre>
   */
  private Face axisX;
  /**
   * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi Y (osi
   * przechodzacej przez UD)
   *
   * <pre>
   *     |
   *   __|__
   *  /___ /|
   * |    | |
   * |____|/
   * </pre>
   */
  private Face axisY;
  /**
   * sciana, ktora pojawi sie na miejsce poprzedniej po obroceniu wokol osi Z (osi
   * przechodzacej przez FB)
   *
   * <pre>
   *   _____
   *  /___ /|
   * |    | |
   * |_/__|/
   *  /
   * </pre>
   */
  private Face axisZ;

  /**
   * wypełnij dwuwymiarowa tablice Naklejek naklejkami z argumentu
   *
   * @param sticker naklejka do wypelniania
   */
  Face(final Sticker sticker) {
    stickers = new Sticker[3][3];
    for (final Sticker[] element : stickers) {
      Arrays.fill(element, sticker);
    }
  }

  /**
   * Zwraca sciane polozona, wzgledem this, ktora pojawi sie na miejsce poprzedniej po
   * obroceniu wokol osi X (osi przechodzacej przez LR)
   *
   * @return Sciana
   */
  Face getAxisX() {
    return axisX;
  }

  /**
   * Setter axisX. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
   * poprzedniej po obroceniu wokol osi X (osi przechodzacej przez RL)
   *
   * @param axisX nowa sciana
   */
  void setAxisX(final Face axisX) {
    this.axisX = axisX;
  }

  /**
   * Zwraca sciane polozona wzgledem this, ktora pojawi sie na miejsce poprzedniej po
   * obroceniu wokol osi Y (osi przechodzacej przez UD)
   *
   * @return Sciana
   */
  Face getAxisY() {
    return axisY;
  }

  /**
   * Setter axisY. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
   * poprzedniej po obroceniu wokol osi Y (osi przechodzacej przez UD)
   *
   * @param axisY nowa sciana
   */
  void setAxisY(final Face axisY) {
    this.axisY = axisY;
  }

  /**
   * Zwraca sciane polozona wzgledem this, ktora pojawi sie na miejsce poprzedniej po
   * obroceniu wokol osi Z (osi przechodzacej przez FB)
   *
   * @return Sciana
   */
  Face getAxisZ() {
    return axisZ;
  }

  /**
   * Setter axisZ. Ustawia sciane polozona wzgledem this, ktora pojawi sie na miejsce
   * poprzedniej po obroceniu wokol osi Z (osi przechodzacej przez FB)
   *
   * @param axisZ nowa sciana
   */
  void setAxisZ(final Face axisZ) {
    this.axisZ = axisZ;
  }

  /**
   * zwraca stan sciany, czyli stickers typu enum
   *
   * @return stickers dwuwymiarowa tablica Naklejek
   */
  Sticker[][] getFaceState() {
    return stickers;
  }

  /**
   * zwraca gorny wiersz scianki
   *
   * @return Row Wiersz zbudowany z tablicy trzech Naklejek
   */
  Row getTopRow() {
    return (new Row(stickers[0][0], stickers[0][1], stickers[0][2]));
  }

  /**
   * Ustawia nowy wiersz w scianie
   *
   * @param newRow
   */
  void setTopRow(final Row newRow) {
    System.arraycopy(newRow.getRow(), 0, stickers[0], 0, newRow.getRow().length);
  }

  /**
   * obraca sciane ClockWise czyli wg wskazowek zegara (8-miu Naklejek)
   */
  void rotate() {
    final Sticker temp1 = stickers[0][0];
    final Sticker temp2 = stickers[0][1];

    // rogi
    stickers[0][0] = stickers[2][0];
    stickers[2][0] = stickers[2][2];
    stickers[2][2] = stickers[0][2];
    stickers[0][2] = temp1;

    // krawedzie
    stickers[0][1] = stickers[1][0];
    stickers[1][0] = stickers[2][1];
    stickers[2][1] = stickers[1][2];
    stickers[1][2] = temp2;
  }

  /**
   * zmienia orientacje scianki z góry na dół (nowy punkt początkowy w [2][2] (stary w
   * [0][0]))
   */
  void reverse() {
    final List<Sticker[]> rows = Arrays.asList(stickers);
    Collections.reverse(rows);

    for (int i = 0; i < rows.size(); i++) {
      final List<Sticker> row = Arrays.asList(rows.get(i));
      Collections.reverse(row);
      stickers[i] = (Sticker[]) row.toArray();
    }
  }
}
