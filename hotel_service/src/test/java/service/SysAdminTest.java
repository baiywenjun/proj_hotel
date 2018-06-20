package service;

import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.service.SysAdminService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/11 11:06
 */
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SysAdminTest {

    @Autowired
    private SysAdminService sysAdminService;

    public void queryOne(){
        SysAdmin admin = sysAdminService.queryByLogin("admin");

    }
}
