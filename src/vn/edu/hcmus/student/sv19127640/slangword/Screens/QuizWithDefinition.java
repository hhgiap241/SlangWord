package vn.edu.hcmus.student.sv19127640.slangword.Screens;

import vn.edu.hcmus.student.sv19127640.slangword.RandomNumber;
import vn.edu.hcmus.student.sv19127640.slangword.SlangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.student.sv19127640.slangword.Screens
 * Created by ADMIN
 * Date 12/17/2021 - 8:58 PM
 * Description: quiz with definition panel
 */
public class QuizWithDefinition implements ActionListener {
    /**
     * attributes
     */
    private JPanel quizPanel1;
    private JLabel question, answer;
    private JButton answerA, answerB, answerC, answerD;
    private JButton nextButton;
    private int quesNumber, randomNum;
    private String[] quiz;
    private SlangWord slangWord;
    private RandomNumber randomNumber;

    /**
     * constructor with parameter
     * @param slangWord SlangWord
     */
    public QuizWithDefinition(SlangWord slangWord){
        this.slangWord = slangWord;
        quizPanel1 = new JPanel();
        question = new JLabel();
        answer = new JLabel();
        answerA = new JButton();
        answerB = new JButton();
        answerC = new JButton();
        answerD = new JButton();
        nextButton = new JButton();
        quesNumber = 1;
        randomNumber = new RandomNumber();
    }

    /**
     * set up panel
     * @return JPanel
     */
    public JPanel setUPPanel(){
        quizPanel1.setLayout(new BorderLayout());
        JLabel header = new JLabel("Quiz with Definition", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont (40.0f));
        header.setForeground(Color.blue);

        JPanel questionPanel = new JPanel(new BorderLayout());
        randomNum = randomNumber.random(1, slangWord.getSize() - 3);
        quiz = slangWord.quizWithDefinition(randomNum);
        for(String s: quiz){
            System.out.println(s);
        }
        question.setText("Question " + quesNumber + " : " + "What is the slang word of " + quiz[0] + "?");
        question.setFont(question.getFont().deriveFont (20.0f));


        JPanel answerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        answerA.setText(quiz[1]);
        answerA.addActionListener(this);
        answerA.setPreferredSize(new Dimension(40, 80));
        answerPanel.add(answerA, gbc);
        gbc.gridx = 1;
        answerB.setText(quiz[2]);
        answerB.addActionListener(this);
        answerB.setPreferredSize(new Dimension(40, 80));
        answerPanel.add(answerB, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        answerC.setText(quiz[3]);
        answerC.addActionListener(this);
        answerC.setPreferredSize(new Dimension(40, 80));
        answerPanel.add(answerC, gbc);
        gbc.gridx = 1;
        answerD.setText(quiz[4]);
        answerD.addActionListener(this);
        answerD.setPreferredSize(new Dimension(40, 80));
        answerPanel.add(answerD, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        answer.setText("Choose your answer: ");
        answer.setFont(answer.getFont().deriveFont (15.0f));
        answerPanel.add(answer, gbc);

        nextButton.setText("Next");
        nextButton.setEnabled(false);
        nextButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel footerContent = new JPanel();
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.X_AXIS));
        footerContent.add(nextButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(footerContent);

        questionPanel.add(question, BorderLayout.PAGE_START);
        questionPanel.add(answerPanel, BorderLayout.CENTER);
        questionPanel.add(buttonPanel, BorderLayout.PAGE_END);

        quizPanel1.add(header, BorderLayout.PAGE_START);
        quizPanel1.add(questionPanel, BorderLayout.CENTER);

        return quizPanel1;
    }

    /**
     * button click handling
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == answerA) {
            if (answerA.getText().equals(quiz[1])) {
                answerA.setBackground(Color.green);
                answerB.setEnabled(false);
                answerC.setEnabled(false);
                answerD.setEnabled(false);
                nextButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Correct");
            }else{
                answerA.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "False");
            }
        } else if (e.getSource() == answerB) {
            if (answerB.getText().equals(quiz[1])) {
                answerB.setBackground(Color.green);
                answerA.setEnabled(false);
                answerC.setEnabled(false);
                answerD.setEnabled(false);
                nextButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Correct");
            }else{
                answerB.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "False");
            }
        } else if (e.getSource() == answerC) {
            if (answerC.getText().equals(quiz[1])) {
                answerC.setBackground(Color.green);
                answerA.setEnabled(false);
                answerB.setEnabled(false);
                answerD.setEnabled(false);
                nextButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Correct");
            }else{
                answerC.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "False");
            }
        } else if (e.getSource() == answerD) {
            if (answerD.getText().equals(quiz[1])) {
                answerD.setBackground(Color.green);
                answerA.setEnabled(false);
                answerB.setEnabled(false);
                answerC.setEnabled(false);
                nextButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Correct");
            }else{
                answerD.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "False");
            }
        } else {
            answerA.setEnabled(true);
            answerA.setBackground(null);
            answerB.setEnabled(true);
            answerB.setBackground(null);
            answerC.setEnabled(true);
            answerC.setBackground(null);
            answerD.setEnabled(true);
            answerD.setBackground(null);
            randomNum = randomNumber.random(1, slangWord.getSize() - 3);
            quiz = slangWord.quizWithDefinition(randomNum);
            ++quesNumber;
            question.setText("Question " + quesNumber + " : " + "What is the slang word of " + quiz[0] + "?");
            // generate number from [1; 4]
            int randNum = randomNumber.random(1, 4);
            System.out.println(randNum);
            if (randNum == 1) {
                answerA.setText(quiz[1]);
                answerB.setText(quiz[2]);
                answerC.setText(quiz[3]);
                answerD.setText(quiz[4]);
            } else if (randNum == 2) {
                answerA.setText(quiz[2]);
                answerB.setText(quiz[1]);
                answerC.setText(quiz[3]);
                answerD.setText(quiz[4]);
            } else if (randNum == 3) {
                answerA.setText(quiz[2]);
                answerB.setText(quiz[3]);
                answerC.setText(quiz[1]);
                answerD.setText(quiz[4]);
            } else {
                answerA.setText(quiz[2]);
                answerB.setText(quiz[3]);
                answerC.setText(quiz[4]);
                answerD.setText(quiz[1]);
            }
            nextButton.setEnabled(false);
        }
    }
}
