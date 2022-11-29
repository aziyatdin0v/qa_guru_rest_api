package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreateUserLombokModel;
import models.lombok.CreateUserResponseLombokModel;
import models.pojo.CreateUserPojoModel;
import models.pojo.CreateUserResponsePojoModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;

public class ReqresInExtendedTests {

    @Test
    void createUserWithPojoModelTest() {
        CreateUserPojoModel body = new CreateUserPojoModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserResponsePojoModel response = given()
                .filter(new AllureRestAssured()) //with Allure Listener
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponsePojoModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }

    @Test
    void createUserWithLombokTest() {
        CreateUserLombokModel body = new CreateUserLombokModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserResponseLombokModel response = given()
                .filter(withCustomTemplates()) //with Custom Allure Listener
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseLombokModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }

    @Test
    void createUserWithSpecsTest() {
        CreateUserLombokModel body = new CreateUserLombokModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserResponseLombokModel response = given() //given(createUserRequestSpec)
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserResponseLombokModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }
}
