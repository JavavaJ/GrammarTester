/** The class is a test of a file chooser in JavaFX
 * 
 */

package java.testmaker;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FileChooserOpenDB extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    File selectedFile;
    
    
    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        
        // set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("db files (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(extFilter);
        
        selectedFile = fileChooser.showOpenDialog(primaryStage);
        // System.out.println(selectedFile.toString());
    }
    
    public File getSelectedFile() {
        return selectedFile;
    }
    
    
}