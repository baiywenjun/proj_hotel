package service;

import com.alibaba.druid.filter.AutoLoad;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.pojo.HotelRoomQuery;
import com.zxt.hotel.service.HotelRoomService;
import com.zxt.hotel.service.SysAdminService;
import org.junit.Test;
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
public class HotelRoomTest {

    @Autowired
    private HotelRoomService hotelRoomService;

    @Test
    public void selectExt(){
        HotelRoomQuery query = new HotelRoomQuery();
        Rt rt = hotelRoomService.queryHotelRoomExtByPage(query, 1, 5);
        System.out.println(rt);
    }
}
