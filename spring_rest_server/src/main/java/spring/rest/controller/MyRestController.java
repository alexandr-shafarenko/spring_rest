package spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import spring.rest.entity.Employee;
import spring.rest.exception_handling.EmployeeIncorrectData;
import spring.rest.exception_handling.NoSuchEmployeeException;
import spring.rest.sevice.EmployeeService;

import javax.print.attribute.standard.NumberUpSupported;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployee(@PathVariable int empId) {
        Employee employee = employeeService.getEmployee(empId);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + empId + " in Database");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) throws Exception {
        if (employee.getId() != 0) {
            throw new Exception("Id must be null");
        }
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(
            @RequestBody Employee employee
    ) throws Exception {
        if (employee.getId() == 0) {
            throw new Exception("Id must be not null");
        }
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{empId}")
    public String deleteEmployee(@PathVariable int empId) throws Exception {
        Employee employee = employeeService.getEmployee(empId);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + empId + " in Database");
        }
        employeeService.deleteEmployee(empId);
        return "Employee with ID " + empId + " was deleted";
    }

}
