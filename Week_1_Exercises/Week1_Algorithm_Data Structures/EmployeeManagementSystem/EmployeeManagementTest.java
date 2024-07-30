package EmployeeManagementSystem;

public class EmployeeManagementTest {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();

        // Add employees
        manager.addEmployee(new Employee(1, "Sara", "Developer", 70000));
        manager.addEmployee(new Employee(2, "Priya", "Designer", 60000));
        manager.addEmployee(new Employee(3, "Suji", "Manager", 80000));

        // Traverse employees
        System.out.println("All Employees:");
        manager.traverseEmployees();

        // Search employee
        System.out.println("\nSearching for employee with ID 2:");
        Employee e = manager.searchEmployeeById(2);
        System.out.println(e);

        // Delete employee
        System.out.println("\nDeleting employee with ID 2:");
        boolean deleted = manager.deleteEmployeeById(2);
        System.out.println("Employee deleted: " + deleted);

        // Traverse employees again
        System.out.println("\nAll Employees after deletion:");
        manager.traverseEmployees();
    }

}

