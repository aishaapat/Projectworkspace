package org.example.projectworkspace.Authentication;

/*

import Database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO {

    private Connection connection;

    public userDAO() {
        this.connection = new DatabaseManager().connect();
    }

    // Create User
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (firstname, lastname, password, zipcode, ssn, address, state, email, question, answer, type, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getZipcode());
            stmt.setString(5, user.getSsn());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getState());
            stmt.setString(8, user.getEmail());
            stmt.setString(9, user.getQuestion());
            stmt.setString(10, user.getAnswer());
            stmt.setString(11, user.getType());
            stmt.setString(12, user.getUsername());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get User by ID
    public User getUserById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"),
                        rs.getString("zipcode"), rs.getString("ssn"), rs.getString("address"),
                        rs.getString("state"), rs.getString("email"), rs.getString("question"),
                        rs.getString("answer"), rs.getString("type"), rs.getString("username"), rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all Users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"),
                        rs.getString("zipcode"), rs.getString("ssn"), rs.getString("address"),
                        rs.getString("state"), rs.getString("email"), rs.getString("question"),
                        rs.getString("answer"), rs.getString("type"), rs.getString("username"), rs.getString("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update User
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET firstname = ?, lastname = ?, password = ?, zipcode = ?, ssn = ?, address = ?, " +
                "state = ?, email = ?, question = ?, answer = ?, type = ?, username = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getZipcode());
            stmt.setString(5, user.getSsn());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getState());
            stmt.setString(8, user.getEmail());
            stmt.setString(9, user.getQuestion());
            stmt.setString(10, user.getAnswer());
            stmt.setString(11, user.getType());
            stmt.setString(12, user.getUsername());
            stmt.setInt(13, user.getUserID());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete User
    public boolean deleteUser(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

 */