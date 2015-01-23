package geo.yoyo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReportCreatorTest {
    private List<Practice> practices = new ArrayList<Practice>();

    @Before
    public void setup() throws Exception {

    }

    private void addPractice(String day, Integer type, int score) {
        practices.add(new Practice(new Practice.Pk(day, type), score));
    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testGetAvgScore() throws Exception {
    }
}