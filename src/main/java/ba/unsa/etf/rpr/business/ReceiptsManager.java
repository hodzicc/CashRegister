package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;
/**
 * Business logic layer for Receipt
 *
 * @author Amna Hodzic
 */

public class ReceiptsManager {

    /**
     * methods for writing and reading db records
     * table Receipts
     * @return
     * @throws CashRegisterException
     */

    public List<Receipts> getAll() throws CashRegisterException {
        return DAOFactory.receiptsDAO().getAll();
    }

    public void delete(int id) throws CashRegisterException{
        DAOFactory.receiptsDAO().delete(id);
    }

    public Receipts getById(int id) throws CashRegisterException{
        return DAOFactory.receiptsDAO().getById(id);
    }

    public void update(Receipts r) throws CashRegisterException{
        DAOFactory.receiptsDAO().update(r);
    }

    public Receipts add(Receipts r) throws CashRegisterException{
        return DAOFactory.receiptsDAO().add(r);
    }

    public Double getTotal() throws CashRegisterException{
         return DAOFactory.receiptsDAO().getTotal();
    }




}
