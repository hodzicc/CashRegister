package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;

import java.sql.*;
import java.util.List;

public class ReceiptsDAOSQLImpl implements ReceiptsDAO {
    private Connection connection;

    public ReceiptsDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Receipts add(Receipts item) {

        String insert = "INSERT INTO Receipts(idReceipts, idProduct, Quantity, LineTotal) VALUES(?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);


            stmt.setInt(1, item.getIdR());
            stmt.setInt(2,item.getIdP());
            stmt.setInt(3,item.getQuantity());
            stmt.setDouble(4,item.getLineTotal());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            item.setIdR(rs.getInt(1));
            return item;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Receipts> getAll() {
        return null;
    }
}
