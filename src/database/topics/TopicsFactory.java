package database.topics;

import question.topics.A1_LEVEL;
import question.topics.A2_LEVEL;
import question.topics.MixType;

import java.util.ArrayList;
import java.util.List;

public class TopicsFactory {

    public static void main(String[] args) {
        TopicsFactory topicsFactory = new TopicsFactory();
        List<TopicEntity> topicList = topicsFactory.getTopicsFromEnums();
        topicList.stream().forEach(System.out::println);
    }

    public List<TopicEntity> getTopicsFromEnums() {
        List<TopicEntity> topicList = new ArrayList<>();

        // create list of a1 levels
        String a1 = "A1";
        A1_LEVEL[] a1LevelsArray = A1_LEVEL.values();
        for (A1_LEVEL a1_level : a1LevelsArray) {
            String topicFull = a1_level.toString().toLowerCase();
            String topicTag = a1_level.getTag();
            TopicEntity topicEntity = new TopicEntity(null, a1, topicFull, topicTag);
            topicList.add(topicEntity);
        }

        // create list of a2 levels
        String a2 = "A2";
        A2_LEVEL[] a2LevelsArray = A2_LEVEL.values();
        for (A2_LEVEL a2_level : a2LevelsArray) {
            String topicFull = a2_level.toString().toLowerCase();
            String topicTag = a2_level.getTag();
            TopicEntity topicEntity = new TopicEntity(null, a2, topicFull, topicTag);
            topicList.add(topicEntity);
        }

        // create list of a2 levels
        String mix = "MIX";
        MixType[] mixTypesArray = MixType.values();
        for (MixType mix_level : mixTypesArray) {
            String topicFull = mix_level.toString().toLowerCase();
            String topicTag = mix_level.getTag();
            TopicEntity topicEntity = new TopicEntity(null, mix, topicFull, topicTag);
            topicList.add(topicEntity);
        }

        return topicList;
    }

}
