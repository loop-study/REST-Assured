package loopstudy.restassured.application;

import loopstudy.restassured.application.dto.StudyResponse;
import loopstudy.restassured.domain.Study;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudyRepository {
    private final Map<Integer, Study> studyMap = new HashMap<>();

    public Study save(final Study study) {
        studyMap.put(study.getId(), study);
        return study;
    }

    public List<Study> findAll() {
        return new ArrayList<>(studyMap.values());
    }

    public Study findById(Integer id) {
        return studyMap.get(id);
    }
}
