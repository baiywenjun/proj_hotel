package com.zxt.api.wechatpay;

/**
 * 
* @ClassName: WxPayConfig  
* @Description: 微信支付信息配置 
* @author Peng
* @date 2016-11-29 上午11:06:26  
*
 */
public class WxPayConfig {
	//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
	public static  String key="rqY6Prr5wsSN6gbwwZPeSxIXXmNjyyrs";
	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static  String appID="wx325ffa9883c8dcc0";
	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static  String mchID="1480309632" ;
	//统一下单访问链接
	public static String payURL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	//通知回调地址
	public static  String NOTIFY_URL="https://interface.189itv.cn/api/pay/orderBack";
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		WxPayConfig.key = key;
	}
	public static String getAppID() {
		return appID;
	}
	public static void setAppID(String appID) {
		WxPayConfig.appID = appID;
	}
	public static String getMchID() {
		return mchID;
	}
	public static void setMchID(String mchID) {
		WxPayConfig.mchID = mchID;
	}
	public static String getPayURL() {
		return payURL;
	}
	public static void setPayURL(String payURL) {
		WxPayConfig.payURL = payURL;
	}
	public static String getNOTIFY_URL() {
		return NOTIFY_URL;
	}
	public static void setNOTIFY_URL(String nOTIFY_URL) {
		NOTIFY_URL = nOTIFY_URL;
	}
}
