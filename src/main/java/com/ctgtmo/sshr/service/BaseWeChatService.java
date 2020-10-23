package com.ctgtmo.sshr.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctgtmo.sshr.config.WeChetAccessToken;

/**  
 * @Title: BaseWeChatServiceImpl.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.service   
 * @Description: 微信通用service层
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:04:31   
 */
public class BaseWeChatService {
  // 获取token
  @Autowired
  protected WeChetAccessToken weChetAccessToken;
}
