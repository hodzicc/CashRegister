package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Receipts;

public interface ReceiptsDAO extends DAO<Receipts>{

    double getTotal(int idr);
}
