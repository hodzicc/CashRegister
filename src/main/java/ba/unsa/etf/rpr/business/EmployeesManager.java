package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;

/**
 * Business logic layer for Employees
 *
 * @author Amna Hodzic
 */

public class EmployeesManager {


    public List<Employees> getAll() throws CashRegisterException {
        return DAOFactory.employeesDAO().getAll();
    }

    public void delete(int id) throws CashRegisterException{
        DAOFactory.employeesDAO().delete(id);
    }

    public Employees getById(int id) throws CashRegisterException{
        return DAOFactory.employeesDAO().getById(id);
    }

    public void update(Employees e) throws CashRegisterException{
        DAOFactory.employeesDAO().update(e);
    }

    public Employees add(Employees e) throws CashRegisterException{
        return DAOFactory.employeesDAO().add(e);
    }

    public Employees getByUsername(String usr) throws CashRegisterException{
        return DAOFactory.employeesDAO().getByUsername(usr);
    }







}
