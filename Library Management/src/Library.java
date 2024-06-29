import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

class LibraryManagementSystem {

    private DatabaseManager dbManager;

    public LibraryManagementSystem() {
        dbManager = new DatabaseManager();

        // Create the login frame
        JFrame loginFrame = new JFrame("Library Management System ");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500, 560);
        loginFrame.setLayout(null);

        JLabel headingLabel = new JLabel("Welcome to [The Library]");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 20));
        headingLabel.setBounds(100, 30, 300, 40);
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginFrame.add(headingLabel);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PMLS\\Downloads\\images.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(5, 0, 100, 100);
        loginFrame.add(logoLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 100, 200, 40);
        loginFrame.add(usernameLabel);

        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(150, 150, 200, 40);
        loginFrame.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 200, 200, 40);
        loginFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 250, 200, 40);
        loginFrame.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 300, 200, 40);
        loginFrame.add(loginButton);


        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (dbManager.login(username, password)) {
                loginFrame.setVisible(false);
                showMainMenuFrame();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Login failed. Invalid credentials.");
            }
        });

        loginFrame.setVisible(true);
    }

    private void showMainMenuFrame() {
        JFrame mainMenuFrame = new JFrame("Library Management System - Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(500, 560);
        mainMenuFrame.setLayout(null);

        JButton addBookButton = new JButton("Add New Book");
        addBookButton.setBounds(100, 150, 300, 40);
        mainMenuFrame.add(addBookButton);

        // Register New Patron
        JButton registerPatronButton = new JButton("Register New Patron");
        registerPatronButton.setBounds(100, 220, 300, 40);
        mainMenuFrame.add(registerPatronButton);

        // Issue Book
        JButton issueBookButton = new JButton("Issue Book");
        issueBookButton.setBounds(100, 290, 300, 40);
        mainMenuFrame.add(issueBookButton);

        // Return Book
        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(100, 360, 300, 40);
        mainMenuFrame.add(returnBookButton);

        addBookButton.addActionListener(e -> {

            String title = JOptionPane.showInputDialog(mainMenuFrame, "Enter book title:");
            String author = JOptionPane.showInputDialog(mainMenuFrame, "Enter author name:");
            String quantityStr = JOptionPane.showInputDialog(mainMenuFrame, "Enter quantity:");

            try {
                int quantity = Integer.parseInt(quantityStr);
                dbManager.addBook(new Book(title, author, quantity));
                JOptionPane.showMessageDialog(mainMenuFrame, "Book added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainMenuFrame, "Invalid quantity format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Register Patron button action listener
        registerPatronButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(mainMenuFrame, "Enter patron name:");
            String phone = JOptionPane.showInputDialog(mainMenuFrame, "Enter patron phone:");

            dbManager.addPatron(new Patron(name, phone));

            JOptionPane.showMessageDialog(mainMenuFrame, "Patron registered successfully!");
        });

        // Issue Book button action listener
        issueBookButton.addActionListener(e -> {
            String bookIdStr = JOptionPane.showInputDialog(mainMenuFrame, "Enter book ID:");
            String patronIdStr = JOptionPane.showInputDialog(mainMenuFrame, "Enter patron ID:");

            try {
                int bookId = Integer.parseInt(bookIdStr);
                int patronId = Integer.parseInt(patronIdStr);
                dbManager.issueBook(bookId, patronId);
                JOptionPane.showMessageDialog(mainMenuFrame, "Book issued successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainMenuFrame, "Invalid ID format. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        returnBookButton.addActionListener(e -> {
            String transactionIdStr = JOptionPane.showInputDialog(mainMenuFrame, "Enter transaction ID:");

            try {
                int transactionId = Integer.parseInt(transactionIdStr);
                dbManager.returnBook(transactionId);
                JOptionPane.showMessageDialog(mainMenuFrame, "Book returned successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainMenuFrame, "Invalid transaction ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainMenuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}

class DatabaseManager {

    private Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "software22";

    public DatabaseManager() {
        try {
            conn = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to add a book to the database
    public void addBook(Book book) {
        String sql = "INSERT INTO book (title, author, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a patron to the database
    public void addPatron(Patron patron) {
        String sql = "INSERT INTO patron (name, phone) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patron.getName());
            pstmt.setString(2, patron.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to issue a book to a patron
    public void issueBook(int bookId, int patronId) {
        LocalDate issueDate = LocalDate.now();
        String sql = "INSERT INTO transaction (book_id, patron_id, issue_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, patronId);
            pstmt.setString(3, issueDate.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return a book from a patron
    public void returnBook(int transactionId) {
        LocalDate returnDate = LocalDate.now();
        String sql = "UPDATE transaction SET return_date = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, returnDate.toString());
            pstmt.setInt(2, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check login credentials
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); //
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Patron {
    private String name;
    private String phone;

    public Patron(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
