package streams_numbers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsNums {

	public static void main(String[] args) {
		
		List<Integer> nums = Arrays.asList(75,1,22,58,41,33,62,95,87,24,0);
		
		//FOR EVEN NUMBERS FOR non primitive numbers
		
		List<Integer>numsevn =  nums.stream().filter(i -> i%2==0).collect(Collectors.toList());
		numsevn.forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		//even no.s for araays
		
		int [] numsarr = new int[] {75,1,26,58,41,33,62,95,87,27};
		
		Arrays.stream(numsarr).boxed().filter(i -> i%2==0).forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		//Get the Even and odd numbers between a range
		IntStream.rangeClosed(10, 100).filter(i->i%2==0).forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		IntStream.rangeClosed(10, 100).filter(i->i%2!=0).forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		//Sort the list of numbers in ascending or descending order
		
		nums.stream().sorted().forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		
		Arrays.stream(numsarr).boxed().sorted().forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		//Descending
		
		nums.stream().sorted((a,b)->b-a).forEach(System.out::println);
		//another way
		System.out.println("----------------------------------------");
		nums.stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).forEach(System.out::println);
		System.out.println("----------------------------------------");
		
		Arrays.stream(numsarr).mapToObj(Integer::valueOf).sorted((a,b)->b-a).forEach(System.out::println);
		//another way for descending
		System.out.println("----------------------------------------");
		Arrays.stream(numsarr).mapToObj(Integer::valueOf).sorted(Comparator.comparingInt(Integer::intValue).reversed()).forEach(System.out::println);
		System.out.println("----------------------------------------");
		//Calculate the sum and average of numbers in an array and list of numbers
		
		int total = nums.stream().mapToInt(Integer::intValue).sum();
		
		System.out.println(total);
		System.out.println("----------------------------------------");
		//another way with reduce
		Optional<Integer>optum = nums.stream().reduce((x,y)->x+y);
		optum.ifPresent(System.out::println);
		
		
		
		
		System.out.println("----------------------------------------");
		//now for arrays
		int totalarr = Arrays.stream(numsarr).sum();
		System.out.println(totalarr);
		
		System.out.println("----------------------------------------");
		
		//Now for Average**for List
		//**Consumer method works on INteger or non primitive types but optionaldouble like some special consumer method works on primitive or int data type
		OptionalDouble optag = nums.stream().mapToDouble(i->i.doubleValue()).average();
		if (optag.isPresent()) {
			
			System.out.println(optag.getAsDouble());	
		}
		
		//for arrays
		optag = Arrays.stream(numsarr).average();
		optag.ifPresent(System.out::println);
		//Find the maximum/minimum number in an array / list of numbers 
		
		
		
		
		
	}

}
