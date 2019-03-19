package com.kb.treatment.configuration.WebMvcConfiguration;


import com.kb.treatment.configuration.LoginConfigration.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 *  1. 配置访问路径的转换；
 *  2. 配置拦截器链；
 * **/

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {


    /***
     *   配置Swagger-ui的访问路径
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        //这里也可以采用ResourceUtils.CLASSPATH_URL_PREFIX 它是：classpath:
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/WEB-INF/resources/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/WEB-INF/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/WEB-INF/resources/css/");
        super.addResourceHandlers(registry);
    }



    public void addInterceptors(InterceptorRegistry registry) {
        //设置一个或者多个拦截器
           registry.addInterceptor(new LoginInterceptor()).
                   addPathPatterns("/**").excludePathPatterns("/*.html","/*.js","/*.css","/image/**");//多个排除拦截的话，只需要用逗号隔开即可
        //这边还可以加好几个拦截器组成拦截器链



    }


}
