import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/school";
    static final String USER = "root";
    static final String PASS = "Asethy#20"; // Put your database password here

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to Database Successfully!\n");

            while (true) {
                System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Student");
                System.out.println("2. Show All Students");
                System.out.println("3. Update Student");
                System.out.println("4. Remove Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter City: ");
                        String city = sc.nextLine();
                        Student.add(con, id, name, city);
                        break;

                    case 2:
                        Student.showAllData(con);
                        break;

                    case 3:
                        System.out.print("Enter ID of student to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New City: ");
                        String newCity = sc.nextLine();
                        Student.update(con, updateId, newName, newCity);
                        break;

                    case 4:
                        System.out.print("Enter ID to remove: ");
                        int removeId = sc.nextInt();
                        Student.remove(con, removeId);
                        break;

                    case 5:
                        System.out.println("Exiting application...");
                        return; // Ends the program

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }
}