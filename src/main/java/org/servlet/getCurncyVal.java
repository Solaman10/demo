package org.servlet;


import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author solai
 */
public class getCurncyVal extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
        try
        {
            double amount= Double.parseDouble(req.getParameter("amount"));
            String fromCurrency=req.getParameter("fromCurrency");
            String toCurrency=req.getParameter("toCurrency");
            String thisDate = req.getParameter("thisDate");
            PrintWriter out = res.getWriter();
            out.println(thisDate);
            double convertedAmount=getConvertedAmount(fromCurrency,toCurrency,thisDate);
            /*ArrayList<String> fromList = getDropList();
            ArrayList<String> toList = getDropList(fromCurrency);
            req.setAttribute("paramSetFrom", fromList);
            req.getRequestDispatcher("index.jsp").forward(req, res);
            req.setAttribute("paramSetTo", toList);
            req.getRequestDispatcher("index.jsp").forward(req, res);*/
            if(convertedAmount!=-1.0) {
                req.setAttribute("param", convertedAmount*amount + " " + toCurrency);
            }else {
                req.setAttribute("param", "Sorry, Currency data for choosen date is not available");
            }
            req.getRequestDispatcher("index.jsp").forward(req,res);
        }
        catch (ServletException ex)
        {
            Logger.getLogger(getCurncyVal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*private ArrayList<String> getDropList(String frmCurncy) throws IOException {

        String GET_URL = "https://api.exchangerate.host/latest?="+frmCurncy;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        ArrayList<String> toList = new ArrayList<>();

        if(responseCode == HttpURLConnection.HTTP_OK)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine=in.readLine())!=null)
            {
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            Set<String> set= obj.getJSONObject("rates").keySet();
            set.remove(frmCurncy);
            toList = new ArrayList<>(set);
        }
        return toList;
    }

    private ArrayList<String> getDropList() throws IOException {

        String GET_URL = "https://api.exchangerate.host/latest";
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        ArrayList<String> codeList = new ArrayList<>();

        if(responseCode == HttpURLConnection.HTTP_OK)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine=in.readLine())!=null)
            {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            Set<String> set= obj.getJSONObject("rates").keySet();
            codeList = new ArrayList<>(set);
            */
    /*for(int i=0;i<codeList.size();i++)
            {
                System.out.println(codeList.get(i));
            }
        }
        return codeList;
    }*/

    static int id=169;
    public static double getConvertedAmount(String fromCurrency, String toCurrency, String thisDate) throws IOException, SQLException {

        Double reqExRate = -1.0;
        try {
            //final String JDBC_DRIVER = "com.mysql.jdbc.driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/apidata";
            final String USER = "root";
            final String PASS = "Solaimani@4";

            Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();//(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "select * from data";
            ResultSet result = stmt.executeQuery(query);
        /*String frmcrncy = "AED";
        String tocrncy = "INR";
        String date = "2022-09-05";
        String basecrncy = "EUR";*/
            Double fromExRate = null;
            Double toExRate = null;
            int fromFlag=0,toFlag=0,count;
//            Double reqExRate = -1.0;
            String date;

//            result.afterLast();
            while (result.next()) {
//                id = result.getInt(1);
                count = result.getInt(1);
                date = result.getString(3);
                if (fromExRate == null) {
                    if (thisDate.equals(result.getString(3)) && fromCurrency.equals(result.getString(4))) {
                        fromExRate = result.getDouble(5);
                        fromFlag =1;
                    }
                }
                if (toExRate == null) {
                    if (thisDate.equals(result.getString(3)) && toCurrency.equals(result.getString(4))) {
                        toExRate = result.getDouble(5);
                        toFlag=1;
                    }
                }
                if((!date.equals(thisDate)) && result.getInt(1)==id)
                {
                    Connection conn=null;
                    conn= DriverManager.getConnection(DB_URL,USER,PASS);
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO data values(?,?,?,?,?)");
//                    String date = "2022-09-05";
                    String baseCrncy = "EUR";

                    String GET_URL = "https://api.exchangerate.host/"+thisDate;
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

                        Iterator<String> itr = curncies.iterator();
                        while (itr.hasNext())
                        {
                            ++id;
                            String curncCode = itr.next();
                            exchngRate = obj.getJSONObject("rates").getDouble(curncCode);
                            // curncName = "\"" + curncName + "\"";
//                          System.out.println(curncName);
                            ps.setInt(1,id);
                            ps.setString(2,baseCrncy);
                            ps.setString(3,thisDate);
                            ps.setString(4,curncCode);
                            ps.setDouble(5,exchngRate);
                            ps.executeUpdate();
                        }
                    }
                    reqExRate = getConvertedAmount(fromCurrency,toCurrency,thisDate);
                    break;
                }
                else if(fromFlag==1 && toFlag==1)
                {
                    break;
                }
//              System.out.println(result.getString(1)+"   "+result.getString(2)+"   "+result.getString(3)+"   "+result.getDouble(4));
            }
            if(fromExRate!=null && toExRate!=null) {
                reqExRate = toExRate / fromExRate;
            }

//        System.out.println(reqExRate*amount);
            result.close();
            stmt.close();
            con.close();

            /*CurncyDao cur = new CurncyDao(DbCon.getConnection());
            cur.getAllProducts();
            Iterator<Currency> curn = cur.currencies.iterator();
            while(curn.hasNext())
            {
                System.out.print(curn.next().getName()+" ");
            }*/
        }
        catch (SQLException e)
        {
            return -1.0;
        } catch (ClassNotFoundException e) {
            return -1.0;
        }

        if(reqExRate!=-1.0)
            return reqExRate;
        else
            return -1.0;
    }
}