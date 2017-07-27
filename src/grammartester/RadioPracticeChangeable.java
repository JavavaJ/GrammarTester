package grammartester;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioPracticeChangeable implements ActionListener {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private JButton submitButton;
    private ButtonGroup group;
    private JRadioButton radA;
    private JRadioButton radB;
    private JRadioButton radC;
    private JRadioButton radD;
    private StartPage startPage;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel mainPanel;

    RadioPracticeChangeable() {
        prepareGUI();
    }
    
    // inner class
    private class StartPage extends JPanel implements ActionListener {
        private JButton startButton;
        
        StartPage() {
            prepareGUI();
        }
    
        public void prepareGUI() {
            startButton = new JButton("Start Test");
            startButton.setPreferredSize(new Dimension(90, 30));
            startButton.addActionListener(this);
            
            this.add(BorderLayout.CENTER, startButton);
        }
        
        public void actionPerformed(ActionEvent event) {
            cardLayout.next(cardPanel);
            
        }
        
    } // end of inner class

    public static void main(String[] args) {
        RadioPracticeChangeable radioPracticeChangeable = new RadioPracticeChangeable();
        radioPracticeChangeable.setGrammarQuestion("1) I ___ breakfast at 8.", "gave", "had", "took", "slept");
    }



    public void prepareGUI() {
        mainFrame = new JFrame("Radio Button Practice");
        mainFrame.setSize(350, 350);
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("Test Practice", JLabel.CENTER);        

        controlPanel = new JPanel();
        
        // create some left side padding of 10 pixels
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        // place the option answers vertically
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        radA = new JRadioButton("A", true);
        radB = new JRadioButton("B");
        radC = new JRadioButton("C");
        radD = new JRadioButton("D");        

        radA.setActionCommand("Option A");
        radB.setActionCommand("Option B");
        radC.setActionCommand("Option C");
        radD.setActionCommand("Option D");        
        

        group = new ButtonGroup();

        group.add(radA);
        group.add(radB);
        group.add(radC);
        group.add(radD);      
        

        controlPanel.add(radA);
        controlPanel.add(radB);
        controlPanel.add(radC);
        controlPanel.add(radD);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(90, 30));
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        mainPanel.add(headerLabel);
        mainPanel.add(controlPanel);
        mainPanel.add(buttonPanel);
        
        startPage = new StartPage();
        
        cardPanel.add("startPage", startPage);
        cardPanel.add("mainPanel", mainPanel);
        
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void actionPerformed(ActionEvent event) {
        String selection = this.group.getSelection().getActionCommand();
        System.out.println(selection);
        setGrammarQuestion("2) I ___ 18.", "is", "am", "are", "don't");
    }

    // the method sets the text for headerLabel and radio buttons
    // TODO add a question number
    public void setGrammarQuestion
    (String question, String optionA, String optionB, String optionC, String optionD) 
    {
        headerLabel.setText(question);
        radA.setText("A) " + optionA);
        radB.setText("B) " + optionB);
        radC.setText("C) " + optionC);
        radD.setText("D) " + optionD);
    }
    
}