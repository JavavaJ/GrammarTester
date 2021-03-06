/** The class is a GUI Table.
 * 
 */

package tagger;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import question.Question;

public class TestTable {
    public static void show(List<Question> allQuestions) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        
        ObservableList<Question>allQuesntionsObs = FXCollections.observableArrayList(allQuestions);
        
        Label labelHeading = new Label("Test 7");
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
        
        VBox mainPane = new VBox();
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
        mainPane.getChildren().addAll(labelHeading, table);
        
        Scene scene = new Scene(mainPane, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test 7");
        primaryStage.showAndWait();
    }
}