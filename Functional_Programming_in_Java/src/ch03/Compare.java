package ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
public class Compare {
	public static void main(String[] args) {
		final List<Person> people = Arrays.asList(
				new Person("John", 20),
				new Person("Sara", 21),
				new Person("Jane", 21),
				new Person("Greg", 35));
		
		List<Person> ascendingAge =
				people.stream().sorted((person1,person2) -> person1.ageDifference(person2))
				.collect(Collectors.toList());
		
		System.out.println("Sorted in ascending order by age: "+ascendingAge);
		List<Person> ascendingAge1 =
				people.stream().sorted(Person::ageDifference)
				.collect(Collectors.toList());
		System.out.println("Sorted in ascending order by age: "+ascendingAge1);
		
		people.stream().min(Person::ageDifference)
		.ifPresent(youngest -> System.out.println("youngest:" +youngest));
		
		List<Person> olderThan20 =
				people.stream()
				.filter(person -> person.getAge() > 20)
				.collect(Collectors.toList());
				System.out.println("People older than 20: " + olderThan20);
				
		Map<Integer, List<Person>> peopleByAge =
				people.stream()
				.collect(Collectors.groupingBy(Person::getAge));
				System.out.println("Grouped by age: " + peopleByAge);
				
		Map<Integer, List<String>> nameOfPeopleByAge =
				people.stream()
				.collect(
						Collectors.groupingBy(Person::getAge,Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println("People grouped by age: " + nameOfPeopleByAge);
		Comparator<Person> byAge = Comparator.comparing(Person::getAge);
		
		Map<Character, Optional<Person>> oldestPersonOfEachLetter =
				people.stream().collect(groupingBy(person -> person.getName().charAt(0),reducing(BinaryOperator.maxBy(byAge))));
		System.out.println("Oldest person of each letter:");
		System.out.println(oldestPersonOfEachLetter);
	}
}
