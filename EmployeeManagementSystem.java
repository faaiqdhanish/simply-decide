import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Employee {
    String name;
    String position;
    String department;
    String status;
    double salary;

    public Employee(String name, String position, String department, String status, double salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.status = status;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Position: " + position + ", Department: " + department + ", Status: " + status + ", Salary: $" + salary;
    }
}

public class EmployeeManagementSystem {

    private JFrame frame;
    private JTextField nameField, salaryField;
    private JTextArea employeeListArea;
    private JComboBox<String> positionComboBox;
    private JRadioButton hrRadio, itRadio, marketingRadio;
    private JCheckBox fullTimeCheckBox, partTimeCheckBox;
    private ArrayList<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();

        // Create the main frame
        frame = new JFrame("Employee Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for employee details
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new GridLayout(6, 2));

        // Labels and input fields
        employeePanel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        employeePanel.add(nameField);

        employeePanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        employeePanel.add(salaryField);

        employeePanel.add(new JLabel("Position:"));
        String[] positions = {"Manager", "Developer", "Assistant"};
        positionComboBox = new JComboBox<>(positions);
        employeePanel.add(positionComboBox);

        employeePanel.add(new JLabel("Department:"));
        JPanel departmentPanel = new JPanel();
        hrRadio = new JRadioButton("HR");
        itRadio = new JRadioButton("IT");
        marketingRadio = new JRadioButton("Marketing");
        ButtonGroup departmentGroup = new ButtonGroup();
        departmentGroup.add(hrRadio);
        departmentGroup.add(itRadio);
        departmentGroup.add(marketingRadio);
        departmentPanel.add(hrRadio);
        departmentPanel.add(itRadio);
        departmentPanel.add(marketingRadio);
        employeePanel.add(departmentPanel);

        employeePanel.add(new JLabel("Status:"));
        fullTimeCheckBox = new JCheckBox("Full-time");
        partTimeCheckBox = new JCheckBox("Part-time");
        JPanel statusPanel = new JPanel();
        statusPanel.add(fullTimeCheckBox);
        statusPanel.add(partTimeCheckBox);
        employeePanel.add(statusPanel);

        // Buttons for Add, Update, Remove
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Update Employee");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
        buttonPanel.add(updateButton);

        JButton removeButton = new JButton("Remove Employee");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployee();
            }
        });
        buttonPanel.add(removeButton);

        // Text Area to display employee list
        employeeListArea = new JTextArea(10, 40);
        employeeListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(employeeListArea);

        // Add components to the frame
        frame.add(employeePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String position = (String) positionComboBox.getSelectedItem();
        String department = getSelectedDepartment();
        String status = getSelectedStatus();
        double salary = Double.parseDouble(salaryField.getText());

        Employee newEmployee = new Employee(name, position, department, status, salary);
        employees.add(newEmployee);

        displayEmployees();
    }

    private void updateEmployee() {
        // In a real system, you would prompt for which employee to update
        // and then let the user update the fields. For simplicity, we'll update the first employee.
        if (!employees.isEmpty()) {
            Employee employee = employees.get(0);
            employee.name = nameField.getText();
            employee.position = (String) positionComboBox.getSelectedItem();
            employee.department = getSelectedDepartment();
            employee.status = getSelectedStatus();
            employee.salary = Double.parseDouble(salaryField.getText());
            displayEmployees();
        }
    }

    private void removeEmployee() {
        if (!employees.isEmpty()) {
            employees.remove(0); // For simplicity, we remove the first employee
            displayEmployees();
        }
    }

    private String getSelectedDepartment() {
        if (hrRadio.isSelected()) return "HR";
        if (itRadio.isSelected()) return "IT";
        if (marketingRadio.isSelected()) return "Marketing";
        return "Not Selected";
    }

    private String getSelectedStatus() {
        if (fullTimeCheckBox.isSelected()) return "Full-time";
        if (partTimeCheckBox.isSelected()) return "Part-time";
        return "Not Selected";
    }

    private void displayEmployees() {
        employeeListArea.setText("");
        for (Employee employee : employees) {
            employeeListArea.append(employee.toString() + "\n");
        }
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.showFrame();
    }
}
