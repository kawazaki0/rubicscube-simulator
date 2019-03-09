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
public class AxisZRotateApplier implements MoveApplier
{
    /** model kostki rubika */
    private final Model model;
    
    /**
     * Konstruktor przyjmujacy w argumencie model
     * 
     * @param model
     *            referencja na model kostki
     */
    public AxisZRotateApplier(final Model model)
    {
        this.model = model;
    }
    
    public void applyMove(final int rot)
    {
        for (int i = 0; i < rot; i++) {
            model.rotateZ();
        }
    }
}
