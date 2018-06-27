package com.zxt.console.controller;

import com.zxt.common.constant.shoConst;
import com.zxt.common.customs.CallBack;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.pojo.HotelOrderQuery;
import com.zxt.hotel.pojo.ServeHotelOrderFullVO;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;
import com.zxt.hotel.service.HotelOrderService;
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


    @Autowired
    private HotelOrderService hotelOrderService;

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
                                  String hotelName,
                                  Long isServeTypeId, Integer page, Integer limit) {
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        ServeHotelOrderQuery query = new ServeHotelOrderQuery();

        query.setServeHotelId(serveHotelId);
        query.setRoomNo(roomNo);
        query.setIsServeTypeId(isServeTypeId);
        query.setHotelName(hotelName);

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
            Map<String, Object> map = new HashMap<>();
            Rt rt = new Rt();

            @Override
            public void dblist(List<ServeHotelOrderFullVO> list) {
                for (ServeHotelOrderFullVO vo : list) {
                    HotelOrderQuery query = new HotelOrderQuery();
                    query.setOrderId(vo.getIsOrderId());
                    rt = hotelOrderService.queryHotelOrderList(query, page, limit);
                }
            }

            @Override
            public Map other() {
                return rt;
            }
        });
    }


    /**
     * 接收
     *
     * @param serveHotelId id
     * @return r
     */
    @RequestMapping("/accept")
    @ResponseBody
    public R accept(Long serveHotelId) {
        if (serveHotelId == null) {
            return R.error(403, "[serveHotelId]不能为空");
        }
        ServeHotelOrder serveHotelOrder = serveHotelOrderService.selectById(serveHotelId);

        String status = serveHotelOrder.getStatus();
        if(status.equals(shoConst.APPLY)){
            serveHotelOrder.setStatus(shoConst.ACCEPT);
            boolean flag = serveHotelOrderService.updateById(serveHotelOrder);
            return (flag) ? R.ok() : R.error();
        }else{
            return R.error(-1,"接收失败!");
        }
    }

    /**
     * 完成
     *
     * @param serveHotelId id
     * @return r
     */
    @RequestMapping("/over")
    @ResponseBody
    public R over(Long serveHotelId) {
        if (serveHotelId == null) {
            return R.error(403, "[serveHotelId]不能为空");
        }
        ServeHotelOrder serveHotelOrder = serveHotelOrderService.selectById(serveHotelId);
        String status = serveHotelOrder.getStatus();
        if(status.equals(shoConst.CANCEL)){
            return R.error(-1,"不能完成已取消的订单!");
        }
        if(status.equals(shoConst.OVER)){
            return R.error(-2,"不能重复完成订单!");
        }
        if(status.equals(shoConst.APPLY)){
            return R.error(-3,"订单还未被接收!");
        }
        serveHotelOrder.setStatus(shoConst.OVER);
        boolean flag = serveHotelOrderService.updateById(serveHotelOrder);
        return (flag) ? R.ok() : R.error();
    }


}
