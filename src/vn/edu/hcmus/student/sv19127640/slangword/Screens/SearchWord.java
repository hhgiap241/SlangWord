package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import vn.edu.hcmus.student.sv19127640.slangword.SlangWord;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:50 PM
 * Description: ...
 */
public class SearchWord extends MouseAdapter implements ActionListener, TableModelListener {
    // attribute
    private JPanel searchPanel;
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
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private ButtonGroup buttonGroup;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemDelete;
    private SlangWord slangWord;
    private String[][] result;

    public SearchWord(SlangWord slangWord){
        this.slangWord = slangWord;
        searchPanel = new JPanel();
        findPanel = new JPanel();
        tablePanel = new JPanel();
        headerPanel = new JPanel();
        findBtn = new JButton();
        findText = new JTextField(30);
        findLable = new JLabel();
        table = new JTable();
        model = new DefaultTableModel();
        scrollPane = new JScrollPane();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        buttonGroup = new ButtonGroup();
        popupMenu = new JPopupMenu();
        menuItemDelete = new JMenuItem();
        result = null;
    }

    public JPanel setUPPanel(){
        searchPanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new GridBagLayout());

        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.LINE_AXIS));
        findLable.setText("Input slang word: ");
        findBtn.setText("Find");
        findBtn.addActionListener(this);
        findPanel.add(findLable);
        findPanel.add(findText);
        findPanel.add(Box.createRigidArea(new Dimension(15,0)));
        findPanel.add(findBtn);

        radioButton1.setText("Search by Slang word");
        radioButton2.setText("Search by definition");
        radioButton1.setSelected(true);
        // use button group in order to make every time user can only choose one radio button
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

        String[] columns = {"NO.", "Slang Word", "Meaning"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getModel().addTableModelListener(this);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(500);

        menuItemDelete.setText("Delete");
        menuItemDelete.addActionListener(this);
        popupMenu.add(menuItemDelete);
        table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(this);

        scrollPane.getViewport().add(table,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(scrollPane, BorderLayout.CENTER);


        searchPanel.add(headerPanel, BorderLayout.PAGE_START);
        searchPanel.add(tablePanel, BorderLayout.CENTER);
        return searchPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findBtn){
            String input = findText.getText();
            if (input.length() == 0){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "The input field can't be empty!!!");
            }else{
                model.setRowCount(0);
                if(radioButton1.isSelected()){
                    result = slangWord.findBySlangWord(input);
                }else if(radioButton2.isSelected()){
                    result = slangWord.findByDefinition(input);
                }
                if (result != null){
                    for (int i = 0; i < result.length; i++){
                        model.addRow(result[i]);
                    }

                }else{
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Can't find anything!!!");
                }
            }
        }else if(e.getSource() == menuItemDelete){
            int rowIndex = table.getSelectedRow();
            slangWord.deleteASlangWord(result[rowIndex][1], result[rowIndex][2]);
            model.removeRow(rowIndex);
            slangWord.saveToFile();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Delete complete!!!");
        }
    }

    /**
     * Reference at: https://www.codejava.net/java-se/swing/jtable-popup-menu-example
     * Making a row selected when the popup menu is displayed
     * @param event MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(currentRow, currentRow);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int rowIndex = table.getSelectedRow();
        int colIndex = table.getSelectedColumn();
        if (rowIndex == -1)
            return;
        if (colIndex == 2){
            slangWord.editSlangWord((String)model.getValueAt(rowIndex, 1), result[rowIndex][2], (String)model.getValueAt(rowIndex, 2));
            slangWord.saveToFile();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Update complete!!!");
            table.getSelectionModel().clearSelection();
        }
    }
}
