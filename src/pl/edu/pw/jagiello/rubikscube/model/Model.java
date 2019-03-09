package pl.edu.pw.jagiello.rubikscube.model;

import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisXRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisYRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisZRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.BackMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.DownMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.FrontMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.LeftMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.LeftSliceMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.MoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.RightMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.RightSliceMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.UpMoveApplier;
import pl.edu.pw.jagiello.rubikscube.view.events.AllowedEvents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Klasa zawierajaca wszystko. 6 scian i metody operowania nimi
 *
 * @author Maciej Jagiełło
 */
public class Model {
  /**
   * Mapa <Naklejka, ruch do wykonania>
   */
  private final Map<AllowedEvents, MoveApplier> moveApplier =
      new HashMap<AllowedEvents, MoveApplier>();
  /**
   * Set <Naklejka, ruch do wykonania>
   */
  private final Set<AllowedEvents> moves = new HashSet<AllowedEvents>();
  /**
   * Referencja na przednia sciane
   */
  private Face face;

  /**
   * Konstruktor. W szczegolnosci ustaw wszystkie polaczenia miedzy soba
   */
  public Model() {
    // wypelnij applier ruchów do metod wykonujacych dany ruch
    moveApplier.put(AllowedEvents.MOVE_L, new LeftMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_LW, new LeftSliceMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_R, new RightMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_RW, new RightSliceMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_U, new UpMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_D, new DownMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_F, new FrontMoveApplier(this));
    moveApplier.put(AllowedEvents.MOVE_B, new BackMoveApplier(this));
    moveApplier.put(AllowedEvents.ROTATE_X, new AxisXRotateApplier(this));
    moveApplier.put(AllowedEvents.ROTATE_Y, new AxisYRotateApplier(this));
    moveApplier.put(AllowedEvents.ROTATE_Z, new AxisZRotateApplier(this));

    // wypelnij mape mozliwych ruchow podczas mieszania kostki
    moves.add(AllowedEvents.MOVE_L);
    moves.add(AllowedEvents.MOVE_R);
    moves.add(AllowedEvents.MOVE_U);
    moves.add(AllowedEvents.MOVE_D);
    moves.add(AllowedEvents.MOVE_F);
    moves.add(AllowedEvents.MOVE_B);

    // stwórz nowe ściany i przyporządkuj im konkretne naklejki
    final Face rightFace = new Face(Sticker.RIGHT);
    final Face leftFace = new Face(Sticker.LEFT);
    final Face upFace = new Face(Sticker.UP);
    final Face downFace = new Face(Sticker.DOWN);
    final Face frontFace = new Face(Sticker.FRONT);
    final Face backFace = new Face(Sticker.BACK);

    // utwórz powiązania miedzy scianami
    rightFace.setAxisZ(upFace);
    rightFace.getAxisZ().setAxisZ(leftFace);
    rightFace.getAxisZ().getAxisZ().setAxisZ(downFace);
    rightFace.getAxisZ().getAxisZ().getAxisZ().setAxisZ(rightFace);

    // ustaw przednia sciane jako prawa
    frontFace.setAxisY(rightFace);

    // nowe polaczenia dla nowej sciany przedniej
    frontFace.getAxisY().setAxisY(backFace);
    frontFace.getAxisY().getAxisY().setAxisY(leftFace);
    frontFace.getAxisY().getAxisY().getAxisY().setAxisY(frontFace);

    // ustaw przednia sciane jako dolna
    frontFace.setAxisX(downFace);

    // nowe polaczenia dla sciany przedniej
    frontFace.getAxisX().setAxisX(backFace);
    frontFace.getAxisX().getAxisX().setAxisX(upFace);
    frontFace.getAxisX().getAxisX().getAxisX().setAxisX(frontFace);

    face = frontFace;
  }

  /**
   * Zwraca sciane, ktora jest aktualnie z przodu
   *
   * @return sciana - sciana na kostce
   */
  Face getFace() {
    return face;
  }

  /**
   * Zwroc stan kostki wpisany w tablice o indeksach [SCIANA][WSP_X][WSP_Y] zakodowana
   * za pomocą cyfr:
   * <ul>
   * <li>0 FRONT</li>
   * <li>1 RIGHT</li>
   * <li>2 BACK</li>
   * <li>3 LEFT</li>
   * <li>4 UP</li>
   * <li>5 DOWN</li>
   * </ul>
   *
   * @return Naklejka[SCIANA][WSP_X][WSP_Y] - tablice naklejek
   */
  public Sticker[][][] getCubeStateView() {
    final Sticker[][][] state = new Sticker[6][3][3];

    // stworz liste scian
    final ArrayList<Face> faces = new ArrayList<Face>();
    faces.add(face);
    faces.add(face.getAxisY());
    faces.add(face.getAxisY().getAxisY());
    faces.add(face.getAxisY().getAxisY().getAxisY());
    faces.add(face.getAxisX().getAxisX().getAxisX());
    faces.add(face.getAxisX());

    // iterator przechodzenia po scianach
    final Iterator<Face> faceIter = faces.iterator();

    // przepisanie naklejek z listy scian do tablicy naklejek
    Face f = null;
    for (int k = 0; k < 6; k++) {
      // wybierz kolejna sciane
      if (faceIter.hasNext())
        f = faceIter.next();

      // przepisz naklejki ze sciany do tablicy naklejek stanu kostki
      for (int i = 0; i < f.getFaceState().length; i++)
        System.arraycopy(f.getFaceState()[i], 0, state[k][i], 0, f.getFaceState()[i].length);
    }
    return state;
  }

