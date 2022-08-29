package loopstudy.restassured.application.dto;

import java.time.LocalDateTime;

public class StudyResponse {
    private final int id;
    private final String topic;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final InnerResponse innerResponse;

    public StudyResponse(int id, String topic, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.topic = topic;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.innerResponse = new InnerResponse();
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

    public InnerResponse getInnerResponse() {
        return innerResponse;
    }

    class InnerResponse {
        private final String jsonName;
        private final String jsonValue;

        public InnerResponse() {
            this.jsonName = "json";
            this.jsonValue = "Path";
        }

        public String getJsonName() {
            return jsonName;
        }

        public String getJsonValue() {
            return jsonValue;
        }
    }
}
