package com.klezovich.robot.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer 
  extends AbstractAnnotationConfigDispatcherServletInitializer {
   
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecurityConfig.class};
    }

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
}