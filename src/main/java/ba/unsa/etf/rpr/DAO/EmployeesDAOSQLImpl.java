package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class EmployeesDAOSQLImpl extends AbstractDAO<Employees> implements EmployeesDAO{

    private static  EmployeesDAOSQLImpl instance = null;

    private EmployeesDAOSQLImpl(){
       super("Employees");
    }

    public static EmployeesDAOSQLImpl getInstance(){
        if(instance==null)
            instance = new EmployeesDAOSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Employees row2object(ResultSet rs) throws CashRegisterException {
        try {
            Employees employee = new Employees();

            employee.setId(rs.getInt("idEmployee"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password"));
            employee.setName(rs.getString("Name"));
            employee.setAdmin(rs.getBoolean("Admin_access"));

            return employee;
        } catch (SQLException e) {
            throw new CashRegisterException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Employees object) {
        return null;
    }

    @Override
    public Employees getById(int id) {

         String query = "SELECT * FROM Employees WHERE idEmployee = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Employees employee = new Employees();

                employee.setId(rs.getInt("idEmployee"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setName(rs.getString("Name"));
                employee.setAdmin(rs.getBoolean("Admin_access"));

                rs.close();
                return employee;

            }
            else return null;

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employees add(Employees item) {

        String insert = "INSERT INTO Employees(username, password, Name, Admin_access) VALUES(?,?,?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);

            stmt.setString(1, item.getUsername());
            stmt.setString(2,item.getPassword());
            stmt.setString(3,item.getName());
            stmt.setBoolean(4,item.isAdmin());

            stmt.execute();

            return null;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employees update(Employees item) {

        String insert = "UPDATE Employees SET username = ?, password = ?, Name = ?, Admin_access = ? WHERE idEmployee = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, item.getUsername());
            stmt.setString(2,item.getPassword());
            stmt.setString(3,item.getName());
            stmt.setBoolean(4,item.isAdmin());

            stmt.executeUpdate();

            return item;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void delete(int id) {

        String delete = "DELETE FROM Employees WHERE idEmployee = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Employees> getAll() {

        String query = "SELECT * FROM Employees";
        List<Employees> employees = new ArrayList<Employees>();

        try{

            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Employees employee = new Employees();

                employee.setId(rs.getInt("idEmployee"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setName(rs.getString("Name"));
                employee.setAdmin(rs.getBoolean("Admin_access"));

                employees.add(employee);
            }

            rs.close();

        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }

        return employees;
    }

    @Override
    public Employees getByUsername(String usr) {

        String query = "SELECT * FROM Employees WHERE username = ?";

        try {

            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, usr);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Employees employee = new Employees();

                employee.setId(rs.getInt("idEmployee"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setName(rs.getString("Name"));
                employee.setAdmin(rs.getBoolean("Admin_access"));

                rs.close();
                return employee;

            }
            else return null;

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
