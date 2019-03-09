/**
 * 
 */
package pl.edu.pw.jagiello.rubikscube.model.moveapplier;

import pl.edu.pw.jagiello.rubikscube.model.Model;

/**
 * Klasa wykonujaca ruch dolna scianka
 * 
 * @author Maciej Jagiello
 */
public class DownMoveApplier implements MoveApplier
{
    /** model kostki rubika */
    private final Model model;
    
    /**
     * Konstruktor przyjmujacy w argumencie model
     * 
     * @param model
     *            referencja na model kostki
     */
    public DownMoveApplier(final Model model)
    {
        this.model = model;
    }
    
    public void applyMove(final int rot)
    {
        model.obrotX();
        model.obrotX();
        for (int i = 0; i < rot; i++)
        {
            model.ruch();
        }
        model.obrotX();
        model.obrotX();
    }
}