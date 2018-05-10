package read;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

class DeserTest {
    public static void main(String[] args) {
        List<Question> allQuestions;
        try {
            ObjectInputStream ois =
                new ObjectInputStream( new FileInputStream("questions.ser"));
            Object one = ois.readObject();
            allQuestions = (List<Question>) one;
            System.out.println(allQuestions.get(1).getOptionA());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        
        
    }
}