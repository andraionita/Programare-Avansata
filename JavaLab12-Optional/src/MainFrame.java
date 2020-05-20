import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
* @author Ionita Andra Paula, grupa 2A7
* Laboratorul 12 Optional
* In aceasta clasa, in care se afla si main-ul aplicatiei, fata de partea obligatorie am adaugat listener pe butoanele save si load
* si le-am oferit functionalitate in metodele save() si load()
*/
public class MainFrame extends JFrame implements Serializable{


    public static void main(String[] args) {

            try {
                new MainFrame().setVisible(true);
            }catch(Exception e){}
        }


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height/2+300;
    int width = screenSize.width/2+300;
    static ControlPanel form;
    public MainFrame frame;
    static ControlPanel control;
    Properties propertiesPanel = new Properties(frame);
    DesignPanel drawArea;

    public void updateDesignPanel(DesignPanel designPanel) {
        remove(this.drawArea);
        this.drawArea = designPanel;
        add(designPanel, BorderLayout.CENTER);
        pack();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == control.resetButton) {
                drawArea.clear();
                repaint();
            } else if (e.getSource() == control.saveButton) {
                    save();
            } else if (e.getSource() == control.loadButton) {
                    load();
            } else if (e.getSource() == control.exitButton) {
                try {
                    exit();
                } catch (IOException e1) {
                }
            } else if (e.getSource() == control.drawButton) {
                try {
                    String component= (String)(MainFrame.control.chooseValue.getSelectedItem());
                    System.out.println(component+component);
                    draw(component);
                }catch(Exception a){};
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
        control.drawButton.addActionListener(actionListener);
        control.exitButton.addActionListener(actionListener);
        drawArea.setBackground(Color.pink);
    }

    private void addComponents(){
        add(control, BorderLayout.NORTH);
        add(drawArea, BorderLayout.CENTER);
        add(propertiesPanel,BorderLayout.SOUTH);


    }

    public void draw(String chooseValue) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {



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
        else
        {
            Class classChoosen=Class.forName(chooseValue);
            Object userObject=classChoosen.getConstructor().newInstance();
            drawArea.addComponent((Component)userObject,"New Button");
            System.out.println("E ok");
        }

        drawArea.repaint();
    }


    private void load() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select an xml document");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML documents", "xml");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getPath());

            try {
                XMLDecoder decoder =
                        new XMLDecoder(new BufferedInputStream(
                                new FileInputStream(file)));

                DesignPanel designPanel = (DesignPanel) decoder.readObject();
                decoder.close();
                this.frame.updateDesignPanel(designPanel);
            } catch (FileNotFoundException e) {

            }
        }
    }


    private void save() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Choose a directory to save your file");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile() + "\\panel.xml");
            try {
                XMLEncoder encoder = new XMLEncoder(
                        new BufferedOutputStream(
                                new FileOutputStream(file)));

                encoder.writeObject(this.drawArea);
                encoder.close();
            } catch (FileNotFoundException e) {

            }
        }
    }

    public DesignPanel getDesignPanel() {
        return drawArea;
    }


    public Properties getPropertiesPanel() {
        return propertiesPanel;
    }

    public void exit() throws IOException {
        System.exit(0);
    }

}
