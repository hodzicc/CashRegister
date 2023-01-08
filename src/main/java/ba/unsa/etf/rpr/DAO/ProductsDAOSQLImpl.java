package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.DAO.ProductsDAO;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.*;

public class ProductsDAOSQLImpl extends AbstractDAO<Products> implements ProductsDAO {
    private static ProductsDAOSQLImpl instance = null;

    private ProductsDAOSQLImpl(){
          super("Products");
    }

    public static ProductsDAOSQLImpl getInstance(){
        if(instance==null)
            instance = new ProductsDAOSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Products row2object(ResultSet rs) throws CashRegisterException {

        try {
            Products product = new Products();

            product.setId(rs.getInt("id"));
            product.setName(rs.getString("Product_name"));
            product.setPrice(rs.getDouble("Price"));
            product.setLeftInStock(rs.getInt("LeftInStock"));

            return product;


        } catch (SQLException e) {
            throw new CashRegisterException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Products object) {

         Map<String, Object> row = new TreeMap<>();
         row.put("id", object.getId());
        row.put("Product_name", object.getName());
        row.put("Price", object.getPrice());
        row.put("LeftInStock", object.getLeftInStock());

        return row;

    }





}
