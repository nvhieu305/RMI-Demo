package control.dao;

import model.User;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;

public class UserDAO extends DAO<User>{
    public UserDAO(Connection connection) {
        this.connection = connection;
        try {
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";

            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );

                users.add(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getByLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE username='" + username + "' and password='" + password + "'";

            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(User user) {
        String sql = "INSERT INTO user (username,password,role) VALUES(?,?,?)";
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, user.getUsername());
            this.preparedStatement.setString(2, user.getPassword());
            this.preparedStatement.setInt(3, user.getRole());

            int rowCount = this.preparedStatement.executeUpdate();
            return rowCount;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE user SET username=?,password=?,role=? WHERE id=?";
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, user.getUsername());
            this.preparedStatement.setString(2, user.getPassword());
            this.preparedStatement.setInt(3, user.getRole());
            this.preparedStatement.setInt(4, user.getId());

            int rowCount = this.preparedStatement.executeUpdate();
            return rowCount;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(User user) {
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, user.getId());

            int rowCount = this.preparedStatement.executeUpdate();
            return rowCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
