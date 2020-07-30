package one.xingyi.datakata;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReducersTest {


    @Test
    public void testSmallestReducerComparesValueUsingByFn() {
        val reducer = Reducers.<String, Integer>smallestReducer(Integer::parseInt);
        assertEquals("1", reducer.apply("1", "3"));
        assertEquals("1", reducer.apply("3", "1"));
    }

}