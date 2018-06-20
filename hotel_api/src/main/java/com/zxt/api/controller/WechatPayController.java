package com.zxt.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxt.api.pojo.OpenidInfo;
import com.zxt.api.sersvice.AuthService;
import com.zxt.api.wechatpay.WeiOrderPay;
import com.zxt.api.wechatpay.WxPayConfig;
import com.zxt.api.wechatpay.payUtil;
import com.zxt.common.result.R;
import com.zxt.common.util.IPUtils;
import com.zxt.hotel.entity.ServeFoodOrder;
import com.zxt.hotel.service.HotelOrderService;
import com.zxt.hotel.service.ServeFoodOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 支付
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/30 15:57
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付")
public class WechatPayController {

    private static Logger log = LoggerFactory.getLogger(WechatPayController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private HotelOrderService hotelOrderService;

    @Autowired
    private ServeFoodOrderService foodOrderService;

    @PostMapping("/wechat")
    @ApiOperation(httpMethod = "POST", value = "微信支付(money单位是分)")
    public R wechatPay(HttpServletRequest request,String productName, String orderNo, Integer money){
        if(money == null){
            return R.error(403,"参数money为空");
        }
        OpenidInfo openid = authService.getOpenidInfoByReq(request);
        String ipAddr = IPUtils.getIpAddr(request);
        log.info("发起交易IP:"+ipAddr);
        Double moneyF = money.doubleValue() / 100;
        log.info("交易金额:"+moneyF.toString());
        Map<String,String>map=new LinkedHashMap<String,String>();
        map.put("appid", WxPayConfig.getAppID());
        map.put("mch_id", WxPayConfig.getMchID());
        map.put("NOTIFY_URL", WxPayConfig.getNOTIFY_URL());
        map.put("orderNum", orderNo);
        map.put("body",productName);
        map.put("openid",openid.getOpenid());
        map.put("spbill_create_ip",ipAddr);
        map.put("pay", moneyF.toString());
        map.put("trade_type","JSAPI");
        JSONObject json = WeiOrderPay.unifiedorder(map);
        log.info("json===>"+json);
        return R.ok("success",json);
    }


    /**
     * 用于微信支付成功后，回调地址
     * @param request req
     * @param response resp
     */
    @RequestMapping("/orderBack")
    public void WeChatOrderBack(HttpServletRequest request,HttpServletResponse response){
        List<NameValuePair> params = new ArrayList<NameValuePair>();//用于返回結果給微信
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            //读取微信服务器返回数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String str = "";
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            // 读取生成的xml转化成String
            String resultFromWx = sb.toString();
            // 将xml转化成map
            Map<String, String> map = payUtil.getMapFromXML(resultFromWx);
            if (map == null||map.size()<=0) {
                //微信支付不對
                params.clear();
                params.add(new BasicNameValuePair("return_code", "FAIL"));
                out.println(payUtil.toXml(params));
                out.flush();
                out.close();
                return;
            }
            if (map.get("return_code").equals("SUCCESS")&& map.get("result_code").equals("SUCCESS")){
                //微信支付成功
                log.info("========开始解析微信回调函数======================");
                log.info("{获得回调信息是【" + map.toString() + "】}");
                String transaction_id = map.get("transaction_id");//微信支付订单号
                String out_trade_no = map.get("out_trade_no");//商户订单号,平台内部的订单号
                String pay_time = map.get("time_end");//支付完成时间
                String appId = map.get("appid");//公众号id
                String openid = map.get("openid");//用户标识

                if(out_trade_no.charAt(0)=='H'){
                    //系统更新订单，将微信的流水号更新到订单字段总,然后将订单状态改成支付成功状态
                    hotelOrderService.updateOrderByPaid("wechat",out_trade_no, transaction_id);
                }else if(out_trade_no.charAt(0)=='F'){//2018-06-08
                    //系统更新订单，将微信的流水号更新到订单字段总,然后将订单状态改成支付成功状态
                    foodOrderService.updateOrderByPaid("food",out_trade_no, transaction_id);
                    foodOrderService.notifyCallback(map);
                }
            }

        }catch (Exception e) {
            log.error("{微信支付回调错误}");
        }
    }


}