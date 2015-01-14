package geo.smoke;

import geo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SmokeRepositoryTest {
    @Autowired
    private SmokeRepository repository;

    @Test
    public void testFindTop1ByOrderByDateDesc() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        repository.save(new Smoke(sdf.parse("20150101"), 0, sdf.parse("20150102")));
        repository.save(new Smoke(sdf.parse("20150102"), 10, sdf.parse("20150103")));
        repository.save(new Smoke(sdf.parse("20150103"), 5, sdf.parse("20150104")));
        repository.save(new Smoke(sdf.parse("20150104"), 10, sdf.parse("20150108")));
        System.out.println(repository.findTop1ByOrderByDateDesc());
    }
}