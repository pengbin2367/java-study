package com.atqingke.ssmintegration;

import com.atqingke.ssmintegration.config.DataSourceJavaConfig;
import com.atqingke.ssmintegration.config.MapperJavaConfig;
import com.atqingke.ssmintegration.config.ServiceJavaConfig;
import com.atqingke.ssmintegration.config.WebJavaConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //指定root容器对应的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {MapperJavaConfig.class, ServiceJavaConfig.class, DataSourceJavaConfig.class };
    }

    //指定web容器对应的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebJavaConfig.class };
    }

    //指定dispatcherServlet处理路径，通常为 /
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
