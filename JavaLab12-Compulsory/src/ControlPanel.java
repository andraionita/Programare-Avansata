import javax.swing.*;
import java.awt.*;

/**
* @author Ionita Andra Paula, grupa 2A7
* Clasa ControlPanel se ocupa cu crearea butoanelor care faciliteaza scopul aplicatiei.
* Anume va exista un JCombo care contine toate tipurile de componente java swing si 3 butoane: unul de load, unul de exit si unul de reset. 
*/

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton resetButton = new JButton("Reset");
    JButton exitButton=new JButton("Exit");
    JLabel chooseLabel = new JLabel("Alege componenta dorita:");
     String[] chooseType = { "javax.swing.JButton","javax.swing.JEditorPane","javax.swing.JTextPane","javax.swing.JFileChooser","javax.swing.JTable","javax.swing.JTextArea","javax.swing.JTree","javax.swing.JLabel","javax.swing.JProgressBar","javax.swing.JSeparator","javax.swing.JLabel","javax.swing.JToolTip","javax.swing.JApplet","javax.swing.JDialog","javax.swing.JFrame","javax.swing.JPanel","javax.swing.JScrollPane","javax.swing.JSplitPane","javax.swing.JColorChooser", "javax.swing.JCheckBox", "javax.swing.JComboBox", "javax.swing.JList", "javax.swing.JMenu", "javax.swing.JRadioButton", "javax.swing.JSlider", "javax.swing.JSpinner", "javax.swing.JTextField", "javax.swing.JPasswordField"};
    JComboBox chooseValue = new JComboBox(chooseType);

    final String defaultText="New";

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
       // this.setLayout(new GridLayout(2,5, 20, 0));
    }


    private void init() {

        add(chooseLabel);
        add(chooseValue);
        add(loadButton);
        add(resetButton);
        add(exitButton);


    }


}
