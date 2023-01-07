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
        Map<String, Object> row = new TreeMap<>();
        row.put("idEmployee", object.getId());
        row.put("username", object.getName());
        row.put("password", object.getPassword());
        row.put("Name", object.getName());
        row.put("Admin_access", object.isAdmin());
        return row;
    }
    @Override
    public Employees getByUsername(String usr) throws CashRegisterException {

        return executeQueryUnique("SELECT * FROM Employees WHERE username = ?", new Object[]{usr});

    }
}
