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

    @Override
    public Products getById(int id)  {
        String query = "SELECT * FROM Products WHERE ID_Product = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Products product = new Products();

                product.setId(rs.getInt("ID_product"));
                product.setName(rs.getString("Product_name"));
                product.setPrice(rs.getDouble("Price"));
                product.setLeftInStock(rs.getInt("LeftInStock"));

                rs.close();
                return product;

            }
            else return null;

        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }

        return null;

    }

    @Override
    public Products add(Products item) {
        String insert = "INSERT INTO Products(Product_name, Price, LeftInStock) VALUES(?,?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, item.getName());
            stmt.setDouble(2,item.getPrice());
            stmt.setInt(3,item.getLeftInStock());
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
    public Products update(Products item) {

        String insert = "UPDATE Products SET Product_name = ?, Price = ?, LeftInStock = ? WHERE ID_product = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getPrice());
            stmt.setObject(3, item.getLeftInStock());
            stmt.setObject(4, item.getId());
            stmt.executeUpdate();

            return item;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delete(int id) {

        String delete = "DELETE FROM Products WHERE ID_Product = ?";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Products> getAll() {

         String query = "SELECT * FROM Products";
        List<Products> products = new ArrayList<Products>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Products product = new Products();
                product.setId(rs.getInt("ID_product"));
                product.setName(rs.getString("Product_name"));
                product.setPrice(rs.getDouble("Price"));
                product.setLeftInStock(rs.getInt("LeftInStock"));
                products.add(product);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return products;
    }




}
