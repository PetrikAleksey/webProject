package spring.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {DataConfig.class};//,SecurityConfig.class};
		//return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {WebConfig.class};
		//return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//		encodingFilter.setInitParameter("encoding", "UTF-8");
//		encodingFilter.setInitParameter("forceEncoding", "true");
//		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//
//	}

	//	@Override
//	protected Filter[] getServletFilters() {
//
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//
//		return new Filter[] { characterEncodingFilter, new DelegatingFilterProxy("springSecurityFilterChain")};
//	}

//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		return new Filter[] { characterEncodingFilter };
//	}


//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//		encodingFilter.setForceEncoding(true);
//		encodingFilter.setEncoding("UTF-8");
//		return new Filter[]{encodingFilter,
//				new DelegatingFilterProxy("springSecurityFilterChain"),
//				new OpenEntityManagerInViewFilter()};
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setForceEncoding(true);
//		characterEncodingFilter.setEncoding("UTF-8");
//		return new Filter[] { characterEncodingFilter};
//	}
//
//@Override
//protected Filter[] getServletFilters() {
//	CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//	encodingFilter.setEncoding("UTF-8");
//	encodingFilter.setForceEncoding(true);
//
//	return new Filter[] { encodingFilter};
//}

//	@Override
//	protected Filter[] getServletFilters(){
//		// создание фильтра кодировки, который позволит работать с русскими символами
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//
//		// создание фильтра, который добавляет поддержку  HTTP методов(например,таких как PUT)
//		HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
//		return new Filter[]{characterEncodingFilter, httpMethodFilter};
//	}

}
