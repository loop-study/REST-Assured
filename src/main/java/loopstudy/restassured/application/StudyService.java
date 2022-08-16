package loopstudy.restassured.application;

import loopstudy.restassured.application.dto.StudyRequest;
import loopstudy.restassured.application.dto.StudyResponse;
import loopstudy.restassured.domain.Study;
import org.springframework.stereotype.Service;

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

    private StudyResponse toResponse(Study study) {
        return new StudyResponse(
                    study.getId(),
                    study.getTopic(),
                    study.getCreatedDate(),
                    study.getModifiedDate()
                );
    }
}
