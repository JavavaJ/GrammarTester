package logic.testmaker;

import java.util.Properties;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

public class TestMakerInitializer {

    public static void main(String[] args) {
        
        // set -Dprism.order=sw command to VM
        Properties systemProperties = System.getProperties();
        // systemProperties.setProperty("prism.order", "sw");
        
        // to check
        System.out.println("Outside JavaFX prism.order=" + System.getProperty("prism.order"));
        
        // just to call JavaFX environment
        JFXPanel pppanel = new JFXPanel();
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new TestMaker().start(new Stage());
            }
        });
    }

}