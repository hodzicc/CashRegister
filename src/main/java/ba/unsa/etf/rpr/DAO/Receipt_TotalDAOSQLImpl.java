package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipt_Total;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Receipt_TotalDAOSQLImpl implements  Receipt_TotalDAO{

    private Connection connection;

    public Receipt_TotalDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Receipt_Total add(Receipt_Total item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Receipt_Total> getAll() {
        return null;
    }

    @Override
    public Receipt_Total update(Receipt_Total item) {
        return null;
    }

    @Override
    public Receipt_Total getById(int id) {
        return null;
    }
}
