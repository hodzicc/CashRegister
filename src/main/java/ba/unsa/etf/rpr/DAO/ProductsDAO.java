package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Products;

public interface ProductsDAO extends DAO<Products> {

    Products getById(int id);
}
