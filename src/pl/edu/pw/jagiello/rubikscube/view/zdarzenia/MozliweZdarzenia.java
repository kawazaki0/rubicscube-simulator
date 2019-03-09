package pl.edu.pw.jagiello.rubikscube.view.zdarzenia;

/**
 * Element zdarzenia przekazanego od użytkownika (tutaj z klawiatury) określający co ma
 * zostać wykonane na kostce.
 * 
 * @author Maciej Jagiełło
 */
public enum MozliweZdarzenia
{
    /** Ruch kostki sciana gorna */
    RUCH_U,
    /** Ruch kostki sciana dolna */
    RUCH_D,
    /** Ruch kostki sciana przednia */
    RUCH_F,
    
    /** Ruch kostki sciana tylna */
    RUCH_B,
    
    /** Ruch kostki sciana prawa */
    RUCH_R,
    
    /** Ruch kostki sciana lewa */
    RUCH_L,
    
    /** Obrot wzgledem osi X */
    OBROT_X,
    
    /** Obrot wzgledem osi Y */
    OBROT_Y,
    
    /** Obrot wzgledem osi Z */
    OBROT_Z,
    
    /** Mieszanie kostki */
    SCRAMBLE,
    
    /** Wyjscie z programu */
    EXIT;
}
