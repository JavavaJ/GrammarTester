package testmaker;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MenuBarSketch extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        menuBar.getMenus().add(fileMenu);
        
        MenuItem openMenuItem = new MenuItem("_Open test");     
        openMenuItem.setOnAction( e -> click_openMenuItem());
        fileMenu.getItems().add(openMenuItem);
        
        MenuItem saveDB = new MenuItem("_Save");
        fileMenu.getItems().add(saveDB);
        
        MenuItem parseTextItem = new MenuItem("Parse text");
        fileMenu.getItems().add(parseTextItem);        
        
        RadioMenuItem pdfExportItem = new RadioMenuItem("PDF");
        RadioMenuItem docxExportItem = new RadioMenuItem("DOCX");
        
        ToggleGroup exportGroup = new ToggleGroup();
        
        pdfExportItem.setToggleGroup(exportGroup);
        docxExportItem.setToggleGroup(exportGroup);
        
        Menu exportMenu = new Menu("_Export");
        exportMenu.getItems().addAll(pdfExportItem, docxExportItem);
        
        fileMenu.getItems().add(exportMenu);
        
        Menu editMenu = new Menu("_Edit");
        menuBar.getMenus().add(editMenu);
        
        MenuItem editTableItem = new MenuItem("Edit Table");
        editMenu.getItems().add(editTableItem);
        
        Menu viewMenu = new Menu("_View");
        menuBar.getMenus().add(viewMenu);
        
        MenuItem showTableItem = new MenuItem("Show as Table");
        viewMenu.getItems().add(showTableItem);
        
        Menu toolsMenu = new Menu("_Tools");
        menuBar.getMenus().add(toolsMenu);
        
        MenuItem taggerItem = new MenuItem("Tagger");
        toolsMenu.getItems().add(taggerItem);
        
        MenuItem testCompilerItem = new MenuItem("Compile test");
        toolsMenu.getItems().add(testCompilerItem);
        
        Menu helpMenu = new Menu("_Help");
        menuBar.getMenus().add(helpMenu);
        
        MenuItem aboutItem = new MenuItem("_About");
        helpMenu.getItems().add(aboutItem);
        
        // menuBar.setStyle("-fx-background-color: lightgray");
        
        
        HBox pane = new HBox(menuBar);
        pane.setPadding(new Insets(5));
        
        Scene scene = new Scene(pane, 400, 200);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public void click_openMenuItem() {
        System.out.println("Open button is clicked...");
        
        Platform.runLater(new Runnable() {
            public void run() {
                FileChooserOpenDB filechooser = new FileChooserOpenDB();
                filechooser.start(new Stage());
                
                System.out.println("You are trying to open (absolute path) : " 
                        + filechooser.getSelectedFile().getAbsolutePath());
                String selectedFile = filechooser.getSelectedFile().getAbsolutePath();
                stage.close();
            }
        });    
    }
}