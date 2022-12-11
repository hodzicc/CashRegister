package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;

import java.sql.*;
import java.util.List;

public class EmployeesDAOSQLImpl implements EmployeesDAO{

    private Connection connection;

    public EmployeesDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
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


        return null;
    }

    @Override
    public Employees update(Employees item) {
        return null;


    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Employees> getAll() {
        return null;
    }

    @Override
    public Employees getByUsername(String usr) {
        return null;
    }
}
