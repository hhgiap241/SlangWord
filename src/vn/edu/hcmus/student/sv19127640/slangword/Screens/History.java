package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:52 PM
 * Description: history panel
 */
public class History {
    private JPanel historyPanel;
    private JLabel header;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;


    public History(){
        historyPanel = new JPanel(new BorderLayout());
        table = new JTable();
        model = new DefaultTableModel();

        header = new JLabel("HISTORY", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (20.0f));
        header.setForeground(Color.blue);

        String[] columns = {"Slang Word", "Meaning"};
        model.setColumnIdentifiers(columns);
//        String[] item={"A","B"};
//        model.addRow(item);
//        model.addRow(item);
        table.setModel(model);

        scrollPane = new JScrollPane(table,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        historyPanel.add(header, BorderLayout.PAGE_START);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getHistoryPanel() {
        return historyPanel;
    }

    public JLabel getHeader() {
        return header;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}

