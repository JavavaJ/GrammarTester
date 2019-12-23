/* The code updates the newly added column "right" answers 
*/


package logic.grammartester;

import logic.utils.ReadFileAsString;

import java.util.HashMap;
import java.util.Map;

import logic.database.SQLUpdateColumn;

class RightAnswersUpdate {
    public static void main(String[] args) {
        // the following two method just read a txt file
        String myFile = "C:\\Users\\ALEXXX\\Documents\\NetBeansProjects\\GrammarTester\\test7_keys.txt";
        String myText = ReadFileAsString.read(myFile);
        
        // create a map of right answers
        Map<Integer, String> rightMap = new HashMap<>();
        
        int qNum = 1;
        int index = 0;
        // index -q means that parser reached the end of string
        while (index != -1) {
            String qNumStr = String.valueOf(qNum);
            index = myText.indexOf(qNumStr, index);     
            
            // these two line are fragile and might break if there is 
            // another txt file
            // TODO implements the same functionality with regualar expressions
            if (qNum < 10) {
                rightMap.put(qNum, myText.substring(index+2, index+3).toLowerCase());
            } else {
                if (index == -1) {
                    break;
                } else {
                    rightMap.put(qNum, myText.substring(index+3, index+4).toLowerCase());
                    index++;
                }
            }            
                
            qNum++;
        }
        
        
        
        
        SQLUpdateColumn updater = new SQLUpdateColumn();
        // and finally update the column with right answers        
        for (int i = 1; i <= rightMap.size(); i++) {
            updater.updateColumn(i, rightMap.get(i));
            
        } // end of loop
        
    }
}