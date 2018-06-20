package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.mapper.HotelInfoMapper;
import com.zxt.hotel.pojo.HotelInfoFullDistanceVO;
import com.zxt.hotel.pojo.HotelInfoFullVO;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.SysAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/11 11:06
 */
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelInfoTest {

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Test
    public void queryPage(){
        List<HotelInfoFullVO> hotelInfoFullVOList = hotelInfoMapper.queryHotelInfoFullByPage(new EntityWrapper(),null,null);
        for (HotelInfoFullVO hotelInfoFullVO : hotelInfoFullVOList) {
            System.out.println(hotelInfoFullVO);
        }
    }

    @Test
    public void queryDistancePage(){
        HotelInfoQuery query = new HotelInfoQuery();
        query.setLng("113.542416");
        query.setLat("22.270773");
//        List<HotelInfoFullVO> hotelInfoFullVOList = hotelInfoMapper.queryHotelInfoFullDistanceByPage(new Page<>(1, 2), query, new EntityWrapper());
//        for (HotelInfoFullVO hotelInfoFullVO : hotelInfoFullVOList) {
//            System.out.println(hotelInfoFullVO);
//        }
        List<HotelInfoFullDistanceVO> hotelInfoFullDistanceVOS = hotelInfoMapper.queryHotelInfoFullDistanceByPageOver(query, new EntityWrapper(),0,1);
        for (HotelInfoFullDistanceVO hotelInfoFullDistanceVO : hotelInfoFullDistanceVOS) {
            System.out.println(hotelInfoFullDistanceVO);
        }

    }
}
