import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
* @author Ionita Andra Paula, grupa 2A7
* Clasa DesignPanel se ocupa cu adaugarea componentei alese, lucru care se intampla in functia addComponent.
*/

public class DesignPanel extends JPanel {

    private BufferedImage image;
    private Graphics2D graphics;
    private int currentMouseX, currentMouseY;
    int x = 50, y = 50, w = 100, h = 40;

    public DesignPanel() {
        this.setBorder(BorderFactory.createTitledBorder("Design Panel:"));
        this.setBackground(Color.pink);
        setDoubleBuffered(false);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.setBackground(Color.pink);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        graphics.setBackground(Color.pink);
        repaint();
    }

    public void addComponent(Component component, String name) {
        JButton newComponent;
        JLabel newComponent2;
        JCheckBox newComponent3;
        JTextField newComponent4;
        Class componentClass = component.getClass();

        switch (componentClass.toString()) {
            case "class javax.swing.JButton":
                newComponent = (JButton) component;
                newComponent.setText("Button");
                newComponent.setBounds(x, y, w, h);
                add(newComponent);
                break;
            case "class javax.swing.JLabel":
                newComponent2 = (JLabel) component;
                newComponent2.setText("Label");
                newComponent2.setBounds(x, y, w, h);
                add(newComponent2);
                break;
            case "class javax.swing.JCheckBox":
                newComponent3 = (JCheckBox) component;
                newComponent3.setText("CheckBox");
                newComponent3.setBounds(x, y, w, h);
                add(newComponent3);
                break;
            case "class javax.swing.JTextArea":
                newComponent4 = (JTextField) component;
                newComponent4.setText("TextArea");
                newComponent4.setBounds(x, y, w, h);
                add(newComponent4);
                break;
            default:
                component.setBounds(x, y, w, h);
                add(component);
                break;
        }
                x += 100;
                if (x > 800) {
                    x = 50;
                    y += 100;
                }
                if (y > 800) {
                    y = 50;
                    x = 50;
                }
        }


}
