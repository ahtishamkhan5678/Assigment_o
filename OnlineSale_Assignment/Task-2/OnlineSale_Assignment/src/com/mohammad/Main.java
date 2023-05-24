package com.mohammad;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
    	   String departmentsFile = "src/Csv/Departments.csv";
           String employeesFile = "src/Csv/Employees.csv";
           String salariesFile = "src/Csv/Salaries.csv";


        // Read data from CSV files
        List<Department> departments = readDepartmentsFromCSV(departmentsFile);
        List<Employee> employees = readEmployeesFromCSV(employeesFile);
        List<Salary> salaries = readSalariesFromCSV(salariesFile);

        // Generate the report
        Map<String, Integer> departmentSalaryMap = calculateDepartmentTotalSalary(departments, employees, salaries);

        // Print the report
        for (Map.Entry<String, Integer> entry : departmentSalaryMap.entrySet()) {
            String departmentName = entry.getKey();
            int totalSalary = entry.getValue();
            System.out.println("Department: " + departmentName);
            System.out.println("Total Salary: " + totalSalary);
            System.out.println();
        }
    }

    private static List<Department> readDepartmentsFromCSV(String filename) {
        List<Department> departments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            // Skip the header line if present
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        String name = data[1].trim();
                        departments.add(new Department(id, name));
                    } catch (NumberFormatException e) {
                    	
                    	
                        // Skip invalid lines in the CSV file
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return departments;
    }

    private static List<Employee> readEmployeesFromCSV(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            // Skip the header line if present
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        String name = data[1].trim();
                        int deptId = Integer.parseInt(data[2].trim());
                        employees.add(new Employee(id, name, deptId));
                    } catch (NumberFormatException e) {
                    	
                        // Skip invalid lines in the CSV file
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<Salary> readSalariesFromCSV(String filename) {
        List<Salary> salaries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip the header line if present
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    try {
                        int empId = Integer.parseInt(data[0].trim());
                        int month = Integer.parseInt(data[1].trim());
                        int amount = Integer.parseInt(data[2].trim());
                        salaries.add(new Salary(empId, month, amount));
                    } catch (NumberFormatException e) {
                        // Skip invalid lines in the CSV file
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salaries;
    }

    private static Map<String, Integer> calculateDepartmentTotalSalary(
            List<Department> departments, List<Employee> employees, List<Salary> salaries) {
        Map<String, Integer> departmentSalaryMap = new HashMap<>();
        for (Department department : departments) {
            int totalSalary = 0;
            for (Employee employee : employees) {
                if (employee.getDeptId() == department.getId()) {
                    for (Salary salary : salaries) {
                        if (salary.getEmpId() == employee.getId()) {
                            totalSalary += salary.getAmount();
                        }
                    }
                }
            }
            departmentSalaryMap.put(department.getName(), totalSalary);
        }
        return departmentSalaryMap;
    }
    
}    
