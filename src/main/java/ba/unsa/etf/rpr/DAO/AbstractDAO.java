package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;
public abstract class AbstractDAO<T extends Idable> implements DAO<T>{
    private static Connection connection = null;
    private String tableName;

    private static void createConnection(){
        if(AbstractDAO.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                AbstractDAO.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

}
