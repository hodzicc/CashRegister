package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Business logic layer for Employees
 *
 * @author Amna Hodzic
 */

public class EmployeesManager {

    /**
     * method for username validation
     * @param name
     * @throws CashRegisterException
     */
    public void validateUsername(String name) throws CashRegisterException{
        if (name == null || name.length() > 45 || name.length() < 3 || !Pattern.compile("[a-zA-Z]*").matcher(name).matches()){
            throw new CashRegisterException("Username must be between 3 and 45 chars, can't contain numbers");
        }
    }

    /**
     * method for users full name validation
     * @param name
     * @throws CashRegisterException
     */

    public void validateFullName(String name) throws CashRegisterException{
        if (name == null || name.length() > 45 || name.length() < 3){
            throw new CashRegisterException("Name must be between 3 and 45 chars, can't contain numbers");
        }

        }

    /**
     * method for password validation
      * @param pass
     * @throws CashRegisterException
     */
    public void validatePassword(String pass) throws CashRegisterException{
        if (pass == null || pass.length() > 20 || pass.length() < 5){
            throw new CashRegisterException("Password must be between 5 and 20 chars");
        }

    }

    /**
     * method that returns list of all employees from db
     * @return
     * @throws CashRegisterException
     */
    public List<Employees> getAll() throws CashRegisterException {
        return DAOFactory.employeesDAO().getAll();
    }

    /**
     * method that deletes employee of given id from db
     * @param id
     * @throws CashRegisterException
     */
    public void delete(int id) throws CashRegisterException{
        DAOFactory.employeesDAO().delete(id);
    }

    /**
     * method that returns Employee object with given id
     * @param id
     * @return
     * @throws CashRegisterException
     */
    public Employees getById(int id) throws CashRegisterException{
        return DAOFactory.employeesDAO().getById(id);
    }

    /**
     * method that updates given Employee in db
     * @param e
     * @throws CashRegisterException
     */
    public void update(Employees e) throws CashRegisterException{
        validateFullName(e.getName());
        validateUsername(e.getUsername());
        validatePassword(e.getPassword());
        DAOFactory.employeesDAO().update(e);
    }

    /**
     * method for adding new Employee to db
     * @param e
     * @return
     * @throws CashRegisterException
     */
    public Employees add(Employees e) throws CashRegisterException{
        validateFullName(e.getName());
        validateUsername(e.getUsername());
        validatePassword(e.getPassword());
        return DAOFactory.employeesDAO().add(e);
    }

    /**
     * method that returns Employee with username thats passed as a parametar
     * @param usr
     * @return
     * @throws CashRegisterException
     */
    public Employees getByUsername(String usr) throws CashRegisterException{
        return DAOFactory.employeesDAO().getByUsername(usr);
    }







}
