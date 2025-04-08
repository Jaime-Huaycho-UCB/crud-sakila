package app.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySql {
    public static Connection connection = null;

    private String name = "sakila";
    private String host = "localhost";
    private String user = "root";
    private String password = "";
    private int port = 3306;

    private String getUrl(){
        return "jdbc:mysql://"+host+":"+port+"/"+name;
    }

    public MySql(){}

    public void connect(){
        try {
            Connection connection = DriverManager.getConnection(getUrl(), user, password);
            MySql.connection = connection;
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
