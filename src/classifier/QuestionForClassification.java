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
        
    }
    
    public Set<TagType> getPossibleTagTypes() {
        return possibleTagTypes;    
    }
    
}
