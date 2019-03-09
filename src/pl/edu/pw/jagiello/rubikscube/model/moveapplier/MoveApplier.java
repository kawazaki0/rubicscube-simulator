package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

/**
 * Interfejs łączący wszystkie rozne ruchy do metody applyMove(int) rot = 1 -- obrot
 * zwykly (z ruchem wskazowek zegara), rot = 2 -- obrot podwojny (o 180 st.), rot = 3 --
 * obrot potrójny lub przeciwny do rot = 1. (o 270 st. lub -90 st.)
 */
public interface MoveApplier
{
    /**
     * Wykonaj ruch
     * 
     * @param obrot
     *            krotnosc obrotu wg wskazowek zegara
     */
    void applyMove(final int obrot);
}