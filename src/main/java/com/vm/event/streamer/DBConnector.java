package com.vm.event.streamer;

import com.vm.event.streamer.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBConnector {
    private static Connection connection = null;

    private final static String QUERY = "insert into wawa_org values(?,?,?,?,?)";

    private DBConnector() {
    }

    public static Connection createConnection(){
        if(connection == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/event_org","root","Narsingarao4");
            }catch(Exception e){ System.out.println(e);}
        }
        return connection;
    }

    public static boolean dbcommit(Product product) throws Exception {
        createConnection();
        PreparedStatement stmt=connection.prepareStatement(QUERY);
        stmt.setInt(1,product.getId());
        stmt.setString(2,product.getCategory());
        stmt.setDouble(3,product.getPrice());
        stmt.setString(4, product.getPricingModel());
        stmt.setString(5,product.getAttribute2());
        boolean result = stmt.execute();
        connection.commit();
        return result;
    }
}
