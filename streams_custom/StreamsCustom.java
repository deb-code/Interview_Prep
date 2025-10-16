package streams_custom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsCustom {

	public static void main(String[] args) {

		List<Employee> employees = Arrays.asList(

				new Employee("Raju", 9000, 1, "Bangalore", "HR"),

				new Employee("Nandu", 29000, 2, "Pune", "HR"),

				new Employee("Rohini", 30000, 3, "Chennai", "Accounts"),

				new Employee("Shyam", 39000, 4, "Bangalore", "Testing"),

				new Employee("Babu", 45000, 5, "Bangalore", "Developer"),
				
				new Employee("Rubel", 11000, 6, "UttarPradesh", "Accounts"),
				
				new Employee("Shyam", 19000, 4, "Guahati", "Testing")
				
				

		);

		// Find all the employees from a specific city and get the count of it
		
		
		employees.stream().filter(city->city.getCity().equals("Bangalore")).forEach(System.out::println);
		
		long count = employees.stream().filter(city->city.getCity().equals("Bangalore")).count();
		System.out.println(count);

		// Find all the employees where salary is greater than a particular value
		
		employees.stream().filter(emp->emp.getSalary()>12000).forEachOrdered(System.out::println);
		
		// Print all the employee names only in upper case and an alphabetical order
		
		employees.stream().map(emp -> emp.getEmpName().toUpperCase()).forEachOrdered(System.out::println);
		// Get the first employee where dept is "HR" and if not available throw
		// exception
		
		Employee emp2 =  employees.stream().filter(i->i.getDepartment().equals("HR")).findFirst().orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
		System.out.println(emp2);
		// Get the total salary of all employees in a specific department
		
		double getSal =  employees.stream().filter(emp->emp.getDepartment().equals("Accounts")).mapToDouble(Employee::getSalary).sum();
		System.out.println(getSal);
		
		
		//another way 
		
		double getSal2= employees.stream().filter(emp->emp.getDepartment().equals("Testing")).collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(getSal2);
		
		
		//check all the employees
		

	}

}
