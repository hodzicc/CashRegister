package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ReceiptsDAOSQLImpl implements ReceiptsDAO {
    private Connection connection;

    public ReceiptsDAOSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8583585", "sql8583585", "F4lcQkfPLG");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Receipts add(Receipts item) {
        return null;
    }

    @Override
    public Receipts update(Receipts item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Receipts> getAll() {
        return null;
    }
}
