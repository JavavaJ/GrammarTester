/** The class Question is a class which contains String questionPart,
 * String optionA, String optionB, String optionC, String optionD
 * of a multiple choice question.
 */


package read;

import java.io.Serializable;

public class Question implements Serializable {
	private int id;
	private String questionPart;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
    private String rightAns;

	/** The Question(String text) constructor is actually is a parser
     * of a text of a multiple choice test question of format
     * "id. A) ..... B) ...... C) ..... D) ...... .
     */    
    public Question(String text) {
		// index of the first dot character "." in the text
		int dotIndex = text.indexOf(".");		

		
		// the id of a question is one or two characters before '.'
		int id = Integer.parseInt(text.substring(0, dotIndex));
		setId(id);

		/* next delimiter is "A) ". Note that we need only 
		* text of A option without "A) " that's why we
		* will later add 3
        */
		int optionAIndex = text.indexOf("A) ");

		// The text between ". " dotIndex + one_space and optionAIndex
		// is actually questionPart
		String questionPart = text.substring(dotIndex + 2, optionAIndex);
		setQuestionPart(questionPart);

		int optionBIndex = text.indexOf("B) ");
		int optionCIndex = text.indexOf("C) ");
		int optionDIndex = text.indexOf("D) ");

		String optionA = text.substring(optionAIndex + 3, optionBIndex);
		setOptionA(optionA);

		String optionB = text.substring(optionBIndex + 3, optionCIndex);
		setOptionB(optionB);

		String optionC = text.substring(optionCIndex + 3, optionDIndex);
		setOptionC(optionC);

		String optionD = text.substring(optionDIndex +3);
		setOptionD(optionD);

	}

	public int getId() {
		return id;
	}

	public String getQuestionPart() {
		return questionPart;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}
    
    public String getRightAns() {
        return rightAns;
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestionPart(String questionPart) {
		this.questionPart = questionPart;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}	
    
    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }

}