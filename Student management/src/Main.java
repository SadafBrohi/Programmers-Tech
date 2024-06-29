import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Management {
    JFrame frame = new JFrame();

    public Management() {
        frame.setTitle("Management");
        frame.setSize(500, 560);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to Student Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        titleLabel.setBounds(50, 50, 400, 50);

        JButton admin = new JButton("Admin");
        JButton studentButton = new JButton("Student");

        admin.setBounds(160, 200, 180, 40);
        studentButton.setBounds(160, 250, 180, 40);

        frame.add(admin);
        frame.add(studentButton);
        frame.add(titleLabel);

        admin.addActionListener(e -> new Admin());
        studentButton.addActionListener(e -> new Student(frame));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Management());
    }
}

class Admin {
    JFrame jframe;
    JTextField idField, nameField, fatherNameField, ageField, genderField, emailField, gpaField;
    JTextArea displayArea;

    public Admin() {
        jframe = new JFrame("Admin");
        jframe.setSize(800, 600);  // Increased size for better display
        jframe.setLayout(new BorderLayout()); // Use BorderLayout for main frame
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Admin Panel", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        jframe.add(header, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel fatherNameLabel = new JLabel("Father's Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel genderLabel = new JLabel("Gender:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel gpaLabel = new JLabel("GPA:");

        idField = new JTextField(5);  // ID field for update/delete operations
        nameField = new JTextField(15);
        fatherNameField = new JTextField(15);
        ageField = new JTextField(15);
        genderField = new JTextField(15);
        emailField = new JTextField(15);
        gpaField = new JTextField(15);

        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View Records");

        displayArea = new JTextArea(20, 60);  // Increased columns for better display
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));  // Use monospaced font for alignment
        JScrollPane scrollPane = new JScrollPane(displayArea);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(fatherNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(fatherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(gpaLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(gpaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(updateButton, gbc);
        gbc.gridx = 1;
        inputPanel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(addButton, gbc);
        gbc.gridx = 1;
        inputPanel.add(viewButton, gbc);

        jframe.add(inputPanel, BorderLayout.NORTH);
        jframe.add(scrollPane, BorderLayout.CENTER);

        // Action listener for Add button
        addButton.addActionListener(e -> addStudentData());

        // Action listener for View Records button
        viewButton.addActionListener(e -> viewStudentData());

        // Action listener for Update button
        updateButton.addActionListener(e -> updateStudentData());

        // Action listener for Delete button
        deleteButton.addActionListener(e -> deleteStudentData());

        jframe.setVisible(true);
    }

    private void addStudentData() {
        String name = nameField.getText();
        String fatherName = fatherNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String email = emailField.getText();
        String gpa = gpaField.getText();

        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "software22";

        String sql = "INSERT INTO Students (name, father_name, age, gender, email, gpa) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, fatherName);
            statement.setInt(3, Integer.parseInt(age));
            statement.setString(4, gender);
            statement.setString(5, email);
            statement.setDouble(6, Double.parseDouble(gpa));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                displayArea.append("A new student was inserted successfully!\n");
            }

        } catch (SQLException e) {
            displayArea.append("Error inserting student: " + e.getMessage() + "\n");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            displayArea.append("Error: Age and GPA must be numbers.\n");
        }
    }

    private void viewStudentData() {
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "software22";
        String sql = "SELECT * FROM Students";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Clear previous content
            displayArea.setText("");

            // Print column headers
            displayArea.append(String.format("| %-5s | %-20s | %-20s | %-5s | %-10s | %-30s | %-5s |\n",
                    "ID", "Name", "Father's Name", "Age", "Gender", "Email", "GPA"));
            displayArea.append("----------------------------------------------------------------------------------------------------------------\n");

            // Print each row of data
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String fatherName = resultSet.getString("father_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                double gpa = resultSet.getDouble("gpa");

                displayArea.append(String.format("| %-5d | %-20s | %-20s | %-5d | %-10s | %-30s | %-5.2f |\n",
                        id, name, fatherName, age, gender, email, gpa));
            }

        } catch (SQLException e) {
            displayArea.setText("Error retrieving student data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateStudentData() {
        String idStr = idField.getText();
        String name = nameField.getText();
        String fatherName = fatherNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String email = emailField.getText();
        String gpa = gpaField.getText();

        if (idStr.isEmpty()) {
            displayArea.append("Error: ID field cannot be empty for update.\n");
            return;
        }

        int id = Integer.parseInt(idStr);
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "software22";

        String sql = "UPDATE Students SET name=?, father_name=?, age=?, gender=?, email=?, gpa=? WHERE id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, fatherName);
            statement.setInt(3, Integer.parseInt(age));
            statement.setString(4, gender);
            statement.setString(5, email);
            statement.setDouble(6, Double.parseDouble(gpa));
            statement.setInt(7, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                displayArea.append("Student record updated successfully.\n");
            } else {
                displayArea.append("Error: No such student record found for update.\n");
            }

        } catch (SQLException e) {
            displayArea.append("Error updating student record: " + e.getMessage() + "\n");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            displayArea.append("Error: Age and GPA must be numbers.\n");
        }
    }

    private void deleteStudentData() {
        String idStr = idField.getText();

        if (idStr.isEmpty()) {
            displayArea.append("Error: ID field cannot be empty for delete.\n");
            return;
        }

        int id = Integer.parseInt(idStr);
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "software22";

        String sql = "DELETE FROM Students WHERE id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                displayArea.append("Student record deleted successfully.\n");
            } else {
                displayArea.append("Error: No such student record found for delete.\n");
            }

        } catch (SQLException e) {
            displayArea.append("Error deleting student record: " + e.getMessage() + "\n");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            displayArea.append("Error: ID must be a number.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Admin());
    }
}
class ViewStudentRecord {
    private static final String URL = "jdbc:mysql://localhost:3306/students";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "software22";

