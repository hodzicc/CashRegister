package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;

public interface DAO<T> {

    T add(T item) throws CashRegisterException;

    void delete(int id) throws CashRegisterException;

    List<T> getAll() throws CashRegisterException;


}
