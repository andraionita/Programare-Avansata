

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
* @author Ionita Andra Paula, grupa 2A7
* Laboratorul 12 Optional
* In aceasta clasa. Am creat clasa Tabelului de proprietati. Am creat JTable-ul si i-am adaugat un scrollbar.
*/

public class Properties extends JPanel {
    private final MainFrame frame;
    private final JTable propertiesTable;

    public Properties(MainFrame frame) {
        this.frame = frame;
        this.propertiesTable = new JTable(new DefaultTableModel(new String[]{"Tipul", "Numele"}, 50));
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        propertiesTable.setFillsViewportHeight(true);
        JScrollPane scrollTable = new JScrollPane(propertiesTable);
        add(scrollTable, BorderLayout.CENTER);
    }

    public JTable getPropertiesTable() {
        return propertiesTable;
    }
}
