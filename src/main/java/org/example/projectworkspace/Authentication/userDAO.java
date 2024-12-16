package org.example.projectworkspace.Authentication;

import Database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAO {

    // Connection object to interact with the database
    private final Connection connection;

    // Constructor to initialize the database connection
    public userDAO() {
        // Connect to the database using DatabaseManager
        this.connection = new DatabaseManager().connect();
    }

    // Method to create a new user in the database
    public boolean createUser(User user) {
        // SQL query to insert a new user into the users table
        String sql = "INSERT INTO users (firstname, lastname, password, zipcode, ssn, address, state, email, question, answer, type, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the values for the query placeholders using the user object
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

            // Execute the insert query and check if the insert was successful
            int result = stmt.executeUpdate();
            return result > 0; // Return true if a row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }

    // Method to get a user by their ID
    public User getUserById(int id) {
        // SQL query to retrieve a user by ID
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the ID parameter for the query
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // Execute the query

            // If a result is found, create and return a User object
            if (rs.next()) {
                return new User(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("password"),
                        rs.getString("zipcode"),
                        rs.getString("ssn"),
                        rs.getString("address"),
                        rs.getString("state"),
                        rs.getString("email"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getString("type"),
                        rs.getString("username"),
                        rs.getInt("id") // Get the user ID
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no user was found
    }

    // Method to get all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        // SQL query to retrieve all users
        String sql = "SELECT * FROM users";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // Execute the query

            // Loop through the result set and add each user to the list
            while (rs.next()) {
                users.add(new User(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("password"),
                        rs.getString("zipcode"),
                        rs.getString("ssn"),
                        rs.getString("address"),
                        rs.getString("state"),
                        rs.getString("email"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getString("type"),
                        rs.getString("username"),
                        rs.getInt("id") // Get the user ID
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users; // Return the list of users
    }

    // Method to update an existing user
    public boolean updateUser(User user) {
        // SQL query to update user information
        String sql = "UPDATE users SET firstname = ?, lastname = ?, password = ?, zipcode = ?, ssn = ?, address = ?, " +
                "state = ?, email = ?, question = ?, answer = ?, type = ?, username = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the values for the query placeholders using the user object
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

            int result = stmt.executeUpdate(); // Execute the update query
            return result > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }

    // Method to delete a user by their ID
    public boolean deleteUser(int id) {
        // SQL query to delete a user by their ID
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Set the ID parameter for the delete query
            int result = stmt.executeUpdate(); // Execute the delete query
            return result > 0; // Return true if the user was successfully deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }
}