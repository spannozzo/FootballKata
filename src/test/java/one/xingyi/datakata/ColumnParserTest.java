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
    public void testIntegrationTestOfParserWithFootball() {
        String s = "    1. Arsenal         38    26   9   3    79  -  36    87";
        assertEquals(new Football("Arsenal", 79, 36), DataKata.footballParser.apply(s));
    }

    @Test
    public void testIntegrationTestOfParserWithWeather() {
        String s = "   6  81    61    71          63.7       0.00 RFH     030  6.2 030  13  9.7  93 60 1012.7";
        assertEquals(new Weather(6, 81, 61), DataKata.weatherParser.apply(s));
    }
}
