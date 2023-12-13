package books;

import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;
import services.BookServices;
import services.TokenServices;
import spec.Especificacoes;
import static io.restassured.RestAssured.baseURI;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BooksTests {

    private static TokenServices tokenser = new TokenServices();
    private static String token;
    private static String userId;

    private Especificacoes response = new Especificacoes();

    @BeforeAll
    public static void setUp() {
        userId = tokenser.obterUserId();
        token = tokenser.obterToken();
        baseURI = "https://bookstore.toolsqa.com/BookStore/v1/";
    }

    @Test
    @Order(1)
    public void consultarBookPorISBN() {
        BookServices.consultarLivro("9781449325862")
                .statusCode(SC_OK)
                .body("isbn", equalTo("9781449325862"))
                .body("title", equalTo("Git Pocket Guide"))
                .body("subTitle", equalTo("A Working Introduction"))
                .body("author", equalTo("Richard E. Silverman"))
                .body("publish_date", equalTo("2020-06-04T08:48:39.000Z"))
                .body("publisher", equalTo("O'Reilly Media"))
                .body("pages", equalTo(234))
                .body("description", equalTo("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp"))
                .body("website", equalTo("http://chimera.labs.oreilly.com/books/1230000000561/index.html"))
        ;
    }

    @Test
    @Order(2)
    public void cadastrarBook() {
        ResponseSpecification rs = response.setResponseCreatedSpec();
        BookServices.cadastrarLivro(token, userId)
                .spec(rs)
        ;
    }

    @Test
    @Order(3)
    public void alterarBook() {
        ResponseSpecification rs = response.setResponseOkSpec();
        BookServices.alterarLivro(token, userId)
                .spec(rs)
        ;
    }

    @Test
    @Order(4)
    public void deletarBook() {
        ResponseSpecification rs = response.setResponseNoContentSemJsonSpec();
        BookServices.excluirLivro(token, userId)
                .spec(rs)
        ;
    }
}
