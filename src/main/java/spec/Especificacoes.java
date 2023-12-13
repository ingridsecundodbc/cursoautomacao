package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import static org.apache.http.HttpStatus.*;

public class Especificacoes {

    ResponseSpecification responseSpec;

    public ResponseSpecification setResponseOkSpec() {
        return responseSpec = new ResponseSpecBuilder().expectStatusCode(SC_OK).expectContentType("application/Json").build();
    }

    public ResponseSpecification setResponseCreatedSpec() {
        return responseSpec = new ResponseSpecBuilder().expectStatusCode(SC_CREATED).expectContentType("application/Json").build();
    }

    public ResponseSpecification setResponseNoContentSemJsonSpec() {
        return responseSpec = new ResponseSpecBuilder().expectStatusCode(SC_NO_CONTENT).build();
    }
}
