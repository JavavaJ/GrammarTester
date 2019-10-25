package mcq_compiler;

import read.Question;
import java.util.List;

public class TestReadAllMCQs {

    public static void main(String[] args) {
        List<Question> questions = MCQCompilationFactory.readAllQuestions();
        System.out.println("Number of MCQs: " + questions.size());
        questions.forEach(q -> q.printQuestion());
    }

}
