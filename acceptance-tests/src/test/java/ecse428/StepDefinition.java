package ecse428;


import io.cucumber.java.en.Given;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonSerializable.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class StepDefinition extends BaseTest {

    public static String errorMessage;
    public static int statusCode;
    public static JSONObject originalValue;
    public static JSONObject response;
    public static JSONObject originalTodoList;
    public static JSONArray taskList;
    public static int counter;

    @Before
    public void initVars() {
        Unirest.config().defaultBaseUrl(BASE_URL);
        // startServer();
        counter = 0;
        statusCode = 0;
        errorMessage = "";
        response = null;
        originalValue = null;
        originalTodoList = null;
        taskList = null;
    }
        
    @Given("^the backend is running$")
    public void theAPIServerIsRunning() {
    }
}