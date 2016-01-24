package com.vulab.code.config;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.vulab.code.springsecurityjwt.vendor.SpringSecurityConfig;

@Priority(value = 1)
public class SpringWebAppInitializer implements WebApplicationInitializer {	

		@Override
	    public void onStartup(ServletContext servletContext) throws ServletException {			
			
			servletContext.setInitParameter("contextConfigLocation", "NOTNULL");
	        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	        ctx.register(ApplicationContextConfig.class,SpringSecurityConfig.class,DispatcherConfig.class);
	        
	        ctx.setServletContext(servletContext);
	        servletContext.addListener(new ContextLoaderListener(ctx));	      
	        ServletRegistration.Dynamic jerseyRESTservlet = servletContext.addServlet("jersey-serlvet", new org.glassfish.jersey.servlet.ServletContainer());
	        jerseyRESTservlet.setInitParameter("jersey.config.server.provider.classnames", "com.vulab.code.restservices.CarRestService");
	        jerseyRESTservlet.addMapping("/rest/*");
	        jerseyRESTservlet.setLoadOnStartup(1);	 
	        
	       

	        // Register and map the dispatcher servlet
	        ServletRegistration.Dynamic dispatcher =
	        		servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
	          dispatcher.setLoadOnStartup(2);
	          dispatcher.addMapping("/web/*");
	        
	        
	        
	        
	        //WARNING: DO NOT REMOVE BELOW LINE
	        //THIS IS ABSOLUTELY REQUIRED AS JERSEY FOR SOME REASON WAS CREATING ANOTHER SPRING CONTEXT.
	        //WE ARE USING BELOW CONFIGURATION SO THAT JERSEY SPRING CONTEXT IS NOT SCANNING OUR PACKAGES.
	        servletContext.setInitParameter("contextConfigLocation", "");
	    }	

}