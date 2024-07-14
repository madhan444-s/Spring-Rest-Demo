package main.java.com.example.demo.service;

import main.java.com.example.demo.entity.Employee;
import main.java.com.example.demo.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        try {
            return  employeeRepository.save(employee);
        }catch (Exception e){
            throw new RuntimeException("Failed to save Employee"+e.getMessage());
        }
    }

    public List<Employee> getEmployees(){
        try {
            return  employeeRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to get all Employees"+e.getMessage());
        }
    }
}
