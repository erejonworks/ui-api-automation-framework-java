package api.requests;
import config.Environment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserRequests {
    public static Response getUser(int id) {
        return given()
                .baseUri(Environment.BASE_API_URL)
                .when()
                .get("/users/" + id);
    }
}
