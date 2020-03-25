/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am creat canvasu in care se va desena si am implementat functionalitatea butonului de draw care va desena
 * random cate forme si ce culoare, informatii date ca input, dar cu dimensiune random.
 * De asemenea am cusotmizat si butoanele de input in care se va desena o forma cu numar de laturi, culoare si dimensiune date printr-un click pe canvas.
 * Pentru functia load image a fost nevoie de o functie care seteaza randeaza imaginea, si aceasta functie este gasita mai jos.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {

    private BufferedImage image;
    private Graphics2D graphics;
    private int currentMouseX, currentMouseY;

    public DrawingPanel() {
        this.setBorder(BorderFactory.createTitledBorder("Deseneaza ceva:"));
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
                if(graphics != null) {
                    try {
                        int stroke = Integer.parseInt(MainFrame.form.shapesForm.getText());
                        int sides = Integer.valueOf((Integer) MainFrame.form.sidesNoValue.getSelectedItem());
                        String colors= (String)(MainFrame.form.colorValue.getSelectedItem());

                        drawShapeAt(currentMouseX, currentMouseY, stroke, sides, colors);
                        repaint();
                    }catch (Exception a){};
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if(image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void drawShapeAt(int x, int y, int stroke, int sides, String colors) {
        Random rand = new Random();
        if(colors=="Black")
            graphics.setColor(new Color(000000));
        else
           graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));
        if(sides!=0)
            graphics.fillPolygon(new RegularPolygon(x, y, stroke, sides));
        else
            graphics.fillOval(x-(stroke/2),y-(stroke/2),stroke,stroke);
    }

    public void drawShapeRandom(int repeatNo, int sides, String colors) {
        while (repeatNo>0) {
            Random rand = new Random();
            if(colors=="Black")
            graphics.setColor(new Color(000000));
        else
            graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));
            int random_x = rand.nextInt(getWidth() - 5);
            int random_y = rand.nextInt(getHeight() - 20);
            int random_radius = rand.nextInt(18) + 6;
            int random_circle_radius = rand.nextInt(50) + 6;
            if(sides!=0)
                graphics.fillPolygon(new RegularPolygon(random_x, random_y, random_radius, sides));
            else
                graphics.fillOval(random_x,random_y,random_circle_radius,random_circle_radius);
            repeatNo--;
        }
    }



    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
    }

}
