package org.dao;

import org.model.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurncyDao {

    private static Connection con;

    public static List<Currency> currencies = new ArrayList<>();

    public CurncyDao(Connection con)
    {
        this.con=con;
    }

    public List<Currency> getAllProducts()
    {

        String query;
        PreparedStatement pst;
        ResultSet rs;
        try
        {
            query = "SELECT * FROM currency.currenc";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next())
            {

                Currency currency = new Currency();
                currency.setName(rs.getString("currencycode"));

                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.print(currencies);
        return currencies;
    }
}
