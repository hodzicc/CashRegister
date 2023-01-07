package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

public interface ReceiptsDAO extends DAO<Receipts>{

    Receipts getTotal(int idr) throws CashRegisterException;
}
