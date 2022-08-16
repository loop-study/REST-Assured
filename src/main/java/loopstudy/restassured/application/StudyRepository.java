package loopstudy.restassured.application;

import loopstudy.restassured.domain.Study;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StudyRepository {
    private final Map<Integer, Study> studyMap = new HashMap<>();

    public Study save(final Study study) {
        studyMap.put(study.getId(), study);
        return study;
    }
}
