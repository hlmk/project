package com.cht.servletFilterListenter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cht.servletFilterListenter.util.filter.CustomFileter;
import com.cht.servletFilterListenter.util.listerner.CustomListener;
import com.cht.servletFilterListenter.util.servlet.CustomServlet;

@SpringBootApplication
@ServletComponentScan
//public class Demo62ServletFilterListernerApplication{
public class Demo62ServletFilterListernerApplication{
//public class Demo62ServletFilterListernerApplication implements ServletContextInitializer{
	
/*	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new CustomServlet(),"/roncoo");
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
//		return new FilterRegistrationBean(new CustomFileter(),servletRegistrationBean()); 	//只拦截servletRegistrationBean
		return new FilterRegistrationBean(new CustomFileter());						//拦截全部
	}
	
	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean(new 	CustomListener());
	}	*/
	


	public static void main(String[] args) {
		SpringApplication.run(Demo62ServletFilterListernerApplication.class, args);
	}

	
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addServlet("customServlet", new CustomServlet()).addMapping("/roncoo");//此处路径少了“/”，启动会报错
		servletContext.addFilter("customFileter", new CustomFileter()).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true,"customFileter");//拦截指定bean
//		servletContext.addFilter("customFileter", new CustomFileter()).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true);//拦截所有
		servletContext.addListener(new CustomListener());
	}*/

	
}
