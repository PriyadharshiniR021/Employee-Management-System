
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeApp1 {

    public static void main(String[] args) {
        showLoginScreen();
    }

    private static void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("23bit021") && password.equals("2025")) {
                loginFrame.dispose();
                showEmployeeTableGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void showEmployeeTableGUI() {
        JFrame frame = new JFrame("Employee Table");
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("CID");
        model.addColumn("CName");
        model.addColumn("Designation");
        model.addColumn("Salary");
        model.addColumn("Join Date");

        fetchDataAndPopulateTable(model);

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton updateEmployeeButton = new JButton("Update Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        addEmployeeButton.addActionListener(e -> showAddEmployeeDialog(model));
        updateEmployeeButton.addActionListener(e -> showUpdateEmployeeDialog(model));
        deleteEmployeeButton.addActionListener(e -> showDeleteEmployeeDialog(model));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(updateEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void fetchDataAndPopulateTable(DefaultTableModel model) {
        String url = "jdbc:mysql://localhost:3309/function";
        String user = "root";
        String password = "dbms";

        String query = "SELECT * FROM employee";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String designation = rs.getString("designation");
                int salary = rs.getInt("salary");
                String joindate = rs.getString("joindate");

                model.addRow(new Object[]{cid, cname, designation, salary, joindate});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showAddEmployeeDialog(DefaultTableModel model) {
        JFrame addFrame = new JFrame("Add Employee");
        JPanel addPanel = new JPanel(new GridLayout(6, 2));

        JTextField cidField = new JTextField();
        JTextField cnameField = new JTextField();
        JTextField designationField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField joindateField = new JTextField();

        addPanel.add(new JLabel("CID:"));
        addPanel.add(cidField);
        addPanel.add(new JLabel("CName:"));
        addPanel.add(cnameField);
        addPanel.add(new JLabel("Designation:"));
        addPanel.add(designationField);
        addPanel.add(new JLabel("Salary:"));
        addPanel.add(salaryField);
        addPanel.add(new JLabel("Join Date (YYYY-MM-DD):"));
        addPanel.add(joindateField);

        JButton submitButton = new JButton("Add");
        addPanel.add(new JLabel());
        addPanel.add(submitButton);

        addFrame.add(addPanel);
        addFrame.setSize(400, 300);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String url = "jdbc:mysql://localhost:3309/function";
            String user = "root";
            String password = "dbms";

            String insertQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(insertQuery)) {

                ps.setInt(1, Integer.parseInt(cidField.getText()));
                ps.setString(2, cnameField.getText());
                ps.setString(3, designationField.getText());
                ps.setInt(4, Integer.parseInt(salaryField.getText()));
                ps.setString(5, joindateField.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Employee added successfully!");
                addFrame.dispose();
                model.setRowCount(0);
                fetchDataAndPopulateTable(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void showUpdateEmployeeDialog(DefaultTableModel model) {
        JFrame updateFrame = new JFrame("Update Employee");
        JPanel updatePanel = new JPanel(new GridLayout(6, 2));

        JTextField cidField = new JTextField();
        JTextField cnameField = new JTextField();
        JTextField designationField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField joindateField = new JTextField();

        updatePanel.add(new JLabel("CID to Update:"));
        updatePanel.add(cidField);
        updatePanel.add(new JLabel("New CName:"));
        updatePanel.add(cnameField);
        updatePanel.add(new JLabel("New Designation:"));
        updatePanel.add(designationField);
        updatePanel.add(new JLabel("New Salary:"));
        updatePanel.add(salaryField);
        updatePanel.add(new JLabel("New Join Date (YYYY-MM-DD):"));
        updatePanel.add(joindateField);

        JButton submitButton = new JButton("Update");
        updatePanel.add(new JLabel());
        updatePanel.add(submitButton);

        updateFrame.add(updatePanel);
        updateFrame.setSize(400, 300);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String url = "jdbc:mysql://localhost:3309/function";
            String user = "root";
            String password = "dbms";

            String updateQuery = "UPDATE employee SET cname=?, designation=?, salary=?, joindate=? WHERE cid=?";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(updateQuery)) {

                ps.setString(1, cnameField.getText());
                ps.setString(2, designationField.getText());
                ps.setInt(3, Integer.parseInt(salaryField.getText()));
                ps.setString(4, joindateField.getText());
                ps.setInt(5, Integer.parseInt(cidField.getText()));

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Employee updated successfully!");
                    updateFrame.dispose();
                    model.setRowCount(0);
                    fetchDataAndPopulateTable(model);
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void showDeleteEmployeeDialog(DefaultTableModel model) {
        String cid = JOptionPane.showInputDialog("Enter CID to Delete:");

        if (cid != null && !cid.isEmpty()) {
            String url = "jdbc:mysql://localhost:3309/function";
            String user = "root";
            String password = "dbms";

            String deleteQuery = "DELETE FROM employee WHERE cid=?";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(deleteQuery)) {

                ps.setInt(1, Integer.parseInt(cid));
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                    model.setRowCount(0);
                    fetchDataAndPopulateTable(model);
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

