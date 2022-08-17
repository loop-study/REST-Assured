package loopstudy.restassured.domain;

import java.time.LocalDateTime;

public class Study {
    private static Integer STUDY_NUMBER = 1;
    private Integer id;
    private String topic;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Study(String topic) {
        validationTopic(topic);
        this.topic = topic;
        this.id = STUDY_NUMBER++;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = this.createdDate;
    }

    public void changeTopic(String topic) {
        validationTopic(topic);
        this.topic = topic;
        this.modifiedDate = LocalDateTime.now();
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

    private void validationTopic(String topic) {
        if (topic == null) {
            throw new IllegalArgumentException();
        }
    }
}
