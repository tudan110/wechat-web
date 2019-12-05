package indi.tudan.wechat.common.yaml;

import indi.tudan.wechat.spring.core.io.support.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * yaml 配置映射类
 *
 * @author wangtan
 * @date 2019-11-28 17:26:39
 * @since 1.0
 */
@Data
@Component
@PropertySource(
        name = "test.yml",
        value = {"classpath:config/wechat.yml"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        factory = YamlPropertySourceFactory.class
)
@ConfigurationProperties(prefix = "wechat")
public class WechatYaml {

    private LoginProp login = new LoginProp();

    /**
     * 登录配置项
     *
     * @author wangtan
     * @date 2019-12-04 10:48:08
     * @since 1.0
     */
    @Data
    public static class LoginProp {

        private String qrPath;

    }

}
