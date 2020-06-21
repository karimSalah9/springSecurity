package com.springsecurity.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;
	}
	
	

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		//	//point to the config calss for servlet created before
		return new Class[] { DemoAppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		//point to the servlet mapping
		return new String[] { "/" };
	}

}
