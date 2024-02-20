package ecse428.features;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import ecse428.BaseTest;

public class SignUpFeature extends BaseTest {

    @Given("the SmartEats registration page is accessed")
    public void theSmartEatsRegistrationPageIsAccessed() {
    }
    
    @When("entering valid registration details")
    public void enteringValidRegistrationDetails() {
        // Create an HttpPost object and set the URL
        HttpPost httpPost = new HttpPost(BASE_URL + "/customers/register");

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "*/*");
                
        // Construct the email with the randomly generated prefix
        String email = randomAlphaNumeric(5) + "@test.com";

        // Set the request body
        String json = "{\"email\": \"" + email + "\", \"password\": \"test1234\"}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // Execute the request
            response = httpClient.execute(httpPost);
            // Convert the response entity to a string and print it out
            // String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            // System.out.println(responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @When("an email that is already associated with an existing account is entered")
    public void anEmailThatIsAlreadyAssociatedWithAnExistingAccountIsEntered() {
        // Create an HttpPost object and set the URL
        HttpPost httpPost = new HttpPost(BASE_URL + "/customers/register");

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "*/*");

        // Set the request body
        String json = "{\"email\": \"main@test.com\", \"password\": \"habib123\"}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // Execute the request
            response = httpClient.execute(httpPost);
            // Convert the response entity to a string and print it out
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @When("an incorrect email format is entered")
    public void anIncorrectEmailFormatIsEntered() {
        // Create an HttpPost object and set the URL
        HttpPost httpPost = new HttpPost(BASE_URL + "/customers/register");

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "*/*");

        // Set the request body
        String json = "{\"email\": \"noattest.com\", \"password\": \"test1234\"}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // Execute the request
            response = httpClient.execute(httpPost);
            // Convert the response entity to a string and print it out
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Then("the account should be successfully created")
    public void theAccountShouldBeSuccessfullyCreated() {
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
    
    @Then("a message indicating that the email is already in use should be received")
    public void aMessageIndicatingThatTheEmailIsAlreadyInUseShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("A user with this email already exists."));
    }
    
    @Then("a message indicating invalid details should be received")
    public void aMessageIndicatingInvalidDetailsShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("must be a well-formed email address"));
    }

    @When("an invalid password format is entered")
    public void anInvalidPasswordFormatIsEntered() {
        // Create an HttpPost object and set the URL
        HttpPost httpPost = new HttpPost(BASE_URL + "/customers/register");

        // Set headers
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "*/*");

        // Set the request body
        String json = "{\"email\": \"main@test.com\", \"password\": \"1\"}";
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // Execute the request
            response = httpClient.execute(httpPost);
            // Convert the response entity to a string and print it out
            responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("a message indicating invalid password should be received")
    public void aMessageIndicatingInvalidPasswordShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("Password must be at least 8 characters long."));
    }
    
    @And("the registration form is submitted")
    public void theRegistrationFormIsSubmitted() {
    }
}


