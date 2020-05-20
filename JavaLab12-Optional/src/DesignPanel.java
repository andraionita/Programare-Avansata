import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
* @author Ionita Andra Paula, grupa 2A7
* Laboratorul 12 Optional
* In aceasta clasa fata de partea obligatorie am adaugat metoda addFocusListenerToComponent care adauga in tabelul de properties
* informatii despre componentele desenate. Informatiile apar dupa in tabelul de proprietati ce dai click pe componenta dorita.
*/
public class DesignPanel extends JPanel {

    public MainFrame frame;
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
				  this.frame.getDesignPanel().addFocusListenerToComponent(newComponent);
                break;
            case "class javax.swing.JLabel":
                newComponent2 = (JLabel) component;
                newComponent2.setText("Label");
                newComponent2.setBounds(x, y, w, h);
                add(newComponent2);
				  this.frame.getDesignPanel().addFocusListenerToComponent(newComponent2);
                break;
            case "class javax.swing.JCheckBox":
                newComponent3 = (JCheckBox) component;
                newComponent3.setText("CheckBox");
                newComponent3.setBounds(x, y, w, h);
                add(newComponent3);
				  this.frame.getDesignPanel().addFocusListenerToComponent(newComponent3);
                break;
            case "class javax.swing.JTextArea":
                newComponent4 = (JTextField) component;
                newComponent4.setText("TextArea");
                newComponent4.setBounds(x, y, w, h);
                add(newComponent4);
				  this.frame.getDesignPanel().addFocusListenerToComponent(newComponent4);
                break;
            default:
                component.setBounds(x, y, w, h);
                add(component);
				 this.frame.getDesignPanel().addFocusListenerToComponent(component);
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

    public void addFocusListenerToComponent(Component component) {
        component.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                BeanInfo info = null;
                Class<?> componentClass = component.getClass();
                try {
                    info = Introspector.getBeanInfo(componentClass);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int i = 0;
                DefaultTableModel model = (DefaultTableModel) frame.getPropertiesPanel().getPropertiesTable().getModel();
                for (PropertyDescriptor propertyDescriptor : info.getPropertyDescriptors()) {
                    model.setValueAt(String.valueOf(propertyDescriptor.getPropertyType()), i, 0);
                    model.setValueAt(String.valueOf(propertyDescriptor.getName()), i, 1);
                    ++i;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
    }
}
