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

    public void validateName(String name) throws CashRegisterException{
        if (name == null || !name.contains(" ") || name.length() > 45 || name.length() < 3 || !name.matches("[A-Za-z]+")){
            throw new CashRegisterException("Name must be between 3 and 45 chars, can't contain numbers");
        }

    }


    public List<Products> getAll() throws CashRegisterException {
        return DAOFactory.productsDAO().getAll();
    }

    public void delete(int id) throws CashRegisterException{
        DAOFactory.productsDAO().delete(id);
    }

    public Products getById(int id) throws CashRegisterException{
        return DAOFactory.productsDAO().getById(id);
    }

    public void update(Products p) throws CashRegisterException{
        validateName(p.getName());
        DAOFactory.productsDAO().update(p);
    }

    public Products add(Products p) throws CashRegisterException{
        validateName(p.getName());
        return DAOFactory.productsDAO().add(p);
    }

}
