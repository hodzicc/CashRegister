package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

/**
 * dao interface for receipts domain bean
 */
public interface ReceiptsDAO extends DAO<Receipts>{
    /**
     * @return sum of all totals from receipts table
     * @throws CashRegisterException
     */
    Double getTotal() throws CashRegisterException;
}
