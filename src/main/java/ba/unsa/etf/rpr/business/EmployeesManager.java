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

    public void validateUsername(String name) throws CashRegisterException{
        if (name == null || name.length() > 45 || name.length() < 3 || name.matches("^[A-Za-z]")){
            throw new CashRegisterException("Username must be between 3 and 45 chars, can't contain numbers");
        }
    }

    public void validateFullName(String name) throws CashRegisterException{
        if (name == null || !name.contains(" ") || name.length() > 45 || name.length() < 3 || name.matches("^[A-Za-z]")){
            throw new CashRegisterException("Name must be between 3 and 45 chars, can't contain numbers");
        }

        }

    public void validatePassword(String pass) throws CashRegisterException{
        if (pass == null || pass.length() > 20 || pass.length() < 5){
            throw new CashRegisterException("Password must be between 5 and 29 chars");
        }

    }

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
        validateFullName(e.getName());
        validateUsername(e.getUsername());
        validatePassword(e.getPassword());
        DAOFactory.employeesDAO().update(e);
    }

    public Employees add(Employees e) throws CashRegisterException{
        validateFullName(e.getName());
        validateUsername(e.getUsername());
        validatePassword(e.getPassword());
        return DAOFactory.employeesDAO().add(e);
    }

    public Employees getByUsername(String usr) throws CashRegisterException{
        return DAOFactory.employeesDAO().getByUsername(usr);
    }







}
