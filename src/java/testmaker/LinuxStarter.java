package java.testmaker;

import javafx.application.Platform;
import javafx.stage.Stage;

public class LinuxStarter {

    public static void main(String[] args) {
        // the following snippet is a way to call another Applicaton from the 
        // current one. But it passes a reference of the current Stage, so the
        // Stage doesn't actually change.
        Platform.runLater(new Runnable() {
           @Override
           public void run() {
               // TODO customize JavaFXGrammarGUI class to make it reusable and
               // capable of working accepting a reference to List<Question>
               new TestMaker().start(new Stage());
           } 
        });
    }

}