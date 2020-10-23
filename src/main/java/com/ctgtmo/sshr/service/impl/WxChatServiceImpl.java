package com.ctgtmo.sshr.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.constant.WxChatConstant;
import com.ctgtmo.sshr.exception.ErrorException;
import com.ctgtmo.sshr.service.BaseWeChatService;
import com.ctgtmo.sshr.service.WxChatService;
import com.ctgtmo.sshr.utils.HttpRequest;

/**  
 * @Title: WxChatServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.service.impl   
 * @Description: 消息接口推送实现类
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:58:41   
 */
@Service("wxChatService")
public class WxChatServiceImpl extends BaseWeChatService implements WxChatService {

  /** 
   * @Title: sendMessage 
   * @Description: 根据模板推送消息
   * @param params 参数
   * @author 王共亮
   * @throws ErrorException 
   * @date 2020年10月22日 上午10:49:50
   */
  @Override
  public JSONObject sendTemplateMessage(String params) throws ErrorException {
    //获取token
    String accessToken = weChetAccessToken.getToken();
    //替换请求地址中的accessToken的值
    String postUrl = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
    //发送请求
    String result = HttpRequest.sendPost(postUrl, params);
    //解析请求结果
    return JSON.parseObject(result);
  }

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
  @Override
  public JSONObject sendTemplateMessage(String appId, String secret, String params) throws ErrorException {
    //获取token
    String accessToken = weChetAccessToken.getToken(appId, secret);
    //替换请求地址中的accessToken的值
    String postUrl = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
    //发送请求
    String result = HttpRequest.sendPost(postUrl, params);
    //解析请求结果
    return JSON.parseObject(result);
  }
}
