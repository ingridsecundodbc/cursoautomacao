package services;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class TokenServices {
    Faker faker = new Faker();
    String nome = faker.name().firstName();

    public String obterUserId() {
        HashMap body = new HashMap();
        body.put("userName", nome);
        body.put("password", "Senha@1234");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        String responseBody = response.getBody().asString();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("userID").getAsString();
    }

    public String obterToken() {
        HashMap body = new HashMap();
        body.put("userName", nome);
        body.put("password", "Senha@1234");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String responseBody = response.getBody().asString();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("token").getAsString();
    }
}
