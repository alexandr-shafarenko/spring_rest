package spring.rest_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.rest_client.entity.Employee;

import java.util.List;

@Component
public class Communication {

    private final String URL = "http://localhost:8080/api/employees";

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                URL
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<Employee>>() {
                });
        List<Employee> alEmployees = responseEntity.getBody();
        return alEmployees;
    }

    public Employee getEmployee(int empId) {
        Employee employee = restTemplate.getForObject(URL + "/" + empId, Employee.class);
        return employee;
    }

    public void saveOrUpdateEmployee(Employee employee) {
        int empId = employee.getId();
        if (empId == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee with ID " + empId + " was updated in DB");
        }
    }

    public void deleteEmployee(int empId) {
        restTemplate.delete(URL + "/" + empId);
        System.out.println("Employee with ID " + empId + " was deleted from DB");
    }

}
