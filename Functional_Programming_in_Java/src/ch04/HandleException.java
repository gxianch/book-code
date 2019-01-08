package ch04;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class HandleException {
	public static void main(String[] args) throws IOException {
		Stream.of("/usr", "/tmp")
		//.map(path -> new File(path).getCanonicalPath())
		.forEach(System.out::println);
		//Error, this code will not compile
	}
}