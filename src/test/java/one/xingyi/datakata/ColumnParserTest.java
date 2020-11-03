package one.xingyi.datakata;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnParserTest {

    String sampleInput = "1234567890123456789";

    @Test
    public void testStringIntIntColumnParser() {
        val parser = ColumnsParser.parseString(2, 3).thenInt(5, 7).thenInt(8, 9).parseWith((l, m, r) -> l + "," + m + "," + r);
        assertEquals("23,567,89", parser.apply(sampleInput));
    }

    @Test
    public void testIntegrationTestOfParser() {
        String s = "    1. Arsenal         38    26   9   3    79  -  36    87";
        assertEquals("Arsenal        ,79,36", DataKata.parserBuilder.parseWith((l, m, r) -> l + "," + m + "," + r).apply(s));
    }
}
