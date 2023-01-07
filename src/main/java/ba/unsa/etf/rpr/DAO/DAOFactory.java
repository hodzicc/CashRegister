package ba.unsa.etf.rpr.DAO;

public class DAOFactory {

    private static final EmployeesDAO employeesDAO = EmployeesDAOSQLImpl.getInstance();
    private static final ProductsDAO productsDAO = ProductsDAOSQLImpl.getInstance();
    private static final Receipt_TotalDAO receipt_totalDAO = Receipt_TotalDAOSQLImpl.getInstance();
    private static final ReceiptsDAO receiptsDAO = ReceiptsDAOSQLImpl.getInstance();

    private DAOFactory(){

    }
    public static EmployeesDAO employeesDAO(){
        return employeesDAO;
    }
    public static ProductsDAO productsDAO(){
        return productsDAO;
    }
    public static Receipt_TotalDAO receipt_totalDAO(){
        return receipt_totalDAO;
    }
    public static ReceiptsDAO receiptsDAO(){
        return receiptsDAO;
    }



}
