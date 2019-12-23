package java.testmaker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox  {
    public static void show(String message, String title) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);  
        
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        
        Button buttonOk = new Button();
        buttonOk.setText("OK");
        buttonOk.setOnAction(e -> stage.close() );
        
        VBox pane = new VBox(20);
        pane.getChildren().addAll(label, buttonOk);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
}