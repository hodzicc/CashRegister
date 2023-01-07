package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.DAOFactory;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;

import java.util.List;

public class ProductsManager {


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
        DAOFactory.productsDAO().update(p);
    }

    public Products add(Products p) throws CashRegisterException{
        return DAOFactory.productsDAO().add(p);
    }

}
