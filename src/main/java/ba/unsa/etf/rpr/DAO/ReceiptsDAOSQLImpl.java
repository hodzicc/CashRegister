package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of dao
 * singleton pattern used
 * @author Amna Hodzic
 */
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
    public Receipts row2object(ResultSet rs) throws CashRegisterException {
        try {
            Receipts receipt = new Receipts();

            receipt.setIdR(rs.getInt("id"));
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
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", object.getIdR());
        row.put("idProduct", object.getIdP());
        row.put("Name", object.getName());
        row.put("UnitPrice", object.getUnitPrice());
        row.put("Quantity", object.getQuantity());
        row.put("LineTotal", object.getLineTotal());
        return row;
    }


    public Double getTotal() throws CashRegisterException {
        return executeQueryDouble("SELECT SUM(LineTotal) FROM Receipts");
}}
