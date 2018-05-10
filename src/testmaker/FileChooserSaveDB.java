package testmaker;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserSaveDB extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    File file;
    
    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        
        // set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("db files (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // show save file dialog
        file = fileChooser.showSaveDialog(primaryStage);
        
        if (file != null) {
            System.out.println("Create file with name: " + file);
        }
        
        
    }
    
    public File getSelectedFile() {
        return file;
    }
    
}