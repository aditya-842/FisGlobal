package stepdefinations;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import java.time.Duration;
import java.util.Map;

import java.util.Map;

public class CoindeskSteps {

    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        // Send GET request to the specified endpoint
        response = RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
        // Verify that we get a 200 status code
        assertEquals("Expected HTTP status code 200", 200, response.getStatusCode());
    }

    @When("I receive the response")
    public void i_receive_the_response() {
        // This step ensures that a response was received and is in JSON format.
        assertNotNull("Response is null", response);
        // Optionally check that the content type is JSON.
        String contentType = response.getHeader("Content-Type");
        assertTrue("Expected JSON content type", contentType != null && contentType.contains("application/json"));
    }

    @Then("the response should contain 3 BPIs: {string}, {string}, {string}")
    public void the_response_should_contain_3_bp_is(String currency1, String currency2, String currency3) {
        // Parse the "bpi" node from the JSON response into a Map.
        Map<String, Object> bpi = response.jsonPath().getMap("bpi");
        assertNotNull("bpi section is missing", bpi);
        // Verify the presence of each currency
        assertTrue("BPI should contain " + currency1, bpi.containsKey(currency1));
        assertTrue("BPI should contain " + currency2, bpi.containsKey(currency2));
        assertTrue("BPI should contain " + currency3, bpi.containsKey(currency3));
        // Optionally verify that there are exactly 3 BPIs
        assertEquals("There should be exactly 3 BPIs", 3, bpi.size());
    }

    @Then("the GBP description should equal {string}")
    public void the_gbp_description_should_equal(String expectedDescription) {
        // Retrieve the GBP description from the response JSON.
        String actualDescription = response.jsonPath().getString("bpi.GBP.description");
        assertNotNull("GBP description is missing", actualDescription);
        assertEquals("GBP description does not match", expectedDescription, actualDescription);
    }
}
