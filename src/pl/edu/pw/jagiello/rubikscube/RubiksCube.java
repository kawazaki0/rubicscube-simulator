package pl.edu.pw.jagiello.rubikscube;

import pl.edu.pw.jagiello.rubikscube.controller.Controller;

/**
 * Symulator kostki rubika.
 * 
 * @author Maciej Jagiełło
 */
public class RubiksCube
{
    /**
     * Główna funkcja
     * 
     * @param args
     *            Lista argumentow w trakcie uruchamiania programu
     */
    public static void main(final String[] args)
    {
        final Controller controller = new Controller();
        controller.doSomething();
    }
}
