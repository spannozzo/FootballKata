package one.xingyi.datakata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WeatherSource {
    static public Stream<String> stream(String resourceName) {
        try {
            return Files.lines(Paths.get(WeatherSource.class.getClassLoader().getResource(resourceName).toURI())).
                    skip(1).
                    filter(s -> !s.trim().startsWith("-")).
                    filter(s -> !s.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
