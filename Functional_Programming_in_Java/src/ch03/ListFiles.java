package ch03;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
public class ListFiles {
	public static void main(String[] args) throws IOException {
		Files.list(Paths.get("."))
		.filter(Files::isDirectory)
		.forEach(System.out::println);
		
		Files.newDirectoryStream(
				Paths.get(".\\src\\ch03"), path->path.toString().endsWith(".java"))
		.forEach(System.out::println);
		
		final File[] files = new File(".").listFiles(file -> file.isHidden());
		Arrays.asList(files).forEach(System.out::println);
		
	}
	@Test
	void listTheHardWay() {
		List<File> files = new ArrayList<>();
		File[] filesInCurrentDir = new File(".").listFiles();
		for(File file:filesInCurrentDir) {
			File[] filesInSubDir = file.listFiles();
			if(filesInSubDir != null) {
				files.addAll(Arrays.asList(filesInSubDir));
			}else {
				files.add(file);
			}
		}
		System.out.println("Count: " + files.size());
	}
	@Test
	void betterWay() {
		List<File>	 files = Stream.of(new File(".").listFiles())
				.flatMap(file -> file.listFiles() == null ?
						Stream.of(file):Stream.of(file.listFiles()))
				.collect(toList());
		System.out.println("Count: " + files.size());
	}
	@Test
	void watchFileChange() throws IOException {
		final Path path = Paths.get(".");
		final WatchService watchService =
		path.getFileSystem()
		.newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		System.out.println("Report any file changed within next 1 minute...");
	}
}
