package indi.tudan.wechat.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import indi.tudan.wechat.common.yaml.WechatYaml;
import indi.tudan.wechat.handler.WeChatMessageHandler;
import indi.tudan.wechat.service.IWechatLogin;
import indi.tudan.wechat.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

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
     * 获取 QR 码
     *
     * @date 2019-12-05 14:08:34
     */
    @Override
    public String getQR() {
        //String qrPath = wechatYaml.getLogin().getQrPath() + "/QR.jpg";
        String qrPath = FileUtils.getQrPath() + "/QR.jpg";
        if (FileUtil.exist(qrPath)) {
            return "data:image/jpeg;base64," + Base64.encode(new File(qrPath));
        }
        return "";
    }

    /**
     * 删除 QR 码
     *
     * @date 2019-12-05 14:12:10
     */
    @Override
    public void deleteQR() {
        //FileUtil.del(wechatYaml.getLogin().getQrPath() + "/QR.jpg");
        FileUtil.del(FileUtils.getQrPath() + "/QR.jpg");
    }

    /**
     * 微信登录
     *
     * @date 2019-12-04 10:54:26
     */
    @Override
    public boolean login() {
        try {
            //new Wechat(new WeChatMessageHandler(), wechatYaml.getLogin().getQrPath()).start();
            new Wechat(new WeChatMessageHandler(), FileUtils.getQrPath()).start();
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
