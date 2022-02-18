package DAO;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;
    private final String GET_SINGLE_USER = "SELECT * FROM users WHERE user_id = ?";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String CREATE_USER = "INSERT INTO users(user_id, first_name, last_name) VALUES(?,?,?)";
    private final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ? WHERE user_id = ?";
    private final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";

    public UserDAO() {
        connection = DBConnection.getConnection();
    }

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS);
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return users;
    }

    public User getUser(int userId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_SINGLE_USER);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    public void createUser(int userId, String firstName, String lastName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_USER);
        ps.setInt(1, userId);
        ps.setString(2, firstName);
        ps.setString(3, lastName);
        ps.executeUpdate();
    }

    public void updateUser(int userId, String newFirstName, String newLastName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
        ps.setString(1, newFirstName);
        ps.setString(2, newLastName);
        ps.setInt(3, userId);
        ps.executeUpdate();
    }

    public void deleteUser(int userId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        ps.setInt(1, userId);
        ps.executeUpdate();
    }
}
