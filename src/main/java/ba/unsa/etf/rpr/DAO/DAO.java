package ba.unsa.etf.rpr.DAO;

import java.util.List;

public interface DAO<T> {

    T getById(int id);

    T add(T item);

    T update(T item);

    void delete(int id);

    List<T> getAll();


}
