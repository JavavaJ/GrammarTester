package read;


import java.util.HashMap;
import java.util.Map;
import grammartester.GrammarTestGUI;
import question.Question;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class TestReadFile {
    
    public static void main(String[] args) {
        String myFile = "C:\\Users\\ALEXXX\\Documents\\NetBeansProjects\\GrammarTester\\testmaster.txt";
        String myText = ReadFile.read(myFile);       
        
        
        Map<String, Integer> mapOfIndices = new HashMap<>();
        mapOfIndices = questionNumIndex(myText);
        
        String[] questions = new String[mapOfIndices.size()];
        
                
        for (int i = 0; i < mapOfIndices.size(); i++) {           
            
            String indexStr = String.valueOf(i+1);
            
            String nextIndexStr = String.valueOf(i+2);            
            if (mapOfIndices.get(nextIndexStr) != null) {
                questions[i] = myText.substring(mapOfIndices.get(indexStr), 
                                                mapOfIndices.get(nextIndexStr));
            } else {
                questions[i] = myText.substring(mapOfIndices.get(indexStr));
            }                
            
        }
        
        // System.out.println(Arrays.toString(questions));
        
        Question q = new Question(questions[15]);
        System.out.println("getQuestionPart: " + q.getQuestionPart());
        System.out.println("Option A: " + q.getOptionA());
        
        GrammarTestGUI gui = new GrammarTestGUI(String.valueOf(q.getId()),
                                                q.getQuestionPart(),
                                                q.getOptionA(), 
                                                q.getOptionB(), 
                                                q.getOptionC(), 
                                                q.getOptionD()
        );
        
        List<Question> allQuestions = new ArrayList<>();
        for (int i =0; i < questions.length; i++) {
            allQuestions.add(new Question(questions[i]));
            System.out.println("Added # " + i);
        }
        
        try {
            FileOutputStream fos = new FileOutputStream("questions.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allQuestions);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        
        
               
                    
            
        
    }
    
    // the method returns a map of indices of occurences
    // of question numbers in the text
    public static Map<String, Integer> questionNumIndex(String str) {
        Map<String, Integer> mapIndex = new HashMap<String, Integer>();
        
        int qNum = 1;
        int index = 0;
        while (index != -1) {
            String qNumStr = String.valueOf(qNum);
            index = str.indexOf(qNumStr, index);
            
            qNum++;
            if (index == -1) {
                break;
            } else {
                mapIndex.put(qNumStr, index);
                index++;
            }
        }        
        return mapIndex;        
    }
    
    
    
}