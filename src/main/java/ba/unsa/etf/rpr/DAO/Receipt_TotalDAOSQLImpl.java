package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;

import java.sql.*;
import java.util.ArrayList;
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
        String query = "SELECT * FROM Receipt_Total";
        List<Receipt_Total> receipts = new ArrayList<Receipt_Total>();

        try{

            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Receipt_Total receipt = new Receipt_Total();

                receipt.setId(rs.getInt("ID_receipt"));
                receipt.setTotal(rs.getDouble("Total"));
                receipt.setDate(rs.getDate("Date"));
                receipt.setEid(rs.getInt("Employee_ID"));

                receipts.add(receipt);
            }

            rs.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return receipts;
    }

    @Override
    public Receipt_Total update(Receipt_Total item) {

        String insert = "UPDATE Receipt_Total SET Total = ?, Date = ?, Employee_ID = ? WHERE ID_receipt = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setDouble(1, item.getTotal());
            stmt.setDate(2, (Date) item.getDate());
            stmt.setInt(3,item.getEid());

            stmt.executeUpdate();

            return item;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Receipt_Total getById(int id) {

        String query = "SELECT * FROM Receipt_Total WHERE ID_receipt = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Receipt_Total receipt = new Receipt_Total();

                receipt.setId(rs.getInt("ID_receipt"));
                receipt.setTotal(rs.getDouble("Total"));
                receipt.setDate(rs.getDate("Date"));
                receipt.setEid(rs.getInt("Employee_ID"));

                rs.close();
                return receipt;

            }
            else return null;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;


    }
}
