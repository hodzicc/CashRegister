package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

public interface EmployeesDAO extends DAO<Employees>{

    Employees getByUsername(String usr) throws CashRegisterException;

}
