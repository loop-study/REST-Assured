package loopstudy.restassured.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyController {

    @GetMapping
    public ResponseEntity call() {

        return ResponseEntity.ok().body(null);
    }
}
