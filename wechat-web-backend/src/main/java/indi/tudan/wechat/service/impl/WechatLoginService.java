package indi.tudan.wechat.service.impl;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import indi.tudan.wechat.common.yaml.WechatYaml;
import indi.tudan.wechat.handler.WeChatMessageHandler;
import indi.tudan.wechat.service.IWechatLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信登录服务
 *
 * @author wangtan
 * @date 2019-12-04 10:53:18
 * @since 1.0
 */
@Slf4j
@Service
public class WechatLoginService implements IWechatLogin {

    @Autowired
    private WechatYaml wechatYaml;

    /**
     * 微信登录
     *
     * @date 2019-12-04 10:54:26
     */
    @Override
    public boolean login() {
        try {
            new Wechat(new WeChatMessageHandler(), wechatYaml.getLogin().getQrPath())
                    .start();
            return true;
        } catch (Exception e) {
            log.error("登录失败。", e);
            return false;
        }
    }

    /**
     * 微信退出接口
     *
     * @date 2019-12-04 13:35:22
     */
    @Override
    public boolean logout() {
        try {
            WechatTools.logout();
            return true;
        } catch (Exception e) {
            log.error("退出失败。", e);
            return false;
        }
    }

    /**
     * 微信是否退出
     *
     * @date 2019-12-04 15:56:37
     */
    @Override
    public boolean isAlive() {
        return WechatTools.getWechatStatus();
    }

}
