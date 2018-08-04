package com.besafx.app.config;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.Future;

@Service
public class EnjazSMS {

    private final static Logger LOG = LoggerFactory.getLogger(EnjazSMS.class);

    public String returnType = "json";

    public String unicode = "E";

    public String sender = "Anni";

    public String userName = "anni";

    public String password = "Ahmad_316";

    @Async("threadMultiplePool")
    public Future<Integer> countSMSPoints(String mobile, String message) throws JSONException {
        String op = "";
        try {
            // Construct The Post Data
            String data = URLEncoder.encode("return", "UTF-8") + "=" + URLEncoder.encode(returnType, "UTF-8");
            data += "&" + URLEncoder.encode("unicode", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("numbers", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
            data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");

            //Push the HTTP Request
            URL url = new URL("https://www.enjazsms.com/dsfKJH/countSMSPoints.php?");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                op += line;
            }
            wr.close();
            rd.close();

            return new AsyncResult<>(new JSONObject(op).getInt("totalcout"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(0);
    }

    @Async("threadMultiplePool")
    public Future<String> sendSMS(String mobile, String message) {
        String op = "";
        try {
            // Construct The Post Data
            String data = URLEncoder.encode("return", "UTF-8") + "=" + URLEncoder.encode(returnType, "UTF-8");
            data += "&" + URLEncoder.encode("unicode", "UTF-8") + "=" + URLEncoder.encode(unicode, "UTF-8");
            data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(sender, "UTF-8");
            data += "&" + URLEncoder.encode("numbers", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
            data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");

            //Push the HTTP Request
            URL url = new URL("https://www.enjazsms.com/api/sendsms.php?");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                op += line;
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(op);
    }

}
