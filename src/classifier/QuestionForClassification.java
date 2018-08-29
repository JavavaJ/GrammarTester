/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.HashSet;
import java.util.Set;
import read.DelimiterCase;
import read.Question;
import tagger.TagType;

/**
 *
 * @author ALEXXX
 */
public class QuestionForClassification extends Question {
    
    
    
    private Set<TagType> possibleTagTypes; // set to hold values of possible
    // TagType's when the Question is analyzed by classifier
    
    // instance initialization block to initialize HashSet
    {
        possibleTagTypes = new HashSet<>();
    }
    
    public QuestionForClassification() {
        super();
    }
    
    public QuestionForClassification(String text) {
        super(text);
    }
    
    public QuestionForClassification(String text, DelimiterCase delimiterCase) {
        super(text, delimiterCase);
    }
    
    public QuestionForClassification(Question question) {
        super();
        this.id = question.getId();
        this.questionPart = question.getQuestionPart();
        this.optionA = question.getOptionA();
        this.optionB = question.getOptionB();
        this.optionC = question.getOptionC();
        this.optionD = question.getOptionD();
        this.rightAns = question.getRightAns();
        this.tags = question.getTags();
        
    }
    
    public Set<TagType> getPossibleTagTypes() {
        return possibleTagTypes;    
    }
    
    public void setPossibleTagType(TagType tagType) {
        possibleTagTypes.add(tagType);
    }
    
    public void printAllPossibleTagTypes() {
        for (TagType tag : possibleTagTypes) {
            System.out.println(tag.toString());
        }
    }
    
    public boolean isAllPossibleTagTypesSetEmpty() {
        return possibleTagTypes.isEmpty();
    }
    
}
