/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am creat multimea de butoane existente in partea de jos a consolei, anume cele care nu se ocupa de desenat ci doar de
 * operatiuni IO ca salvarea sau incarcarea unei imagini, resetarea canvasului sau existenta unui buton de exit.
 */
import javax.swing.*;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton resetButton = new JButton("Reset");
    JButton exitButton=new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        add(loadButton);
        add(saveButton);
        add(resetButton);
        add(exitButton);
    }
}
