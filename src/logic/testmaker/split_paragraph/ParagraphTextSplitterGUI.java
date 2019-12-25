package logic.testmaker.split_paragraph;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ParagraphTextSplitterGUI extends Application {

    private Stage stage;
    private TextArea textArea;
    private Scene textAreaScene;
    private Scene tableScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        textArea = new TextArea();
        textArea.setPrefSize(500, 400);
        textArea.setWrapText(true);
        textArea.setFocusTraversable(false); // unfocuses the textarea to display prompt text

        String promptTextAreaText = "Copy your plain text multiple choice questions here!";
        textArea.setPromptText(promptTextAreaText);

        Button buttonShowAsTable = new Button("Operate");
        buttonShowAsTable.setOnAction(e -> click_buttonShowAsTable());
        Tooltip tooltipShowAsTable = new Tooltip("Parse text and show as a table");
        tooltipShowAsTable.setStyle("-fx-font-size: 15");
        buttonShowAsTable.setTooltip(tooltipShowAsTable);

        HBox paneButtonChoice = new HBox(buttonShowAsTable);
        paneButtonChoice.setSpacing(10);

        VBox pane = new VBox(textArea, paneButtonChoice);
        pane.setPadding(new Insets(10));
        pane.setSpacing(10);


        textAreaScene = new Scene(pane);

        // -------------- tableScene ----------------


        String iconPath = "icon_test.jpg";

        // setting icon for a window
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(textAreaScene);
        primaryStage.show();
    }

    public void click_buttonShowAsTable() {
        String text = textArea.getText();
        String formatedParagraph = Util.getFormatedParagraph(text);
        textArea.setText(formatedParagraph);
    }

}
