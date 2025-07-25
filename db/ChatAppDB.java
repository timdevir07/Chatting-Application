package db;

import java.sql.*;

public class ChatAppDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chatapp";
    static final String DB_USER = "root";
    static final String DB_PASS = "MySql@1601";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static boolean isUserRegistered(String input) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE phone = ? OR email = ?"
            );
            ps.setString(1, input);
            ps.setString(2, input);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void registerUser(String input) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps;
            if (input.contains("@")) {
                ps = conn.prepareStatement("INSERT INTO users (email) VALUES (?)");
                ps.setString(1, input);
            } else {
                ps = conn.prepareStatement("INSERT INTO users (phone) VALUES (?)");
                ps.setString(1, input);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            if (!e.getMessage().contains("Duplicate entry")) {
                e.printStackTrace();
            }
        }
    }

    public static void updateUserName(String input, String name) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps;
            if (input.contains("@")) {
                ps = conn.prepareStatement("UPDATE users SET name = ? WHERE email = ?");
                ps.setString(1, name);
                ps.setString(2, input);
            } else {
                ps = conn.prepareStatement("UPDATE users SET name = ? WHERE phone = ?");
                ps.setString(1, name);
                ps.setString(2, input);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUserName(String input) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT name FROM users WHERE phone = ? OR email = ?"
            );
            ps.setString(1, input);
            ps.setString(2, input);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void saveMessage(String sender, String receiver, String message) {
        String query = "INSERT INTO messages (sender, receiver, message) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, message);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
