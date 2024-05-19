import com.atqingke.ssmintegration.controller.EmployeeController;
import com.atqingke.ssmintegration.pojo.Employee;
import com.atqingke.ssmintegration.service.EmployeeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void retListTest() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, "John Doe", 6000.00),
                new Employee(2, "Jane Smith", 5500.00)
        );

        when(employeeService.findAll()).thenReturn(mockEmployees);

        mockMvc.perform(get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"empId\":1,\"empName\":\"John Doe\",\"empSalary\":6000.0},{\"empId\":2,\"empName\":\"Jane Smith\",\"empSalary\":5500.0}]"));

        Mockito.verify(employeeService).findAll();
    }
}
