import java.sql.*;

public class Student {
    private int id;
    private String name;
    private String city;

    public Student(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public static void add(Connection con, int id, String name, String city) {
        try {
            String query = "INSERT INTO students (id, name, city) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, city);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public static void showAllData(Connection con) {
        try {
            String query = "SELECT * FROM students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Student List ---");
            System.out.println("ID\tName\tCity");
            System.out.println("--------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                System.out.println(id + "\t" + name + "\t" + city);
            }
            System.out.println("--------------------");
        } catch (SQLException e) {
            System.out.println(" Error showing data: " + e.getMessage());
        }
    }

    public static void remove(Connection con, int id) {
        try {
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Student removed successfully!");
            } else {
                System.out.println(" No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(" Error removing student: " + e.getMessage());
        }
    }

    public static void update(Connection con, int id, String newName, String newCity) {
        try {
            String query = "UPDATE students SET name = ?, city = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, newName);
            pstmt.setString(2, newCity);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Student updated successfully!");
            } else {
                System.out.println(" No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(" Error updating student: " + e.getMessage());
        }
    }
}