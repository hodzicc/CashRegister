package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;

/**
 * root interface for all dao classes
 * @param <T>
 */
public interface DAO<T> {

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    T add(T item) throws CashRegisterException;

    /**
     * Hard delete of item from database with given id
     * @param id - primary key of entity
     */
    void delete(int id) throws CashRegisterException;

    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<T> getAll() throws CashRegisterException;

    /**
     * Fully updates entity in database based on id (primary) match.
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     */
    T update(T item) throws CashRegisterException;

    /**
     * get entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     */
    T getById(int id) throws CashRegisterException;


}
