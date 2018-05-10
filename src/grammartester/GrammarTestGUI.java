/* The GUI is supposed to be a version of a GUI for
    grammar tests. Multiple choice questions.
*/

package grammartester;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class GrammarTestGUI {
    private JFrame mainFrame;
    private JButton submitButton;
    private ButtonGroup buttonGroup;
    private JPanel firstPanel;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JLabel questionLabel;
    private JPanel buttonPanel;
    private Container controlPanel;
    private CardLayout cardlayout;
    private Container cardPanel;
    private JRadioButton radA;
    private JRadioButton radB;
    private JRadioButton radC;
    private JRadioButton radD;
    private String questionText;
    private String optionAText;
    private String optionBText;
    private String optionCText;
    private String optionDText;
    private Map<String, String> firstQMap;
    private List<Map<String, String>> allQs;
    private int testSize;
    private int countQ;
    private int countRightA;
    private int score;


    GrammarTestGUI() {
        // set the number of right answers to zero
        countRightA = 0;
        countQ = 1;
        allQs = getAllQs();
        // number of quiz questions
        testSize = allQs.size();
        firstQMap = getFirstMap(firstQMap);
        String id = firstQMap.get("id");
        String questionText = firstQMap.get("question");
        String a = firstQMap.get("a");
        String b = firstQMap.get("b");
        String c = firstQMap.get("c");
        String d = firstQMap.get("d");
        prepareGUI(id, questionText, a, b, c, d);
    } // end of first constructor

    public GrammarTestGUI( String id,
                    String questionText,
                    String optionAText,
                    String optionBText,
                    String optionCText,
                    String optionDText) {
        prepareGUI(id, questionText, optionAText, optionBText,
                optionCText, optionDText);

    }

    public static void main(String[] args) {
        GrammarTestGUI grammarTestGUI = new GrammarTestGUI();
    }

    public void prepareGUI(String id,
                           String questionText,
                           String optionAText,
                           String optionBText,
                           String optionCText,
                           String optionDText) {
        mainFrame = new JFrame();
        mainFrame.setSize(600, 500);

        firstPanel = new JPanel();
        firstPanel.setLayout(new GridLayout(3, 1));

        String labelText = "<html>" + id + ". " + questionText + "</html>";
        questionLabel = new JLabel(labelText, JLabel.CENTER);
        questionLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
        questionLabel.setFont(questionLabel.getFont().deriveFont(20.0f));

        // code for radio button group
        optionAText = "A) " + optionAText;
        optionBText = "B) " + optionBText;
        optionCText = "C) " + optionCText;
        optionDText = "D) " + optionDText;

        radA = new JRadioButton(optionAText, true);
        radB = new JRadioButton(optionBText);
        radC = new JRadioButton(optionCText);
        radD = new JRadioButton(optionDText);

        radA.setActionCommand("a");
        radB.setActionCommand("b");
        radC.setActionCommand("c");
        radD.setActionCommand("d");

        buttonGroup = new ButtonGroup();

        buttonGroup.add(radA);
        buttonGroup.add(radB);
        buttonGroup.add(radC);
        buttonGroup.add(radD);

        controlPanel = new JPanel();

        GroupLayout grouplayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(grouplayout);

        grouplayout.setAutoCreateGaps(true);
        grouplayout.setAutoCreateContainerGaps(true);

        grouplayout.setHorizontalGroup(grouplayout.createSequentialGroup()
            .addGroup(grouplayout.createParallelGroup()
                .addComponent(radA)
                .addComponent(radB)
                .addComponent(radC)
                .addComponent(radD))
        );

        grouplayout.setVerticalGroup(grouplayout.createSequentialGroup()
            .addGroup(grouplayout.createSequentialGroup()
                .addComponent(radA)
                .addComponent(radB)
                .addComponent(radC)
                .addComponent(radD))
        );


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(90, 30));
        submitButton.addActionListener(new SubmitListener());

        buttonPanel.add(submitButton);


        firstPanel.add(questionLabel);
        firstPanel.add(controlPanel);
        firstPanel.add(buttonPanel);

        resultPanel = new JPanel();
        resultLabel = new JLabel();

        resultPanel.add(resultLabel);

        cardlayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardlayout);

        cardPanel.add("firstPanel", firstPanel);
        cardPanel.add("resultPanel", resultPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(cardPanel);

        // set GUI on a needed location on a screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        mainFrame.setLocation(screenWidth*1/5, screenHeight*1/10);

        mainFrame.show();

    } // end of prepareGUI()

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            countScore();

            if (countQ < testSize) {
                // countQ is ordinal number of a question to show
                countQ++;
                setAllTexts();
            } else {
                String resultText = "Your Score: " + score;
                resultLabel.setText(resultText);
                resultLabel.setFont(resultLabel.getFont().deriveFont(50.0f));
                cardlayout.next(cardPanel);
            }


        }

    }

    // TODO to change the code to get only first map out of allQs
    public Map<String, String> getFirstMap(Map<String, String> firstQMap) {
        firstQMap = allQs.get(0);
        return firstQMap;

    }

    public List<Map<String, String>> getAllQs() {
        
        // number of ids in my SQL database
        int numOfIds = 69; // the request "SELECT COUNT(*) FROM test7" is better here
        
        // create a list of maps for all the Qs in my database
        List<Map<String, String>> allQs = new ArrayList<>();
        
        // inititalize the class SQLTest where there is a code to
        // fetch the question from SQL DataBase
        SQLReader sqlReader = new SQLReader();
        for (int i = 1; i <= numOfIds; i++) {
            // makeQuery(i) takes i as an argument which means
            // that it returns a map with the question
            // number i in the database
            allQs.add(sqlReader.makeQuery(i));
            System.out.println("Query #:" + i + " is successful!");
        }
        return allQs;
    }

    // the method reads values of radio buttons, compares them to
    // the right answers, and keeps track of a score
    public void countScore() {
        // read value from radio buttons
        String selection = buttonGroup.getSelection().getActionCommand();
        System.out.println("Selection: " + selection);

        String right = allQs.get(countQ - 1).get("right");
        System.out.println("Right: " + right);
        System.out.println(selection.equals(right));
        if (selection.equals(right)) {
            countRightA++;
        }
        score = (100*countRightA/countQ);
        System.out.println("Score: " + score);
    } // end of countScore

    public void setAllTexts() {
        Map<String, String> tempMap = allQs.get(countQ - 1);
        String labelText = "<html>" + tempMap.get("id") + ". " +
               tempMap.get("question") + "</html>";
        questionLabel.setText(labelText);
        String a = "A) " + tempMap.get("a");
        String b = "B) " + tempMap.get("b");
        String c = "C) " + tempMap.get("c");
        String d = "D) " + tempMap.get("d");

        radA.setText(a);
        radB.setText(b);
        radC.setText(c);
        radD.setText(d);

    } // end of setAllTexts


    /*
    public void setTexts(Map<String, String> firstQMap) {
        questionText = firstQMap.get("question");
        // code for radio button group
        optionAText = "A) " + firstQMap.get("a");
        optionBText = "B) " + firstQMap.get("b");
        optionCText = "C) " + firstQMap.get("c");
        optionDText = "D) " + firstQMap.get("d");


    }
    */



} // end of class