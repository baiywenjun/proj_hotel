package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.pojo.HotelOrderQuery;
import com.zxt.hotel.service.HotelOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Title: 酒店订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 9:55
 */
@RestController
@RequestMapping("/hotel/order")
@Api(tags = "酒店订单")
public class HotelOrderController {

    private static Logger log = LoggerFactory.getLogger(HotelOrderController.class);
    @Autowired
    private HotelOrderService hotelOrderService;

    @Autowired
    private AuthService authService;

    /**
     * 列表
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/my-list")
    @ApiOperation(httpMethod = "GET", value = "我的订单列表")
    public Rt hotelOrderList(HttpServletRequest request,String paymentStatus, Integer page, Integer limit){
        SysUser sysUser = authService.getUserInfoByReq(request);
        HotelOrderQuery query = new HotelOrderQuery();
        query.setIsUserId(sysUser.getUserId());
        query.setPaymentStatus(paymentStatus);
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return hotelOrderService.queryHotelOrderList(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 主键查询订单
     * @param orderId
     * @return
     */
    @RequestMapping("/find-one")
    @ApiOperation(httpMethod = "GET", value = "主键查询订单")
    public Rt findOneById(Long orderId){
        HotelOrderQuery query = new HotelOrderQuery();
        query.setOrderId(orderId);
        return hotelOrderService.queryHotelOrderList(query, 1, 1);
    }

    /**
     * 新增一条订单
     * 1.未付款
     * @param hotelOrder
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(httpMethod = "POST", value = "新增一条订单(未付款),仅适用小程序")
    public R addHotelOrder(@RequestBody HotelOrder hotelOrder ,HttpServletRequest request) {
        // todo 欠缺校验，如金额校验
        Map<String, Object> map = hotelOrderService.addHotelOrder(hotelOrder);
        // 直接调用支付 fix 降低耦合，废弃此处 20180606
        //OpenidInfo openid = authService.getOpenidInfoByReq(request);
        //Integer money = hotelOrder.getAmountPrice();
        //Map<String, Object> mapResult = this.wechatPay(map.get("orderNo").toString(), money, openid.getOpenid(),request);
        //map.putAll(mapResult);
        return (map.size()>1) ? R.ok(map) : R.error();
    }

    /**
     * 修改订单
     * 1.已付款，添加订单支付方式，支付状态，支付流水
     * 2.已分房，添加房间编号或id
     * @param hotelOrder
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改订单")
    public R updateHotelOrder(@RequestBody HotelOrder hotelOrder){
        if(hotelOrder.getOrderId() == null){
            return R.error(403,"主键不能为空");
        }
        Boolean flag = hotelOrderService.updateHotelOrder(hotelOrder);
        return (flag)?R.ok():R.error();
    }

    /**
     * 取消订单
     * @param hotelOrderId hotelOrderId
     * @return r
     */
    @RequestMapping("/delete")
    @ApiOperation(httpMethod = "GET", value = "取消订单")
    public R deleteHotelOrder(Long hotelOrderId){
        if(hotelOrderId == null){
            return R.error(403,"主键不能为空");
        }
        boolean flag = hotelOrderService.deleteById(hotelOrderId);
        return (flag)?R.ok():R.error();
    }


    // 直接增加订单后调用微信支付请求 fix 废弃20180606
    /*private Map<String,Object> wechatPay(String orderNo, Integer money, String openid, HttpServletRequest request){
        Double moneyF = money.doubleValue() / 100;
        log.info(moneyF.toString());
        String ipAddr = IPUtils.getIpAddr(request);
        Map<String,String> map = new HashMap<>();
        map.put("appid", WxPayConfig.getAppID());
        map.put("mch_id", WxPayConfig.getMchID());
        map.put("NOTIFY_URL", WxPayConfig.getNOTIFY_URL());
        map.put("orderNum", orderNo);
        map.put("body","入住酒店");
        map.put("spbill_create_ip",ipAddr);
        map.put("pay", moneyF.toString());
        map.put("trade_type","JSAPI");
        map.put("openid",openid);
        JSONObject json = WeiOrderPay.unifiedorder(map);
        log.info("[支付请求结果]===>"+json);
        return json;
    }*/


}
