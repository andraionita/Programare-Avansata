/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am creat, deschis si customizat, prin adaugarea butoanelor si functionalitatilor lor, consola
 * in care se se va desena forme, incarca imagini si salva imagini. S-a creat si butonul de reset care v-a da clear la canvas.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height/2+300;
    int width = screenSize.width/2+300;
    static ConfigPanel form;
    DrawingPanel drawArea;
    ControlPanel control;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == control.resetButton) {
                drawArea.clear();
            } else if (e.getSource() == control.loadButton) {
                try {
                    load();
                } catch (IOException e1) {
                }
            } else if (e.getSource() == control.saveButton) {
                try {
                    save();
                } catch (IOException e1) {
                }
            } else if (e.getSource() == control.exitButton) {
                try {
                    exit();
                } catch (IOException e1) {
                }
            } else if (e.getSource() == form.drawButton) {
                try {
                    int repeat = Integer.parseInt(form.shapesNo.getText());
                    int sides = Integer.valueOf((Integer) form.sidesNoValue.getSelectedItem());
                    String colors= (String)(MainFrame.form.colorValue.getSelectedItem());
                    drawArea.drawShapeRandom(repeat, sides,colors);
                    repaint();
                }catch(Exception a){};
            }
        }
    };

    public MainFrame() {
        super("Laboratorul 6 Compulsory - Ionita Andra Paula - grupa 2A7");
        rootPane.setBorder(BorderFactory.createTitledBorder("Canvas"));
        rootPane.setPreferredSize(new Dimension(width, height));
        init();
        addComponents();
        this.pack();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        form = new ConfigPanel(this);
        drawArea = new DrawingPanel();
        control = new ControlPanel(this);
        form.drawButton.addActionListener(actionListener);
        control.resetButton.addActionListener(actionListener);
        control.saveButton.addActionListener(actionListener);
        control.loadButton.addActionListener(actionListener);
        control.exitButton.addActionListener(actionListener);
    }

    private void addComponents(){
        add(form, BorderLayout.NORTH);
        add(drawArea, BorderLayout.CENTER);
        add(control, BorderLayout.SOUTH);
    }

    public void save() throws IOException {
        ImageIO.write(drawArea.getImage(), "PNG", new File("lab6_save_try.png"));
    }

    public void load() throws IOException {
        drawArea.setImage(ImageIO.read(new File("try.png")));
        repaint();
    }
    public void exit() throws IOException {
      System.exit(0);
    }


}
