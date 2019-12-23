package test.database.topics;

import logic.database.topics.TopicEntity;
import logic.database.topics.TopicGather;
import java.util.List;

public class TopicsGatherTest {

    public static void main(String[] args) {
        List<TopicEntity> topics = TopicGather.getTopics();
        topics.stream().forEach(System.out::println);
    }

}
