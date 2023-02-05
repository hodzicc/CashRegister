package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employees;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeesManagerTest {
    private EmployeesManager employeesManager;
    private Employees employee;
    private EmployeesDAOSQLImpl employeeDaoSQLMock;
    private List<Employees> employees;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        employeesManager = Mockito.mock(EmployeesManager.class);
        employee = new Employees();
        employee.setName("Mujo Mujic");
        employee.setId(30);
        employee.setUsername("mujo");
        employee.setPassword("blabla123");
        employee.setAdmin(true);

        employeeDaoSQLMock = Mockito.mock(EmployeesDAOSQLImpl.class);
        employees = new ArrayList<>();
        employees.addAll(Arrays.asList(new Employees(31,"Haso Hasic", "haso", "haso12354",false), new Employees(32, "Semsa Semsic", "semsa", "semsa1234", true)));
    }

    @Test
    void validateUsername() throws CashRegisterException {
        String correctName = "mujo";
        try {
            Mockito.doCallRealMethod().when(employeesManager).validateUsername(correctName);
        } catch (CashRegisterException e) {
            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String incorrectNameShort = "A";
        Mockito.doCallRealMethod().when(employeesManager).validateUsername(incorrectNameShort);
        CashRegisterException crException1 = assertThrows(CashRegisterException.class, () -> {
            employeesManager.validateUsername(incorrectNameShort);}, "Username must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Username must be between 3 and 45 chars, can't contain numbers", crException1.getMessage());

        String incorrectNameNumbers = "a33";
        Mockito.doCallRealMethod().when(employeesManager).validateUsername(incorrectNameNumbers);
        CashRegisterException crException2 = assertThrows(CashRegisterException.class, () -> {
            employeesManager.validateUsername(incorrectNameNumbers);}, "Username must be between 3 and 45 chars, can't contain numbers");
        Assertions.assertEquals("Username must be between 3 and 45 chars, can't contain numbers", crException2.getMessage());

    }

    @Test
    void validateFullName() throws CashRegisterException {
        String tooshort = "i";
        assertThrows(CashRegisterException.class,()-> { employeesManager.validateFullName(tooshort); });
    }

    @Test
    void validatePassword() {
        String toolong = "abcdefghijklljmnnjoprstuvzz";
        assertThrows(CashRegisterException.class,()-> { employeesManager.validatePassword(toolong); });
    }

    
}
