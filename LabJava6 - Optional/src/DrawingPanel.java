/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am creat canvasu in care se va desena si am implementat functionalitatea butonului de draw care va desena
 * random cate forme si ce culoare, informatii date ca input, dar cu dimensiune random.
 * De asemenea am cusotmizat si butoanele de input in care se va desena o forma cu numar de laturi, culoare si dimensiune date printr-un click pe canvas.
 * Pentru functia load image a fost nevoie de o functie care seteaza randeaza imaginea, si aceasta functie este gasita mai jos.
 *
 * Laborator 6 Optional
 * Aici am dezvoltat functiile drawShapeAt si drawShapeRandom de la partea compulsory.
 * Amintesc ca drawShapeAt deseneaza cu marime si forma impusa la coordonatele unde sa da click pe canvas iar drawShapeRandom deseneaza
 * numarul de elemente si forma impusa la coordonate random cu dimensiuni random.
 * Am adaugat in interiorul lor cazurile in care de la input se dau celelate forme create inafara de poligoane.
 * Celelalte forme inafara de poligoane sunt: Cerc, Nori, Floare si PackMan (toate sunt foarte cute).
 * Acestea s-au creat in principiu cu ajutorul functiei de creat ovale.
 * Tot in aceasta clasa am oferit functionalitate butoanelor LoadWithPath si SaveWithPath.
 * Pentru ambele am folosit functionalitatea JFileChooser pentru a alege calea catre fisierul care vrem sa fie incarcat
 * sau catre directorul in care vrem sa salvam. Cu exceptia customizarii filechoose-ului, save-ul si load-ul se face identic
 * cum s-a facut la Compulsory in clasa Main Frame.
 * Tot aici, in cazul in care Radiera se afla pe "On" nu se vor putea desena elemente. Va exista (ca in cazul aplicatiei paint)
 * o forma geometrica (in cazul meu un cerc) proportional cu dimensiunea data ca input care va "sterge" (anume va desena cu alb)
 * peste restul desenelor existente.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

                    try {
                        int stroke = Integer.parseInt(MainFrame.form.shapesForm.getText());
                        String sides = (String) MainFrame.form.sidesNoValue.getSelectedItem();
                        String colors= (String)(MainFrame.form.colorValue.getSelectedItem());
                        String eraser=(String)(MainFrame.form.eraserMode.getSelectedItem());
                        drawShapeAt(currentMouseX, currentMouseY, stroke, sides, colors, eraser);


                        repaint();
                    }catch (Exception a){};


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

    public void drawShapeAt(int x, int y, int stroke, String sidess, String colors, String mode) {
        Random rand = new Random();
if(mode!="On") {
    if (colors == "Black")
        graphics.setColor(new Color(000000));
    else
        graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));
    if (sidess != "Cloud" && sidess != "Flower" && sidess != "Cerc" && sidess != "PackMan") {
        int sides = Integer.parseInt(sidess);

        graphics.fillPolygon(new RegularPolygon(x, y, stroke, sides));
    } else if (sidess == "Cerc") {

        graphics.fillOval(x - (stroke / 2), y - (stroke / 2), stroke, stroke);
    } else if (sidess == "Flower") {

        graphics.setStroke(
                new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


        int radius = stroke;

        graphics.setColor(Color.yellow);
        graphics.fillOval(x - radius / 4, y - radius / 4, radius * 3 / 2, radius * 3 / 2);

        graphics.setColor(new Color(255, 192, 203));
        graphics.fillOval(x - radius, y, radius, radius);
        graphics.fillOval(x - radius + radius / 3, y - radius + radius / 3, radius, radius);
        graphics.fillOval(x, y - radius, radius, radius);
        graphics.fillOval(x + radius - radius / 3, y - radius + radius / 3, radius, radius);
        graphics.fillOval(x + radius, y, radius, radius);
        graphics.fillOval(x + radius - radius / 3, y + radius - radius / 3, radius, radius);
        graphics.fillOval(x, y + radius, radius, radius);
        graphics.fillOval(x - radius + radius / 3, y + radius - radius / 3, radius, radius);
    } else if (sidess == "Cloud") {

        {
            graphics.setColor(new Color(131, 186, 216));
            int radius = stroke;
            graphics.fillOval(x - radius, y - (radius * 2), radius * 2, radius * 2);
            graphics.fillOval(x - radius, y - radius + radius / 3, radius * 2, radius * 2);
            graphics.fillOval(x - (radius * 2) - radius / 4, y - radius, radius * 2 + radius / 4, radius * 2 + radius / 4);
            graphics.fillOval(x, y - radius / 2, radius * 2 + radius / 4, radius * 2 + radius / 4);

            graphics.fillOval(x + radius / 3, y - radius * 2 - radius / 2, radius * 3 - radius / 3, radius * 3 - radius / 3);

            graphics.fillOval(x + radius + radius / 2, y - radius - radius / 4, radius * 3, radius * 3);

        }

    } else if (sidess == "PackMan") {

        int radius = stroke;

        graphics.setColor(Color.yellow);
        graphics.fillArc(x, y, stroke * 3, stroke * 3, 30, 300);
        graphics.setColor(Color.black);
        graphics.fillOval(x + radius * 2 - radius / 3 - radius / 7, y + radius / 2, radius / 3, radius / 3);
    }
}
else
{
    graphics.setColor(Color.white);
    graphics.fillOval(x - (stroke*3 / 2), y - (stroke*3 / 2), stroke*3, stroke*3);
}

    }


        public void drawShapeRandom(int repeatNo, String sidess, String colors) {
        while (repeatNo>0) {
            Random rand = new Random();
            if(colors=="Black")
            graphics.setColor(new Color(000000));
        else
            graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));


            int random_x = rand.nextInt(getWidth() - 5);
            int random_y = rand.nextInt(getHeight() - 20);
            int random_radius = rand.nextInt(18) + 6;
            int stroke=random_radius;
           int x=random_x;
           int y=random_y;
            int random_circle_radius = rand.nextInt(50) + 6;
            if (sidess != "Cloud" && sidess != "Flower" &&sidess!="Cerc" &&sidess!="PackMan")
            {
                int sides = Integer.parseInt(sidess);

                graphics.fillPolygon(new RegularPolygon(random_x, random_y, random_radius, sides));}
            else if(sidess=="0")
                graphics.fillOval(random_x,random_y,random_circle_radius,random_circle_radius);


            if (sidess != "Cloud" && sidess != "Flower" &&sidess!="Cerc" &&sidess!="PackMan") {
                int sides = Integer.parseInt(sidess);

                graphics.fillPolygon(new RegularPolygon(x, y, stroke, sides));
            }
            else if (sidess == "Cerc") {

                graphics.fillOval(x-(stroke/2),y-(stroke/2),stroke,stroke);
            }
            else if(sidess=="Flower")
            {

                graphics.setStroke(
                        new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                //graphics.setColor(new Color(0, 128, 0));  // green
                // graphics.drawLine(x+100, y+190, x+100, y+70);

                int radius=stroke;

                graphics.setColor(Color.yellow);
                graphics.fillOval(x-radius/4, y-radius/4, radius*3/2, radius*3/2);

                graphics.setColor(new Color(255, 192, 203));
                graphics.fillOval(x-radius, y, radius, radius);
                graphics.fillOval(x-radius+radius/3, y-radius+radius/3, radius, radius);
                graphics.fillOval(x, y-radius, radius, radius);
                graphics.fillOval(x+radius-radius/3, y-radius+radius/3, radius, radius);
                graphics.fillOval(x+radius, y, radius, radius);
                graphics.fillOval(x+radius-radius/3, y+radius-radius/3, radius, radius);
                graphics.fillOval(x, y+radius, radius, radius);
                graphics.fillOval(x-radius+radius/3, y+radius-radius/3, radius, radius);
            }

            else if(sidess=="Cloud"){

                {
                    graphics.setColor(new Color(131,186,216));
                    int radius = stroke;
                    graphics.fillOval(x - radius, y - (radius * 2), radius * 2, radius * 2);
                    graphics.fillOval(x - radius, y- radius+radius/3, radius * 2, radius * 2);
                    graphics.fillOval(x - (radius * 2)-radius/4, y - radius, radius * 2+radius/4, radius * 2+radius/4);
                    graphics.fillOval(x, y-radius/2, radius * 2+radius/4, radius * 2+radius/4);

                    graphics.fillOval(x +radius/3 , y -radius*2 -radius/2, radius * 3-radius/3, radius * 3-radius/3);

                    graphics.fillOval(x +radius+radius/2, y- radius-radius/4, radius * 3, radius * 3);

                }

            }

            else if(sidess=="PackMan") {

                int radius=stroke;

                graphics.setColor(Color.yellow);
                graphics.fillArc(x, y, stroke*3, stroke*3, 30, 300);
                graphics.setColor(Color.black);
                graphics.fillOval(x +radius*2-radius/3-radius/7, y+radius/2,radius/3, radius/3);
            }

            repeatNo--;

        }
    }


    public void saveWithPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Alege un director");
        fileChooser.setCurrentDirectory(new File("."));

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);

        JOptionPane optionPane = new JOptionPane();
        String fileName = JOptionPane.showInputDialog("Scrie numele fisierului:");
        File location = new File(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName + ".png");
        try {
            ImageIO.write(image, "PNG", location);
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
        clear();
    }

    public void loadWithPath() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("alege un fisier (de preferat o imagine png):");
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        repaint();
        setImage(ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath())));
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
    }

}
