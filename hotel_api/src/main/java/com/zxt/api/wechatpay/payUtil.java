package com.zxt.api.wechatpay;


import com.alibaba.fastjson.JSONObject;
import com.zxt.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
public class payUtil {
	private static Logger log = LoggerFactory.getLogger(payUtil.class);
	/**
	 * 
	 * @discription 生成签名
	 * @author wwj
	 * @created 2015-8-4 下午2:39:54
	 * @param params
	 *            签名参数
	 * @return
	 */
	public static String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		System.out.println("生成值==>"+sb.toString());
		sb.append("key=");
		sb.append(WxPayConfig.key);
		String packageSign =MD5.MD5Encode(sb.toString()).toUpperCase();
		return packageSign;
	}

	
	/**
	 * 
	* @Title: genPackageSign  
	* @Description:生成支付签名 
	* @param  params
	* @param  key 支付密钥
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws 
	  @author  Peng
	 */
	public static String genPackageSign(List<NameValuePair> params,String key) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(key);
		log.info("{得到的签名字符是：【"+sb.toString()+"】}");
		String packageSign = MD5.MD5Encode(sb.toString()).toUpperCase();
		return packageSign;
	}
	
	/**
	 * 
	 * @discription 拼接xml
	 * @author wwj
	 * @created 2015-8-4 下午2:42:16
	 * @param params
	 *            参数
	 * @return
	 */
	public static String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");

			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 
	 * @discription 请求统一下单
	 * @author wwj
	 * @created 2015-8-4 下午2:48:22
	 * @return
	 */
	public static Map<String, String> doInBackground(String xml) {

		String url = String.format(WxPayConfig.payURL);

		byte[] buf = new HttpRequestor().httpPost(url, xml);
		String content = new String(buf, Charset.forName("UTF-8"));
		Map<String, String> map = getMapFromXML(content);
		return map;
	}

	/**
	 * 
	 * @discription 解析xml
	 * @author wwj
	 * @created 2015-8-4 下午3:08:20
	 * @param xmlString
	 * @return
	 */
	public static Map<String, String> getMapFromXML(String xmlString) {
		try {
			// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = payUtil.getStringStream(xmlString);
			Document document = builder.parse(is);

			// 获取到document里面的全部结点
			NodeList allNodes = document.getFirstChild().getChildNodes();
			Node node;
			Map<String, String> map = new HashMap<String, String>();
			int i = 0;
			while (i < allNodes.getLength()) {
				node = allNodes.item(i);
				if (node instanceof Element) {
					map.put(node.getNodeName(), node.getTextContent());
				}
				i++;
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * @discription 获取字符流
	 * @author wwj
	 * @created 2015-8-4 下午3:08:28
	 * @param sInputString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputStream getStringStream(String sInputString)
			throws UnsupportedEncodingException {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(
					sInputString.getBytes("UTF-8"));
		}
		return tInputStringStream;
	}

	/**
	 * 
	 * @discription 随机字符串
	 * @author wwj
	 * @created 2015-8-4 下午2:46:56
	 * @return
	 */
	public static String genNonceStr() {
		Random random = new Random();
		return MD5.MD5Encode(String.valueOf(random.nextInt(10000)));
	}

	/**
	 * 
	 * @discription 获取时间戳（秒数）
	 * @author wwj
	 * @created 2015-8-4 下午2:47:24
	 * @return
	 */
	public static long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

    
	/**
	 * 
	     * @discription 生成统一下单XML参数
	     * @author Peng      
	     * @created 2015-8-3 下午5:39:56 
	     * @param appid 公众账号ID <br/> 
	     * @param attach 自定义数据  java.lang.String(127) <br/> 
	     * @param mch_id 商户id <br/>
	     * @param NOTIFY_URL 回调地址 <br/>  
	     * @param orderNum 订单号<br/>
	     * @param body 商品描述<br/>
	     * @param nonce_str 随机字符串 <br/>
	     * @param openid 微信用户标识 <br/>
	     * @param pay 支付金额  java.lang.double <br/>
	     * @param spbill_create_ip 客户端IP <br/>
	     * @param trade_type 支付方式  JSAPI，NATIVE，APP 默认为JSAPI <br/>
	     * @return  xml java.lang.String
	 */
	public static  String buildWxPayInfo(String appid,String mch_id,String attach,String NOTIFY_URL,String orderNum,String body,String nonce_str,String openid,String spbill_create_ip,double pay,String trade_type){
		StringBuffer xml = new StringBuffer();
		try {
			xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid",appid));
			//packageParams.add(new BasicNameValuePair("attach",attach));
			//body=URLEncoder.encode(body, "utf-8");
			packageParams.add(new BasicNameValuePair("body",body));
			packageParams.add(new BasicNameValuePair("mch_id",mch_id));
			packageParams.add(new BasicNameValuePair("nonce_str", payUtil.genNonceStr()));
			packageParams.add(new BasicNameValuePair("notify_url", NOTIFY_URL));
			if(!StringUtils.isEmpty(openid)){
			packageParams.add(new BasicNameValuePair("openid", openid));
			}
			packageParams.add(new BasicNameValuePair("out_trade_no",orderNum));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",spbill_create_ip));
			
			String price=pay*100+"";
			price=price.substring(0, price.indexOf("."));
			packageParams.add(new BasicNameValuePair("total_fee", price));
			packageParams.add(new BasicNameValuePair("trade_type", trade_type==null?"JSAPI":trade_type));
			//根据参数生成签名信息
			String sign = payUtil.genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));
			String xmlstring =payUtil.toXml(packageParams);
			log.info("{统一下单接口==》生成微信统一下单参数结束生成xml，生成结果\n【"+xmlstring+"】}");
			return xmlstring;
		} catch (Exception e) {
			//log.error("{payUtil 统一下单接口==》生成微信统一下单参数结束生成xml，生成结果\n【"+e.getMessage()+"】}");
			return "";
		}
	}

	
   
	/**
	 * 
	     * @discription 生成统一下单XML参数--支付服务商子商户
	     * @author Peng      
	     * @created 2015-8-3 下午5:39:56 
	     * @param appid 公众账号ID <br/> 
	     * @param attach 自定义数据  java.lang.String(127) <br/> 
	     * @param mch_id 商户id <br/>
	     * @param sub_appid 子商户app id
	     * @param sub_mch_id 子商户商户id
	     * @param sub_openid 子商户openid
	     * @param NOTIFY_URL 回调地址 <br/>  
	     * @param orderNum 订单号<br/>
	     * @param body 商品描述<br/>
	     * @param nonce_str 随机字符串 <br/>
	     * @param openid 微信用户标识 <br/>
	     * @param pay 支付金额  java.lang.double <br/>
	     * @param spbill_create_ip 客户端IP <br/>
	     * @param trade_type 支付方式  JSAPI，NATIVE，APP 默认为JSAPI <br/>
	     * @return  xml java.lang.String
	 */
	public static  String buildWxPayInfo(String appid,String mch_id,String sub_appid,String sub_mch_id,String sub_openid,String attach,String NOTIFY_URL,String orderNum,String body,String nonce_str,String openid,String spbill_create_ip,double pay,String trade_type){
		StringBuffer xml = new StringBuffer();
		try {
			xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid",appid));
			packageParams.add(new BasicNameValuePair("attach",attach));
			//body=URLEncoder.encode(body, "utf-8");
			packageParams.add(new BasicNameValuePair("body",body));
			packageParams.add(new BasicNameValuePair("mch_id",mch_id));
			packageParams.add(new BasicNameValuePair("nonce_str", payUtil.genNonceStr()));
			packageParams.add(new BasicNameValuePair("notify_url", NOTIFY_URL));
			//packageParams.add(new BasicNameValuePair("openid", openid));
			packageParams.add(new BasicNameValuePair("out_trade_no",orderNum));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",spbill_create_ip));
			packageParams.add(new BasicNameValuePair("sub_appid",sub_appid));
			packageParams.add(new BasicNameValuePair("sub_mch_id",sub_mch_id));
			packageParams.add(new BasicNameValuePair("sub_openid",sub_openid));
			String price=pay*100+"";
			price=price.substring(0, price.indexOf("."));
			packageParams.add(new BasicNameValuePair("total_fee", price));
			packageParams.add(new BasicNameValuePair("trade_type", trade_type==null?"JSAPI":trade_type));
			//根据参数生成签名信息
			String sign = payUtil.genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));
			String xmlstring =payUtil.toXml(packageParams);
			log.info("{统一下单接口==》生成微信统一下单参数结束生成xml，生成结果\n【"+xmlstring+"】}");
			return xmlstring;
		} catch (Exception e) {
			//log.error("{payUtil 统一下单接口==》生成微信统一下单参数结束生成xml，生成结果\n【"+e.getMessage()+"】}");
			return "";
		}
	}

	
	
	
	
	
	
	/**
	 * 
	     * @discription 提交统一下单接口，返回唤起微信支付凭证
	     * @author Peng       
	     * @created 2015-8-4 下午3:19:30     
	     * @param orderNum 订单编号
	     * @param pay 支付金额
	     * @param spbill_create_ip 请求ip
	     * @param openid 微信openid
	     * @param attach 自定义参数
	     * @param body 商品描述
	     * @return  JSONobject 返回微信H5支付凭证
	 */
	public static  JSONObject getWxPayParam(String appid,String mch_id,String attach,String NOTIFY_URL,String orderNum,String body,String openid,String spbill_create_ip,double pay,String trade_type){
		JSONObject result=new JSONObject();
		trade_type=trade_type==null||"".equals(trade_type)?"JSAPI":trade_type;
		String noncestr=payUtil.genNonceStr();
		
		//获取统一下单请求xml
		String ty_pay_xml=buildWxPayInfo(appid,mch_id,attach,NOTIFY_URL,orderNum,body,noncestr,openid,spbill_create_ip,pay,trade_type);
		if("".equals(ty_pay_xml)){
		// log.info("请求统一下单接口，微信返回结果失败，失败原因:生成微信下单请求参数错误");
		 result.put("success", false);
		 result.put("error_code","1001");
		 result.put("message", "生成参数错误");
		 return result;	
		}
		
		
		
		
			//生成的xml字符提交微信支付，提交统一下单接口
			Map<String,String> map=payUtil.doInBackground(ty_pay_xml);
			if(map==null||map.size()<=0){
			//	log.info("请求统一下单接口，微信返回结果失败，失败原因:返回结果不能生成map。\n返回结果是【"+ty_pay_xml+"】");
				 result.put("success", false);
				 result.put("error_code","1001");
				 result.put("message", "调用下单接口错误，结果是【"+ty_pay_xml+"】");
				 return result;	
			}
			
			
			
			
			
				//log.info("请求统一下单接口，微信返回结果:"+map.get("return_code")+map.get("return_msg"));
				if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
					
					String prepay_id=map.get("prepay_id");//预支付交易会话ID
					String timestamp=String.valueOf(payUtil.genTimeStamp());
					String sign=makeJsSign(appid,timestamp,noncestr,prepay_id);
				
					//拼接请求交易的json
					JSONObject json=new JSONObject();
					json.put("appid", appid);
					json.put("timestamp", timestamp);
					json.put("noncestr", noncestr);
					json.put("packages", "prepay_id="+prepay_id);
					json.put("signType", "MD5");
					json.put("code_url",map.get("code_url") );
					json.put("paySign", sign);
					json.put("success", true);
					json.put("message", "生成成功");
					return json;
				}
				
					//log.info("请求统一下单接口，微信返回结果失败，失败原因:"+map.get("return_msg"));
					 result.put("success", false);
					 result.put("error_code","1001");
					 result.put("message", "调用下单接口错误，结果是【"+map.get("return_msg")+"】");
			   return result;	
				
	}
	
	
	
	public static  JSONObject getWxPayParam(String appid,String mch_id,String sub_appid,String sub_mch_id,String sub_openid,String attach,String NOTIFY_URL,String orderNum,String body,String openid,String spbill_create_ip,double pay,String trade_type){
		
		JSONObject result=new JSONObject();
		trade_type=trade_type==null||"".equals(trade_type)?"JSAPI":trade_type;
		String noncestr=payUtil.genNonceStr();
		
		//获取统一下单请求xml
		String ty_pay_xml=buildWxPayInfo(appid,mch_id,sub_appid,sub_mch_id,sub_openid,attach,NOTIFY_URL,orderNum,body,noncestr,openid,spbill_create_ip,pay,trade_type);
		if("".equals(ty_pay_xml)){
		// log.info("请求统一下单接口，微信返回结果失败，失败原因:生成微信下单请求参数错误");
		 result.put("success", false);
		 result.put("error_code","1001");
		 result.put("message", "生成参数错误");
		 return result;	
		}
		
		
		
		
			//生成的xml字符提交微信支付，提交统一下单接口
			Map<String,String> map=payUtil.doInBackground(ty_pay_xml);
			if(map==null||map.size()<=0){
			//	log.info("请求统一下单接口，微信返回结果失败，失败原因:返回结果不能生成map。\n返回结果是【"+ty_pay_xml+"】");
				 result.put("success", false);
				 result.put("error_code","1001");
				 result.put("message", "调用下单接口错误，结果是【"+ty_pay_xml+"】");
				 return result;	
			}
			
			
			
			
			
				//log.info("请求统一下单接口，微信返回结果:"+map.get("return_code")+map.get("return_msg"));
				if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
					
					String prepay_id=map.get("prepay_id");//预支付交易会话ID
					String timestamp=String.valueOf(payUtil.genTimeStamp());
					String sign=makeJsSign(appid,timestamp,noncestr,prepay_id);
				
					//拼接请求交易的json
					JSONObject json=new JSONObject();
					json.put("appid", appid);
					json.put("timestamp", timestamp);
					json.put("noncestr", noncestr);
					json.put("packages", "prepay_id="+prepay_id);
					json.put("signType", "MD5");
					json.put("paySign", sign);
					json.put("success", true);
					json.put("message", "生成成功");
					return json;
				}
				
					//log.info("请求统一下单接口，微信返回结果失败，失败原因:"+map.get("return_msg"));
					 result.put("success", false);
					 result.put("error_code","1001");
					 result.put("message", "调用下单接口错误，结果是【"+map.get("return_msg")+"】");
			   return result;	
				
	}
	
	
	
	
	
	/***
	   * 根据参数生成二维码
	   * @return
	   */
	 public static String createPayQr(String product_id){
		 List<NameValuePair> params=new ArrayList<NameValuePair>();
		 long time_stamp=genTimeStamp();
		 String nonce_str=genNonceStr();
		 params.add(new BasicNameValuePair("appid",WxPayConfig.getAppID()));
		 params.add(new BasicNameValuePair("mch_id",WxPayConfig.getMchID()));
		 params.add(new BasicNameValuePair("nonce_str",nonce_str));
		 params.add(new BasicNameValuePair("product_id",product_id));
		 params.add(new BasicNameValuePair("time_stamp",time_stamp+""));
		 String sign=genPackageSign(params,WxPayConfig.getKey());
		 
		 StringBuilder qrStr=new StringBuilder();
		 qrStr.append("weixin://wxpay/bizpayurl?");
		 qrStr.append("appid="+WxPayConfig.getAppID());
		 qrStr.append("&mch_id="+WxPayConfig.getMchID());
		 qrStr.append("&nonce_str="+nonce_str.trim());
		 qrStr.append("&product_id="+product_id.trim());
		 qrStr.append("&time_stamp="+time_stamp);
		 qrStr.append("&sign="+sign.trim());
		
		 
		// String qrStr="weixin://wxpay/bizpayurl?sign="+sign+"&appid="+WxPayConfig.getAppID()+"&mch_id="+WxPayConfig.getMchID()+"&product_id="+product_id+"&time_stamp="+time_stamp+"&nonce_str="+nonce_str;
				 //"weixin://wxpay/bizpayurl?appid="+WxPayConfig.getAppID()+"&mch_id="+WxPayConfig.getMchID()+"&nonce_str="+nonce_str+"&product_id="+product_id+"&time_stamp="+time_stamp+"&sign="+sign;
	   return qrStr.toString();
	 }  
	
	 
	 /***
	  * 
	  * 生成订单号
	  * **/
	 public static String getOrderNum(String product_id){
		 product_id=StringUtils.isEmpty(product_id)?"":product_id;
		 String orderNuM= DateUtil.formatDateByFormat(new Date(), "yyyyMMddHHmmss");
		 return product_id + orderNuM;
	 }
	
	 
	
	 /**
	  * 
	  * 生成url参数
	  * **/
	 public String ToUrlParams(SortedMap<Object, Object> packageParams){  
	        //实际可以不排序  
	        StringBuffer sb = new StringBuffer();    
	        Set es = packageParams.entrySet();    
	        Iterator it = es.iterator();    
	        while (it.hasNext()) {    
	            Map.Entry entry = (Map.Entry) it.next();    
	            String k = (String) entry.getKey();    
	            String v = (String) entry.getValue();    
	            if (null != v && !"".equals(v)) {    
	                sb.append(k + "=" + v + "&");    
	            }    
	        }  
	          
	        sb.deleteCharAt(sb.length()-1);//删掉最后一个&  
	        return sb.toString();  
	    }  
	 
	 
	 
	
	/***
	 * 
	* @Title: makeJsSign  
	* @Description:
	* @param @param appid
	* @param @param timeStamp
	* @param @param noncestr
	* @param @param prepay_id
	* @param @return    设定文件  
	* @return JSONObject    返回类型  
	* @throws 
	  @author  Peng
	 */
	private static String makeJsSign(String appid,String timeStamp, String noncestr,String prepay_id){
		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appId", appid));
		signParams.add(new BasicNameValuePair("nonceStr", noncestr));
		signParams.add(new BasicNameValuePair("package","prepay_id="+prepay_id));
		signParams.add(new BasicNameValuePair("signType", "MD5"));
		signParams.add(new BasicNameValuePair("timeStamp", timeStamp));
		String sign = payUtil.genPackageSign(signParams); 	
		return sign;
	}
	


    

     

}
