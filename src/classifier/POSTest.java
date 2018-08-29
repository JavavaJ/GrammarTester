/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import tagger.A1_LEVEL;
import tagger.A2_LEVEL;
import tagger.TagType;

/**
 *
 * @author ALEXXX
 */
public class POSTest {
    static InputStream inputStream;
    static POSModel model;
    static POSTaggerME tagger;
    
    static Map<String, TagType> nlpTagMaps = new HashMap<>();
    static {
        nlpTagMaps.put("IN", A1_LEVEL.PREPOSITIONS);
        nlpTagMaps.put("JJ", A2_LEVEL.ADJECTIVES_ED_ING);
        nlpTagMaps.put("JJR", A2_LEVEL.COMPARATIVES);
        nlpTagMaps.put("JJS", A2_LEVEL.COMPARATIVES);
        nlpTagMaps.put("RB", A2_LEVEL.ADVERBS);
        nlpTagMaps.put("RBR", A2_LEVEL.COMPARATIVES);
        nlpTagMaps.put("RBS", A2_LEVEL.COMPARATIVES);
        
    }
        
    
    static {
        String pathToProject = new File("").getAbsolutePath();
        String pathToFile = "\\libs\\en-pos-maxent.bin";
        String fullPathToFile = pathToProject + pathToFile;
        try {
            
            inputStream = new FileInputStream(fullPathToFile);
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
        
        String oneString = inStr + " " + atStr + " " + asteriskStr + " " + slashedStr + " ";
        
        /*
        analyzeWord(atStr);
        analyzeWord(inStr);
        analyzeWord(asteriskStr);
        analyzeWord(slashedStr);
        */
        
        analyzeWord(oneString);
        
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
    
    
    
    public static TagType interpretNLPTag(String str) {
        return nlpTagMaps.get(str);
    }
    /** The method takes a word as a parameter and 
     * prints which part of speech it belongs to. 
     * @param word
     * @throws IOException 
     */
    public static void analyzeWord(String word) throws IOException {
        
        // divide a string into separate words
        String dividedByTokens = "";
        // get rid of " -/*" symbols in options of a question
        StringTokenizer st = new StringTokenizer(word, " /-*");
        while (st.hasMoreTokens()) {
            dividedByTokens += st.nextToken() + " ";
        }
        dividedByTokens = dividedByTokens.trim();
        
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        
        String[] tokensWord = tokenizer.tokenize(dividedByTokens);       
        
        String[] tagsWord = tagger.tag(tokensWord);
        
        POSSample sampleWord = new POSSample(tokensWord, tagsWord);
        
        List<String> allSampleWords = new ArrayList<>();
        String sampleWordString = sampleWord.toString();
        
        StringTokenizer sTokenizer = new StringTokenizer(sampleWordString, " ");
        while (sTokenizer.hasMoreTokens()) {
            allSampleWords.add(sTokenizer.nextToken());
        }
        
        
        Set<TagType> tagsSet = new HashSet<>();
        for (String sampleSeparateWord : allSampleWords) {
            tagsSet.add(interpretNLPTag(getNLPTag(sampleSeparateWord)));
        }
        
        for (TagType tag : tagsSet) {
            if (tag != null) {
                System.out.println(tag.toString());
            }
        }
        
        /*
        System.out.println(sampleWord.toString());
        System.out.println(getNLPTag(sampleWord.toString()));
        System.out.println(interpretNLPTag(getNLPTag(sampleWord.toString())));
        System.out.println(""); 
        */
    }
}
