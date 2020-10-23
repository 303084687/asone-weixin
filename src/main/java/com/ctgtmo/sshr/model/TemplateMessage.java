package com.ctgtmo.sshr.model;

/**  
 * @Title: TemplateMessage.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.ssg.model   
 * @Description: 消息模板实体
 * @author: 王共亮     
 * @date: 2020年10月22日 下午3:02:25   
 */
public class TemplateMessage {
  //用户openid
  private String touser;

  //模板消息ID
  private String template_id;

  //详情跳转页面--请注意，URL置空，则在发送后，点击模板消息无法点击
  private String url;

  //模板数据封装实体--参数对应模板中的key
  private Object data;

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getTemplate_id() {
    return template_id;
  }

  public void setTemplate_id(String template_id) {
    this.template_id = template_id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
