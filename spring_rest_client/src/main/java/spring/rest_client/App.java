package spring.rest_client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.rest_client.configuration.MyConfig;
import spring.rest_client.entity.Employee;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> allEmployees = communication.getAllEmployees();
        System.out.println(allEmployees);

        Employee empById = communication.getEmployee(12);
        System.out.println(empById);

        Employee employee = new Employee("Misha", "Galystyan", "HR", 100);
        communication.saveOrUpdateEmployee(employee);

        empById.setDepartment("RISK");
        communication.saveOrUpdateEmployee(empById);

        communication.deleteEmployee(21);

    }
}
