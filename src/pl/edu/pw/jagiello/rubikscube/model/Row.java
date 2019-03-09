package pl.edu.pw.jagiello.rubikscube.model;

/**
 * @author Maciej Jagiełło 3 kolejne naklejki zgrupowane w wiersz
 */
class Row {
  /**
   * tablica jednowymiarowa naklejek
   */
  private final Sticker[] stickerRow;

  /**
   * Konstruktor kopiujacy wiersz z naklejek
   *
   * @param sticker1 Naklejka pierwsza
   * @param sticker2 Naklejka druga
   * @param sticker3 Naklejka trzecia
   */
  Row(final Sticker sticker1, final Sticker sticker2, final Sticker sticker3) {
    stickerRow = new Sticker[3];

    stickerRow[0] = sticker1;
    stickerRow[1] = sticker2;
    stickerRow[2] = sticker3;
  }

  /**
   * konstruktor kopiujący
   *
   * @param newRow wiersz do skopiowania
   */
  Row(final Row newRow) {
    stickerRow = new Sticker[3];
    System.arraycopy(newRow.getRow(), 0, stickerRow, 0, newRow.getRow().length);
  }

  /**
   * Zwraca jeden gorny wiersz sciany
   *
   * @return zwroc 3-elementowa tablice Naklejek
   */
  Sticker[] getRow() {
    return stickerRow;
  }
}
