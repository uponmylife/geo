package geo.smoke;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StatRepositoryTest {
    @Test
    public void testGenerate() throws Exception {
        Date d1 = newDate("20141231 0600");
        Date d2 = newDate("20150101 0600");
        List<Stat> stats = new StatRepository().generate(new Smoke(d1, 20, d2));
        assertEquals(2, stats.size());
        for (Stat stat : stats) {
            assertEquals(1.2, stat.getScore(), 0.0001);
            if (stat.getDate().equals("20141231")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150101")) assertEquals(0.25, stat.getWeight(), 0.0001);
            else fail();
        }
        Date d3 = newDate("20150102 1800");
        stats = new StatRepository().generate(new Smoke(d1, 20, d3));
        assertEquals(3, stats.size());
        for (Stat stat : stats) {
            assertEquals(3.0, stat.getScore(), 0.0001);
            if (stat.getDate().equals("20141231")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150101")) assertEquals(1.0, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150102")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else fail();
        }
    }

    private Date newDate(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd HHmm").parse(dateString);
    }

    @Test
    public void testGetDayWeight() throws Exception {
        StatRepository repo = new StatRepository();
        Date date = new SimpleDateFormat("yyyyMMdd HH").parse("20150113 6");
        assertEquals(0.25, repo.getDayWeight(date, true), 0.00000001);
        assertEquals(0.75, repo.getDayWeight(date, false), 0.00000001);
    }

    @Test
    public void testFindAll() throws Exception {

    }
}