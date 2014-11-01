package im.dadoo.auth.web.configuration;

import im.dadoo.auth.biz.configuration.BizContext;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebContext.class, BizContext.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
  
  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter ceFilter = new CharacterEncodingFilter();
    ceFilter.setEncoding("UTF-8");
    ceFilter.setForceEncoding(true);

    HiddenHttpMethodFilter hhmFilter = new HiddenHttpMethodFilter();
    return new Filter[]{ceFilter, hhmFilter};
  }
}
