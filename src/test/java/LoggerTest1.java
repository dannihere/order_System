
import com.SellApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
@Slf4j
//@Data
public class LoggerTest1 {

    @Test
    public void test1() {
        String name = "didi";
        String password = "123456";
        log.debug("debug...");
        log.info("name:{},password:{}", name, password);
    }
}
