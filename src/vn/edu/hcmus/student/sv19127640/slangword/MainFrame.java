package vn.edu.hcmus.student.sv19127640.slangword;

import vn.edu.hcmus.student.sv19127640.slangword.Screens.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.student.sv19127640.slangword
 * Created by ADMIN
 * Date 12/15/2021 - 8:44 PM
 * Description: main frame for the java app
 */
public class MainFrame extends JPanel{
    private SlangWord slangWord;
    MainFrame(){
        slangWord = new SlangWord();
        setUpGUI();
    }
    public void showGUI(){
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setOpaque(true);
        frame.setContentPane(this);
        frame.setMinimumSize(new Dimension(1000, 500));
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

        MyInformation myInformation = new MyInformation();
        SearchWord searchWord = new SearchWord();
        AddSlangWord addSlangWord = new AddSlangWord();
        History history = new History();
        RandomWord randomWord = new RandomWord();


        CardLayout cardLayout = new CardLayout();
        cardLayout.setHgap(10);
        cardLayout.setVgap(10);
        JPanel hidePanelField = new JPanel();
        hidePanelField.setLayout(cardLayout);
        hidePanelField.add(myInformation.getInfoPanel());
        hidePanelField.add(searchWord.getSearchPanel());
        hidePanelField.add(addSlangWord.getAddPanel());
        hidePanelField.add(history.getHistoryPanel());
        JPanel randomWordPanel = randomWord.setUPPanel(slangWord);
        hidePanelField.add(randomWordPanel);

//        // assign value
        hidePanelField.add(myInformation.getInfoPanel(), "link#1");
        hidePanelField.add(searchWord.getSearchPanel(), "link#2");
        hidePanelField.add(addSlangWord.getAddPanel(), "link#3");
        hidePanelField.add(history.getHistoryPanel(), "link#4");
        hidePanelField.add(randomWordPanel, "link#5");
        // change panel when click specified button
        leftButtons.getMyInfoButton().addActionListener(e -> cardLayout.show(hidePanelField, "link#1"));
        leftButtons.getSearchButton().addActionListener(e -> cardLayout.show(hidePanelField, "link#2"));
        leftButtons.getAddButton().addActionListener(e -> cardLayout.show(hidePanelField, "link#3"));
        leftButtons.getHistoryButton().addActionListener(e -> cardLayout.show(hidePanelField, "link#4"));
        leftButtons.getRandomWordButton().addActionListener(e->cardLayout.show(hidePanelField, "link#5"));


        leftButtons.getExitBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Do you really want to close program?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (select == 0) // if yes
                    System.exit(1);
            }
        });
        contentPanel.add(hidePanelField, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }
}
