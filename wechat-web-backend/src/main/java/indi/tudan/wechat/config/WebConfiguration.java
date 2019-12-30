package indi.tudan.wechat.config;

import indi.tudan.wechat.common.yaml.WechatYaml;
import indi.tudan.wechat.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JavaEE 注解配置
 *
 * @author wangtan
 * @date 2019-12-05 10:59:59
 * @since 1.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private WechatYaml wechatYaml;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 以二维码所在系统目录为资源目录
        registry.addResourceHandler("/image/**").addResourceLocations(FileUtils.getQrPath());
    }

    /**
     * CROS跨域的处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
