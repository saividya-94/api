package com.example.demo;

import com.example.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

	@RequestMapping("/")
	public String hello(){
		return "Hello Employee";
	}

	//Getting the details of Employee
	@RequestMapping("/admin/getEmployee")
	public ResponseEntity<Employee> getEmployee(){
		Employee employee = new Employee(1,"james","john",25,5);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	//Adding a Employee and incrementing id with 100
	@RequestMapping(value = "/admin/addEmployee", method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employee.setId(employee.getId()+100);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	//Updating the details based on parameter passed
	@RequestMapping(value = "/admin/update/{fieldName}/{fieldValue}", method = RequestMethod.POST)
	public ResponseEntity<Employee> updateYear(@PathVariable("fieldName") String fieldName,
											   @PathVariable("fieldValue") String fieldValue,
											   @RequestBody Employee employee) {
		if(fieldName.equalsIgnoreCase("id")){
			employee.setId(Integer.parseInt(fieldValue));
		} else if (fieldName.equalsIgnoreCase("firstName")) {
			employee.setFirstName(fieldValue);
		} else if (fieldName.equalsIgnoreCase("lastName")) {
			employee.setLastName(fieldValue);
		} else if (fieldName.equalsIgnoreCase("age")) {
			employee.setAge(Integer.parseInt(fieldValue));
		} else if (fieldName.equalsIgnoreCase("yearOfExperience")) {
			employee.setYearOfExperience(Integer.parseInt(fieldValue));
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
