package ch04;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Camera {
	private Function<Color, Color> filter;
	public Color capture(final Color inputColor) {
		final Color processedColor = filter.apply(inputColor);
		return processedColor;
	}
	public void setFilters(final Function<Color, Color> ... filters) {
		filter = Stream.of(filters).reduce((filter,next) -> filter.compose(next))
				.orElse(color -> color);
	}
	public Camera() {
		setFilters();
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		 final Camera camera = new Camera();
		    final Consumer<String> printCaptured = (filterInfo) ->
		      System.out.println(String.format("with %s: %s", filterInfo,   
		        camera.capture(new Color(200, 100, 200))));
		        
		    printCaptured.accept("no filter");
		    
		    camera.setFilters(Color::brighter);
		    printCaptured.accept("brighter filter");
		    
		    camera.setFilters(Color::darker);
		    printCaptured.accept("darker filter");
		    
		    camera.setFilters(Color::brighter, Color::darker);
		    printCaptured.accept("brighter & darker filter");
	}

}
