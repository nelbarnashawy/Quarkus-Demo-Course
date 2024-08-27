//package com.sumerge;
//
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.json.Json;
//import jakarta.json.JsonObject;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.junit.jupiter.api.*;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.hasItems;
//
//@QuarkusTest
//@Tag("integration-test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class MovieControllerIntegrationTest {
//
//    @Test
//    @Order(1)
//    void getMovies() {
//        given()
//                .when().get("/movies")
//                .then()
//                .body("size()", equalTo(2))
//                .body("id", hasItems(1, 2))
//                .body("name", hasItems("FirstMovie", "SecondMovie"))
//                .statusCode(Response.Status.OK.getStatusCode());
//    }
//
//    @Test
//    @Order(1)
//    void getMoviesSize() {
//        given().when().get("/movies/size")
//                .then()
//                .body(equalTo("2"))
//                .statusCode(Response.Status.OK.getStatusCode());
//    }
//
//    @Test
//    @Order(2)
//    void createMovie() {
//        JsonObject object = Json.createObjectBuilder()
//                .add("name", "ThirdMovie")
//                .build();
//        given()
//                .body(object.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .when()
//                .post("/movies")
//                .then()
//                .statusCode(Response.Status.CREATED.getStatusCode())
//                .body("size()", equalTo(3))
//                .body("id", hasItems(1, 2, 3))
//                .body("name", hasItems("FirstMovie", "SecondMovie", "ThirdMovie"));
//    }
//
//    @Test
//    @Order(3)
//    void updateMovie() {
//        JsonObject object = Json.createObjectBuilder()
//                .add("name", "UpdatedMovie")
//                .build();
//        given()
//                .body(object.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .when()
//                .put("/movies/3/UpdatedMovie")
//                .then()
//                .statusCode(Response.Status.OK.getStatusCode())
//                .body("size()", equalTo(3))
//                .body("id", hasItems(1, 2, 3))
//                .body("name", hasItems("FirstMovie", "SecondMovie", "UpdatedMovie"));
//    }
//
//    @Test
//    @Order(3)
//    void updateMovieNotFound() {
//        JsonObject object = Json.createObjectBuilder()
//                .add("name", "UpdatedMovie")
//                .build();
//        given()
//                .body(object.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .when()
//                .put("/movies/5/UpdatedMovie")
//                .then()
//                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
//    }
//
//    @Test
//    @Order(4)
//    void deleteMovie() {
//        given()
//                .when()
//                .delete("/movies/3")
//                .then()
//                .statusCode(Response.Status.OK.getStatusCode())
//                .body("size()", equalTo(2))
//                .body("id", hasItems(1, 2))
//                .body("name", hasItems("FirstMovie", "SecondMovie"));
//    }
//
//    @Test
//    @Order(4)
//    void deleteMovieNotFound() {
//        given()
//                .when()
//                .delete("/movies/5")
//                .then()
//                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
//    }
//}