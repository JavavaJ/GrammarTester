

package testmaker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import question.Question;

/** The class is a GUI which firstly asks a user to input a plain text of multiple
 * choice questions, displays that text as a table and asks a user to input 
 * right answers of those questions. At the end you can conduct a self-checking
 * test based on input information.
 * 
 * @author ALEXXX
 */
public class TestMaker extends Application {
    Stage stage;
    TextArea textArea;
    Scene textAreaScene;
    Scene tableScene;
    Scene rightAnsScene;
    TextArea rightAnsArea;    
    List<Question> allQuestions;
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override 
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        // --------- textAreaScene --------------------
        
        textArea = new TextArea();
        textArea.setPrefSize(500, 400);
        textArea.setWrapText(true);
        textArea.setFocusTraversable(false); // unfocuses the textarea to display prompt text
        
        String promptTextAreaText = "Copy your plain text multiple choice questions here!";
        textArea.setPromptText(promptTextAreaText);
        
        Button buttonShowAsTable = new Button("Show as Table");
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
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(textAreaScene);
        primaryStage.show();
    }
    
    public void click_buttonShowAsTable() {
        allQuestions = Arrays.asList(QuestionUtil.parseTextToQsArray(textArea.getText()));
        
        setTableScene();
                
        stage.setScene(tableScene);        
        
        // make stage appear on the left of the screen
        stage.setX(10);
        stage.setY(10);
    }
    
    public void click_buttonAddRightAns() {
        
        rightAnsArea = new TextArea();
        rightAnsArea.setPrefSize(500, 400);
        rightAnsArea.setWrapText(true);
        rightAnsArea.setFocusTraversable(false);
        
        String promptRightAnsAreaText = "Copy your plain text right answers here!";
        rightAnsArea.setPromptText(promptRightAnsAreaText);
        
        
        Button buttonShowAsTable = new Button("Show as Table");
        buttonShowAsTable.setOnAction(e -> click_buttonShowAsTableWithAns());  
        
        VBox pane = new VBox(rightAnsArea, buttonShowAsTable);
        pane.setPadding(new Insets(10));
        pane.setSpacing(10);
        
        rightAnsScene = new Scene(pane);
        stage.setScene(rightAnsScene);
        
    }
    
    public void setTableScene() {
        
        // ------------- GUI Scene code ----------------
        
        ObservableList<Question>allQuesntionsObs = FXCollections.observableArrayList(allQuestions);
        String tableName = "Random Test";
        
        Label labelHeading = new Label(tableName);
        labelHeading.setFont(new Font("Arial", 20));
        
        TableView<Question> table = new TableView<>();
        table.setItems(allQuesntionsObs);
        
        TableColumn<Question, Integer> colId = new TableColumn<>("Id");
        colId.setPrefWidth(30);
        colId.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Id"));
        
        TableColumn<Question, String> colQuestionPart = new TableColumn<>("Question Part");
        colQuestionPart.setPrefWidth(300);
        colQuestionPart.setCellValueFactory(new PropertyValueFactory<>("QuestionPart"));
        
        TableColumn<Question, String> colOptionA = new TableColumn<>("Option A");
        colOptionA.setPrefWidth(100);
        colOptionA.setCellValueFactory(new PropertyValueFactory<>("OptionA"));
        
        TableColumn<Question, String> colOptionB = new TableColumn<>("Option B");
        colOptionB.setPrefWidth(100);
        colOptionB.setCellValueFactory(new PropertyValueFactory<>("OptionB"));
        
        TableColumn<Question, String> colOptionC = new TableColumn<>("Option C");
        colOptionC.setPrefWidth(100);
        colOptionC.setCellValueFactory(new PropertyValueFactory<>("OptionC"));
        
        TableColumn<Question, String> colOptionD = new TableColumn<>("Option D");
        colOptionD.setPrefWidth(100);
        colOptionD.setCellValueFactory(new PropertyValueFactory<>("OptionD"));
        
        TableColumn<Question, String> colRightAns = new TableColumn<>("Right Answer");
        colRightAns.setPrefWidth(100);
        colRightAns.setCellValueFactory(new PropertyValueFactory<>("RightAns"));
        
        TableColumn<Question, String> colTags = new TableColumn<>("Tags");
        colTags.setPrefWidth(100);
        colTags.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        
        table.getColumns().addAll(colId, 
                colQuestionPart, 
                colOptionA, 
                colOptionB, 
                colOptionC, 
                colOptionD, 
                colRightAns, 
                colTags);
        table.setPrefHeight(300);
        
        Button buttonAddRightAns = new Button("Add Right Answers");
        buttonAddRightAns.setOnAction(e -> click_buttonAddRightAns()); 
        Tooltip tooltipAddRightAnswers = new Tooltip("Add keys, right answers as a plain text");
        tooltipAddRightAnswers.setStyle("-fx-font-size: 15");
        buttonAddRightAns.setTooltip(tooltipAddRightAnswers);
        
        Button buttonSave = new Button("Save");
        buttonSave.setOnAction( e -> click_buttonSave());
        
        Button buttonConduct = new Button("Conduct Test");
        buttonConduct.setOnAction(e -> click_buttonConduct());
        Tooltip tooltipConduct = new Tooltip("Conduct a self-checking test!");
        tooltipConduct.setStyle("-fx-font-size: 15");
        buttonConduct.setTooltip(tooltipConduct);
        
        HBox buttonPane = new HBox(buttonAddRightAns, buttonSave, buttonConduct);
        buttonPane.setSpacing(10);
        
        VBox tablePane = new VBox();
        tablePane.setSpacing(10);
        tablePane.setPadding(new Insets(10));
        tablePane.getChildren().addAll(labelHeading, table, buttonPane);  
        
        tableScene = new Scene(tablePane, 1000, 500);
    }
    
    public void click_buttonShowAsTableWithAns() {
        // get text from rightAns textarea and update out allQuestions list
        updateRightAnswers(rightAnsArea.getText());
        
    }
    
    public void click_buttonSave() {
        NewOrOldFileMessageBox.show();        
        String chosenPath = NewOrOldFileMessageBox.getFilePath();
        System.out.println("The chosen path inside testmaker: " + chosenPath);
        TableNameMessageBox.show();
        String chosenTableName = TableNameMessageBox.getTableName();
        System.out.println("The chosen table name inside testmaker: " + chosenTableName);
        DataBasesUtil.createDB(chosenPath, chosenTableName);
        DataBasesUtil.storeValues(allQuestions, chosenPath, chosenTableName);
        System.out.println("Table in Database is created!");
    }
    
    public void updateRightAnswers(String plainRightAns) {        
        // create a map of right answers with key Integer Q num and value 
        // String its right answer
        Map<Integer, String> rightMap = new HashMap<>();
        
        int qNum = 1;
        int index = 0;
        // index -q means that parser reached the end of string
        while (index != -1) {
            String qNumStr = String.valueOf(qNum);
            index = plainRightAns.indexOf(qNumStr, index);
            
            // these two line are fragile and might break if there is 
            // another txt file
            // TODO implements the same functionality with regualar expressions
            if (qNum < 10) {
                rightMap.put(qNum, plainRightAns.substring(index+2, index+3).toLowerCase());
            } else {
                if (index == -1) {
                    break;
                } else {
                    rightMap.put(qNum, plainRightAns.substring(index+3, index+4).toLowerCase());
                    index++;
                }
            }                            
            qNum++;
        }
        
        // update right answers in Question list
        for (Question question : allQuestions) {
            // the line gets a right answer from a map based on a current 
            // question's id and sets it as a right answer
            question.setRightAns(rightMap.get( question.getId()));
            
            setTableScene();
                
            stage.setScene(tableScene);        

            // make stage appear on the left of the screen
            stage.setX(10);
            stage.setY(10);
        }        
    }
    
    public void click_buttonConduct() {
        
        // the following snippet is a way to call another Applicaton from the 
        // current one. But it passes a reference of the current Stage, so the
        // Stage doesn't actually change.
        Platform.runLater(new Runnable() {
           @Override
           public void run() {
               // TODO customize JavaFXGrammarGUI class to make it reusable and
               // capable of working accepting a reference to List<Question>
               new GrammarGUI(allQuestions).start(stage);
           } 
        });
        
        
    }
}