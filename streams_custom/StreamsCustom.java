package streams_custom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
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
				
				new Employee("Shyamasundar", 19000, 4, "Guahati", "Testing")
				
				

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
		
		System.out.println("------------------------INTERMEDIATE-----------------------------------------------------");
		//check all the employees have a salary greater than a specific value
		
		List<Employee>emp4 =  employees.stream().filter(emp->emp.getSalary()>10000).collect(Collectors.toList());
		
		System.out.println(emp4);
		
		//another way 
		boolean emp5 =  employees.stream().anyMatch(emp->emp.getSalary()>10000);
		System.out.println(emp5);
		
		System.out.println("-------------------------------------------------------------");
		//Get the sum of salaries of all employees and the average salary
		
		//sum
		Double empSalry = employees.stream().mapToDouble(Employee::getSalary).sum();
		
		System.out.println(empSalry);
		
		Double empSalary2 = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		
		System.out.println(empSalary2);
		
		System.out.println("----------------------------------------------------------------");
		//average
		
		OptionalDouble empSalry1 = employees.stream().mapToDouble(Employee::getSalary).average();
		
		if( empSalry1.isPresent()){
			System.out.println("Average  ->  " + empSalry1);
		}
		
		
		//another way 
		
		Double avgSal =  employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avgSal);
		
		System.out.println("----------------------------------------------------------------");
		//Get a Map of Employee Names and their salaries
		
		Map<String, Double>EmpMap =  employees.stream().collect(Collectors.toMap(i->i.getEmpName(), j->j.getSalary()));
		EmpMap.forEach((name,sal)->{
			System.out.println(name +"Emplyee Name  " + " " + sal +"Employee Salary");
		});
		
		System.out.println("------------------------------------------------------------------");
		//Print the employees details from their highest to their lowest salary
		
		employees.stream().sorted((emp1,emp12)-> ((Double)emp12.getSalary()).compareTo(emp1.getSalary())).forEachOrdered(System.out::println);
		
		//another way
		
		employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(System.out::println);
		
		System.out.println("-------------------------------------------------------------");
		//Get the employees with the highest salary
		
		Optional<Employee>empMaxSal =  employees.stream().max(Comparator.comparing(Employee::getSalary));
		
		empMaxSal.ifPresent(System.out::println);
		
		System.out.println("--------------------------------------------------------"); 
		
		//Get the employees with second highest salary
		
		
		double secondMaxSal =  employees.stream().map(Employee::getSalary).sorted((sal1,sal2)->sal2.compareTo(sal1)).skip(1).findFirst().get();
		System.out.println(secondMaxSal);
		
		System.out.println("-----------------------------------------------");
		//Get the employees who are earning above the average salary
		
		employees.stream().filter(emp->emp.getSalary()>avgSal).forEach(System.out::println);
		
		System.out.println("--------------------------------------------------------------------");
		
		//Get the employee with the longest name
		
		Employee empLongName =  employees.stream().max(Comparator.comparing(Employee::getEmpName)).get();
		System.out.println(empLongName);
		
		System.out.println("---------------------------------------------------------");
		//List all the employees by department
		
		Map<String, List<Employee>> empList =  employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println(empList);
		//Get the count of the employees by the department 
		Map<String, Long> empList2 =  employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		
		System.out.println(empList2);
		
		System.out.println("----------------------------------------------------");
		
		//Get the sum of all salaries by all employees of the department
		
		Map<String, Double>empSalSum = employees.stream().collect(Collectors.groupingBy(Employee:: getDepartment, Collectors.summingDouble(Employee:: getSalary)));
		
		System.out.println(empSalSum);
		
		//Get the average of all salaries by all employees of the department
		
		Map<String, Double>empSalAvg = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		
		System.out.println(empSalAvg);
		
		System.out.println("-----------------------------------------------------------------------------------");
		//Check if all employees belong to a specific department
		
		boolean isMatching =  employees.stream().anyMatch(emp->emp.getDepartment().equals("Accounts"));
		
		System.out.println(isMatching);
		
		//List all employees grouping by cities but belong to the same department
		
		Map<String, List<Employee>>empList14 =  employees.stream().collect(Collectors.groupingBy(Employee:: getDepartment));
		
		empList14.forEach((city,empList1)->{
			System.out.println("Department" + city);
			empList1.stream().collect(Collectors.groupingBy(Employee:: getDepartment))
			.forEach((dept,emps)->{
				System.out.println("Employees in city "+ dept);
				emps.forEach(empList15 -> System.out.println(empList15.getEmpName()));
			});
		});
		
		
		//List the department with the highest average salary
		
		Map<String, Double>mapEmps = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		
		System.out.println(mapEmps);
		
		
		String key = mapEmps.keySet().stream().max(Comparator.comparing(dept -> mapEmps.get(dept))).get();
		
		double highest = mapEmps.get(key);
		System.out.println(highest);
		
		
		
		
		
		
	}

}
