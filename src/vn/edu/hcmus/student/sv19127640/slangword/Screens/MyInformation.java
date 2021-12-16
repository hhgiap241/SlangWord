package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import javax.swing.*;
import java.awt.*;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 9:58 PM
 * Description: ...
 */
public class MyInformation {
    private JPanel infoPanel;
    public MyInformation(){
        infoPanel = new JPanel(new GridBagLayout());
        JLabel overview = new JLabel("My student information", SwingConstants.CENTER);
        overview.setFont(overview.getFont().deriveFont (50.0f));
        overview.setForeground(Color.blue);
        JLabel id = new JLabel("ID:");
        id.setFont(id.getFont().deriveFont (26.0f));
        JLabel name = new JLabel("Name:");
        name.setFont(name.getFont().deriveFont (26.0f));
        JLabel classs = new JLabel("Class:");
        classs.setFont(classs.getFont().deriveFont (26.0f));
        JLabel lecturers = new JLabel("Lecturers:");
        lecturers.setFont(lecturers.getFont().deriveFont (26.0f));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(id, gbc);
        gbc.gridy = 2;
        infoPanel.add(name, gbc);
        gbc.gridy = 3;
        infoPanel.add(classs, gbc);
        gbc.gridy = 4;
        infoPanel.add(lecturers, gbc);
        JLabel id_1 = new JLabel("19127640");
        id_1.setFont(id_1.getFont().deriveFont (26.0f));
        JLabel name_1 = new JLabel("Hoàng Hữu Giáp");
        name_1.setFont(name_1.getFont().deriveFont (26.0f));
        JLabel classs_1 = new JLabel("Introduction to Java");
        classs_1.setFont(classs_1.getFont().deriveFont (26.0f));
        JLabel lecturers_1 = new JLabel("Nguyễn Văn Khiết,");
        lecturers_1.setFont(lecturers_1.getFont().deriveFont (26.0f));
        JLabel lecturers_2 = new JLabel("Nguyễn Đức Huy");
        lecturers_2.setFont(lecturers_2.getFont().deriveFont (26.0f));
        gbc.gridy = 1;
        gbc.gridx = 1;
        infoPanel.add(id_1, gbc);
        gbc.gridy = 2;
        infoPanel.add(name_1, gbc);
        gbc.gridy = 3;
        infoPanel.add(classs_1, gbc);
        gbc.gridy = 4;
        infoPanel.add(lecturers_1, gbc);
        gbc.gridy = 5;
        infoPanel.add(lecturers_2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.add(overview, gbc);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }
}
