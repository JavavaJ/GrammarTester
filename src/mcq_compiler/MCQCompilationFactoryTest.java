/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcq_compiler;

import java.util.List;
import read.Question;
import tagger.A1_LEVEL;
import tagger.TagType;

/**
 *
 * @author ALEXXX
 */
public class MCQCompilationFactoryTest {
    
    public static void main(String[] args) {
        TagType toBeTagType = A1_LEVEL.TO_BE;
        System.out.println(MCQCompilationFactory.getNumOfRows(toBeTagType));
        
        
        List<Question> qList = MCQCompilationFactory.getSpecifiedQList(toBeTagType);
        
        /*
        List<Question> qList = MCQCompilationFactory.getSpecifiedQList(null);
        */
        
        for (Question question : qList) {
            question.printQuestion();
        }
        
    }
    
}
