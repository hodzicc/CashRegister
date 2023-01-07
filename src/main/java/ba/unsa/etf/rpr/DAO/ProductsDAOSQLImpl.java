package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.DAO.ProductsDAO;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ProductsDAOSQLImpl extends AbstractDAO<Products> implements ProductsDAO {
    private static ProductsDAOSQLImpl instance = null;

    private ProductsDAOSQLImpl(){
          super("Products");
    }

    @Override
    public Products row2object(ResultSet rs) throws CashRegisterException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Products object) {
        return null;
    }





}
