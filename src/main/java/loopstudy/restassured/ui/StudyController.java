package loopstudy.restassured.ui;

import loopstudy.restassured.application.StudyService;
import loopstudy.restassured.application.dto.StudyRequest;
import loopstudy.restassured.application.dto.StudyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping
    public ResponseEntity<StudyResponse> createStudy(@RequestBody StudyRequest studyRequest) {
        StudyResponse studyResponse = studyService.saveStudy(studyRequest);
        return ResponseEntity.created(URI.create("/study/" + studyResponse.getId())).body(studyResponse);
    }
}
