package loopstudy.restassured.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudyTest extends AcceptanceTest {

    /**
     * When 스터디 생성 요청을 하면
     * Then 스터디 생성이 된다.
     */
    @Test
    void 스터디_생성() {
        String topic = "REST-Assured";

        Map<String, Object> params = new HashMap<>();
        params.put("topic", topic);

        ExtractableResponse<Response> createResponse = RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/study")
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(createResponse.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(getTopic(createResponse)).isEqualTo(topic)
        );
    }

    /**
     * Given 스터디 생성 요청을 하고
     * When 스터디 조회 요청을 하면
     * Then 스터디가 조회된다.
     */
    @Test
    void 스터디_조회() {
        String topic = "REST-Assured";

        Map<String, Object> params = new HashMap<>();
        params.put("topic", topic);

        RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/study")
                .then().log().all();

        ExtractableResponse<Response> getStudy = RestAssured
                .when()
                .get("/study")
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(getStudy.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(getStudy.jsonPath().getList("topic")).contains(topic)
        );
    }

    /**
     * Given 스터디 생성 요청을 하고
     * When 스터디 정보 수정을 요청하면
     * Then 스터디 정보가 수정된다.
     */
    @Test
    void 스터디_수정() {
        String topic = "REST-Assured";

        Map<String, Object> params = new HashMap<>();
        params.put("topic", topic);

        ExtractableResponse<Response> create = RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/study")
                .then().log().all()
                .extract();

        Map<String, Object> params2 = new HashMap<>();
        params2.put("topic", "Acceptance");

        ExtractableResponse<Response> updateStudy = RestAssured
                .given().log().all()
                .body(params2)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(create.header("Location"))
                .then().log().all()
                .extract();

        assertThat(updateStudy.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


    /**
     * Given 스터디 생성 요청을 하고
     * When 스터디 삭제 요청을 하면
     * Then 스터디가 삭제된다.
     */
    @Test
    void 스터디_삭제() {
        ExtractableResponse<Response> deleteStudy = RestAssured
                .when()
                .delete("/study/1")
                .then().log().all()
                .extract();
    }

    private String getTopic(ExtractableResponse<Response> createResponse) {
        return createResponse.jsonPath().get("topic");
    }
}
