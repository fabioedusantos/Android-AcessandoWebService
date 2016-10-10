package br.fabio.professor.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Http {

    private static String request(String location, String param, String type) {
        if(param == null){
            param = "";
        }

        try {
            URL url = new URL(location);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br;
            StringBuilder sb = new StringBuilder();
            String line;

            connection.setDoOutput(true);
            connection.setRequestMethod(type);
            connection.setReadTimeout(30000);

            Writer writer = new OutputStreamWriter(connection.getOutputStream());

            writer.write(param);
            writer.flush();
            writer.close();

            if (200 <= connection.getResponseCode() && connection.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            } else {
                br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
            }

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (ProtocolException e) {
            Log.e("apk", "Class: Http; Method: request; Location: " + location + "; Param: " + param + "; Type: " + type + "; Message: " + e.getMessage());
        } catch (MalformedURLException e) {
            Log.e("apk", "Class: Http; Method: request; Location: " + location + "; Param: " + param + "; Type: " + type + "; Message: " + e.getMessage());
        } catch (IOException e) {
            Log.e("apk", "Class: Http; Method: request; Location: " + location + "; Param: " + param + "; Type: " + type + "; Message: " + e.getMessage());
        }

        return null;
    }

    public static String get(String location, String getParam) {
        return request(location, getParam, "GET");
    }

    public static String post(String location, String getParam) {
        return request(location, getParam, "POST");
    }
}