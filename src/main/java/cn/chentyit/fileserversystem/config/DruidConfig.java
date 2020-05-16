package cn.chentyit.fileserversystem.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ChenYue
 * @Description:
 */
@Configuration
public class DruidConfig {

    /**
     * 配置一个 druid 监控
     * 1. 配置一个 druid 的后台，用于管理 servlet
     * 2. 配置一个 druid 的 filter
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //注意：请求是 /druid/*
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //设置初始化参数值
        Map<String, String> initParam = new HashMap<>(16);

        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "123");
        //如果不写，则默认所有ip都可以访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "");

        bean.setInitParameters(initParam);

        return bean;
    }

    /**
     * 2. 配置一个 druid 的 filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        // 设置拦截请求
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }
}
