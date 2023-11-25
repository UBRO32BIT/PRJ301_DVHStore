/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author ubro3
 */
public class GoogleLogin {
    static final Logger LOGGER = Logger.getLogger(GoogleLogin.class);
    public static JSONObject checkAccount(String idToken) {
       JSONObject result = null;
       try {
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;

            // Create an HTTP connection
            URL url = new URL(tokenInfoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(response.toString());

                // Check if email_verified is true
                boolean emailVerified = jsonObject.getBoolean("email_verified");

                if (emailVerified) {
                    result = jsonObject;
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
}
