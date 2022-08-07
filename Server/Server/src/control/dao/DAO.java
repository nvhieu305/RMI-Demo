package control.dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class DAO <T>  {
    Statement statement;
    PreparedStatement preparedStatement;
    Connection connection;
    ResultSet resultSet;

    public abstract ArrayList<T> getAll();
    public abstract T getByLogin(String name, String password);
    public abstract int insert(T t);
    public abstract int update(T t);
    public abstract int delete(T t);
    public abstract void closeConnection();
}
