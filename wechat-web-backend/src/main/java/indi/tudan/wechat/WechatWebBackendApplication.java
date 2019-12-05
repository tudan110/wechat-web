package indi.tudan.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 程序入口
 *
 * @author wangtan
 * @date 2019-12-05 09:32:44
 * @since 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class WechatWebBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(WechatWebBackendApplication.class, args);

    }

}
