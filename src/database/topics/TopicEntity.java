package database.topics;

public class TopicEntity {

    private Integer id;
    private String level;
    private String topicFull;
    private String topicTag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTopicFull() {
        return topicFull;
    }

    public void setTopicFull(String topicFull) {
        this.topicFull = topicFull;
    }

    public String getTopicTag() {
        return topicTag;
    }

    public void setTopicTag(String topicTag) {
        this.topicTag = topicTag;
    }

    public TopicEntity(Integer id, String level, String topicFull, String topicTag) {
        this.id = id;
        this.level = level;
        this.topicFull = topicFull;
        this.topicTag = topicTag;
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", topicFull='" + topicFull + '\'' +
                ", topicTag='" + topicTag + '\'' +
                '}';
    }
}
