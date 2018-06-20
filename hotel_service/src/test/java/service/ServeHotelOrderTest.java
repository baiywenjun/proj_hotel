package service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;
import com.zxt.hotel.service.ServeHotelOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/7 14:54
 */
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ServeHotelOrderTest {

    @Autowired
    private ServeHotelOrderService serveHotelOrderService;

    @Test
    public void selectList(){
        ServeHotelOrderQuery query = new ServeHotelOrderQuery();
        query.setRoomNo("503");
        Rt rt = serveHotelOrderService.queryListByPage(query, 1, 5);
        System.out.println(rt);
    }
}
