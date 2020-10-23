package com.ctgtmo.sshr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.constant.ApiResponse;
import com.ctgtmo.sshr.constant.ResponseMsg;
import com.ctgtmo.sshr.service.WxChatService;

/**  
 * @Title: WxChatController.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.controller   
 * @Description: 微信消息推送api
 * @author: 王共亮     
 * @date: 2020年10月22日 上午11:11:55   
 */
@Validated
@RestController
@RequestMapping("/wechat/template")
public class WxChatController {
  //服务注册
  @Autowired
  private WxChatService wxChatService;

  /** 
  * @Title: sendTemplateMessage 
  * @Description: 微信公众号推送消息-不带公众号appId和secret
  * @param content 模板内容json的格式数据
  * {
     "data":{
        "first":{
            "color":"#FF0000",--替换模板中的key值字体颜色
            "value":"预警" --替换模板中的key值
        },
        "remark":{
            "color":"#FF0000",
            "value":"点此处查看详情"
        }
    },
    "templateId":"onBYEwIV0t-IaB0swYg8Zg3h8mGM6NzPrUBhs84wSWQ",--模板id
    "touser":"o8ATL6qYwxJ04Abcll0tZgw5v3FM",--接收人的openId
    "url":"www.baidu.com"--URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
  }
  * @author 王共亮
  * @date 2020年10月22日 上午11:21:13
  */
  @RequestMapping(value = "/sendTemplateMessage", method = RequestMethod.POST)
  public ApiResponse sendTemplateMessage(@RequestBody String content) {
    // 返回结果集合声明
    ApiResponse response = null;
    try {
      // 返回和封装结果集
      JSONObject jsonObject = wxChatService.sendTemplateMessage(content);
      int code = jsonObject.getInteger("errcode");
      String errmsg = jsonObject.getString("errmsg");
      if (code == 0) {
        response = new ApiResponse(ApiResponse.SUCCESS, ResponseMsg.OPERATE_SUCCESS);
      } else {
        response = new ApiResponse(ApiResponse.FAIL, errmsg);
      }
    } catch (Exception e) {
      // 错误处理及返回
      response = new ApiResponse(ApiResponse.FAIL, ResponseMsg.OPERATE_FAIL);
      e.printStackTrace();
    }
    return response;
  }

  /** 
   * @Title: pushTemplateMessage
   * @Description: 微信公众号推送消息-带公众号appId和secret
   * @param appId 公众号的appid
   * @param secret 公众号的appsecret
   * @param content 模板内容json的格式数据
   * {
      "data":{
         "first":{
             "color":"#FF0000",--替换模板中的key值字体颜色
             "value":"预警" --替换模板中的key值
         },
         "remark":{
             "color":"#FF0000",
             "value":"点此处查看详情"
         }
     },
     "templateId":"onBYEwIV0t-IaB0swYg8Zg3h8mGM6NzPrUBhs84wSWQ",--模板id
     "touser":"o8ATL6qYwxJ04Abcll0tZgw5v3FM",--接收人的openId
     "url":"www.baidu.com"--URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
   }
   * @author 王共亮
   * @date 2020年10月22日 上午11:21:13
   */
  @RequestMapping(value = "/pushTemplateMessage", method = RequestMethod.POST)
  public ApiResponse pushTemplateMessage(@RequestBody Map<String, Object> map) {
    //微信公众号appId
    String appId = map.get("appId").toString();
    //微信公众号secret
    String secret = map.get("secret").toString();
    //传入的模板消息
    String content = map.get("content").toString();
    // 返回结果集合声明
    ApiResponse response = null;
    try {
      // 返回和封装结果集
      JSONObject jsonObject = wxChatService.sendTemplateMessage(appId, secret, content);
      int code = jsonObject.getInteger("errcode");
      String errmsg = jsonObject.getString("errmsg");
      if (code == 0) {
        response = new ApiResponse(ApiResponse.SUCCESS, ResponseMsg.OPERATE_SUCCESS);
      } else {
        response = new ApiResponse(ApiResponse.FAIL, errmsg);
      }
    } catch (Exception e) {
      // 错误处理及返回
      response = new ApiResponse(ApiResponse.FAIL, ResponseMsg.OPERATE_FAIL);
      e.printStackTrace();
    }
    return response;
  }
}
