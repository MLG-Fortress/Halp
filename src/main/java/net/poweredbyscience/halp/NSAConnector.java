package net.poweredbyscience.halp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.poweredbyscience.halp.Bukkit.HalpBukkit;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lax on 3/8/2016.
 */
public class NSAConnector {

    public static String gistJson = "{\"description\": \"the description for this gist\", \"public\": false, \"files\": { \"Halp.file\": { \"content\": \"%CONTENT%\" } } }"; //heh heh heh
    public static String service;

    public static String upload(String string, String service) {
        string = string.replaceAll("([0-9]{1,3}\\.){3}[0-9]{1,3}", "REDACTED");
        if (service.equalsIgnoreCase("HASTE")) {
            try {
                URL youareell = new URL("https://hasteb.in/documents");
                HttpsURLConnection con = (HttpsURLConnection) youareell.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setDoOutput(true);
                try (BufferedOutputStream B = new BufferedOutputStream(con.getOutputStream())) {
                    B.write(string.getBytes("utf8"));
                    B.flush();
                }
                int i = con.getResponseCode();
                final Reader reader = new InputStreamReader(con.getInputStream());
                final BufferedReader br = new BufferedReader(reader);
                String datLine = br.readLine();
                return parseData(datLine, "");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(service.equalsIgnoreCase("GIST")) {
            try {
                URL yoooooarrrrellle = new URL("https://api.github.com/gists");
                HttpsURLConnection con = (HttpsURLConnection) yoooooarrrrellle.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                try (BufferedOutputStream B = new BufferedOutputStream(con.getOutputStream())) {
                    B.write(serializeFile(string).getBytes("utf8"));
                    B.flush();
                }
                int i = con.getResponseCode();
                final Reader reader = new InputStreamReader(con.getInputStream());
                final BufferedReader br = new BufferedReader(reader);
                String datLine = br.readLine();
                return parseData(datLine, "gist");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Something went wrong. Please check the console";
    }

    public static String upload(File sensitiveData, String service) {
        try {
            return upload(new Scanner(sensitiveData).useDelimiter("\\A").next(), service);
        } catch (FileNotFoundException e) {
            return "File missing";
        }
    }

    public static String serializeFile(String payload) {
        JsonObject jo = new JsonObject();
        jo.addProperty("description", "Halp halp file");
        jo.addProperty("public", false);
        JsonObject taco = new JsonObject();
        JsonObject burrito = new JsonObject();
        burrito.addProperty("content", payload);
        taco.add("Content", burrito);
        jo.add("files", taco);
        return jo.toString();
    }

    public static String parseData(String datLine, String service) {
        if (service.equalsIgnoreCase("gist")) {
            JsonElement taco = new JsonParser().parse(datLine);
            JsonObject  burrito = taco.getAsJsonObject();
            return (burrito.get("html_url").toString().replace("\"",""));
        }
        Matcher m = Pattern.compile("^\\{\"key\":\"(.*)\"}$").matcher(datLine);
        if (m.matches()) {
            return "https://hasteb.in/"+m.group(1)+".halp";
        } else return "unknownPaste";
    }

}
