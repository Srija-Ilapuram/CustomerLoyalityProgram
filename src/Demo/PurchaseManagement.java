package Demo;

import java.sql.*;
import java.util.Scanner;

public class PurchaseManagement {
    public static void addPurchase(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();
            System.out.print("Enter points earned: ");
            int pointsEarned = scanner.nextInt();
            System.out.print("Enter purchase date (YYYY-MM-DD): ");
            String purchaseDate = scanner.next();

            String query = "INSERT INTO Purchase (customer_id, reward_id, purchase_date, points_earned) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, rewardId);
            pstmt.setDate(3, Date.valueOf(purchaseDate));
            pstmt.setInt(4, pointsEarned);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Purchase added successfully.");
                updateCustomerPoints(conn, customerId, pointsEarned);
            }
        } catch (SQLException e) {
            System.err.println("Error adding purchase: " + e.getMessage());
        }
    }

    public static void viewPurchase(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();

            String query = "SELECT * FROM Purchase WHERE purchase_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, purchaseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Purchase ID: " + rs.getInt("purchase_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Reward ID: " + rs.getInt("reward_id"));
                System.out.println("Purchase Date: " + rs.getDate("purchase_date"));
                System.out.println("Points Earned: " + rs.getInt("points_earned"));
            } else {
                System.out.println("Purchase not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing purchase: " + e.getMessage());
        }
    }

    public static void updatePurchase(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();
            System.out.print("Enter new customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter new reward ID: ");
            int rewardId = scanner.nextInt();
            System.out.print("Enter new points earned: ");
            int pointsEarned = scanner.nextInt();
            System.out.print("Enter new purchase date (YYYY-MM-DD): ");
            String purchaseDate = scanner.next();

            String query = "UPDATE Purchase SET customer_id = ?, reward_id = ?, purchase_date = ?, points_earned = ? WHERE purchase_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, rewardId);
            pstmt.setDate(3, Date.valueOf(purchaseDate));
            pstmt.setInt(4, pointsEarned);
            pstmt.setInt(5, purchaseId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Purchase updated successfully.");
                // Adjust customer's points accordingly
                updateCustomerPoints(conn, customerId, pointsEarned);
            }
        } catch (SQLException e) {
            System.err.println("Error updating purchase: " + e.getMessage());
        }
    }

    public static void deletePurchase(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter purchase ID: ");
            int purchaseId = scanner.nextInt();

            String query = "DELETE FROM Purchase WHERE purchase_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, purchaseId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Purchase deleted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting purchase: " + e.getMessage());
        }
    }

    private static void updateCustomerPoints(Connection conn, int customerId, int pointsEarned) {
        try {
            String query = "UPDATE Customer SET total_points = total_points + ? WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pointsEarned);
            pstmt.setInt(2, customerId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating customer points: " + e.getMessage());
        }
    }
}
