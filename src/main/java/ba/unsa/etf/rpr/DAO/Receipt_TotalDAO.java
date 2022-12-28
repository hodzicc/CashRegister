package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Receipt_Total;

public interface Receipt_TotalDAO extends DAO<Receipt_Total> {

    Receipt_Total update(Receipt_Total item);
    Receipt_Total getById(int id);

}