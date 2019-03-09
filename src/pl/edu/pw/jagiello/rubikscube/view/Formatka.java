package pl.edu.pw.jagiello.rubikscube.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;

import pl.edu.pw.jagiello.rubikscube.model.Naklejka;

/**
 * Klasa z JPanel rysujaca kostke
 * 
 * @author Maciej Jagiello
 */
class Formatka extends JPanel
{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** Obiekt liczacy grafike 2D */
    private Graphics2D g2d;
    
    /**
     * listaNaklejek - lista NaklejekNaKostce zawierajaca wspolrzedne pikseli oraz wartosc
     * koloru na odpowiadajacej jej tablicy Naklejek
     */
    private final LinkedList<NaklejkaNaKostce> listaNaklejek;
    
    /** stanKostki - tablica naklejek */
    private Naklejka[][][] stanKostki;
    
    /** naklejkaKolor - mapa naklejki do koloru */
    private final Map<Naklejka, Color> naklejkaKolor;
    
    /**
     * konstruktor wypełnianiajacy współrzędnymi liste NaklejekNaKostce
     */
    Formatka()
    {
        listaNaklejek = new LinkedList<NaklejkaNaKostce>();
        setLayout(null);
        /*
         * <ul> <li>0 FRONT</li> <li>1 RIGHT</li> <li>2 BACK</li> <li>3 LEFT</li> <li>4
         * UP</li> <li>5 DOWN</li> </ul>
         */
        // Dodanie do listy naklejek naklejki ze sciany lewej
        listaNaklejek.add(new NaklejkaNaKostce(66, 258, 59, 232, 66, 209, 70, 231, 3, 2, 2));
        listaNaklejek.add(new NaklejkaNaKostce(52, 193, 58, 225, 65, 191, 59, 164, 3, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(42, 146, 49, 185, 59, 144, 51, 112, 3, 0, 2));
        listaNaklejek.add(new NaklejkaNaKostce(67, 184, 70, 207, 77, 171, 72, 157, 3, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(60, 148, 67, 173, 72, 150, 65, 117, 3, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(51, 104, 58, 129, 65, 94, 59, 71, 3, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(74, 149, 77, 162, 82, 148, 79, 133, 3, 2, 0));
        listaNaklejek.add(new NaklejkaNaKostce(68, 107, 73, 123, 75, 94, 71, 99, 3, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(61, 63, 64, 84, 70, 67, 67, 41, 3, 0, 0));
        
        // Dodanie do listy naklejek naklejki ze sciany dolnej
        listaNaklejek.add(new NaklejkaNaKostce(226, 257, 182, 258, 181, 226, 223, 224, 5, 0, 2));
        listaNaklejek.add(new NaklejkaNaKostce(171, 256, 131, 256, 128, 227, 170, 225, 5, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(117, 256, 75, 256, 79, 221, 120, 225, 5, 0, 0));
        listaNaklejek.add(new NaklejkaNaKostce(221, 219, 181, 221, 179, 187, 217, 183, 5, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(169, 218, 130, 220, 131, 190, 169, 186, 5, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(119, 218, 79, 217, 82, 186, 122, 186, 5, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(216, 180, 175, 180, 173, 153, 213, 151, 5, 2, 2));
        listaNaklejek.add(new NaklejkaNaKostce(169, 180, 129, 182, 134, 153, 168, 152, 5, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(125, 179, 88, 178, 87, 153, 123, 152, 5, 2, 0));
        
        // Dodanie do listy naklejek naklejki ze sciany tylnej
        listaNaklejek.add(new NaklejkaNaKostce(74, 43, 118, 42, 119, 74, 77, 76, 2, 0, 2));
        listaNaklejek.add(new NaklejkaNaKostce(129, 44, 169, 44, 172, 73, 130, 75, 2, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(183, 44, 225, 44, 221, 79, 180, 75, 2, 0, 0));
        listaNaklejek.add(new NaklejkaNaKostce(79, 81, 119, 79, 121, 113, 83, 117, 2, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(131, 82, 170, 80, 169, 110, 131, 114, 2, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(181, 82, 221, 83, 218, 114, 178, 114, 2, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(84, 120, 125, 120, 127, 147, 87, 149, 2, 2, 2));
        listaNaklejek.add(new NaklejkaNaKostce(131, 120, 171, 118, 166, 147, 132, 148, 2, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(175, 121, 212, 122, 213, 147, 177, 148, 2, 2, 0));
        
        // Dodanie do listy naklejek naklejki ze sciany prawej
        listaNaklejek.add(new NaklejkaNaKostce(234, 258, 241, 232, 234, 209, 230, 231, 1, 2, 0));
        listaNaklejek.add(new NaklejkaNaKostce(248, 193, 242, 225, 235, 191, 241, 164, 1, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(258, 146, 251, 185, 241, 144, 249, 112, 1, 0, 0));
        listaNaklejek.add(new NaklejkaNaKostce(233, 184, 230, 207, 223, 171, 228, 157, 1, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(240, 148, 233, 173, 228, 150, 235, 117, 1, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(249, 104, 242, 129, 235, 94, 241, 71, 1, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(226, 149, 223, 162, 218, 148, 221, 133, 1, 2, 2));
        listaNaklejek.add(new NaklejkaNaKostce(232, 107, 227, 123, 225, 94, 229, 99, 1, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(239, 63, 236, 84, 230, 67, 233, 41, 1, 0, 2));
        
        // Dodanie do listy naklejek naklejki ze sciany gornej 
        listaNaklejek.add(new NaklejkaNaKostce(70, 34, 117, 34, 114, 61, 65, 61, 4, 0, 0));
        listaNaklejek.add(new NaklejkaNaKostce(128, 35, 173, 34, 175, 60, 125, 60, 4, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(182, 35, 229, 34, 236, 60, 185, 60, 4, 0, 2));
        listaNaklejek.add(new NaklejkaNaKostce(63, 67, 114, 67, 111, 100, 56, 99, 4, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(125, 67, 175, 67, 178, 100, 123, 100, 4, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(184, 66, 237, 67, 244, 100, 188, 100, 4, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(55, 107, 111, 107, 108, 146, 46, 145, 4, 2, 0));
        listaNaklejek.add(new NaklejkaNaKostce(122, 107, 178, 107, 181, 146, 120, 145, 4, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(189, 107, 245, 107, 253, 146, 191, 145, 4, 2, 2));
        
        // Dodanie do listy naklejek naklejki ze sciany przedniej
        listaNaklejek.add(new NaklejkaNaKostce(70, 266, 117, 266, 114, 239, 65, 239, 0, 2, 0));
        listaNaklejek.add(new NaklejkaNaKostce(128, 265, 173, 266, 175, 240, 125, 240, 0, 2, 1));
        listaNaklejek.add(new NaklejkaNaKostce(182, 265, 229, 266, 236, 240, 185, 240, 0, 2, 2));
        listaNaklejek.add(new NaklejkaNaKostce(63, 233, 114, 233, 111, 200, 56, 201, 0, 1, 0));
        listaNaklejek.add(new NaklejkaNaKostce(125, 233, 175, 233, 178, 200, 123, 200, 0, 1, 1));
        listaNaklejek.add(new NaklejkaNaKostce(184, 234, 237, 233, 244, 200, 188, 200, 0, 1, 2));
        listaNaklejek.add(new NaklejkaNaKostce(55, 193, 111, 193, 108, 154, 46, 155, 0, 0, 0));
        listaNaklejek.add(new NaklejkaNaKostce(122, 193, 178, 193, 181, 154, 120, 155, 0, 0, 1));
        listaNaklejek.add(new NaklejkaNaKostce(189, 193, 245, 193, 253, 154, 191, 155, 0, 0, 2));
        
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(0x00, 0x00, 0x00));
        
        // przyporzadkuj kolory do naklejek 
        naklejkaKolor = new HashMap<Naklejka, Color>();
        naklejkaKolor.put(Naklejka.BACK, new Color(0x00, 0x66, 0x99));
        naklejkaKolor.put(Naklejka.UP, new Color(0xdd, 0xdd, 0xdd));
        naklejkaKolor.put(Naklejka.DOWN, new Color(0xff, 0xff, 0x00));
        naklejkaKolor.put(Naklejka.FRONT, new Color(0x33, 0xcc, 0x11));
        naklejkaKolor.put(Naklejka.RIGHT, new Color(0xaa, 0x00, 0x00));
        naklejkaKolor.put(Naklejka.LEFT, new Color(0xd0, 0x34, 0xa5));
        
        stanKostki = new Naklejka[6][3][3];
    }
    
    /**
     * Aktualizacja stanu kostki w widoku
     * 
     * @param nak
     *            tablica naklejek
     */
    void aktualizujStan(final Naklejka[][][] nak)
    {
        stanKostki = nak;
    }
    
    protected void paintComponent(final Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        
        // wyswietl kazda naklejke z listy naklejek na panelu 
        for (final NaklejkaNaKostce i : listaNaklejek)
        {
            g2d.setColor(naklejkaKolor.get(stanKostki[i.getSciana()][i.getWspolrzednaX()][i.getWspolrzednaY()]));
            g2d.fillPolygon(i.getPunktyX(), i.getPunktyY(), 4);
        }
        
    }
}