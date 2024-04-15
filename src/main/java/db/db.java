package db;
import java.sql.*;
public class db{
  private Connection connection;

  // Constructor to establish database connection
  public db(String jdbcUrl, String username, String password) throws SQLException {
    this.connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("DB Connect Initialized!");
  }


  public void see() {
    try (Statement stmt = this.connection.createStatement()) {
      String strSelect = "SELECT name, price FROM Products";
      System.out.println("Executing SQL statement: " + strSelect);

      ResultSet rset = stmt.executeQuery(strSelect);

      while (rset.next()) {
        String name = rset.getString("name");
        double price = rset.getDouble("price");
        System.out.println("Product: " + name + ", Price: " + price);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
