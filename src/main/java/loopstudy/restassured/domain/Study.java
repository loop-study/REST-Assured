package loopstudy.restassured.domain;

import java.time.LocalDateTime;

public class Study {
    private static Integer STUDY_NUMBER = 1;
    private final Integer id;
    private final String topic;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public Study(String topic) {
        validationTopic(topic);
        this.topic = topic;
        this.id = STUDY_NUMBER++;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = this.createdDate;
    }

    private void validationTopic(String topic) {
        if (topic == null) {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
