/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.Set;
import tagger.A2_LEVEL;

/**
 *
 * @author ALEXXX
 */
public class ComparativeClassifier extends AbstractClassifier {
    
    @Override
    public void classify(QuestionForClassification qToClassify, Set<String> allNLPTags) {
        for (String tag : allNLPTags) {
            switch (tag) {
                case "JJR":
                    qToClassify.setPossibleTagType(A2_LEVEL.COMPARATIVES);
                    break;
                case "JJS":
                    qToClassify.setPossibleTagType(A2_LEVEL.COMPARATIVES);
                    break;
                case "RBR":
                    qToClassify.setPossibleTagType(A2_LEVEL.COMPARATIVES);
                    break;
                case "RBS":
                    qToClassify.setPossibleTagType(A2_LEVEL.COMPARATIVES);
                    break;
            }
        }
    }
    
    
    
}
