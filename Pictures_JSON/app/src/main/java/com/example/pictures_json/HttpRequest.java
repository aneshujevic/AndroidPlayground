package com.example.pictures_json;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {
    public static enum Method {
        POST, PUT, DELETE, GET;
    }

    private HttpURLConnection conn;
    private OutputStream os;

    private HttpRequest(URL url) throws IOException {
        conn = (HttpURLConnection) url.openConnection();
    }

    HttpRequest(String url) throws IOException {
        this(new URL(url));
        Log.d("parameters", url);
    }

    private void prepareAll (Method method) throws IOException {
        conn.setDoInput(true);
        conn.setRequestMethod(method.name());

        if (method == Method.POST || method == Method.PUT) {
            conn.setDoOutput(true);
            os = conn.getOutputStream();
        }
    }

    public HttpRequest prepare () throws IOException {
        prepareAll(Method.GET);
        return this;
    }

    HttpRequest prepare(Method method) throws IOException {
        prepareAll(method);
        return this;
    }

    public HttpRequest withHeaders(String ... headers) {
        for (String header : headers) {
            String[] h = header.split("[:]]");
            conn.setRequestProperty(h[0], h[1]);
        }
        return this;
    }

    private HttpRequest withData(String query) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.close();
        return this;
    }

    HttpRequest withData(HashMap<String, String> params) throws IOException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry: params.entrySet()) {
            result.append(result.length() > 0 ? "&" : "").append(entry.getKey()).append("=").append(entry.getValue());
            Log.d("parameters",entry.getKey() + " ====> " + entry.getValue());
        }
        withData(result.toString());
        return this;
    }

    public int send() throws IOException {
        return conn.getResponseCode();
    }

    String sendAndReadString() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response=new StringBuilder();

        for(String line;(line=br.readLine())!=null;)
            response.append(line).append("\n");
        Log.d("response ",response.toString());

        return response.toString();
    }

    public byte[] sendAndReadBytes() throws IOException{
        byte[] buffer = new byte[8192];
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int bytesRead; (bytesRead=is.read(buffer))>=0; )
            output.write(buffer, 0, bytesRead);
        return output.toByteArray();
    }

    public JSONObject sendAndReadJSON() throws JSONException, IOException{
        return new JSONObject(sendAndReadString());
    }
}
