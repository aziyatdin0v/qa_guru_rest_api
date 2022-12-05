package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreateUserLombokModel;
import models.pojo.CreateUserPojoModel;
import org.junit.jupiter.api.Test;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;

public class ReqresInExtendedTests {

    @Test
    void createUserWithPojoModelTest() {
        CreateUserPojoModel body = new CreateUserPojoModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserPojoModel response = given()
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
                .extract().as(CreateUserPojoModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }

    @Test
    void createUserWithLombokTest() {
        CreateUserLombokModel body = new CreateUserLombokModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserLombokModel response = given()
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
                .extract().as(CreateUserLombokModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }

    @Test
    void createUserWithSpecsTest() {
        CreateUserLombokModel body = new CreateUserLombokModel();
        body.setName("Azat");
        body.setJob("student");

        CreateUserLombokModel response = given() //given(createUserRequestSpec)
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserLombokModel.class);

        assertThat(response.getName()).isEqualTo("Azat");
        assertThat(response.getJob()).isEqualTo("student");
    }

    @Test
    public void listUsersTest() {

        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
                        "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"));
    }
}
