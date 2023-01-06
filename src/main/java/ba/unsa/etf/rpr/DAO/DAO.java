package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;

public interface DAO<T> {

    T add(T item);

    void delete(int id);

    List<T> getAll() throws CashRegisterException;


}
