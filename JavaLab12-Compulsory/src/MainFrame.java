import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
* @author Ionita Andra Paula, grupa 2A7
* Clasa MainFrame este si main-ul aplcatiei. Aici am initializat Frame-ul aplicatiei si am adaugat Design Panel si Control Panel.
* Tot aici am oferit functionalitate butoanelor de load(care incarca o componenta java swing in functie de elementul ales din JCombo 
* [in JCombo aplicatiei sunt specificate toate componentele java swing iar aici sunt tratate]), exit si reset.
*/
public class MainFrame extends JFrame {


    public static void main(String[] args) {

            try {
                new MainFrame().setVisible(true);
            }catch(Exception e){}
        }


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height/2+300;
    int width = screenSize.width/2+300;
    static ControlPanel form;
    static ControlPanel control;
    DesignPanel drawArea;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == control.resetButton) {
                drawArea.clear();
                repaint();
            } else if (e.getSource() == control.loadButton) {
                try {
                    String component= (String)(MainFrame.control.chooseValue.getSelectedItem());
                    System.out.println(component+component);
                    load(component);
                }catch(Exception a){};
            } else if (e.getSource() == control.exitButton) {
                try {
                    exit();
                } catch (IOException e1) {
                }
            }
        }
    };

    public MainFrame() {
        super("Laboratorul 12 Compulsory - Ionita Andra Paula - grupa 2A7");
        rootPane.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        rootPane.setPreferredSize(new Dimension(width, height));
        init();
        addComponents();
        this.pack();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawArea = new DesignPanel();
        control = new ControlPanel(this);
        control.resetButton.addActionListener(actionListener);
        control.loadButton.addActionListener(actionListener);
        control.exitButton.addActionListener(actionListener);
        drawArea.setBackground(Color.pink);
    }

    private void addComponents(){
        add(control, BorderLayout.NORTH);
        add(drawArea, BorderLayout.CENTER);

    }


    public void load(String chooseValue) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        if((String)chooseValue=="javax.swing.JButton"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Button");
            System.out.println("E ok");

        }
        else if(chooseValue=="javax.swing.JCheckBox"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New CheckBox");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JMenu"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JMenu");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JRadioButton"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JRadioButton");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JSpinner"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JSpinner");
            System.out.println("E ok");
        }
        else if(chooseValue=="JTextField"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JTextField");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JComboBox"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JCombo");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JColorChooser"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New ColorChooser");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JList"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JList");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JSlider"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Slider");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JPasswordField"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New PasswordField");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JEditorPane"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New EditorPane");
            System.out.println("E ok");
        }

        else if(chooseValue=="javax.swing.JTextPane"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New TextPane");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JFileChooser"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New FileChooser");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JTextArea"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New TextArea");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JTable"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New JTable");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JTree"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Tree");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JLabel"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Label");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JSeparator"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Separator");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JProgressBar"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New ProgressBar");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JToolTip"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New ToolTip");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JApplet"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Applet");
            System.out.println("E ok");
        }

        else if(chooseValue=="javax.swing.JDialog"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Dialog");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JFrame"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Frame");
            System.out.println("E ok");
        }

        else if(chooseValue=="javax.swing.JPanel"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Panel");
            System.out.println("E ok");
        }

        else if(chooseValue=="javax.swing.JScrollPane"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New ScrollPane");
            System.out.println("E ok");
        }
        else if(chooseValue=="javax.swing.JSplitPane"){
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New SplitPane");
            System.out.println("E ok");
        }

        drawArea.repaint();
    }

    public void exit() throws IOException {
        System.exit(0);
    }

}
