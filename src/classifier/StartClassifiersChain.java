/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import static classifier.POSTest.inputStream;
import static classifier.POSTest.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import mcq_compiler.MCQCompilationFactory;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import read.Question;
import tagger.A1_LEVEL;
import tagger.A2_LEVEL;
import tagger.TagType;

/**
 *
 * @author ALEXXX
 */
public class StartClassifiersChain {

    public static void main(String[] args) throws IOException {
        StartClassifiersChain obj = new StartClassifiersChain();
        obj.start();
    }

    public void start() throws IOException {
        /*
        TagType prepositionTagType = A1_LEVEL.PREPOSITIONS;

        // get the list of question with tag PREPOSITIONS
        List<Question> qList = MCQCompilationFactory.getSpecifiedQList(prepositionTagType);
        */
        
        TagType comparativeTagType = A2_LEVEL.COMPARATIVES;
        // get the list of questions with tag COMPARATIVES
        List<Question> qListComparatives = 
                MCQCompilationFactory.getSpecifiedQList(comparativeTagType);
        
        // print the first Question in this list
        // qList.get(0).printQuestion();
        // get the first Question in the list and instantiate QuestionForClassification from it
        QuestionForClassification currentClassQuestion = new QuestionForClassification(qListComparatives.get(0));

        // concatenate all the options of the Question into one string
        String allQuestionsOptionString = currentClassQuestion.getOptionA() + " "
                + currentClassQuestion.getOptionB() + " "
                + currentClassQuestion.getOptionC() + " "
                + currentClassQuestion.getOptionD();

        StringBuilder sbDividedByTokens = new StringBuilder();
        // divide a string into separate words
        // get rid of " -/*" symbols in options of a question
        StringTokenizer st = new StringTokenizer(allQuestionsOptionString, " /-*");
        while (st.hasMoreTokens()) {
            sbDividedByTokens.append(st.nextToken()).append(" ");
        }
        String dividedByTokens = sbDividedByTokens.toString();
        dividedByTokens = dividedByTokens.trim();

        // initialize some classes from openNLP 
        InputStream inputStream;
        POSModel model;
        POSTaggerME tagger;
        
        String fullPathToFile = new File("").getAbsolutePath() + "\\libs\\en-pos-maxent.bin";

        inputStream = new FileInputStream(fullPathToFile);
        model = new POSModel(inputStream);
        tagger = new POSTaggerME(model);


            
        
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;        
        String[] tokensWord = tokenizer.tokenize(dividedByTokens);              
        String[] tagsWord = tagger.tag(tokensWord);        
        POSSample sampleWord = new POSSample(tokensWord, tagsWord);
        
        // add all the separate sample words which is a big text into ArrayList
        List<String> allSampleWords = new ArrayList<>();
        String sampleWordString = sampleWord.toString();
        
        StringTokenizer sTokenizer = new StringTokenizer(sampleWordString, " ");
        while (sTokenizer.hasMoreTokens()) {
            allSampleWords.add(sTokenizer.nextToken());
        }
        
        // create a set and add there all the unique NLP tags
        Set<String> allNLPTags = new HashSet<>();
        for (String separateSampleWord : allSampleWords) {
            allNLPTags.add(getNLPTag(separateSampleWord));
        }
        
        
        // finally some code for creating Chain of Responsiblity
        AbstractClassifier comparativeClassifier = new ComparativeClassifier();
        comparativeClassifier.classify(currentClassQuestion, allNLPTags);
        
        currentClassQuestion.printAllPossibleTagTypes();
             
    }
    
    public String getNLPTag(String str) {
        int posUnderscore = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                posUnderscore = i;
                break;
            }
        }
        String tag = str.substring(posUnderscore + 1);
        
        return tag;
    }

}
