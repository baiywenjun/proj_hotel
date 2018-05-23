import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> param = new HashMap<>();
        ResponseEntity<Map> entity = restTemplate.postForEntity("http://127.0.0.1:8080/console/test/",null, Map.class, param);
        Map result = entity.getBody();
        System.out.println(result);
    }
}
