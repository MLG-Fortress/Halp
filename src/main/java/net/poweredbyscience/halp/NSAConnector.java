package net.poweredbyscience.halp;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public static void main(String[] args) {
        upload("My milkshake brings all the boys to the yard.");
    }

    public static String upload(String string) {
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
            return parseData(con.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "LaxWasHere";
    }

    public static String upload(File sensitiveData) {
        try {
            return upload(new Scanner(sensitiveData).useDelimiter("\\A").next());
        } catch (FileNotFoundException e) {
            return "File missing";
        }
    }

    public static String parseData(InputStream in) {
        try {
            final Reader reader = new InputStreamReader(in);
            final BufferedReader br = new BufferedReader(reader);
            String datLine = br.readLine();
            Matcher m = Pattern.compile("^\\{\"key\":\"(.*)\"}$").matcher(datLine);
            if (m.matches()) {
                return "https://hasteb.in/"+m.group(1);
            } else return "unknownPaste";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

}
