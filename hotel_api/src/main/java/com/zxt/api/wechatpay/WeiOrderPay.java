package com.zxt.api.wechatpay;


import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
/***
 * 
* @ClassName: WeiOrderPay  
* @Description: 微信支付訂單相關  
* @author Peng
* @date 2016-11-29 上午11:09:28  
*
 */
public class WeiOrderPay{
  
  /***
   * 微信支付，trade_type JSAPI 微信公众号支付，NATIVE 原生扫码支付，APP APP支付
  * @Title: unifiedorderByPayMerchant  
  * @Description:微信支付服务商支付功能
  * @param  map
  * @param 
  * @return JSONObject    返回类型  
  * @throws 
    @author  Peng
   */
  public static JSONObject unifiedorderByPayMerchant(Map<String,String>map){
	  String appid=map.get("appid");
	  String mch_id=map.get("mch_id");
	  String attach=map.get("attach");
	  String NOTIFY_URL=map.get("NOTIFY_URL");
	  String orderNum=map.get("orderNum");
	  String body=map.get("body");
	  String openid=map.get("openid");
	  String spbill_create_ip=map.get("spbill_create_ip");
	  double pay=Double.parseDouble(map.get("pay"));
	  String trade_type=map.get("trade_type");  
	  String sub_appid=map.get("sub_appid");
	  String sub_mch_id=map.get("sub_mch_id");
	  String sub_openid=map.get("sub_openid");
			  
	  return payUtil.getWxPayParam(appid, mch_id, sub_appid, sub_mch_id, sub_openid, attach, NOTIFY_URL, orderNum, body, openid, spbill_create_ip, pay, trade_type);
  }
  
  
  
  /*****
   * 微信支付
   * 生成统一下单接口-非品牌商
   * @param map
   * @return
   */
  public static JSONObject  unifiedorder(Map<String,String>map){
	  String appid=map.get("appid");
	  String mch_id=map.get("mch_id");
	  String attach=map.get("attach");
	  String NOTIFY_URL=map.get("NOTIFY_URL");
	  String orderNum=map.get("orderNum");
	  String body=map.get("body");
	  String openid=map.get("openid");
	  String spbill_create_ip=map.get("spbill_create_ip");
	  double pay=Double.parseDouble(map.get("pay"));
	  String trade_type=map.get("trade_type");  
	  return payUtil.getWxPayParam(appid,mch_id, attach, NOTIFY_URL, orderNum, body, openid, spbill_create_ip, pay, trade_type);
  }
  
	public static void main(String[] args){
		Map<String,String>map=new LinkedHashMap<String,String>();
		map.put("appid", WxPayConfig.getAppID());
		map.put("mch_id", WxPayConfig.getMchID());
		map.put("NOTIFY_URL", WxPayConfig.getNOTIFY_URL());
		map.put("orderNum",payUtil.getOrderNum("168687688687878797465478979"));
		map.put("body","逸米酒店-大床房1间");
		map.put("openid","ozHe05LQEcSqKvVoMixaxzXyvzEA");
		map.put("spbill_create_ip","112.124.113.188");
		map.put("pay", "12");
		map.put("trade_type","JSAPI");
		JSONObject json=unifiedorder(map);
		System.out.println("json===>"+json);
	}
 
}
