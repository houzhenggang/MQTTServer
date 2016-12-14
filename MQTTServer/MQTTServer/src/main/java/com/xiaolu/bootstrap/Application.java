package com.xiaolu.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 *<p>Title:NettyService</p>
 *<p>Description: 项目启动类</p>
 * @author xiaolu
 * @date 2016年12月13日 下午4:53:17
 */
@SpringBootApplication 
//@SpringBootApplication可以代替@Configuration、@EnableAutoConfiguration、@ComponentScan
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setWebEnvironment(false);
    	app.run(args);
    }
   
}
