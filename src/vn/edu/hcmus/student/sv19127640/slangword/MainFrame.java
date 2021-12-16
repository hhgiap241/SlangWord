package vn.edu.hcmus.student.sv19127640.slangword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * vn.edu.hcmus.student.sv19127640.slangword
 * Created by ADMIN
 * Date 12/15/2021 - 8:44 PM
 * Description: main frame for the java app
 */
public class MainFrame extends JPanel{
    MainFrame(){
        setUpGUI();
    }
    public void showGUI(){
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setOpaque(true);
        frame.setContentPane(this);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setVisible(true);
    }
    private void setUpGUI(){
        setLayout(new BorderLayout());
        JLabel headerLabel = new JLabel("SLANG DICTIONARY", SwingConstants.CENTER);
        headerLabel.setForeground(Color.red);
        headerLabel.setFont(headerLabel.getFont().deriveFont (32.0f));
        add(headerLabel, BorderLayout.PAGE_START);

        Buttons leftButtons = new Buttons();
        add(leftButtons, BorderLayout.LINE_START);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JTextArea(5,20), BorderLayout.CENTER);


//        SwitchPanelHandling sw = new SwitchPanelHandling();
//        CardLayout cardLayout = new CardLayout();
//        cardLayout.setHgap(10);
//        cardLayout.setVgap(10);
//        JPanel hidePanelField = new JPanel();
//        hidePanelField.setLayout(cardLayout);
//        hidePanelField.add(sw.myInformation());
//        this.studentList.loadFromDatabase(connection);
//        // assign value
//        hidePanelField.add(sw.myInformation(), "link#1");
//        hidePanelField.add(sw.addStudentPanel(studentList, connection), "link#2");
//        hidePanelField.add(sw.updateStudentPanel(studentList, connection), "link#3");
//        hidePanelField.add(sw.deleteStudentPanel(studentList, connection), "link#4");
//
//        hidePanelField.add(sw.viewStudentList(studentList), "link#5");
//        hidePanelField.add(sw.importFromCSV(studentList), "link#6");
//        hidePanelField.add(sw.exportToCSV(studentList), "link#7");
//        // change panel when click specified button
//        leftButtons.getMyInfo().addActionListener(e -> cardLayout.show(hidePanelField, "link#1"));
//        leftButtons.getAddStudent().addActionListener(e -> cardLayout.show(hidePanelField,"link#2"));
//        leftButtons.getUpdateStudent().addActionListener(e -> cardLayout.show(hidePanelField, "link#3"));
//        leftButtons.getDeleteStudent().addActionListener(e -> cardLayout.show(hidePanelField, "link#4"));
//        leftButtons.getViewStudentList().addActionListener(e -> cardLayout.show(hidePanelField, "link#5"));
//        leftButtons.getImportFromCSV().addActionListener(e -> cardLayout.show(hidePanelField, "link#6"));
//        leftButtons.getExportToCSV().addActionListener(e -> cardLayout.show(hidePanelField, "link#7"));
//        leftButtons.getSaveStudentDB().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    studentList.saveToDB(connection);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Student list is saved to database successfully!!!");
//            }
//        });
//        leftButtons.getExitBtn().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(1);
//            }
//        });
//        contentPanel.add(hidePanelField, BorderLayout.CENTER);
//        add(contentPanel, BorderLayout.CENTER);
    }
}
