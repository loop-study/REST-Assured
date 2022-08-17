package loopstudy.restassured.application;

import loopstudy.restassured.application.dto.StudyRequest;
import loopstudy.restassured.application.dto.StudyResponse;
import loopstudy.restassured.domain.Study;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private StudyRepository repository;

    public StudyService(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyResponse saveStudy(StudyRequest studyRequest) {
        Study study = repository.save(new Study(studyRequest.getTopic()));
        return toResponse(study);
    }

    public List<StudyResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateStudy(Integer id, StudyRequest studyRequest) {
        Study study = repository.findById(id);
        study.changeTopic(studyRequest.getTopic());
    }

    private StudyResponse toResponse(Study study) {
        return new StudyResponse(
                    study.getId(),
                    study.getTopic(),
                    study.getCreatedDate(),
                    study.getModifiedDate()
                );
    }
}
