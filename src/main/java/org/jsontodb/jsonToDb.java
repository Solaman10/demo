package org.jsontodb;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class jsonToDb {

    static final String JDBC_DRIVER = "com.mysql.jdbc.driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/apidata";

    static final String USER = "root";
    static final String PASS = "Solaimani@4";

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, SQLException, IOException {

        Connection con=null;
        con= DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement ps = con.prepareStatement("INSERT INTO data values(?,?,?,?,?)");
        String date = "2022-09-05";

        String baseCrncy = "EUR";

        String GET_URL = "https://api.exchangerate.host/"+date;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            Set set = obj.getJSONObject("rates").keySet();
            ArrayList<String> curncies = new ArrayList<>(set);
            Double exchngRate;
            int id=1;

            Iterator<String> itr = curncies.iterator();
            while (itr.hasNext())
            {
                String curncCode = itr.next();
                exchngRate = obj.getJSONObject("rates").getDouble(curncCode);
                // curncName = "\"" + curncName + "\"";
//                System.out.println(curncName);
                ps.setInt(1,id);
                ps.setString(2,baseCrncy);
                ps.setString(3,date);
                ps.setString(4,curncCode);
                ps.setDouble(5,exchngRate);
                ++id;
                ps.executeUpdate();
            }
        }
    }
}
