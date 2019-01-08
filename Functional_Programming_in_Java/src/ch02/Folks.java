package ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;



public class Folks {
	public void testbefore() throws Exception {

	}
	public static final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
	public static final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
	public static  final List<String> comrades =                      
			Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
	@Test
	public static void main(String[] args) {

		friends.forEach(new Consumer<String>() {

			@Override
			public void accept(final String name) {
				System.out.println(name);
			}
		});

		friends.forEach((final String name) -> System.out.println(name));
	}
	@Test
	public void testforeach() throws Exception {
		friends.forEach((final String name) -> System.out.println(name));
	}
	@Test
	public void testName() throws Exception {
		friends.forEach(System.out::println);
	}
	@Test
	public void testName1() throws Exception {
		final List<String> uppercaseNames = new ArrayList<>();
		friends.forEach((name) -> uppercaseNames.add(name.toUpperCase()));
		System.out.println(uppercaseNames);
	}
	@Test
	public void test39() throws Exception {
		friends.stream().map(name -> name.toUpperCase())
		.forEach(name -> System.out.println(name + " "));
	}
	@Test
	public void test40() throws Exception {
		friends.stream().map(name -> name.length()).forEach(System.out::println);
	}
	@Test
	public void test42() throws Exception {
		final List<String> startsWithN = 
				friends.stream().filter(name -> name.startsWith("N"))
				.collect(Collectors.toList());
		System.out.println(String.format("Found %d names", startsWithN.size()));
	}
	@Test
	public void test44() throws Exception {
		final Predicate<String> startsWithN = name -> name.startsWith("N");
		final Function<String, Predicate<String>> startsWithLetter = 
				(String letters)->{
					Predicate<String> checkStarts = (String name) -> name.startsWith(letters);
					return checkStarts;
				};
		final Function<String, Predicate<String>> startsWithLetter1 = 
				(String letters) -> (String name) -> name.startsWith(letters);
		final Function<String, Predicate<String>> startsWithLetter2 = 
				letters -> name ->name.startsWith(letters);
				
		friends.stream().filter(startsWithLetter2.apply("N")).forEach(System.out::println);
	}
	public void pickName( final List<String> names, final String startingLetter){
		final Optional<String> foundName = 
				names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		System.out.println(String.format("A name starting with %s: %s",
				startingLetter, foundName.orElse("No name found")));
	}
	@Test
	public void test50() throws Exception {
		pickName(friends, "N");
		pickName(friends, "Z");
	}
	@Test
	public void test51() throws Exception {
		System.out.println("Total number of characters in all names: " +
		friends.stream().mapToInt(name -> name.length()).sum());
		
	}
	@Test
	public void test52() throws Exception {
		final Optional<String> aLongName = 
				friends.stream().reduce((name1,  name2) -> 
				name1.length() >= name2.length() ? name1:name2);
		aLongName.ifPresent(name ->
		System.out.println(String.format("A longest name: %s", name)));

	}
	@Test
	public void test53() throws Exception {
		final String aLongName = 
				friends.stream().reduce("Steve",(name1,  name2) -> 
				name1.length() >= name2.length() ? name1:name2);
	}
	@Test
	public void test54(){
		System.out.println(String.join(",", friends));
		System.out.println(
				friends.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList()));
	}
		
	
}
