/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classifier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

/**
 *
 * @author ALEXXX
 */
public class POSTest {
    static InputStream inputStream;
    static POSModel model;
    static POSTaggerME tagger;
    
    static Map<String, String> nlpTagMaps = new HashMap<>();
    static {
        nlpTagMaps.put("IN", "Preposition");
        nlpTagMaps.put("JJ", "Adjective");
        nlpTagMaps.put("JJR", "Adjective comparative");
        nlpTagMaps.put("JJS", "Adj superlative");
        nlpTagMaps.put("RB", "Adverb");
        nlpTagMaps.put("RBR", "Adverb Compar");
        nlpTagMaps.put("RBS", "Adverb Superlative");
        
    }
        
    
    static {
        try {
            inputStream = new FileInputStream("D:\\Soft\\Java Libraries\\OpenNLP Models\\en-pos-maxent.bin");
            model = new POSModel(inputStream);
            tagger = new POSTaggerME(model);            
        } catch (IOException ioException) {
            ioException.printStackTrace();            
        }        
    }
    
    public static void main(String[] args) throws IOException {
        String baseAdj = "old";
        String comparAdj = "older";
        String superlatAdj = "oldest";
        String strThan = "than";
        String strThen = "then";
        
        String inStr = "in";
        String atStr = "at";
        String asteriskStr = "*";
        String slashedStr = "in / at";
        
        analyzeWord(atStr);
        analyzeWord(inStr);
        analyzeWord(asteriskStr);
        analyzeWord(slashedStr);
        
        /*
        analyzeWord(baseAdj);
        analyzeWord(comparAdj);
        analyzeWord(superlatAdj);
        analyzeWord(strThan);
        analyzeWord(strThen);        
        */
        
        
        
    }
    
    public static String getNLPTag(String str) {
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
    
    public static String interpretNLPTag(String str) {
        String tagInterprepation = "";
        switch (str) {
            case "JJ":
                tagInterprepation = "Adjective";
                break;
            case "JJR":
                tagInterprepation = "Adjective comparative";
                break;
            case "JJS":
                tagInterprepation = "Adj superlative";
                break;
            case "RB":
                tagInterprepation = "Adverb";
                break;
            case "RBR":
                tagInterprepation = "Adverb Compar";
                break;
            case "RBS":
                tagInterprepation = "Adverb Superlative";
                break;
        }
        return tagInterprepation;
    }
    
    public static String construeNLPTag(String str) {
        return nlpTagMaps.get(str);
    }
    
    public static void analyzeWord(String word) throws IOException {
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        
        String[] tokensWord = tokenizer.tokenize(word);       
        
        String[] tagsWord = tagger.tag(tokensWord);
        
        POSSample sampleWord = new POSSample(tokensWord, tagsWord);
        
        System.out.println(sampleWord.toString());
        System.out.println(getNLPTag(sampleWord.toString()));
        System.out.println(construeNLPTag(getNLPTag(sampleWord.toString())));
        System.out.println("");        
    }
}
