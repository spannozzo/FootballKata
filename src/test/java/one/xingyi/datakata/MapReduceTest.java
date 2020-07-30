package one.xingyi.datakata;

import lombok.val;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MapReduceTest {

    @Test
    public void testMapReduce() {
        val reducer = new MapReduce<String, Integer>(Integer::parseInt, (acc, v) -> acc + v);
        assertEquals(10, reducer.mapReduce(Stream.of("1", "2", "3", "4")).intValue());
    }
}
