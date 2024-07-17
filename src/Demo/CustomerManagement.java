package Demo;
import java.sql.*;
import java.util.Scanner;

public class CustomerManagement {
    public static void addCustomer(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.next();
            System.out.print("Enter email: ");
            String email = scanner.next();
            System.out.print("Enter phone: ");
            String phone = scanner.next();

            String query = "INSERT INTO Customer (name, email, phone, total_points) VALUES (?, ?, ?, 0)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    public static void viewCustomer(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();

            String query = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Total Points: " + rs.getInt("total_points"));
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing customer: " + e.getMessage());
        }
    }

    public static void updateCustomer(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter new name: ");
            String name = scanner.next();
            System.out.print("Enter new email: ");
            String email = scanner.next();
            System.out.print("Enter new phone: ");
            String phone = scanner.next();

            String query = "UPDATE Customer SET name = ?, email = ?, phone = ? WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setInt(4, customerId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    public static void deleteCustomer(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();

            String query = "DELETE FROM Customer WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer deleted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }
}

