package geo.smoke;

import geo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SmokeRepositoryTest {
    @Autowired
    private SmokeRepository repository;

    @Test
    public void save() throws Exception {
        repository.save(new Smoke(new Date(), 40, 20));
        Thread.sleep(10);
        repository.save(new Smoke(new Date(), 24, 10));
        Thread.sleep(10);
        repository.save(new Smoke(new Date(), 60, 30));
        System.out.println(repository.findTop1ByOrderByDateDesc());
    }
}