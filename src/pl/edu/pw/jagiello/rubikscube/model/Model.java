package pl.edu.pw.jagiello.rubikscube.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisXRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisYRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.AxisZRotateApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.BackMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.DownMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.FrontMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.LeftMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.MoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.RightMoveApplier;
import pl.edu.pw.jagiello.rubikscube.model.moveapplier.UpMoveApplier;
import pl.edu.pw.jagiello.rubikscube.view.zdarzenia.MozliweZdarzenia;

/**
 * Klasa zawierajaca wszystko. 6 scian i metody operowania nimi
 * 
 * @author Maciej Jagiełło
 */
public class Model
{
    /** Referencja na przednia sciane */
    private Sciana sciana;
    
    /** Mapa <Naklejka, ruch do wykonania> */
    private final Map<MozliweZdarzenia, MoveApplier> moveApplier = new HashMap<MozliweZdarzenia, MoveApplier>();
    
    /** Mapa <Losowa liczba, Typ zdarzenia czyli ruch> */
    private final Map<Integer, MozliweZdarzenia> scrambler = new HashMap<Integer, MozliweZdarzenia>();
    
    /** Set <Naklejka, ruch do wykonania> */
    private final Set<MozliweZdarzenia> ruchy = new HashSet<MozliweZdarzenia>();
    
    /**
     * Konstruktor. W szczegolnosci ustaw wszystkie polaczenia miedzy soba
     */
    public Model()
    {
        // wypelnij applier ruchów do metod wykonujacych dany ruch 
        moveApplier.put(MozliweZdarzenia.RUCH_L, new LeftMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.RUCH_R, new RightMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.RUCH_U, new UpMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.RUCH_D, new DownMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.RUCH_F, new FrontMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.RUCH_B, new BackMoveApplier(this));
        moveApplier.put(MozliweZdarzenia.OBROT_X, new AxisXRotateApplier(this));
        moveApplier.put(MozliweZdarzenia.OBROT_Y, new AxisYRotateApplier(this));
        moveApplier.put(MozliweZdarzenia.OBROT_Z, new AxisZRotateApplier(this));
        
        // wypelnij mape wylosowanej liczby do ruchu 
        scrambler.put(0, MozliweZdarzenia.RUCH_L);
        scrambler.put(1, MozliweZdarzenia.RUCH_R);
        scrambler.put(2, MozliweZdarzenia.RUCH_U);
        scrambler.put(3, MozliweZdarzenia.RUCH_D);
        scrambler.put(4, MozliweZdarzenia.RUCH_F);
        scrambler.put(5, MozliweZdarzenia.RUCH_B);
        
        // wypelnij mape mozliwych ruchow podczas mieszania kostki 
        ruchy.add(MozliweZdarzenia.RUCH_L);
        ruchy.add(MozliweZdarzenia.RUCH_R);
        ruchy.add(MozliweZdarzenia.RUCH_U);
        ruchy.add(MozliweZdarzenia.RUCH_D);
        ruchy.add(MozliweZdarzenia.RUCH_F);
        ruchy.add(MozliweZdarzenia.RUCH_B);
        
        // stwórz nowe ściany i przyporządkuj im konkretne naklejki 
        final Sciana right = new Sciana(Naklejka.RIGHT);
        final Sciana left = new Sciana(Naklejka.LEFT);
        final Sciana up = new Sciana(Naklejka.UP);
        final Sciana down = new Sciana(Naklejka.DOWN);
        final Sciana front = new Sciana(Naklejka.FRONT);
        final Sciana back = new Sciana(Naklejka.BACK);
        
        // utwórz powiązania miedzy scianami 
        right.setAxisZ(up);
        right.getAxisZ().setAxisZ(left);
        right.getAxisZ().getAxisZ().setAxisZ(down);
        right.getAxisZ().getAxisZ().getAxisZ().setAxisZ(right);
        
        // ustaw przednia sciane jako prawa 
        front.setAxisY(right);
        
        // nowe polaczenia dla nowej sciany przedniej 
        front.getAxisY().setAxisY(back);
        front.getAxisY().getAxisY().setAxisY(left);
        front.getAxisY().getAxisY().getAxisY().setAxisY(front);
        
        // ustaw przednia sciane jako dolna 
        front.setAxisX(down);
        
        // nowe polaczenia dla sciany przedniej 
        front.getAxisX().setAxisX(back);
        front.getAxisX().getAxisX().setAxisX(up);
        front.getAxisX().getAxisX().getAxisX().setAxisX(front);
        
        sciana = front;
    }
    
