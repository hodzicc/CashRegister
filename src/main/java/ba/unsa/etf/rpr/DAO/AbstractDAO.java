package ba.unsa.etf.rpr.DAO;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;
public abstract class AbstractDAO<T extends Idable> implements DAO<T>{
    private static Connection connection = null;
    private String tableName;

}
