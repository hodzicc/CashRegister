package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;
/**
 * Business logic layer for Products
 *
 * @author Amna Hodzic
 */

public class ProductsManager {
    /**
     * method for name validation
     * @param name
     * @throws CashRegisterException
     */

    public void validateName(String name) throws CashRegisterException{
        if (name == null || name.length() > 45 || name.length() < 3 || name.matches("^[A-Za-z]")){
            throw new CashRegisterException("Name must be between 3 and 45 chars, can't contain numbers");
        }

    }


    /**
     * method that returns list of all products from db
     * @return
     * @throws CashRegisterException
     */
    public List<Products> getAll() throws CashRegisterException {
        return DAOFactory.productsDAO().getAll();
    }

    /**
     * method that deletes product with given id from db
     * @param id
     * @throws CashRegisterException
     */
    public void delete(int id) throws CashRegisterException{
        DAOFactory.productsDAO().delete(id);
    }

    /**
     * method that returns product from db
     * @param id
     * @return
     * @throws CashRegisterException
     */

    public Products getById(int id) throws CashRegisterException{
        return DAOFactory.productsDAO().getById(id);
    }

    /**
     * method that updates Product received as a parametar in db
     * @param p
     * @throws CashRegisterException
     */
    public void update(Products p) throws CashRegisterException{
        validateName(p.getName());
        DAOFactory.productsDAO().update(p);
    }

    /**
     * method that adds new Product to the db
     * @param p
     * @return
     * @throws CashRegisterException
     */
    public Products add(Products p) throws CashRegisterException{
        validateName(p.getName());
        return DAOFactory.productsDAO().add(p);
    }

}
