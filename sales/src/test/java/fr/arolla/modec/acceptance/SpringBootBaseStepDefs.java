package fr.arolla.modec.acceptance;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
public class SpringBootBaseStepDefs {

}
