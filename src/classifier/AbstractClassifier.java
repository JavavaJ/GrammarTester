/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.Set;

/**
 *
 * @author ALEXXX
 */
public abstract class AbstractClassifier {
    // next element in a chain
    protected AbstractClassifier nextClassifier;
    
    public void setNextClassifier(AbstractClassifier nextClassifier) {
        this.nextClassifier = nextClassifier;
    }
    
    public abstract void classify(QuestionForClassification classQuestion, Set<String> allNLPTags);
    
}
