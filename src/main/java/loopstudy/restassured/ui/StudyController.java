package loopstudy.restassured.ui;

import loopstudy.restassured.application.StudyService;
import loopstudy.restassured.application.dto.StudyRequest;
import loopstudy.restassured.application.dto.StudyResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudyResponse>> showStudies() {
        return ResponseEntity.ok().body(studyService.findAll());
    }
}
