package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import vn.edu.hcmus.student.sv19127640.slangword.RandomNumber;
import vn.edu.hcmus.student.sv19127640.slangword.SlangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/16/2021 - 8:22 PM
 * Description: random word panel
 */
public class RandomWord {
    private JPanel randomWordPanel;
    private JLabel header;
    private JLabel slagLable;
    private JLabel slagMean;
    private JLabel title;
    private JLabel description;
    private JButton nextBtn;
    private ArrayList<String> result;
    private RandomNumber randomNumber;

    public RandomWord(){
        randomWordPanel = new JPanel();
        header = new JLabel("ON THIS DAY SLANG WORD", SwingConstants.CENTER);
        slagLable = new JLabel();
        slagMean = new JLabel();
        title = new JLabel();
        description = new JLabel();
        nextBtn = new JButton();
        result = new ArrayList<>();
        randomNumber = new RandomNumber();
    }
    public JPanel setUPPanel(SlangWord slangWord){

        randomWordPanel.setLayout(new BorderLayout());
        header.setFont(header.getFont().deriveFont (40.0f));
        header.setForeground(Color.blue);

        JPanel smallPanel = new JPanel();


        smallPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        int randNum = randomNumber.random(0, slangWord.getSize() - 1);
        result = slangWord.randomSlangWord(randNum);
        System.out.println(result);
        JPanel titlePanel = new JPanel(new FlowLayout());
        title.setText("TODAY SLANG WORD IS ");
        title.setFont(title.getFont().deriveFont (35.0f));

        int slagWordIndex = result.size() - 1;
        slagLable.setText(result.get(slagWordIndex).toUpperCase());
        slagLable.setFont(slagLable.getFont().deriveFont (35.0f));
        slagLable.setForeground(Color.red);

        titlePanel.add(title);
        titlePanel.add(slagLable);

        smallPanel.add(titlePanel, gbc);
        gbc.gridy = 1;
        description.setText("THIS SLANG WORD HAS " + slagWordIndex + " MEANINGS:");
        description.setFont(description.getFont().deriveFont (28.0f));
        smallPanel.add(description, gbc);
        gbc.gridy = 2;
        slagMean.setText(slangWord.concateMeanings(result));
        slagMean.setFont(slagMean.getFont().deriveFont (25.0f));
        smallPanel.add(slagMean, gbc);


        nextBtn.setText("Next");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel footerContent = new JPanel();
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.X_AXIS));
        footerContent.add(nextBtn);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(footerContent);

        randomWordPanel.add(header, BorderLayout.PAGE_START);
        randomWordPanel.add(smallPanel, BorderLayout.CENTER);
        randomWordPanel.add(buttonPanel, BorderLayout.PAGE_END);

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int randNum = randomNumber.random(0, slangWord.getSize() - 1);
                result = slangWord.randomSlangWord(randNum);
                System.out.println(result);
                int slagWordIndex = result.size() - 1;
                description.setText("THIS SLANG WORD HAS " + slagWordIndex + " MEANINGS:");
                slagLable.setText(result.get(slagWordIndex));
                slagMean.setText(slangWord.concateMeanings(result));
            }
        });
        return randomWordPanel;
    }
}
