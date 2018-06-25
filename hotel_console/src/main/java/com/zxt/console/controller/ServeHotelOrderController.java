package com.zxt.console.controller;

import com.zxt.common.constant.shoConst;
import com.zxt.common.customs.CallBack;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.pojo.ServeHotelOrderFullVO;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;
import com.zxt.hotel.service.ServeHotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 酒店服务订单管理
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/7 14:13
 */
@Controller
@RequestMapping("/serve/hotel")
public class ServeHotelOrderController {

    @Autowired
    private ServeHotelOrderService serveHotelOrderService;

    @RequestMapping("/page")
    public String serveHotelOrderPage() {
        return "components/serve_hotel_order";
    }

    /**
     * 查询服务订单
     *
     * @param request
     * @param roomNo
     * @param isServeTypeId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Rt serveHotelOrderList(HttpServletRequest request,
                                  Long serveHotelId,
                                  String roomNo,
                                  Long isServeTypeId, Integer page, Integer limit) {
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        ServeHotelOrderQuery query = new ServeHotelOrderQuery();

//        System.out.println("=== serveHotelId start ===");
        query.setServeHotelId(serveHotelId);
//        System.out.println(serveHotelId);
//        System.out.println("=== serveHotelId end ===");

//        System.out.println("=== roomNo start ===");
        query.setRoomNo(roomNo);
//        System.out.println(roomNo);
//        System.out.println("=== roomNo end ===");

//        System.out.println("=== isServeTypeId start ===");
//        System.out.println(isServeTypeId);
        query.setIsServeTypeId(isServeTypeId);
//        System.out.println("=== isServeTypeId end ===");

        return serveHotelOrderService.queryListByPage2(query, handle.getPage(), handle.getLimit());
    }


    /**
     * 查询服务订单
     *
     * @param request
     * @param roomNo
     * @param isServeTypeId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/orderDetail")
    @ResponseBody
    public Rt serveHotelOrderDetail(HttpServletRequest request,
                                  Long serveHotelId,
                                  String roomNo,
                                  Long isServeTypeId, Integer page, Integer limit) {
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        ServeHotelOrderQuery query = new ServeHotelOrderQuery();
        query.setServeHotelId(serveHotelId);
        query.setRoomNo(roomNo);
        query.setIsServeTypeId(isServeTypeId);

        return serveHotelOrderService.queryListByPageNCallback(query, handle.getPage(), handle.getLimit(), new CallBack<ServeHotelOrderFullVO>() {
            Map<String,Object> map=new HashMap<>();
            @Override
            public void dblist(List<ServeHotelOrderFullVO> list) {
                for (ServeHotelOrderFullVO vo:list){
//                    vo.getIsUserId();
                }
            }

            @Override
            public Map other() {
                return map;
            }
        });
    }


}
