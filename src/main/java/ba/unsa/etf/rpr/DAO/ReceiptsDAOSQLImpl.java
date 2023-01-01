package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Receipts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReceiptsDAOSQLImpl implements ReceiptsDAO {
    private Connection connection;

    public ReceiptsDAOSQLImpl(){
        try{
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Receipts add(Receipts item) {

        String insert = "INSERT INTO Receipts(idReceipts, idProduct, Name, UnitPrice, Quantity, LineTotal) VALUES(?,?,?,?,?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);


            stmt.setInt(1, item.getIdR());
            stmt.setInt(2,item.getIdP());
            stmt.setString(3,item.getName());
            stmt.setDouble(4,item.getUnitPrice());
            stmt.setInt(3,item.getQuantity());
            stmt.setDouble(4,item.getLineTotal());

            stmt.executeUpdate();

            return item;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Receipts WHERE idReceipts = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Receipts> getAll() {

        String query = "SELECT * FROM Receipts";
        List<Receipts> receipts = new ArrayList<Receipts>();

        try{

            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Receipts receipt = new Receipts();

                receipt.setIdR(rs.getInt("idReceipts"));
                receipt.setIdP(rs.getInt("idProduct"));
                receipt.setQuantity(rs.getInt("Quantity"));
                receipt.setLineTotal(rs.getDouble("LineTotal"));

                receipts.add(receipt);
            }

            rs.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return receipts;

    }

    @Override
    public double getTotal(int idr) {
        String query = "SELECT SUM(LineTotal) FROM Receipts WHERE idReceipts = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, idr);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                double total = rs.getDouble(1);

                rs.close();
                return total;

            }
            else return 0;

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
