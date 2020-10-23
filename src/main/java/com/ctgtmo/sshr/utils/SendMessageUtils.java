package com.ctgtmo.sshr.utils;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.constant.WxChatCache;
import com.ctgtmo.sshr.constant.WxChatConstant;
import com.ctgtmo.sshr.exception.ErrorException;
import com.ctgtmo.sshr.model.TemplateMessage;

/**  
 * @Title: SendMessageUtils.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.config   
 * @Description: 获取微信token工具类
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:05:29   
 */
@Component
public class SendMessageUtils {

  /**
   * 获取 accessToken （token有效期为2小时, 设置缓存, 过期并重新获取 accessToken）
   * 微信请求正确返回 {\"access_token\":\"37_B7bSN7N0VoqOVhf5rOk7NOHY6aMoxvE15VxNIcnD3f2kXvZkc0HOU-9rhfGZyWAoYkVfLrPzxTMhdcf86kgQeabfWSV-DH0hUYD8YMBF9vcbASzwRlEE3zJbKW2PuHJIl5Nu4BLouY4rUSFwTCBbAHAIRQ\",\"expires_in\":7200}
   * 微信请求错误返回 {"errcode":40013,"errmsg":"invalid appid"}
   * 流程：
   * 1、判断token是否过期
   * 2、获取 url 并拼接参数 APPID + APPSECRET
   * 3、发起请求
   * 4、判断请求是否成功
   * 5、缓存accessToken + accessToken过期时间到jvm 内存中
   * @throws ErrorException 
   */
  public static String getToken(String appId, String secret) throws ErrorException {
    if (WxChatCache.AccessToken.expiration <= System.currentTimeMillis()) {
      String url = WxChatConstant.Url.ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", secret);
      //http请求获取token
      String result = HttpRequest.sendGet(url);
      //解析请求结果
      JSONObject jsonObject = JSON.parseObject(result);
      //处理错误的请求并返回错误信息
      Object errcode = jsonObject.get("errcode");
      Object errmsg = jsonObject.get("errmsg");
      if (errcode != null) {
        throw new ErrorException(errmsg.toString());
      }
      // 设置为1小时59分钟有效期，防止空指针异常
      WxChatCache.AccessToken.token = jsonObject.get("access_token").toString();
      WxChatCache.AccessToken.expiration = ((Integer.parseInt(jsonObject.get("expires_in").toString()) - 1) * 1000) + System.currentTimeMillis();
    }
    return WxChatCache.AccessToken.token;
  }

  /** 
   * @Title: sendTemplateMessage 
   * @Description: 根据模板发送消息方法
   * @param appId 公众号的appid
   * @param secret 公众号的appsecret
   * @param templateMessage 数据封装实体
   * @author 王共亮
   * @throws ErrorException 
   * @date 2020年10月22日 下午3:48:27
   */
  public static JSONObject sendTemplateMessage(String appId, String secret, TemplateMessage templateMessage) throws ErrorException {
    //获取accessToken
    String accessToken = getToken(appId, secret);
    //替换请求地址中的accessToken的值
    String postUrl = WxChatConstant.Url.SEND_URL.replace("ACCESS_TOKEN", accessToken);
    //将封装的数据转成JSON
    String params = JSON.toJSONString(templateMessage);
    //发送请求
    String result = HttpRequest.sendPost(postUrl, params);
    // 返回和封装结果集
    return JSON.parseObject(result);
  }
}
