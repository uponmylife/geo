package geo.smoke;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StatRepositoryTest {
    private Date d1 = newDate("20141231 0600");
    private Date d2 = newDate("20150101 0600");
    private Date d3 = newDate("20150103 1800");

    @Test
    public void testGenerate() throws Exception {
        List<Stat> stats = new StatRepository().generate(new Smoke(d1, 20, d2));
        assertEquals(2, stats.size());
        for (Stat stat : stats) {
            assertEquals(1.2, stat.getScore(), 0.0001);
            if (stat.getDate().equals("20141231")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150101")) assertEquals(0.25, stat.getWeight(), 0.0001);
            else fail();
        }
        stats = new StatRepository().generate(new Smoke(d1, 20, d3));
        assertEquals(4, stats.size());
        for (Stat stat : stats) {
            assertEquals(4.2, stat.getScore(), 0.0001);
            if (stat.getDate().equals("20141231")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150101")) assertEquals(1.0, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150102")) assertEquals(1.0, stat.getWeight(), 0.0001);
            else if (stat.getDate().equals("20150103")) assertEquals(0.75, stat.getWeight(), 0.0001);
            else fail();
        }
    }

    @Test
    public void testGetAvgScore() throws Exception {
        Date d4 = newDate("20150103 0600");
        List<Smoke> smokes = Arrays.asList(new Smoke(null, 0, d1), new Smoke(d1, 10, d2), new Smoke(d2, 10, d4));
        StatRepository repo = new StatRepository(smokes);
        assertEquals(3.6, repo.getAvgScore(3), 0.01);
    }

    private Date newDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyyMMdd HHmm").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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