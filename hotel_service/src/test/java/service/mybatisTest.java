package service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.mapper.HotelInfoMapper;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/25 16:57
 */
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class mybatisTest {

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    @Test
    public void testSelect(){
        HotelInfoQuery query = new HotelInfoQuery();
        query.setLng("113.556838");
        query.setLat("22.222202");
        Page<HotelInfoExt> page = new Page<>(1,3);

        List<HotelInfoExt> hotelInfoExts = hotelInfoMapper.queryHotelInfoExtByPage(page,query);
        for (HotelInfoExt hotelInfoExt : hotelInfoExts) {
            System.out.println(hotelInfoExt.toString());
        }
    }

    @Test
    public void selectHotelRoomTypeFull(){
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(1l);
        List<HotelRoomTypeFullVO> hotelRoomTypeFullVOS = hotelRoomTypeMapper.queryHotelRoomTypeFullByPage(new Page(1, 20), query);
        for (HotelRoomTypeFullVO hotelRoomTypeFullVO : hotelRoomTypeFullVOS) {
            System.out.println(hotelRoomTypeFullVO);
        }
    }

    @Test
    public void selectSSS(){
        HotelRoomTypeFullVO hotelRoomTypeFullVO = hotelRoomTypeMapper.queryById(1l);
        System.out.println(hotelRoomTypeFullVO);
    }
}
