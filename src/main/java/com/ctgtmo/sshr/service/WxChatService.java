package com.ctgtmo.sshr.service;

import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.exception.ErrorException;

/**  
 * @Title: WxChatService.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.service   
 * @Description: 消息推送接口
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:49:02   
 */
public interface WxChatService {

  /** 
  * @Title: sendMessage 
  * @Description: 根据模板推送消息
  * @param params 参数
  * @author 王共亮
  * @date 2020年10月22日 上午10:49:50
  */
  JSONObject sendTemplateMessage(String params) throws ErrorException;

  /** 
  * @Title: sendTemplateMessage 
  * @Description: 使用传参方式appid发送消息
  * @param appId 公众号的appid
  * @param secret 公众号的appsecret
  * @param params 数据json
  * @throws ErrorException JSONObject  
  * @author 王共亮
  * @date 2020年10月22日 下午4:36:45
  */
  JSONObject sendTemplateMessage(String appId, String secret, String params) throws ErrorException;
}
