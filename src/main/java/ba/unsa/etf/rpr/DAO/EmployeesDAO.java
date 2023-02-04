package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

/**
 * dao interface for employees domain bean
 */
public interface EmployeesDAO extends DAO<Employees>{

    /**
     * get entity from db with given username
     * @param usr username of entity
     * @return Entity from db
     * @throws CashRegisterException
     */
    Employees getByUsername(String usr) throws CashRegisterException;

}
