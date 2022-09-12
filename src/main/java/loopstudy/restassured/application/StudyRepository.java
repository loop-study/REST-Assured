package loopstudy.restassured.application;

import loopstudy.restassured.domain.Study;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

    public void deleteById(Integer id) {
        studyMap.remove(id);
    }
}
