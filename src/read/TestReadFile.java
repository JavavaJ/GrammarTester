package read;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TestReadFile {
    
    public static void main(String[] args) {
        String myFile = "C:\\Users\\ALEXXX\\Documents\\NetBeansProjects\\GrammarTester\\testmaster.txt";
        String myText = ReadFile.read(myFile);       
        
        
        Map<String, Integer> mapOfIndices = new HashMap<>();
        mapOfIndices = questionNumIndex(myText);
        
        String[] questions = new String[mapOfIndices.size()];
        
        System.out.println();
        
        for (int i = 0; i < mapOfIndices.size(); i++) {
            // System.out.println("i: " + i);
            
            String indexStr = String.valueOf(i+1);
            // System.out.println("indexStr: " + indexStr);
            String nextIndexStr = String.valueOf(i+2);
            // System.out.println("nextIndexStr: " + nextIndexStr);
            if (mapOfIndices.get(nextIndexStr) != null) {
                questions[i] = myText.substring(mapOfIndices.get(indexStr), 
                                                mapOfIndices.get(nextIndexStr));
            } else {
                questions[i] = myText.substring(mapOfIndices.get(indexStr));
            }     
            
            // System.out.println("questions[" + i + "]: " + questions[i]);
        }
        
        System.out.println(Arrays.toString(questions));
        
        
               
                    
            
        
    }
    
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