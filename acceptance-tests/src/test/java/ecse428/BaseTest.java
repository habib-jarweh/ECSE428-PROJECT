package ecse428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class BaseTest {
    public static final String BASE_URL = "http://localhost:8080";
    protected static final int STATUS_CODE_OK = 200;
    protected static final int STATUS_CODE_CREATED = 201;
    protected static final int STATUS_CODE_BAD_REQUEST = 400;
    protected static final int STATUS_CODE_NOT_FOUND = 404;
    private static final int TIME_OUT = 5000;
    private static Process serverProcess;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public HttpResponse response;
    public String responseString;
    public HttpClient httpClient = HttpClients.createDefault();

    // @BeforeClass
    // public static void setupForAllTests() {
    //     Unirest.config().defaultBaseUrl(BASE_URL);
    //     String projectPath = "/Users/habibjarweh/ECSE428-PROJECT/backend/";
    //     runGradleBootRunInBackground(projectPath);    }

    // @AfterClass
    // public static void tearDownAllTests() {
    //     stopServer();
    // }

    public static void runGradleBootRunInBackground(String projectPath) {
        try {
            // Assuming gradlew is located in the project directory
            String gradlewPath = projectPath + "gradlew";
            List<String> commands = List.of(gradlewPath, "bootRun");

            // Create a ProcessBuilder instance with the command
            ProcessBuilder processBuilder = new ProcessBuilder(commands);

            // Set the working directory to the project directory
            processBuilder.directory(new java.io.File(projectPath));

            // Print the exact command being run
            System.out.println("Executing command: " + String.join(" ", commands) + " in directory: " + projectPath);

            // Redirect error stream to standard output stream
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Optionally, read the process's output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            System.out.println("Gradle bootRun command executed in background.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while trying to execute the gradle bootRun command.");
        }
    }

        public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        Random rand = new SecureRandom();
        while (count-- != 0) {
            int character = (int)(rand.nextDouble() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
