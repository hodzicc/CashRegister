package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductsManagerTest {
    private ProductsManager productsManager;
    private Products product;
    private ProductsDAOSQLImpl productDaoSQLMock;
    private List<Products> products;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        productsManager = Mockito.mock(ProductsManager.class);
        product = new Products();
        product.setName("banana");
        product.setPrice(2.3);
        product.setId(40);
        product.setLeftInStock(20);


        productDaoSQLMock = Mockito.mock(ProductsDAOSQLImpl.class);
        products = new ArrayList<>();
        products.addAll(Arrays.asList(new Products(41,"ananas", 4.5, 30), new Products(42, "krompir", 2.0, 50)));
    }


    @Test
    void validateName() throws CashRegisterException {
        String incorrectName = "A222";
        Mockito.doCallRealMethod().when(productsManager).validateName(incorrectName);
        CashRegisterException crException1 = assertThrows(CashRegisterException.class, () -> {
            productsManager.validateName(incorrectName);}, "Name must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Name must be between 3 and 45 chars, can't contain numbers", crException1.getMessage());
    }

    @Test
    void getAll() throws CashRegisterException {
        when(productsManager.getAll()).thenReturn(products);
        Assertions.assertEquals(products, productsManager.getAll());
    }

    @Test
    void addExisting() throws CashRegisterException {
        when(productsManager.getAll()).thenReturn(products);

        Products pro = new Products(41,"ananas", 4.5, 30);
        Mockito.doCallRealMethod().when(productsManager).add(pro);

        CashRegisterException exception = Assertions.assertThrows(CashRegisterException.class, () -> {productsManager.add(pro);});

        Assertions.assertEquals("Already exists", exception.getMessage());

        Mockito.verify(productsManager).add(pro);
    }


    @Test
    void add() throws CashRegisterException {
        Products newprod = new Products(43,"narandza", 2.5, 40);
        productsManager.add(newprod);

        Assertions.assertTrue(true);
        Mockito.verify(productsManager).add(newprod);
    }
}