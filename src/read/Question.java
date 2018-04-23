/** The class Question is a class which contains String questionPart,
 * String optionA, String optionB, String optionC, String optionD
 * of a multiple choice question.
 */


/* TODO move all trim() statements to setOption[ABCD] method

*/

package read;

import java.io.Serializable;
import static testmaker.Constants.IS_DEBUGGING_MODE;

public class Question implements Serializable {
	private int id;
	private String questionPart;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
    private String rightAns;
    private String tags;
    
    public Question() {
        // do nothing and let all the field values be default values
    }

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
		* will later add (3) 2(!!!!!) because somethimes there is no space between
        * "A)" and option A.
        */
		int optionAIndex = text.indexOf("A)");

		// The text between ". " dotIndex + one_space and optionAIndex
		// is actually questionPart
		String questionPart = text.substring(dotIndex + 1, optionAIndex);
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
    
    
    /** The Question(String text) constructor is actually is a parser
     * of a text of a multiple choice test question of format
     * "id. A) ..... B) ...... C) ..... D) ...... or 
     * "id. a) ..... b) ...... c) ..... d) ...... . The second parameter is 
     * an enum indicating a question opition's delimiter's case.
     * @param text plain text of a question to parse
     * @param delimiterCase upper or lower case of a)b)c)d) or A)B)C)D)
     */    
    public Question(String text, DelimiterCase delimiterCase) {
		// index of the first dot character "." in the text
		int dotIndex = text.indexOf(".");		

		
		// the id of a question is one or two characters before '.'
		int id = Integer.parseInt(text.substring(0, dotIndex));
		setId(id);

		
        int optionAIndex = 0;
        String questionPart;
        int optionBIndex = 0;
        int optionCIndex = 0;
        int optionDIndex = 0;
        
        if (delimiterCase == DelimiterCase.UPPERCASE) {
            
            /* next delimiter is "A) " or a). Note that we need only 
            * text of A option without "A) " that's why we
            * will later add 3
            */
            optionAIndex = text.indexOf("A) ");

            // The text between ". " dotIndex + one_space and optionAIndex
            // is actually questionPart
            questionPart = text.substring(dotIndex + 2, optionAIndex);
            setQuestionPart(questionPart);

            optionBIndex = text.indexOf("B) ");
            optionCIndex = text.indexOf("C) ");
            optionDIndex = text.indexOf("D) ");
            
        }
        
        if (delimiterCase == DelimiterCase.LOWERCASE) {
            
            /* next delimiter is "A) " or a). Note that we need only 
            * text of A option without "A) " that's why we
            * will later add 3
            */
            optionAIndex = text.indexOf("a) ");

            // The text between ". " dotIndex + one_space and optionAIndex
            // is actually questionPart
            questionPart = text.substring(dotIndex + 2, optionAIndex).trim();
            setQuestionPart(questionPart);

            optionBIndex = text.indexOf("b) ");
            optionCIndex = text.indexOf("c) ");
            optionDIndex = text.indexOf("d) ");
            
        }
        
        if (IS_DEBUGGING_MODE) {
            System.out.println("optionAIndex: " + optionAIndex);
            System.out.println("optionBIndex: " + optionBIndex);
        }
        
		String optionA = text.substring(optionAIndex + 3, optionBIndex);     // error out of bound occurs here!         
        
		setOptionA(optionA.trim());               

		String optionB = text.substring(optionBIndex + 3, optionCIndex);
		setOptionB(optionB.trim());

		String optionC = text.substring(optionCIndex + 3, optionDIndex);
		setOptionC(optionC.trim());

		String optionD = text.substring(optionDIndex +3);
		setOptionD(optionD.trim());

	}
    

	/** Returns an id integer of a question which is non-zero based 
     * ordinal number of question.
     * @return id integer of a question which is non-zero based 
     * ordinal number of question
     */
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
    
    public String getTags() {
        return tags;
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
		this.optionC = optionC.trim();
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}	
    
    /** Sets the right answer for a Question object.
     * 
     * @param rightAns a String (a, b, c, d) which represent a right answer 
     * of a Question
     */
    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public void printQuestion() {
        System.out.println("" + id + ". " + questionPart + "\n A) " 
                + optionA + "\n B) " + optionB + "\n C) " 
                + optionC + "\n D) " + optionD + "\n right) " + rightAns); 
    }

}