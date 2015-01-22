package geo.yoyo;

import geo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PracticeRepositoryTest {
    @Autowired
    private PracticeRepository repository;

    @Test
    public void test() throws Exception {
        repository.save(new Practice(new Practice.Pk("20150120", 0), 20));
        repository.save(new Practice(new Practice.Pk("20150120", 1), 20));
        repository.save(new Practice(new Practice.Pk("20150120", 2), 20));
        repository.save(new Practice(new Practice.Pk("20150121", 0), 20));
        repository.save(new Practice(new Practice.Pk("20150121", 3), 20));
        repository.save(new Practice(new Practice.Pk("20150121", 4), 20));

    }
}