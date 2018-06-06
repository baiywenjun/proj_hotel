package service;

import com.zxt.hotel.entity.SysWechat;
import com.zxt.hotel.service.SysWechatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/23 15:07
 */
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SysWechatTest {

    @Autowired
    private SysWechatService sysWechatService;

    @Test
    public void hello(){
        System.err.println("ok");
    }

    @Test
    public void findByOpenid(){
        String opendid = "ozHe05HqhWOvMRydC5DLjydRSwKM";
        SysWechat wechat = sysWechatService.findByOpenid(opendid);
        System.err.println(wechat);
    }


}
