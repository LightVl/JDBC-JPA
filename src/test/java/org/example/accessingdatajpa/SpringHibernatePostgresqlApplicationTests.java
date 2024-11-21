package org.example.accessingdatajpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpringHibernatePostgresqlApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void addDeleteNewEmployee() {

        Employee irina = new Employee("Ирина","СММщик");
        employeeRepository.save(irina);
        Optional<Employee> empToDelete = employeeRepository.findById(irina.getId());
        if(empToDelete.isPresent()) {
            employeeRepository.delete(empToDelete.get());
        }
    }
    @Test
    void addNewEmployeeApi() throws Exception {

        Employee vasya = new Employee("Василий","Дизайнер");
        String responseContent = mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vasya)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Long vasyaId = Long.valueOf(responseContent);
        System.out.println(vasyaId + " ");
        if (employeeRepository.findById(vasyaId).isPresent()) {
            System.out.println(employeeRepository.findById(vasyaId).get().getName());
        }
        Long dbEntryId = employeeRepository.findById(vasyaId).map(Employee::getId).orElseThrow();

        assertEquals(vasyaId, dbEntryId);
    }
}