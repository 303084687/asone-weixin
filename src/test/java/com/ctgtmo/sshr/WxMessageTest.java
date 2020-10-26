package com.ctgtmo.sshr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctgtmo.sshr.exception.ErrorException;
import com.ctgtmo.sshr.model.Data;
import com.ctgtmo.sshr.model.Miniprogram;
import com.ctgtmo.sshr.model.TemplateContent;
import com.ctgtmo.sshr.model.TemplateMessage;
import com.ctgtmo.sshr.service.WxChatService;

/**  
 * @Title: WxMessageTest.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.test   
 * @Description: 
 * @author: 王共亮     
 * @date: 2020年10月23日 上午10:58:41   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class WxMessageTest {
  //服务注册
  @Autowired
  private WxChatService wxChatService;

  /** 
  * @Title: pushTemplateMessage 
  * @Description: 自带appId和secret的消息推送。微信公众号测试账号地址http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
  * @throws ErrorException void  
  * @author 王共亮
  * @date 2020年10月23日 上午11:36:56
  */
  @Test
  public void pushTemplateMessage() throws ErrorException {
    //创建消息发送实体对象
    TemplateMessage templateMessage = new TemplateMessage();
    //url为空则没有链接地址
    templateMessage.setUrl("www.baidu.com");
    //接收人的openId
    templateMessage.setTouser("oZAEusxcZPmw-stEL8_Ga_Qc6rVQ");
    //模板id
    templateMessage.setTemplate_id("cPN2wZ7w0_isFgR6_gfg2wUKvYhCbFTBCy_FZHZvgo4");
    /********************************模板中对应的key*********************************************/
    //设置模板标题
    TemplateContent first = new TemplateContent("您提交的审批，有最新结果！", "#D2691E");
    //设置模板内容
    TemplateContent keyword1 = new TemplateContent("通用表单测试", "#FF0000");
    //设置模板位置
    TemplateContent keyword2 = new TemplateContent("TYBD-170306-00299", "#0000FF");
    //设置设备
    TemplateContent keyword3 = new TemplateContent("审批通过", "#00FF7F");
    //设置时间
    TemplateContent keyword4 = new TemplateContent("2020-10-26 10:40:42", "#808080");
    //设置跳转内容
    TemplateContent remark = new TemplateContent("感谢你的使用", "#FFA500");
    //创建模板信息数据对象
    Data data = new Data();
    data.setFirst(first);
    data.setKeyword1(keyword1);
    data.setKeyword2(keyword2);
    data.setKeyword3(keyword3);
    data.setKeyword4(keyword4);
    data.setRemark(remark);
    templateMessage.setData(data);
    //小程序参数对象--非必传
    Miniprogram miniprogram = new Miniprogram();
    miniprogram.setAppid("wx7c4c3e4664f81fd3");
    miniprogram.setPagepath("");
    templateMessage.setMiniprogram(miniprogram);
    String params = JSON.toJSONString(templateMessage);
    //微信公众号appId
    String appId = "wx0525e166b8946b29";
    //微信公众号secret
    String secret = "0e8d1003165696b9aa45acd8aa4c3dd6";
    // 返回和封装结果集
    JSONObject jsonObject = wxChatService.sendTemplateMessage(appId, secret, params);
    int code = jsonObject.getInteger("errcode");
    String errmsg = jsonObject.getString("errmsg");
    Assert.assertEquals("0", String.valueOf(code));
  }

  /** 
  * @Title: sendTemplateMessage 
  * @Description: 使用配置的appId和secret的消息推送。微信公众号测试账号地址http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
  * @throws ErrorException void  
  * @author 王共亮
  * @date 2020年10月23日 上午11:36:37
  */
  //@Test
  public void sendTemplateMessage() throws ErrorException {
    //创建消息发送实体对象
    TemplateMessage templateMessage = new TemplateMessage();
    //url为空则没有链接地址
    templateMessage.setUrl("");
    //接收人的openId
    templateMessage.setTouser("o8ATL6qYwxJ04Abcll0tZgw5v3FM");
    //模板id
    templateMessage.setTemplate_id("onBYEwIV0t-IaB0swYg8Zg3h8mGM6NzPrUBhs84wSWQ");
    /********************************模板中对应的key*********************************************/
    //设置模板标题
    TemplateContent first = new TemplateContent("预警测试", "#D2691E");
    //设置模板内容
    TemplateContent keyword1 = new TemplateContent("测试", "#FF0000");
    //设置模板位置
    TemplateContent keyword2 = new TemplateContent("测试推送消息", "#0000FF");
    //设置设备
    TemplateContent keyword3 = new TemplateContent("传感器设备", "#00FF7F");
    //设置时间
    TemplateContent keyword4 = new TemplateContent("2020-10-26 12:40:42", "#808080");
    //设置跳转内容
    TemplateContent remark = new TemplateContent("点此处查看详情", "#FFA500");
    //创建模板信息数据对象
    Data data = new Data();
    data.setFirst(first);
    data.setKeyword1(keyword1);
    data.setKeyword2(keyword2);
    data.setKeyword3(keyword3);
    data.setKeyword4(keyword4);
    data.setRemark(remark);
    templateMessage.setData(data);
    //小程序参数对象--非必传
    Miniprogram miniprogram = new Miniprogram();
    //appid必须跟微信公众号关联，wx7c4c3e4664f81fd3需要换
    miniprogram.setAppid("wx7c4c3e4664f81fd3");
    miniprogram.setPagepath("");
    templateMessage.setMiniprogram(miniprogram);
    String params = JSON.toJSONString(templateMessage);
    // 返回和封装结果集
    JSONObject jsonObject = wxChatService.sendTemplateMessage(params);
    int code = jsonObject.getInteger("errcode");
    String errmsg = jsonObject.getString("errmsg");
    Assert.assertEquals("0", String.valueOf(code));
  }
}
