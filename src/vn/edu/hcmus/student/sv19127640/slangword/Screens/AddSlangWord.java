package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import vn.edu.hcmus.student.sv19127640.slangword.SlangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/15/2021 - 8:50 PM
 * Description: add slang word panel
 */
public class AddSlangWord implements ActionListener {
    /**
     * attributes
     */
    private JPanel addPanel;
    private JLabel header;
    private JPanel footerPanel;
    private JLabel slagLabel;
    private JTextArea slagText;
    private JLabel meaningsLabel;
    private JTextArea meaningsText;
    private JLabel attentionLable;
    private JButton submitButton;
    private JButton cancelButton;
    private SlangWord slangWord;

    /**
     * constructor with parameter
     * @param slangWord SlangWord
     */
    public AddSlangWord(SlangWord slangWord){
        this.slangWord = slangWord;
        addPanel = new JPanel();
        footerPanel = new JPanel();
        slagLabel = new JLabel();
        slagText = new JTextArea(3,20);
        meaningsLabel = new JLabel();
        meaningsText = new JTextArea(10,20);
        attentionLable = new JLabel();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
    }

    /**
     * set up panel
     * @return JPanel
     */
    public JPanel setUPGUI(){
        addPanel.setLayout(new GridBagLayout());
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
        slagLabel.setText("Input new slag word:");
        addPanel.add(slagLabel, gbc);
        gbc.gridy = 2;
        addPanel.add(slagText, gbc);

        gbc.gridy = 3;
        meaningsLabel.setText("Input meanings:");
        addPanel.add(meaningsLabel, gbc);
        gbc.gridy = 4;
        attentionLable.setText("*You can add multiple meanings, each meaning must be seperated by character |");
        attentionLable.setFont(attentionLable.getFont().deriveFont(Font.ITALIC, 11.0f));
        attentionLable.setForeground(Color.red);
        addPanel.add(attentionLable, gbc);

        gbc.gridy = 5;
        addPanel.add(meaningsText, gbc);

        gbc.gridy = 6;
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.PAGE_AXIS));
        JPanel footerContent = new JPanel();
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.X_AXIS));
        footerContent.add(submitButton);
        footerContent.add(Box.createRigidArea(new Dimension(20,0)));
        footerContent.add(cancelButton);
        footerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(footerContent);
        addPanel.add(footerPanel, gbc);
        return addPanel;
    }

    /**
     * button click handling
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            String slag = slagText.getText();
            String meanings = meaningsText.getText();
            if (slag.length() == 0 || meanings.length() == 0){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input. Please input again!");
            }else{
                // true is exist, false is not
                if (!slangWord.isExistedSlag(slag)){
                    // not exist => add new
                    slangWord.addNewSlangWord(slag, meanings);
                }else{
                    // exist => check add overwrite or duplicate
                    // reference at: https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
                    Object[] options = { "Add duplicate", "Add overwrite" };
                    int select = JOptionPane.showOptionDialog(JOptionPane.getRootFrame(), "This slag word is already exist! Please choose your option", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (select == 0){
                        slangWord.addDuplicateSlangWord(slag, meanings);
                    }else{
                        slangWord.addOverwriteSlangWord(slag, meanings);
                    }
                }
                slangWord.saveToFile();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Add new word successfully!!!");
                slagText.setText("");
                meaningsText.setText("");
            }
        }else if (e.getSource() == cancelButton){
            slagText.setText("");
            meaningsText.setText("");
        }
    }
}
