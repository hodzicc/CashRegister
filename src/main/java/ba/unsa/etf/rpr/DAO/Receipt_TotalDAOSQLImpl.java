package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Receipt_TotalDAOSQLImpl extends AbstractDAO<Receipt_Total> implements  Receipt_TotalDAO{

    private static Receipt_TotalDAOSQLImpl instance = null;

    private Receipt_TotalDAOSQLImpl(){
        super("Receipt_total");
    }

    public static Receipt_TotalDAOSQLImpl getInstance(){
        if(instance==null)
            instance = new Receipt_TotalDAOSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Receipt_Total add(Receipt_Total item) {
        String insert = "INSERT INTO Receipt_total(Total, Date) VALUES(?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);


            stmt.setDouble(1,item.getTotal());
            stmt.setDate(2, (Date) item.getDate());

            stmt.executeUpdate();

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

        String insert = "UPDATE Receipt_Total SET Total = ?, Date = ? WHERE ID_receipt = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setDouble(1, item.getTotal());
            stmt.setDate(2, (Date) item.getDate());

            stmt.executeUpdate();

            return item;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Receipt_Total row2object(ResultSet rs) throws CashRegisterException {
        try {
            Receipt_Total receipt = new Receipt_Total();

            receipt.setId(rs.getInt("ID_receipt"));
            receipt.setTotal(rs.getDouble("Total"));
            receipt.setDate(rs.getDate("Date"));
            return receipt;

        } catch (SQLException e) {
            throw new CashRegisterException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Receipt_Total object) {

        Map<String, Object> row = new TreeMap<>();
        row.put("ID_receipt", object.getId());
        row.put("Total", object.getTotal());
        row.put("Date", object.getDate());

        return row;
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
