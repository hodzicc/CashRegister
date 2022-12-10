package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Products;

import java.sql.*;

public class ProductsDAOSQLImpl {
    private Connection connection;

    public ProductsDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Products getById(int id)  {
        String query = "SELECT * FROM products WHERE id = ?";
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


}
