package com.ctgtmo.sshr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>ClassName: WxApplication.java</p>
 * <p>Company: 乾通互联(北京)科技有限公司</p>
 * <p>Description: 项目启动项</p>
 * <p>author wanggongliang</p>
 * <p>2018年12月4日 下午2:25:38</p>
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.ctgtmo.sshr" })
public class WxApplication {

  public static void main(String[] args) {
    SpringApplication.run(WxApplication.class, args);
  }
}
