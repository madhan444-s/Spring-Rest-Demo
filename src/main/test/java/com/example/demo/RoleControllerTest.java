package main.test.java.com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.demo.DemoApplication;
import main.java.com.example.demo.controller.RoleController;
import main.java.com.example.demo.entity.Role;
import main.java.com.example.demo.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveRole() throws  Exception{
        Role role = new Role();
        role.setName("TestRole");

        when(roleService.saveRole(any(Role.class))).thenReturn(role);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.name").value(role.getName()));

        verify(roleService, times(1)).saveRole(any(Role.class));
    }

    @Test
    public void testGetRoles() throws  Exception{
        Role role1 = new Role();
        role1.setName("GetTestRole1");

        Role role2 = new Role();
        role2.setName("GetTestRole2");

        List<Role> roles = Arrays.asList(role1,role2);

        when(roleService.getRoles()).thenReturn(roles);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/role")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(role1.getId()))
                .andExpect(jsonPath("$[0].name").value(role1.getName()))
                .andExpect(jsonPath("$[1].id").value(role2.getId()))
                .andExpect(jsonPath("$[1].name").value(role2.getName()));

        verify(roleService, times(1)).getRoles();

    }
}

//Explanation
//Annotations:
//
//@WebMvcTest(EmployeeController.class): Used to test the controller layer. It sets up the necessary components for Spring MVC testing.
//@ExtendWith(MockitoExtension.class): Integrates Mockito with JUnit 5.
//@MockBean: Creates a mock of the EmployeeService and adds it to the Spring application context.
//@InjectMocks: Injects the mocks into the EmployeeController.
//MockMvc:
//
//mockMvc is used to simulate HTTP requests and assert responses.
//        MockMvcBuilders.standaloneSetup(employeeController).build(): Sets up MockMvc for standalone testing of the EmployeeController.
//Test Methods:
//
//testSaveEmployee: Tests the saveEmployee endpoint.
//Mocks the saveEmployee method of EmployeeService to return a sample Employee.
//Performs a POST request to /api/employee with the sample employee data.
//Asserts that the response status is 200 OK and the response body contains the expected employee data.
//testGetEmployees: Tests the getEmployees endpoint.
//Mocks the getEmployees method of EmployeeService to return a list of sample employees.
//Performs a GET request to /api/employee.
//Asserts that the response status is 200 OK and the response body contains the expected employee data.
//ObjectMapper:
//
//objectMapper is used to convert the Java objects into JSON strings.
//This setup ensures that your EmployeeController is tested in isolation, with the EmployeeService being mocked to return predefined data.
//
