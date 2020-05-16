package cn.chentyit.fileserversystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author ChenYue
 */
@Configuration
public class CustomWebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            // 重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry corsRegistry) {
                // 添加映射路径
                corsRegistry.addMapping("/**")
                        // 放行哪些原始域
                        .allowedOrigins("*")
                        // 是否发送Cookie信息
                        .allowCredentials(true)
                        // 放行哪些原始域(请求方式)
                        .allowedMethods("*")
                        // 放行哪些原始域(头部信息)
                        .allowedHeaders("*")
                        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                        .allowedHeaders("*")
                        // 表明在 3600 秒内，不需要再发送预检验请求，可以缓存该结果
                        .maxAge(3600L);
            }
        };
    }
}
