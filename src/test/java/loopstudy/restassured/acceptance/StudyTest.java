package loopstudy.restassured.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudyTest extends AcceptanceTest {

    /**
     * When 스터디 생성 요청을 하면
     * Then 스터디 생성이 된다.
     */
    @Test
    void 스터디_생성() {
        ExtractableResponse<Response> createResponse = RestAssured
                .given().log().all()
                .param("topic", "REST-Assured")
                .when()
                .post("/study")
                .then().log().all()
                .extract();

        assertThat(createResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 스터디 생성 요청을 하고
     * When 스터디 조회 요청을 하면
     * Then 스터디가 조회된다.
     */
    @Test
    void 스터디_조회() {
        ExtractableResponse<Response> getStudy = RestAssured
                .when()
                .get("/study")
                .then().log().all()
                .extract();
    }

    /**
     * Given 스터디 생성 요청을 하고
     * When 스터디 정보 수정을 요청하면
     * Then 스터디 정보가 수정된다.
     */
    @Test
    void 스터디_수정() {
        ExtractableResponse<Response> updateStudy = RestAssured
                .given().log().all()
                .formParam("topic", "REST-Assured")
                .when()
                .put("/study")
                .then().log().all()
                .extract();
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
}
