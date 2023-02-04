package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;
/**
 * Business logic layer for Receipts
 *
 * @author Amna Hodzic
 */

public class Receipt_TotalManager {


    public List<Receipt_Total> getAll() throws CashRegisterException {
        return DAOFactory.receipt_totalDAO().getAll();
    }

    public void delete(int id) throws CashRegisterException{
        DAOFactory.receipt_totalDAO().delete(id);
    }

    public Receipt_Total getById(int id) throws CashRegisterException{
        return DAOFactory.receipt_totalDAO().getById(id);
    }

    public void update(Receipt_Total r) throws CashRegisterException{
        DAOFactory.receipt_totalDAO().update(r);
    }

    public Receipt_Total add(Receipt_Total r) throws CashRegisterException {
        return DAOFactory.receipt_totalDAO().add(r);
    }


}
