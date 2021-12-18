package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import vn.edu.hcmus.student.sv19127640.slangword.SlangWord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:52 PM
 * Description: history panel
 */
public class History {
    /**
     * attributes
     */
    private JPanel historyPanel;
    private JLabel header;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private SlangWord slangWord;

    /**
     * constructor with parameter
     * @param slangWord SlangWord
     */
    public History(SlangWord slangWord){
        this.slangWord = slangWord;
        historyPanel = new JPanel();
        table = new JTable();
        model = new DefaultTableModel();
        scrollPane = new JScrollPane();
    }

    /**
     * set up panel
     * @return JPanel
     */
    public JPanel setUPPanel(){
        historyPanel.setLayout(new BorderLayout());
        header = new JLabel("HISTORY", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (20.0f));
        header.setForeground(Color.blue);

        String[] columns = {"No", "Slang Word", "Meaning"};
        model.setColumnIdentifiers(columns);
        // auto renew table
        int delay = 1; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                model.setRowCount(0);
                Vector<Vector<String>> values = slangWord.readFromHistory();
                for (Vector<String> data: values){
                    model.addRow(data);
                }
            }
        };
        new Timer(delay, taskPerformer).start();
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(500);
        scrollPane.getViewport().add(table,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        historyPanel.add(header, BorderLayout.PAGE_START);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        return historyPanel;
    }
}

