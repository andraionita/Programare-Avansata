/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am configurat butoanele si field-urile in care se itnroduc date pentru input.
 * Astea contin numar de laturi, numar de forme, culoarea formei (negru sau multicolor) si dimensiunea formei. De asemenea tot aici am creat butonul de "Draw"
 * care deseneaza un numar de forme dat ca input, cu laturi date ca input,cu dimensiuni random in pozitii random.
 * Acestea apar la fiecare apasare a butonului de "Draw".
 *
 * Laborator 6 Optional
 * In aceasta clasa am adaugat noi optiuni de input pentru Numarul de laturi/Forma care includ formele noi create anume cerc,
 * nor, floare si packman. Si desigur s-a creat un nou Combo pentru radiera cu optiunile "on" si "off".
 * By default radiera va fi pe off.
 *
 */

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    private String[] sidesNo = { "PackMan","Cloud", "Flower", "Cerc", "3", "4", "5", "6", "7", "8", "9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","100" };

    JLabel shapeNoLabel = new JLabel("Numar forme: ");
    JLabel formLabel = new JLabel("Dimensiune forma: ");
    JLabel sideLabel = new JLabel("Numar laturi/Forma:");
    JLabel eraserLabel = new JLabel("Radiera:");
    JLabel colorLabel= new JLabel("Alege Culoare:");

    JTextField shapesNo = new JFormattedTextField();
    JTextField shapesForm = new JFormattedTextField();
    JComboBox sidesNoValue = new JComboBox(sidesNo);
    JButton drawButton = new JButton("Draw");
    String[] colors={"Black", "Combo"};
    JComboBox colorValue = new JComboBox(colors);
    String[] mode={"Off","On"};
    JComboBox eraserMode = new JComboBox(mode);

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        this.setBorder(BorderFactory.createTitledBorder("Panou de configurare"));
        init();
        this.setLayout(new GridLayout(2,6, 20, 0));
    }

    private void init() {

        shapesForm.setText("Introdu un numar");
        shapesNo.setText("Introdu un numar");
        add(sideLabel);
        add(shapeNoLabel);
        add(formLabel);
        add(colorLabel);
        add(eraserLabel);
        add(drawButton);
        add(sidesNoValue);
        add(shapesNo);
        add(shapesForm);
        add(colorValue);
        add(eraserMode);

    }

}
