package cafe.service.common;


import cafe.service.CafeApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { CafeApplication.class })
public class CucumberSpingConfiguration {
    
}
