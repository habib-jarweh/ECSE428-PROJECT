package ecse428;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;


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
    //     startServer();
    // }

    // @AfterClass
    // public static void tearDownAllTests() {
    //     stopServer();
    // }

    // public static void startServer() {
    //     boolean success = false;
    //     final ExecutorService service = Executors.newSingleThreadExecutor();
    //     try {
    //         final Future<Boolean> f = service.submit(new Callable<Boolean>() {
    //             @Override
    //             public Boolean call() {
    //                 startServerUntimed();
    //                 return true;
    //             }
    //         });
    //         success = f.get(TIME_OUT, TimeUnit.MILLISECONDS);
    //     } catch (Exception ignored) { } finally {
    //         if (!success) {
    //             if (serverProcess != null) {
    //                 serverProcess.destroy();
    //             }
    //             System.out.println("Failed to start server -- exiting program");
    //             System.exit(-1);
    //         }
    //     }
    // }

    // public static void startServerUntimed() {
    //     try {
    //         ProcessBuilder pb = new ProcessBuilder("../backend/gradlew", "bootRun");
    //         if (serverProcess != null) {
    //             serverProcess.destroy();
    //         }
    //         serverProcess = pb.start();
    //         final InputStream is = serverProcess.getInputStream();
    //         final BufferedReader output = new BufferedReader(new InputStreamReader(is));
    //         while (true) {
    //             String line = output.readLine();
    //             if (line != null && line.contains("Running on 8080")) {
    //                 waitUntilOnline();
    //                 return;
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void waitUntilOnline() {
    //     int tries = 0;
    //     while (!isOnline()) {
    //         System.out.println("WAITING HERE");
    //         try {
    //             Thread.sleep(10);
    //         } catch (InterruptedException ignored) {}
    //         tries++;
    //         if (tries > 100) {
    //             startServer();
    //             tries = 0;
    //         }
    //     }
    // }

    // public static boolean isOnline() {
    //     try {
    //         int status = Unirest.get("/").asString().getStatus();
    //         System.out.println(status);
    //         return status == 404;
    //     } catch (UnirestException ignored) { }
    //     return false;
    // }

    // public static void stopServer() {
    //     serverProcess.destroy();
    // }

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