    public static void viewStudentData(JTextArea displayArea) {
        String sql = "SELECT * FROM Students";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {

            StringBuilder results = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String fatherName = resultSet.getString("father_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                double gpa = resultSet.getDouble("gpa");

                results.append(String.format("ID: %d, Name: %s, Father's Name: %s, Age: %d, Gender: %s, Email: %s, GPA: %.2f%n",
                        id, name, fatherName, age, gender, email, gpa));
            }
            displayArea.setText(results.toString());

        } catch (SQLException e) {
            displayArea.setText("Error retrieving student data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class Student {
    JFrame jframe;
    JTextArea displayArea;

    public Student(JFrame parentFrame) {
        parentFrame.setVisible(false);  // Hide the parent frame

        jframe = new JFrame("Student");
        jframe.setSize(800, 600);  // Increased size for better display
        jframe.setLayout(new BorderLayout()); // Use BorderLayout for main frame
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel header = new JLabel("Student Records", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        jframe.add(header, BorderLayout.NORTH);

        displayArea = new JTextArea(20, 60);  // Increased columns for better display
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));  // Use monospaced font for alignment
        JScrollPane scrollPane = new JScrollPane(displayArea);
        jframe.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        jframe.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for Refresh button
        refreshButton.addActionListener(e -> {
            viewStudentData(displayArea);
        });

        // Initially load student records
        viewStudentData(displayArea);

        jframe.setVisible(true);
    }

    private void viewStudentData(JTextArea displayArea) {
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "software22";
        String sql = "SELECT * FROM Students";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Clear previous content
            displayArea.setText("");

            // Print column headers
            displayArea.append(String.format("| %-5s | %-20s | %-20s | %-5s | %-10s | %-30s | %-5s |\n",
                    "ID", "Name", "Father's Name", "Age", "Gender", "Email", "GPA"));
            displayArea.append("----------------------------------------------------------------------------------------------------------------\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String fatherName = resultSet.getString("father_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                double gpa = resultSet.getDouble("gpa");

                displayArea.append(String.format("| %-5d | %-20s | %-20s | %-5d | %-10s | %-30s | %-5.2f |\n",
                        id, name, fatherName, age, gender, email, gpa));
            }

        } catch (SQLException e) {
            displayArea.setText("Error retrieving student data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Management();
    }
}
