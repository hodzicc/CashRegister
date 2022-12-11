package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipt_Total;

import java.sql.*;
import java.util.List;

public class Receipt_TotalDAOSQLImpl implements  Receipt_TotalDAO{

    private Connection connection;

    public Receipt_TotalDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Receipt_Total add(Receipt_Total item) {
        String insert = "INSERT INTO Receipt_total(ID_receipt, Total, Date, Employee_ID) VALUES(?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);


            stmt.setInt(1, item.getId());
            stmt.setDouble(2,item.getTotal());
            stmt.setDate(3, (Date) item.getDate());
            stmt.setInt(4,item.getEid());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            item.setId(rs.getInt(1));
            return item;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Receipt_Total WHERE ID_receipt = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Receipt_Total> getAll() {
        return null;
    }

    @Override
    public Receipt_Total update(Receipt_Total item) {
        return null;
    }

    @Override
    public Receipt_Total getById(int id) {
        return null;
    }
}
