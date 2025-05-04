import java.sql.*;

public class ConnectionFactory {

    private Connection conn = null;


    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/INVENT", "root", "Mehedi1105");
            System.out.println("Connection established successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/INVENT", "root", "Mehedi1105");
                System.out.println("Connected successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public boolean checkLogin(String username, String password, String userType) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND usertype = ? LIMIT 1";
        ResultSet resultSet = null;

        try (PreparedStatement stmt = getConn().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, userType);

            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close Result
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
