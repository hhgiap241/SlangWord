package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:50 PM
 * Description: ...
 */
public class SearchWord {
    private JPanel searchPanel;

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    // attribute
    private JPanel findPanel;
    private JPanel tablePanel;
    private JPanel headerPanel;
    private JButton findBtn;
    private JTextField findText;
    private JLabel findLable;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JLabel header;


    public SearchWord(){
        searchPanel = new JPanel(new BorderLayout());
        findPanel = new JPanel();
        tablePanel = new JPanel();
        headerPanel = new JPanel(new GridBagLayout());
        table = new JTable();
        model = new DefaultTableModel();

        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.LINE_AXIS));
        findLable = new JLabel("Input slang word: ");
        findText = new JTextField(30);
        findBtn = new JButton("Find");
        findPanel.add(findLable);
        findPanel.add(findText);
        findPanel.add(Box.createRigidArea(new Dimension(15,0)));
        findPanel.add(findBtn);

        JRadioButton radioButton1 = new JRadioButton("Search by Slang word");
        JRadioButton radioButton2 = new JRadioButton("Search by definition");
        radioButton1.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;
        headerPanel.add(radioButton1, gbc);
        gbc.gridx = 1;
        headerPanel.add(radioButton2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.gridwidth = 2;
        headerPanel.add(findPanel, gbc);


        tablePanel.setLayout(new BorderLayout());
        header = new JLabel("FIND SLANG WORD", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (14.0f));
        header.setForeground(Color.blue);
        tablePanel.add(header, BorderLayout.PAGE_START);

        String[] columns = {"Slang Word", "Meaning"};
        model.setColumnIdentifiers(columns);
//        String[] item={"A","B"};
//        model.addRow(item);
//        model.addRow(item);
        table.setModel(model);

        scrollPane = new JScrollPane(table,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(scrollPane, BorderLayout.CENTER);


        searchPanel.add(headerPanel, BorderLayout.PAGE_START);
        searchPanel.add(tablePanel, BorderLayout.CENTER);
    }
    public JPanel getFindPanel() {
        return findPanel;
    }

    public JButton getFindBtn() {
        return findBtn;
    }

    public JTextField getFindText() {
        return findText;
    }

    public JLabel getFindLable() {
        return findLable;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JLabel getHeader() {
        return header;
    }
}
