package ecse428.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*; // Import JUnit assertions
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import ecse428.BaseTest;
import ecse428.helper_code.HttpGetWithEntity;

public class SignInFeature extends BaseTest{

    @Given("the SmartEats sign-in page is accessed")
    public void theSmartEatsSignInPageIsAccessed() {
        // Code to navigate to the sign-in page
    }

    @When("a valid email and password are entered")
    public void aValidEmailAndPasswordAreEntered() {
        try {
            // Create an instance of HttpGetWithEntity
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(new URI(BASE_URL + "/customers/login"));
            
            // Optionally, if you need to send a body with the GET request
            // This is unconventional for GET requests
            String json = "{\"email\": \"main@test.com\", \"password\": \"habib123\"}";
            StringEntity entity = new StringEntity(json);
            httpGetWithEntity.setEntity(entity);
            httpGetWithEntity.setHeader("Content-Type", "application/json");
            
            // Execute the request
            response = httpClient.execute(httpGetWithEntity);
            
            // Process the response
            responseString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("the sign-in form is submitted")
    public void theSignInFormIsSubmitted() {
        // Code to submit the sign-in form
    }

    @Then("sign-in should be successful")
    public void signInShouldBeSuccessful() {
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        assertTrue(responseString.contains("true"));
    }

    @When("an incorrect email or password is used to sign in")
    public void anIncorrectEmailOrPasswordIsUsedToSignIn() {
        try {
            // Create an instance of HttpGetWithEntity
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(new URI(BASE_URL + "/customers/login"));
            
            // Optionally, if you need to send a body with the GET request
            // This is unconventional for GET requests
            String json = "{\"email\": \"main@test.com\", \"password\": \"1\"}";
            StringEntity entity = new StringEntity(json);
            httpGetWithEntity.setEntity(entity);
            httpGetWithEntity.setHeader("Content-Type", "application/json");
            
            // Execute the request
            response = httpClient.execute(httpGetWithEntity);
            
            // Process the response
            responseString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("an error message indicating incorrect credentials should be received")
    public void anErrorMessageIndicatingIncorrectCredentialsShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("Invalid email or password."));
    }

    @When("an unregistered email is used to sign in")
    public void anUnregisteredEmailIsUsedToSignIn() {
        try {
            // Create an instance of HttpGetWithEntity
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(new URI(BASE_URL + "/customers/login"));
            
            // Optionally, if you need to send a body with the GET request
            // This is unconventional for GET requests
            String json = "{\"email\": \"unregistered@test.com\", \"password\": \"1\"}";
            StringEntity entity = new StringEntity(json);
            httpGetWithEntity.setEntity(entity);
            httpGetWithEntity.setHeader("Content-Type", "application/json");
            
            // Execute the request
            response = httpClient.execute(httpGetWithEntity);
            
            // Process the response
            responseString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("an error message indicating that the account is invalid should be received")
    public void anErrorMessageIndicatingThatTheAccountIsInvalidShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("Invalid email or password."));    
    }

    @When("a deactivated account's email is used to sign in")
    public void aDeactivatedAccountsEmailIsUsedToSignIn() {
        try {
            // Create an instance of HttpGetWithEntity
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(new URI(BASE_URL + "/customers/login"));
            
            // Optionally, if you need to send a body with the GET request
            // This is unconventional for GET requests
            String json = "{\"email\": \"deactivated@test.com\", \"password\": \"12345678\"}";
            StringEntity entity = new StringEntity(json);
            httpGetWithEntity.setEntity(entity);
            httpGetWithEntity.setHeader("Content-Type", "application/json");
            
            // Execute the request
            response = httpClient.execute(httpGetWithEntity);
            
            // Process the response
            responseString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("an error message indicating the account has been deactivated should be received")
    public void anErrorMessageIndicatingTheAccountHasBeenDeactivatedShouldBeReceived() {
        assertEquals(response.getStatusLine().getStatusCode(), 400);
        assertTrue(responseString.contains("Invalid email or password."));      
    }
}
