package one.xingyi.datakata;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class WeatherSourceTest {

    @Test
    public void testCanLoadFromAFile() {
        assertEquals(Arrays.asList( "line2", "line3"), WeatherSource.stream("someLines.dat").collect(Collectors.toList()));
    }

}