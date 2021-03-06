import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Juan Carlos Tapia Flores  14133
 * Inteligencia Artificial
 * 
 * Clase que maneja la creacion de dibujos con pixeles.
 * */

public class DirectDraw extends JPanel {

    private BufferedImage canvas;

    public DirectDraw(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //fillCanvas(Color.BLUE);
        //drawRect(Color.RED, 0, 0, width/2, height/2);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void drawPoint(Color c, int x, int y) {
        // Implement point drawing
    	int color = c.getRGB();
    	canvas.setRGB(x, y, color);
        repaint();
    }
    
    public void drawPoint(int c, int x, int y) {
        // Implement point drawing
    	canvas.setRGB(x, y, c);
        repaint();
    }
    
    public void drawLine(Color c, int x1, int y1, int x2, int y2) {
        // Implement line drawing
        repaint();
    }

    public void drawRect(Color c, int x1, int y1, int x2, int y2) {
    	int color = c.getRGB();
        // Implement rectangle drawing
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }
    
    public void drawRect(int c, int x1, int y1, int x2, int y2) {
        
        // Implement rectangle drawing
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                canvas.setRGB(x, y, c);
            }
        }
        repaint();
    }

    public void drawOval(Color c, int x1, int y1, int width, int height) {
        // Implement oval drawing
        repaint();
    }

}