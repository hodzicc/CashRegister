package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.*;
public abstract class AbstractDAO<T extends Idable> implements DAO<T>{
    private static Connection connection = null;
    private String tableName;

    public AbstractDAO(String tableName) {
        this.tableName = tableName;
        if(connection==null) createConnection();
    }

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

    public static Connection getConnection(){
        return AbstractDAO.connection;
    }

    public static void closeConnection() {
        System.out.println("Closing connection");
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }

    public abstract T row2object(ResultSet rs) throws CashRegisterException;
    public abstract Map<String, Object> object2row(T object);

    public List<T> executeQuery(String query, Object[] params) throws CashRegisterException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new CashRegisterException(e.getMessage(), e);
        }
    }

    public T executeQueryUnique(String query, Object[] params) throws CashRegisterException{
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new CashRegisterException("Object not found");
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }



}
