package app;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/22 9:42
 */
public class appTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml","spring/spring-service.xml");
        System.err.println(applicationContext);
    }

    @Test
    public void query(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
