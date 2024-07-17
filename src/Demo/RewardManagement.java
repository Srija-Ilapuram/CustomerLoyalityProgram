package Demo;
import java.sql.*;
import java.util.Scanner;

public class RewardManagement {
    public static void addReward(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter reward name: ");
            String name = scanner.next();
            System.out.print("Enter points required: ");
            int pointsRequired = scanner.nextInt();
            System.out.print("Enter description: ");
            String description = scanner.next();

            String query = "INSERT INTO Reward (name, points_required, description) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, pointsRequired);
            pstmt.setString(3, description);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Reward added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding reward: " + e.getMessage());
        }
    }

    public static void viewReward(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();

            String query = "SELECT * FROM Reward WHERE reward_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, rewardId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Reward ID: " + rs.getInt("reward_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Points Required: " + rs.getInt("points_required"));
                System.out.println("Description: " + rs.getString("description"));
            } else {
                System.out.println("Reward not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing reward: " + e.getMessage());
        }
    }

    public static void updateReward(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();
            System.out.print("Enter new name: ");
            String name = scanner.next();
            System.out.print("Enter new points required: ");
            int pointsRequired = scanner.nextInt();
            System.out.print("Enter new description: ");
            String description = scanner.next();

            String query = "UPDATE Reward SET name = ?, points_required = ?, description = ? WHERE reward_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, pointsRequired);
            pstmt.setString(3, description);
            pstmt.setInt(4, rewardId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Reward updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating reward: " + e.getMessage());
        }
    }

    public static void deleteReward(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter reward ID: ");
            int rewardId = scanner.nextInt();

            String query = "DELETE FROM Reward WHERE reward_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, rewardId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Reward deleted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting reward: " + e.getMessage());
        }
    }
}
