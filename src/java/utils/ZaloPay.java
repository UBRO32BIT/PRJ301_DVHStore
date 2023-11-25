/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import models.order.Order;

public class ZaloPay {
    private static final String API_ENDPOINT = "http://localhost:8000/api/zalopay";
    public static JSONObject createOrder(Order order, double total) throws Exception {
        Random rand = new Random();
        int random_id = rand.nextInt(1000000);
        final Map embed_data = new HashMap(){{}};
        final Map[] item = {
            new HashMap(){{}}
        };

        Map<String, Object> params = new HashMap<String, Object>() {{
            put("app_user", order.getUserID());
            put("amount", total);
            put("description", "DVHStore - Payment for the order #" + 0);
            put("callback_url", "http://localhost:8080/PRJ301_DVHStore/PaymentController");
            put("item", new JSONArray().toString());
            put("embed_data", new JSONObject(embed_data).toString());
        }};

        URL url = new URL(API_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");

        Map<String, String> parameters = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue().toString());
        }

        try (OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            writer.write(getParamsString(parameters));
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        JSONObject result = new JSONObject(response.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }
        return result;
    }

    private static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
        }

        return result.toString();
    }
}