    /**
     * Zwraca sciane, ktora jest aktualnie z przodu
     * 
     * @return sciana - sciana na kostce
     */
    Sciana getSciana()
    {
        return sciana;
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
    public Naklejka[][][] getStanKostkiView()
    {
        final Naklejka[][][] stan = new Naklejka[6][3][3];
        
        // stworz liste scian
        final ArrayList<Sciana> listaScian = new ArrayList<Sciana>();
        listaScian.add(sciana);
        listaScian.add(sciana.getAxisY());
        listaScian.add(sciana.getAxisY().getAxisY());
        listaScian.add(sciana.getAxisY().getAxisY().getAxisY());
        listaScian.add(sciana.getAxisX().getAxisX().getAxisX());
        listaScian.add(sciana.getAxisX());
        
        // iterator przechodzenia po scianach
        final Iterator<Sciana> iterSciana = listaScian.iterator();
        
        // przepisanie naklejek z listy scian do tablicy naklejek
        Sciana t = null;
        for (int k = 0; k < 6; k++)
        {
            // wybierz kolejna sciane
            if (iterSciana.hasNext())
                t = iterSciana.next();
            
            // przepisz naklejki ze sciany do tablicy naklejek stanu kostki
            for (int i = 0; i < t.getStanSciany().length; i++)
                System.arraycopy(t.getStanSciany()[i], 0, stan[k][i], 0, t.getStanSciany()[i].length);
        }
        return stan;
    }
    
    /**
     * Obroc kostke wzgledem wspolrzednej X
     */
    public void obrotX()
    {
        // obroc sciany
        sciana.getAxisY().obrot();
        sciana.getAxisY().getAxisY().getAxisY().obrot();
        sciana.getAxisY().getAxisY().getAxisY().obrot();
        sciana.getAxisY().getAxisY().getAxisY().obrot();
        
        // wprowadz nowe polaczenia miedzy scianami
        final Sciana right = sciana.getAxisY();
        final Sciana left = sciana.getAxisY().getAxisY().getAxisY();
        final Sciana up = sciana.getAxisX().getAxisX().getAxisX();
        final Sciana down = sciana.getAxisX();
        final Sciana front = sciana;
        final Sciana back = sciana.getAxisY().getAxisY();
        
        // zmien orientacje patrzenia na kostke ze sciany przedniej na prawa. nowa sciana
        // przednia jest poprzednia sciana prawa
        sciana = down;
        
        // nowe polaczenia dla sciany prawej
        right.setAxisZ(front);
        right.getAxisZ().setAxisZ(left);
        right.getAxisZ().getAxisZ().setAxisZ(back);
        right.getAxisZ().getAxisZ().getAxisZ().setAxisZ(right);
        
        // nowe polaczenia dla sciany przedniej
        sciana.setAxisY(right);
        sciana.getAxisY().setAxisY(up);
        sciana.getAxisY().getAxisY().setAxisY(left);
        sciana.getAxisY().getAxisY().getAxisY().setAxisY(sciana);
        
        // odwroc dolna i tylna sciane
        sciana.getAxisX().odwroc();
        sciana.getAxisX().getAxisX().odwroc();
        
    }
    
    /**
     * Obroc kostke wzgledem wspolrzednej Y
     */
    public void obrotY()
    {
        final Sciana right = sciana.getAxisY();
        final Sciana left = sciana.getAxisY().getAxisY().getAxisY();
        final Sciana up = sciana.getAxisX().getAxisX().getAxisX();
        final Sciana down = sciana.getAxisX();
        final Sciana front = sciana;
        
        // zmien orientacje patrzenia na kostke ze sciany przedniej na prawa. nowa sciana
        // przednia jest poprzednia sciana prawa
        sciana = right;
        
        // nowe polaczenia dla sciany prawej
        right.setAxisZ(up);
        right.getAxisZ().setAxisZ(front);
        right.getAxisZ().getAxisZ().setAxisZ(down);
        right.getAxisZ().getAxisZ().getAxisZ().setAxisZ(right);
        
        // nowe polaczenia dla sciany przedniej
        sciana.setAxisX(down);
        sciana.getAxisX().setAxisX(left);
        sciana.getAxisX().getAxisX().setAxisX(up);
        sciana.getAxisX().getAxisX().getAxisX().setAxisX(sciana);
        
        // obroc sciany
        sciana.getAxisX().obrot();
        sciana.getAxisX().obrot();
        sciana.getAxisX().obrot();
        sciana.getAxisX().getAxisX().getAxisX().obrot();
    }
    
    /**
     * Obroc kostke wzgledem wspolrzednej Z
     */
    public void obrotZ()
    {
        // wykonaj obroty wzgledem wspolrzednych Y i X aby uzyskac obrot wzgledem
        // wspolrzednej Z
        obrotY();
        obrotY();
        obrotY();
        obrotX();
        obrotY();
    }
    
    /**
     * Obroc gorna warstwe o 90 st. zgodnie z ruchem wskazówek zegara.
     */
    public void ruch()
    {
        // obroc gorne sciane
        sciana.getAxisX().getAxisX().getAxisX().obrot();
        final Wiersz tempRow = new Wiersz(sciana.getTopRow());
        
        // przepisz gorne wiersze scian wokol osi Y
        sciana.setTopRow(sciana.getAxisY().getTopRow());
        sciana.getAxisY().setTopRow(sciana.getAxisY().getAxisY().getTopRow());
        sciana.getAxisY().getAxisY().setTopRow(sciana.getAxisY().getAxisY().getAxisY().getTopRow());
        sciana.getAxisY().getAxisY().getAxisY().setTopRow(tempRow);
    }
    
    /**
     * Miesza kostkę 50-cioma losowymi ruchami. Gdzie każde dwa z kolei się nie powtarzają
     */
    public void scramble()
    {
        MozliweZdarzenia ruchNaKostce, ruchPoprzedni = null;
        final Random randInt = new Random();
        
        // kolekcja mozliwych ruchow do wykonania w danej iteracji z uwzglednieniem
        // niepowtarzania poprzedniego ruchu
        final Set<MozliweZdarzenia> mozliweRuchy = new HashSet<MozliweZdarzenia>();
        
        for (int i = 0; i < 50; i++)
        {
            // dodaj wszystkie mozliwe ruchy
            mozliweRuchy.addAll(ruchy);
            
            // usun poprzedni ruch
            mozliweRuchy.remove(ruchPoprzedni);
            
            // wygeneruj nowy ruch
            ruchNaKostce = scrambler.get(randInt.nextInt(6));
            
            // sprawdz czy jest w mozliwych ruchach po usunieci ostatniego
            if (mozliweRuchy.contains(ruchNaKostce))
                zrobRuch(ruchNaKostce, randInt.nextInt(3) + 1);
            mozliweRuchy.clear();
            ruchPoprzedni = ruchNaKostce;
        }
    }
    
    /**
     * Wykonaj ruch na kostce za pomoca MoveApplier'a
     * 
     * @param ruchDoPrzetworzenia
     *            Ruch, ktory nalezy wykonac
     * @param obrot
     *            krotnosc ruchu
     */
    public void zrobRuch(final MozliweZdarzenia ruchDoPrzetworzenia, final int obrot)
    {
        if (moveApplier.containsKey(ruchDoPrzetworzenia))
            moveApplier.get(ruchDoPrzetworzenia).applyMove(obrot);
    }
}