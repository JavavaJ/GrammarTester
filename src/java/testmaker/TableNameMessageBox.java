package java.testmaker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class TableNameMessageBox  {
    public static String tableName;
    static Stage stage;
    static TextField textField;
    
    public static void show() {
        String title = "Write Table Name";
        String message = "Please, write the name of the table you want to save";
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);  
        
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        
        textField = new TextField();
        
        
        Button buttonOk = new Button();
        buttonOk.setText("OK");
        buttonOk.setOnAction(e -> click_buttonOk());
        
        HBox inputPane = new HBox(textField, buttonOk);
        inputPane.setPadding(new Insets(5));
        inputPane.setSpacing(10);
        
        
        VBox pane = new VBox(20);
        pane.getChildren().addAll(label, inputPane);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public static void setTableName(String name) {
        tableName = name;
    }
    
    public static void click_buttonOk() {
        setTableName(textField.getText());        
        stage.close();
    }
    
    public static String getTableName() {
        return tableName;
    }
    
}