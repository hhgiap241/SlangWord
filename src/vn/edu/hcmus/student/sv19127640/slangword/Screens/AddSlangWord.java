package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import javax.swing.*;
import java.awt.*;


/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:50 PM
 * Description: add slang word panel
 */
public class AddSlangWord extends JPanel {
    private JLabel slangLable;
    private JLabel meaningsLable;
    private JTextField slangText;
    private JTextArea meaningsText;
    private JLabel header;
    AddSlangWord(){
        JPanel panel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel();
        header = new JLabel("ADD NEW SLANG WORD", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (20.0f));
        header.setForeground(Color.blue);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        slangLable = new JLabel("Input slang word:");
        contentPanel.add(slangLable, gbc);
        gbc.gridy = 2;
        meaningsLable = new JLabel("Input meanings:");
        contentPanel.add(meaningsLable, gbc);


        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        slangText = new JTextField(10);
        contentPanel.add(slangText, gbc);
        gbc.gridy = 2;
        meaningsText = new JTextArea(10, 20);
        contentPanel.add(meaningsText, gbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(header, gbc);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel footerContent = new JPanel();
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.X_AXIS));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        footerContent.add(ok);
        footerContent.add(Box.createRigidArea(new Dimension(20,0)));
        footerContent.add(cancel);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(footerContent);

        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
