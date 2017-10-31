/* The GUI is supposed to be a version of a GUI for
	grammar tests. Multiple choice questions.
*/

package grammartester;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

class GrammarTestGUI {
    private JFrame mainFrame;
	private JButton submitButton;
	private ButtonGroup buttonGroup;
	private JPanel firstPanel;
	private JLabel questionLabel;
	private JPanel buttonPanel;
	private Container controlPanel;
    private String questionText;
    private String optionAText;
    private String optionBText;
    private String optionCText;
    private String optionDText;
    private Map<String, String> firstQMap;
   
    
    GrammarTestGUI() {
    	firstQMap = getFirstMap(firstQMap);
        prepareGUI();
    }
    
     public static void main(String[] args) {
        GrammarTestGUI grammarTestGUI = new GrammarTestGUI();
    }

    public void prepareGUI() {
    	mainFrame = new JFrame();
    	mainFrame.setSize(600, 500);

    	firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(3, 1));

		questionText = firstQMap.get("question");
		questionLabel = new JLabel(questionText, JLabel.CENTER);
		questionLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
		questionLabel.setFont(questionLabel.getFont().deriveFont(20.0f));

		// code for radio button group
		optionAText = "A) " + firstQMap.get("a");
        optionBText = "B) " + firstQMap.get("b");
        optionCText = "C) " + firstQMap.get("c");
        optionDText = "D) " + firstQMap.get("d");

		JRadioButton radA = new JRadioButton(optionAText, true);
		JRadioButton radB = new JRadioButton(optionBText);
		JRadioButton radC = new JRadioButton(optionCText);
		JRadioButton radD = new JRadioButton(optionDText);

		radA.setActionCommand("Option A");
        radB.setActionCommand("Option B");
        radC.setActionCommand("Option C");
        radC.setActionCommand("Option D");

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

        buttonPanel.add(submitButton);


        firstPanel.add(questionLabel);
        firstPanel.add(controlPanel);
        firstPanel.add(buttonPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(firstPanel);
        
        // set GUI on a needed location on a screen 
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		mainFrame.setLocation(screenWidth*1/5, screenHeight*1/10);
        
        mainFrame.show();

    } // end of prepareGUI()

    public Map<String, String> getFirstMap(Map<String, String> firstQMap) {
    	// inititalize the class SQLTest where there is a code to
    	// fetch the question from SQL DataBase
    	SQLTest testSQL = new SQLTest();

    	// makeQuery(1) takes 1 as an argument which means 
    	// that it returns a map with the question 
    	// number 1 in the database
        firstQMap = testSQL.makeQuery(1);
        return firstQMap;
        
    }
     
     
     
     
}