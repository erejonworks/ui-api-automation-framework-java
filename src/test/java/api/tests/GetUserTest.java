package api.tests;

import api.requests.UserRequests;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetUserTest {
    @Test
    public void testGetUserReturns200() {
        UserRequests.getUser(2)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }
}
