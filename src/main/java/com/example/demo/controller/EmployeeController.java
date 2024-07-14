package main.java.com.example.demo.controller;

import main.java.com.example.demo.entity.Employee;
import main.java.com.example.demo.service.EmployeeService;
import org.antlr.v4.runtime.ListTokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee savedEmployee = this.employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = this.employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

}
