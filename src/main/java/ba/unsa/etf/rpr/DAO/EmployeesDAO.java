package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Employees;

public interface EmployeesDAO extends DAO<Employees>{

    Employees getByUsername(String usr);

    Employees getById(int id);

    Employees update(Employees item);
}
