package logic.tagger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.database.topics.TopicEntity;
import logic.database.topics.TopicsDBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagCreationStage extends Application {

    Stage stage;
    Map<String, TextField> name2fieldMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        List<VBox> verticalPanes = new ArrayList<>();

        Text levelText = new Text("Level: ");
        TextField levelField = new TextField();
        name2fieldMap.put("levelField", levelField);
        VBox levelBox = new VBox(levelText, levelField);
        verticalPanes.add(levelBox);

        Text topicFullText = new Text("Full topic: ");
        TextField fullField = new TextField();
        name2fieldMap.put("fullField", fullField);
        VBox fullBox = new VBox(topicFullText, fullField);
        verticalPanes.add(fullBox);

        Text topicTagText = new Text("Topic tag: ");
        TextField tagField = new TextField();
        name2fieldMap.put("tagField", tagField);
        VBox tagBox = new VBox(topicTagText, tagField);
        verticalPanes.add(tagBox);

        Button saveButton = new Button("Save");
        saveButton.setFont(new Font(20));
        saveButton.setTooltip(new Tooltip("Save entry"));
        saveButton.setOnAction(e -> click_saveButton());

        for (VBox vBox : verticalPanes) {
            vBox = beautifyVBox(vBox);
        }

        HBox hBox = new HBox();
        hBox.getChildren().addAll(verticalPanes);
        hBox.getChildren().add(saveButton);

        Scene scene = new Scene(hBox, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox beautifyVBox(VBox vBox) {
        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        return vBox;
    }

    private void click_saveButton() {
        String level = name2fieldMap.get("levelField").getText();
        String fullTopic = name2fieldMap.get("fullField").getText();
        String tag = name2fieldMap.get("tagField").getText();

        boolean hasAllFields = level != null && fullTopic != null && tag != null;
        if (hasAllFields) {
            TopicEntity topic = new TopicEntity();
            topic.setLevel(level);
            topic.setTopicFull(fullTopic);
            topic.setTopicTag(tag);

            System.out.println("Preparing to write new tag....");
            TopicsDBUtil.writeSingleTopic(topic);
            System.out.println("Successfully saved new tag!!!");
            stage.close();
        } else {
            // please, fill all fields
            String message = "Please, fill all fields!";
            String title = "Fill the fields!";
            MessageBox.show(message, title);
        }
    }
}
