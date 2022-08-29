package loopstudy.restassured.acceptance;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.annotation.processing.Messager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.matcher.RestAssuredMatchers.endsWithPath;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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

        ExtractableResponse<Response> createResponse =
                RestAssured
                .given().log().all()
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

        createStudy(topic);

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

        ExtractableResponse<Response> create = createStudy(topic);

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
        String topic = "REST-Assured";

        ExtractableResponse<Response> create = createStudy(topic);

        ExtractableResponse<Response> deleteStudy = RestAssured
                .when()
                .delete(create.header("Location"))
                .then().log().all()
                .extract();

        assertThat(deleteStudy.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private String getTopic(ExtractableResponse<Response> createResponse) {
        return createResponse.jsonPath().get("topic");
    }

    private ExtractableResponse<Response> createStudy(String topic) {
        Map<String, Object> params = new HashMap<>();
        params.put("topic", topic);

        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/study")
                .then().log().all()
                .extract();
    }

    /**
     * 공식가이드에 따라 Request 값만 세팅해서 확인함.
     */
    @Test
    void 공식문서_유저가이드_요청_세팅() {
        // Parameters
//        RestAssured
//                .given().log().all()
//                    .param("name1", "value1")
//                    .param("name2", "value2")
//                .when()
//                    .get("/study");
//
//        RestAssured
//                .given().log().all()
//                    .param("name1", "value1")
//                    .param("name2", "value2")
//                .when()
//                    .post("/study");
//
//        RestAssured
//                .given().log().all()
//                    .formParam("formParamName", "formValue")
//                    .queryParam("queryParamName", "queryValue");

//        RestAssured.when()
//                    .get("/study?topic=RestAssued");
//
//        // multi-value parameters
//        RestAssured
//                .given()
//                .param("name1", "value1", "value2");
//
//        List<String> values = new ArrayList<String>();
//        values.add("value1");
//        values.add("value2");
//
//        RestAssured
//                .given()
//                .param("name1", values);
//
//        // no-value parameters
//        RestAssured
//                .given()
//                .param("name");
//
//        // path parameters
//        RestAssured
//                .when()
//                .post("/study/{topic}/{status}", "RestAssured", "모집중");
//
//        RestAssured
//                .given()
//                    .pathParam("topic", "RestAssured")
//                    .pathParam("status", "모집중")
//                .when()
//                    .post("/study/{topic}/{status}");
//
//
//        Map<String, Object> pathMap = new HashMap<>();
//        pathMap.put("topic", "Acceptance");
//        pathMap.put("status", "모집중");
//
//        RestAssured
//                .given()
//                    .pathParams(pathMap)
//                .when()
//                    .post("/study/{topic}/{status}");

//        // Cookies 쿠키
//        RestAssured.given().log().all()
//                .cookie("topic", "rest-assured")
//                .when().get("/study");
//
//
//        RestAssured.given().log().all()
//                .cookie("topic", "rest1", "rest2")
//                .when().get("/study");
//
//        Cookie topicCookie = new Cookie.Builder("topic", "rest-assured")
//                .setSecured(true)
//                .setComment("Rest Assured study")
//                .build();
//
//        RestAssured.given().log().all()
//                .cookie(topicCookie)
//                .when().get("/study");
//
//        Cookie cookie1  = new Cookie.Builder("name1", "loop").build();
//        Cookie cookie2  = new Cookie.Builder("name2", "study").build();
//        Cookies cookies = new Cookies(cookie1, cookie2);
//
//        RestAssured.given().log().all()
//                .cookies(cookies)
//                .when().get("/study");
//
//        // Headers
//        RestAssured.given().log().all()
//                .header("MyHeader", "Something")

//        RestAssured.given().log().all()
//                .headers("MyHeader", "Something", "MyOtherHeader", "SomethingElse")
//                .when().get("/study");

//        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("MapHeader", "Something");
//        headerMap.put("MapOtherHeader", "SomethingElse");
//
//        RestAssured.given().log().all()
//                .headers(headerMap)
//                .when().get("/study");
//
//        // Content type
//        RestAssured.given().log().all()
//                .contentType(ContentType.JSON)
//                .when().get("/study");
//
//        RestAssured.given().log().all()
//                .contentType("application/json")
//                .when().get("/study");
//
        // Request Body
//        RestAssured.given().log().all()
//                .contentType("application/json")
//                .body("some body")
//                .when().put("/study"); // POST, PUT, DELETE에 사용한다.

//        BodyParam bodyParam = new BodyParam();
//        bodyParam.setName("loop");
//        bodyParam.setValue("study");
//
//        RestAssured.given().log().all()
//                .body(bodyParam)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .when().put("/study"); // POST, PUT, DELETE에 사용한다.
//
//        Map<String, Object> bodyMap = new HashMap<>();
//        bodyMap.put("mapName", "loop");
//        bodyMap.put("mapValue", "study");
//
//        RestAssured.given().log().all()
//                .body(bodyMap)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .when().put("/study"); // POST, PUT, DELETE에 사용한다.

//        // Authentication
//        // 유저 생성 과정은 생략한다
//        // - form
//        RestAssured.given().log().all()
//                .auth().form("email@email.com", "q1w2e3r4!", new FormAuthConfig("/login/session", "username", "password"))
//                .when().post("/myinfo");
//
//        // - token
//        Map<String, String> params = new HashMap<>();
//        params.put("email", "email@email.com");
//        params.put("password", "q1w2e3r4!");
//
//        ExtractableResponse<Response> response = RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(params)
//                .when().post("/login/token")
//                .then().log().all()
//                .statusCode(HttpStatus.OK.value()).extract();
//
//        String accessToken = response.jsonPath().getString("accessToken");
//
//        RestAssured.given().log().all()
//                .auth().oauth2(accessToken)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .when().get("/members/me")
//                .then().log().all()
//                .statusCode(HttpStatus.OK.value())
//                .extract();
    }

    class BodyParam {
        String name;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
