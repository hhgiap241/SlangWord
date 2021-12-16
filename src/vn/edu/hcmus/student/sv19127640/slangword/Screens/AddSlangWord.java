package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import javax.swing.*;
import java.awt.*;


/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:50 PM
 * Description: add slang word panel
 */
public class AddSlangWord {
    private JPanel addPanel;
    private JLabel header;
    private JPanel footerPanel;
    private JLabel slagLabel;
    private JTextArea slagText;
    private JLabel meaningsLabel;
    private JTextArea meaningsText;
    private JLabel attentionLable;

    public JLabel getAttentionLable() {
        return attentionLable;
    }

    public AddSlangWord(){
        addPanel = new JPanel(new GridBagLayout());
        header = new JLabel("ADD NEW SLANG WORD", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (20.0f));
        header.setForeground(Color.blue);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        addPanel.add(header, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        slagLabel = new JLabel("Input new slag word:");
        addPanel.add(slagLabel, gbc);
        gbc.gridy = 2;
        slagText = new JTextArea(3,20);
        addPanel.add(slagText, gbc);

        gbc.gridy = 3;
        meaningsLabel = new JLabel("Input meanings:");
        addPanel.add(meaningsLabel, gbc);
        gbc.gridy = 4;
        attentionLable = new JLabel("*You can add multiple meanings, each meaning must be seperated by character |");
        attentionLable.setFont(attentionLable.getFont().deriveFont(Font.ITALIC, 11.0f));
        attentionLable.setForeground(Color.red);
        addPanel.add(attentionLable, gbc);

        gbc.gridy = 5;
        meaningsText = new JTextArea(10,20);
        addPanel.add(meaningsText, gbc);

        gbc.gridy = 6;
        footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.PAGE_AXIS));
        JPanel footerContent = new JPanel();
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.X_AXIS));
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        footerContent.add(submit);
        footerContent.add(Box.createRigidArea(new Dimension(20,0)));
        footerContent.add(cancel);
        footerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(footerContent);
        addPanel.add(footerPanel, gbc);
    }

    public JPanel getAddPanel() {
        return addPanel;
    }

    public JLabel getHeader() {
        return header;
    }

    public JPanel getFooterPanel() {
        return footerPanel;
    }

    public JLabel getSlagLabel() {
        return slagLabel;
    }

    public JTextArea getSlagText() {
        return slagText;
    }

    public JLabel getMeaningsLabel() {
        return meaningsLabel;
    }

    public JTextArea getMeaningsText() {
        return meaningsText;
    }
}
