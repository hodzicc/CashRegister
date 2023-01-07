package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.*;

public class ReceiptsDAOSQLImpl  extends AbstractDAO<Receipts> implements ReceiptsDAO {
    private static ReceiptsDAOSQLImpl instance = null;

    private ReceiptsDAOSQLImpl(){
        super("Receipts");
    }

    public static ReceiptsDAOSQLImpl getInstance(){
        if(instance==null)
            instance = new ReceiptsDAOSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
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
            stmt.setInt(5,item.getQuantity());
            stmt.setDouble(6,item.getLineTotal());

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
    public Receipts row2object(ResultSet rs) throws CashRegisterException {
        try {
            Receipts receipt = new Receipts();

            receipt.setIdR(rs.getInt("idReceipts"));
            receipt.setIdP(rs.getInt("idProduct"));
            receipt.setName(rs.getString("Name"));
            receipt.setUnitPrice(rs.getDouble("UnitPrice"));
            receipt.setQuantity(rs.getInt("Quantity"));
            receipt.setLineTotal(rs.getDouble("LineTotal"));

            return receipt;

        } catch (SQLException e) {
            throw new CashRegisterException(e.getMessage(), e);
        }

    }

    @Override
    public Map<String, Object> object2row(Receipts object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("idReceipts", object.getIdR());
        row.put("idProduct", object.getIdP());
        row.put("Name", object.getName());
        row.put("UnitPrice", object.getUnitPrice());
        row.put("Quantity", object.getQuantity());
        row.put("LineTotal", object.getLineTotal());
        return row;
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
                receipt.setName(rs.getString("Name"));
                receipt.setUnitPrice(rs.getDouble("UnitPrice"));
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
