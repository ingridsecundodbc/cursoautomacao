package services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class BookServices {

    public static ValidatableResponse consultarLivro(String isbn) {
        return given()
                .contentType(ContentType.JSON)
                .param("ISBN", isbn)
                .when()
                .get("Book")
                .then();
    }

    public static ValidatableResponse cadastrarLivro(String token, String userId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{ \"userId\": \"" + userId + "\",\n" +
                        "  \"collectionOfIsbns\": [\n" +
                        "    {\n" +
                        "      \"isbn\": \"9781449337711\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .post("Books")
                .then();
    }

    public static ValidatableResponse alterarLivro(String token, String userId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("isbn", "9781449337711")
                .body("{\n" +
                        "  \"userId\": \"" + userId + "\",\n" +
                        "  \"isbn\": \"9781449365035\"\n" +
                        "}")
                .when()
                .put("Books/{isbn}")
                .then();
    }

    public static ValidatableResponse excluirLivro(String token, String userId) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "  \"isbn\": \"9781449365035\",\n" +
                        "  \"userId\": \"" + userId + "\"\n" +
                        "}")
                .when()
                .delete("Book")
                .then();
    }
}
