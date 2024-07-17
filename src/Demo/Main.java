package Demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Customer Management");
            System.out.println("2. Reward Management");
            System.out.println("3. Purchase Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleCustomerManagement(scanner);
                    break;
                case 2:
                    handleRewardManagement(scanner);
                    break;
                case 3:
                    handlePurchaseManagement(scanner);
                    break;
                case 4:
                   // System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleCustomerManagement(Scanner scanner) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    CustomerManagement.addCustomer(conn, scanner);
                    break;
                case 2:
                    CustomerManagement.viewCustomer(conn, scanner);
                    break;
                case 3:
                    CustomerManagement.updateCustomer(conn, scanner);
                    break;
                case 4:
                    CustomerManagement.deleteCustomer(conn, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void handleRewardManagement(Scanner scanner) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("1. Add Reward");
            System.out.println("2. View Reward");
            System.out.println("3. Update Reward");
            System.out.println("4. Delete Reward");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    RewardManagement.addReward(conn, scanner);
                    break;
                case 2:
                    RewardManagement.viewReward(conn, scanner);
                    break;
                case 3:
                    RewardManagement.updateReward(conn, scanner);
                    break;
                case 4:
                    RewardManagement.deleteReward(conn, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void handlePurchaseManagement(Scanner scanner) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("1. Add Purchase");
            System.out.println("2. View Purchase");
            System.out.println("3. Update Purchase");
            System.out.println("4. Delete Purchase");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    PurchaseManagement.addPurchase(conn, scanner);
                    break;
                case 2:
                    PurchaseManagement.viewPurchase(conn, scanner);
                    break;
                case 3:
                    PurchaseManagement.updatePurchase(conn, scanner);
                    break;
                case 4:
                    PurchaseManagement.deletePurchase(conn, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
