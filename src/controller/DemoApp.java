package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.microsoft.alm.oauth2.useragent.AuthorizationException;
import com.microsoft.alm.oauth2.useragent.AuthorizationResponse;
import com.microsoft.alm.oauth2.useragent.UserAgent;
import com.microsoft.alm.oauth2.useragent.UserAgentImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DemoApp extends Application {

    /*
     * This is where the magic happens...
     */
    public static void main(String[] args) throws AuthorizationException, URISyntaxException  {
        launch(args);
    }

    // These are pulled from gradle.properties
    String oktaDomain;
    String clientId;
    String clientSecret;
    String redirectUri;
    String scope;
    String grantType;

    /**
     * Build the authorization request URL
     *
     * @return
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public URI getAuthorizationEndpointUri() throws URISyntaxException, MalformedURLException {

        URIBuilder builder = new URIBuilder();

        builder.setScheme("https");
        builder.setHost(oktaDomain);
        builder.setPath("/oauth2/default/v1/authorize");
        builder.addParameter("client_id", clientId);
        builder.addParameter("redirect_uri", redirectUri);
        builder.addParameter("response_type", "code");
        builder.addParameter("state", "this is a state");
        builder.addParameter("scope", scope);

        URL url = builder.build().toURL();

        return url.toURI();

    }

    /**
     * Requests an authorization code from the auth server
     *
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     * @throws AuthorizationException
     */
    public String requestAuthCode() throws MalformedURLException, URISyntaxException, AuthorizationException {

        // Generate the auth endpoint URI to request the auth code

        URI authorizationEndpoint = getAuthorizationEndpointUri();

        System.out.print("Authorization Endpoint URI: ");
        System.out.println(authorizationEndpoint.toString());

        final URI redirectUri = new URI(this.redirectUri);

        // Create the user agent and make the call to the auth endpoint

        final UserAgent userAgent = new UserAgentImpl();

        final AuthorizationResponse authorizationResponse = userAgent.requestAuthorizationCode(authorizationEndpoint, redirectUri);

        // We should have the code, which we can trade for the token

        final String code = authorizationResponse.getCode();

        System.out.print("Authorization Code: ");
        System.out.println(code);

        return code;

    }

    /**
     * Given an authorization code, calls the auth server to request a token
     *
     * @param code
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String getTokenForCode(String code) throws URISyntaxException, IOException {

        // The token request URL

        final String tokenUrl = "https://"+ oktaDomain +"/oauth2/default/v1/token";

        // The original redirect URL

        final URI redirectUri = new URI(this.redirectUri);

        // Using HttpClient to make the POST to exchange the auth code for the token

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(tokenUrl);

        // Adding the POST params to the request

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("grant_type", grantType));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("redirect_uri", redirectUri.toString()));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        urlParameters.add(new BasicNameValuePair("scope", scope));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // Execute the request

        HttpResponse response = client.execute(post);

        // Print the status code

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        // Get the content as a String

        String content = EntityUtils.toString(response.getEntity());

        System.out.println("Result : " + content.toString());

        return content.toString();
    }

    /**
     * Uses com.google.code.gson to pretty print JSON, just for fun
     * @param json
     * @return
     */
    public static String prettyPrintJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    /**
     * Loads our config info from the app.properties file
     * @throws IOException
     */
    public void loadProperties() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");
        Properties appProps = new Properties();
        appProps.load(inputStream);
        oktaDomain = appProps.getProperty("oktaDomain");
        clientId = appProps.getProperty("oktaClientId");
        clientSecret = appProps.getProperty("oktaClientSecret");
        redirectUri = appProps.getProperty("redirectUri");
        scope = appProps.getProperty("scope");
        grantType = appProps.getProperty("grantType");
    }

    /**
     * Entry point for the JavaFX application.
     * 1) Loads the system properties
     * 2) Requests the authorization code
     * 3) Exchanges the code for the token
     * 4) Displays the token
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        try {

            // Load auth info from the app.properties
            loadProperties();

            // Request the authorization code from the Okta OAuth provider

            String code = requestAuthCode();

            // Exchange the auth code for the access token

            String token = getTokenForCode(code);

            // Configure stage and scene to display token results

            primaryStage.setTitle("JavaFX Okta OAuth");

            Text tokenText = new Text();
            tokenText.setText("Your token: \n" + prettyPrintJson(token));
            tokenText.setWrappingWidth(600);
            tokenText.setX(100);
            tokenText.setY(100);
            StackPane root = new StackPane();
            root.getChildren().add(tokenText);
            primaryStage.setScene(new Scene(root, 800, 800));
            primaryStage.show();


        }
        catch (IOException | AuthorizationException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