  /**
   * Obroc kostke wzgledem wspolrzednej X
   */
  public void rotateX() {
    // obroc sciany
    face.getAxisY().rotate();
    face.getAxisY().getAxisY().getAxisY().rotate();
    face.getAxisY().getAxisY().getAxisY().rotate();
    face.getAxisY().getAxisY().getAxisY().rotate();

    // wprowadz nowe polaczenia miedzy scianami
    final Face right = face.getAxisY();
    final Face left = face.getAxisY().getAxisY().getAxisY();
    final Face up = face.getAxisX().getAxisX().getAxisX();
    final Face down = face.getAxisX();
    final Face front = face;
    final Face back = face.getAxisY().getAxisY();

    // zmien orientacje patrzenia na kostke ze sciany przedniej na prawa. nowa sciana
    // przednia jest poprzednia sciana prawa
    face = down;

    // nowe polaczenia dla sciany prawej
    right.setAxisZ(front);
    right.getAxisZ().setAxisZ(left);
    right.getAxisZ().getAxisZ().setAxisZ(back);
    right.getAxisZ().getAxisZ().getAxisZ().setAxisZ(right);

    // nowe polaczenia dla sciany przedniej
    face.setAxisY(right);
    face.getAxisY().setAxisY(up);
    face.getAxisY().getAxisY().setAxisY(left);
    face.getAxisY().getAxisY().getAxisY().setAxisY(face);

    // odwroc dolna i tylna sciane
    face.getAxisX().reverse();
    face.getAxisX().getAxisX().reverse();

  }

  /**
   * Obroc kostke wzgledem wspolrzednej Y
   */
  public void rotateY() {
    final Face right = face.getAxisY();
    final Face left = face.getAxisY().getAxisY().getAxisY();
    final Face up = face.getAxisX().getAxisX().getAxisX();
    final Face down = face.getAxisX();
    final Face front = face;

    // zmien orientacje patrzenia na kostke ze sciany przedniej na prawa. nowa sciana
    // przednia jest poprzednia sciana prawa
    face = right;

    // nowe polaczenia dla sciany prawej
    right.setAxisZ(up);
    right.getAxisZ().setAxisZ(front);
    right.getAxisZ().getAxisZ().setAxisZ(down);
    right.getAxisZ().getAxisZ().getAxisZ().setAxisZ(right);

    // nowe polaczenia dla sciany przedniej
    face.setAxisX(down);
    face.getAxisX().setAxisX(left);
    face.getAxisX().getAxisX().setAxisX(up);
    face.getAxisX().getAxisX().getAxisX().setAxisX(face);

    // obroc sciany
    face.getAxisX().rotate();
    face.getAxisX().rotate();
    face.getAxisX().rotate();
    face.getAxisX().getAxisX().getAxisX().rotate();
  }

  /**
   * Obroc kostke wzgledem wspolrzednej Z
   */
  public void rotateZ() {
    // wykonaj obroty wzgledem wspolrzednych Y i X aby uzyskac obrot wzgledem
    // wspolrzednej Z
    rotateY();
    rotateY();
    rotateY();
    rotateX();
    rotateY();
  }

  /**
   * Obroc gorna warstwe o 90 st. zgodnie z ruchem wskazówek zegara.
   */
  public void moveU() {
    // obroc gorne sciane
    face.getAxisX().getAxisX().getAxisX().rotate();
    final Row tempRow = new Row(face.getTopRow());

    // przepisz gorne wiersze scian wokol osi Y
    face.setTopRow(face.getAxisY().getTopRow());
    face.getAxisY().setTopRow(face.getAxisY().getAxisY().getTopRow());
    face.getAxisY().getAxisY().setTopRow(face.getAxisY().getAxisY().getAxisY().getTopRow());
    face.getAxisY().getAxisY().getAxisY().setTopRow(tempRow);
  }

  /**
   * Miesza kostkę 50-cioma losowymi ruchami. Gdzie każde dwa z kolei się nie powtarzają
   */
  public void scramble() {
    AllowedEvents moveOnCube, previousMove = null;
    final Random randInt = new Random();

    // kolekcja mozliwych ruchow do wykonania w danej iteracji z uwzglednieniem
    // niepowtarzania poprzedniego ruchu
    final Set<AllowedEvents> possibleMoves = new HashSet<AllowedEvents>(moves);

    for (int i = 0; i < 100; i++) {
      // usun poprzedni ruch
      possibleMoves.remove(previousMove);

      // wygeneruj nowy ruch
      moveOnCube = (AllowedEvents) possibleMoves.toArray()[randInt.nextInt(possibleMoves.size())];

      makeMove(moveOnCube, randInt.nextInt(3) + 1);

      // dodaj usuniety ruch
      possibleMoves.add(previousMove);

      previousMove = moveOnCube;
    }
  }

  /**
   * Wykonaj ruch na kostce za pomoca MoveApplier'a
   *
   * @param moveToProcess Ruch, ktory nalezy wykonac
   * @param rotateCount        krotnosc ruchu
   */
  public void makeMove(final AllowedEvents moveToProcess, final int rotateCount) {
    if (moveApplier.containsKey(moveToProcess)) {
      moveApplier.get(moveToProcess).applyMove(rotateCount);
    }
  }

  public boolean isSolved() {
    final Face front = face;
    final Face right = face.getAxisY();
    final Face back = face.getAxisY().getAxisY();
    final Face left = face.getAxisY().getAxisY().getAxisY();
    final Face up = face.getAxisX().getAxisX().getAxisX();
    final Face down = face.getAxisX();
    List<Face> faces = Arrays.asList(front, right, back, left, up, down);
    for (Face f : faces) {
      Sticker faceColor = f.getFaceState()[1][1];
      for (Sticker[] row : f.getFaceState()) {
        for (Sticker sticker : row) {
          if (sticker != faceColor) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
