package loopstudy.restassured.application.dto;

import java.time.LocalDateTime;

public class StudyResponse {
    private final int id;
    private final String topic;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public StudyResponse(int id, String topic, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.topic = topic;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
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
