package com.WebService.DondeEstanApp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

 @SuppressWarnings({ "rawtypes", "unchecked" })
@Override
 protected Class[] getRootConfigClasses() {
  return new Class[]{ WebConfig.class };
 }

 @SuppressWarnings({ "rawtypes", "unchecked" })
@Override
 protected Class[] getServletConfigClasses() {
  return null;
 }

 @Override
 protected String[] getServletMappings() {
  return new String[]{ "/" };
 }

}
