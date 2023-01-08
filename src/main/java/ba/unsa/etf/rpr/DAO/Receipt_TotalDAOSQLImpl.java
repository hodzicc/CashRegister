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
    public Receipt_Total row2object(ResultSet rs) throws CashRegisterException {
        try {
            Receipt_Total receipt = new Receipt_Total();

            receipt.setId(rs.getInt("id"));
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
        row.put("id", object.getId());
        row.put("Total", object.getTotal());
        row.put("Date", object.getDate());

        return row;
    }

}
