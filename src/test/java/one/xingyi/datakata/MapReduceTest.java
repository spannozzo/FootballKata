package one.xingyi.datakata;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MapReduceTest {

    @Test
    public void testMapReduce() {
        assertEquals(10, DataKata.mapReduce(Stream.of("1", "2", "3", "4"), Integer::parseInt, (acc, v) -> acc + v).intValue());
    }
}
