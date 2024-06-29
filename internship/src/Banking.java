import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "software22"; // Replace with your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Banking {
    Banking() {
        JFrame frame = new JFrame();
        frame.setSize(500, 560);
        frame.setTitle("Banking Application");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Welcome to Banking System", SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        titleLabel.setBounds(50, 100, 400, 30);

        JButton login = new JButton("Log IN");
        login.setBounds(160, 200, 180, 40);

        JButton register = new JButton("Register");
        register.setBounds(160, 250, 180, 40);

        frame.add(login);
        frame.add(register);
        frame.add(titleLabel);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(frame);

            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register(frame);
            }
        });

        frame.setVisible(true);

    }

    class Login {
        JFrame jFrame = new JFrame();

        Login(JFrame parentFrame) {
            parentFrame.setVisible(false);

            jFrame.setSize(500, 560);
            jFrame.setTitle("Login");
            jFrame.setLayout(null);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JLabel loginLabel = new JLabel("Login ID:");
            loginLabel.setBounds(100, 100, 80, 30);
            JTextField loginId = new JTextField();
            loginId.setBounds(200, 100, 200, 30);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(100, 150, 80, 30);
            JPasswordField password = new JPasswordField();
            password.setBounds(200, 150, 200, 30);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(200, 150, 200, 30);
            jFrame.add(passwordField);

            JLabel resultLabel = new JLabel("");
            resultLabel.setBounds(200, 250, 250, 30);

            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(200, 200, 100, 30);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String labelLogin = loginId.getText();
                    String passwordLabel = password.getText();
                    char[] password = passwordField.getPassword();
                    if (labelLogin.isEmpty() || passwordLabel.isEmpty() || password.length < 8) {
                        resultLabel.setText("Login successfully");
                        new Account(new JFrame());
                        jFrame.setVisible(false);
                    } else {
                        resultLabel.setText("Password should contain at least 8 characters");
                    }
                }
            });

            jFrame.add(loginLabel);
            jFrame.add(loginId);
            jFrame.add(passwordLabel);
            jFrame.add(password);
            jFrame.add(submitButton);
            jFrame.add(resultLabel);
            jFrame.setVisible(true);

        }
    }

    class Register {
        JFrame Jframe = new JFrame("Registration");

        Register(JFrame homeFrame) {
            homeFrame.setVisible(false);

            JButton back = new JButton("<-");
            back.setBounds(10, 10, 50, 30);
            Jframe.add(back);

            Jframe.setSize(500, 560);
            Jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Jframe.setLayout(null);

            JLabel nameLabel = new JLabel("Enter Name:");
            nameLabel.setBounds(100, 100, 80, 30);
            JTextField name = new JTextField();
            name.setBounds(200, 100, 200, 30);

            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setBounds(100, 150, 80, 30);
            JTextField email = new JTextField();
            email.setBounds(200, 150, 200, 30);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(100, 200, 80, 30);
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(200, 200, 200, 30);

            JLabel resultLabel = new JLabel("");
            resultLabel.setBounds(200, 250, 250, 30);

            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(200, 250, 100, 30);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nameLabel = name.getText();
                    String emailLabel = email.getText();
                    char[] password = passwordField.getPassword();
                    if (nameLabel.isEmpty() || emailLabel.isEmpty() || password.length < 8) {
                        JOptionPane.showMessageDialog(Jframe, "All fields are required and password must be at least 8 digits");
                    } else {
                        resultLabel.setText("Register successfully");
                        int response = JOptionPane.showConfirmDialog(Jframe,
                                "Do you want to open a new bank account?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            new OpenAccount(new JFrame());
                        }
                        else {
                            new Account(new JFrame());

                        }
                    }
                }
            });

            Jframe.add(nameLabel);
            Jframe.add(name);
            Jframe.add(emailLabel);
            Jframe.add(email);
            Jframe.add(passwordLabel);
            Jframe.add(passwordField);
            Jframe.add(submitButton);
            Jframe.add(resultLabel);

            Jframe.setVisible(true);

        }
    }
    class OpenAccount {
        JFrame jFrame = new JFrame();

        OpenAccount(JFrame homeFrame) {
            homeFrame.setVisible(false);

            jFrame.setSize(500, 560);
            jFrame.setTitle("Open New Account");
            jFrame.setLayout(null);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JLabel accountNameLabel = new JLabel("Enter Name:");
            accountNameLabel.setBounds(100, 100, 100, 30);
            JTextField accountNameField = new JTextField();
            accountNameField.setBounds(200, 100, 200, 30);

            JLabel initialDepositLabel = new JLabel("Initial Deposit:");
            initialDepositLabel.setBounds(100, 150, 100, 30);
            JTextField initialDepositField = new JTextField();
            initialDepositField.setBounds(200, 150, 200, 30);

            JLabel pinLabel = new JLabel("Security PIN:");
            pinLabel.setBounds(100, 200, 150, 30);
            JPasswordField pinField = new JPasswordField();
            pinField.setBounds(200, 200, 200, 30);

            JButton createAccountButton = new JButton("Create Account");
            createAccountButton.setBounds(200, 250, 150, 30);


            createAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String accountName = accountNameField.getText();
                    String initialDeposit = initialDepositField.getText();
                    char[] pin = pinField.getPassword();

                    if (accountName.isEmpty() || initialDeposit.isEmpty() || pin.length < 4) {
                        JOptionPane.showMessageDialog(jFrame, "All fields are required and PIN must be at least 4 digits.");
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "Account Created: " + accountName + " with initial deposit of Rs" + initialDeposit);
                        new Account(jFrame);
                    }
                }
            });
            jFrame.add(accountNameField);
            jFrame.add(createAccountButton);
            jFrame.add(accountNameLabel);
            jFrame.add(initialDepositLabel);
            jFrame.add(initialDepositField);
            jFrame.add(pinField);
            jFrame.add(pinLabel);
            jFrame.add(createAccountButton);
            jFrame.setVisible(true);
        }
    }

    class Account {
        JFrame jFrame;

        Account(JFrame previousFrame) {
            previousFrame.setVisible(false);

            jFrame = new JFrame("Dashboard");
            jFrame.setSize(500, 560);
            jFrame.setLayout(null);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JButton checkBalanceButton = new JButton("Check Balance");
            checkBalanceButton.setBounds(150, 100, 200, 40);
            JButton depositingMoneyButton = new JButton("Deposit Money");
            depositingMoneyButton.setBounds(150, 150, 200, 40);
            JButton transferMoneyButton = new JButton("Transfer Money");
            transferMoneyButton.setBounds(150, 200, 200, 40);
            JButton withdrawMoneyButton = new JButton("Withdraw Money");
            withdrawMoneyButton.setBounds(150, 250, 200, 40);
            JButton logoutButton = new JButton("Logout");
            logoutButton.setBounds(150, 300, 200, 40);

            checkBalanceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(2, 2));

                    panel.add(new JLabel("Enter name:"));
                    JTextField nameField = new JTextField();
                    panel.add(nameField);

                    panel.add(new JLabel("Enter security pin:"));
                    JPasswordField pinField = new JPasswordField();
                    panel.add(pinField);

                    int result = JOptionPane.showConfirmDialog(
                               jFrame,
                               panel,
                               "Security Check",
                               JOptionPane.OK_CANCEL_OPTION,
                               JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                           String name = nameField.getText();
                           String pin = new String(pinField.getPassword());

                           JOptionPane.showMessageDialog(jFrame, "Your balance is Rs");
                    }
                }
            });

            depositingMoneyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(3, 2));

                    panel.add(new JLabel("Account Number:"));
                    JTextField accountNumberField = new JTextField();
                    panel.add(accountNumberField);

                    panel.add(new JLabel("Amount to Deposit:"));
                    JTextField depositField = new JTextField();
                    panel.add(depositField);

                    panel.add(new JLabel("Enter security pin:"));
                    JPasswordField pinField = new JPasswordField();
                    panel.add(pinField);

                    int result = JOptionPane.showConfirmDialog(
                            jFrame,
                            panel,
                            "Deposit Money",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        String accountNumber = accountNumberField.getText();
                        String amount = depositField.getText();
                        String pin = new String(pinField.getPassword());

                        if (accountNumber.isEmpty() || amount.isEmpty() || pin.isEmpty()) {
                            JOptionPane.showMessageDialog(jFrame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            double amountToDeposit = Double.parseDouble(amount);

                            if (amountToDeposit <= 0) {
                                JOptionPane.showMessageDialog(jFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            JOptionPane.showMessageDialog(jFrame, "Amount deposited successfully.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(jFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            transferMoneyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Logic for transferring money
                    JOptionPane.showMessageDialog(jFrame, "Transfer Money Functionality");
                }
            });

            transferMoneyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Logic for transferring money
                    JOptionPane.showMessageDialog(jFrame, "Transfer Money Functionality");
                }
            });
            withdrawMoneyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(3, 2));

                    panel.add(new JLabel("Account Number:"));
                    JTextField accountNumberField = new JTextField();
                    panel.add(accountNumberField);

                    panel.add(new JLabel("Amount to Withdraw:"));
                    JTextField amountField = new JTextField();
                    panel.add(amountField);

                    panel.add(new JLabel("Enter security pin:"));
                    JPasswordField pinField = new JPasswordField();
                    panel.add(pinField);

                    int result = JOptionPane.showConfirmDialog(
                            jFrame,
                            panel,
                            "Withdraw Money",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        String accountNumber = accountNumberField.getText();
                        String amount = amountField.getText();
                        String pin = new String(pinField.getPassword());

                        if (accountNumber.isEmpty() || amount.isEmpty() || pin.isEmpty()) {
                            JOptionPane.showMessageDialog(jFrame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            double amountToWithdraw = Double.parseDouble(amount);

                            if (amountToWithdraw <= 0) {
                                JOptionPane.showMessageDialog(jFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            double accountBalance = 1000.0; // Example balance
                            if (amountToWithdraw > accountBalance) {
                                JOptionPane.showMessageDialog(jFrame, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            accountBalance -= amountToWithdraw;

                            JOptionPane.showMessageDialog(jFrame, "Amount withdrawn successfully. New balance: $" + accountBalance);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(jFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.setVisible(false);
                    previousFrame.setVisible(true);
                }
            });

            jFrame.add(checkBalanceButton);
            jFrame.add(depositingMoneyButton);
            jFrame.add(transferMoneyButton);
            jFrame.add(withdrawMoneyButton);
            jFrame.add(logoutButton);

            jFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Banking login = new Banking();

    }
}
