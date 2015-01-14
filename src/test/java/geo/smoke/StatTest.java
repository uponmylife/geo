package geo.smoke;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StatTest {

    @Test
    public void testBetween() throws Exception {
        Date start = new SimpleDateFormat("yyyyMMdd").parse("20150101");
        Date middle = new SimpleDateFormat("yyyyMMdd").parse("20150102");
        Date end = new SimpleDateFormat("yyyyMMdd").parse("20150103");
        assertTrue(new Stat(middle, 0, 0).between(start, end));
        assertTrue(new Stat(middle, 0, 0).between(start, middle));
        assertTrue(new Stat(middle, 0, 0).between(middle, end));
        assertTrue(new Stat(middle, 0, 0).between(middle, middle));
        assertFalse(new Stat(start, 0, 0).between(middle, end));
        assertFalse(new Stat(end, 0, 0).between(start, middle));
    }
}