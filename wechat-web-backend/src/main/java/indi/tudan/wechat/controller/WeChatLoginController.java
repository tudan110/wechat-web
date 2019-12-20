package indi.tudan.wechat.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.zhouyafeng.itchat4j.core.Core;
import com.alibaba.fastjson.JSONObject;
import indi.tudan.wechat.common.Const;
import indi.tudan.wechat.service.IWechatLogin;
import indi.tudan.wechat.utils.StringUtils;
import indi.tudan.wechat.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信登录控制器
 *
 * @author wangtan
 * @date 2019-12-04 09:54:56
 * @since 1.0
 */
@Slf4j
@RestController
public class WeChatLoginController {

    @Autowired
    private IWechatLogin iWechatLogin;

    /**
     * 获取 QR 码
     *
     * @return 返回数据
     * @date 2019-12-05 14:07:38
     */
    @GetMapping("getQR")
    public JSONObject getQR() {
        String qrData = iWechatLogin.getQR();
        boolean showQR = StrUtil.isNotBlank(qrData);
        return WebUtils.result(
                new JSONObject()
                        .fluentPut("showQR", showQR)
                        .fluentPut("qrData", qrData)
                        .fluentPut("isAlive", iWechatLogin.isAlive()),
                Const.HttpStatus.OK.getStatus(),
                showQR ? "获取成功。" : "获取失败。");
    }

    /**
     * 登录微信
     *
     * @return 返回信息
     * @date 2019-12-04 09:58:06
     */
    @GetMapping("login")
    public JSONObject login() {

        // 登录
        boolean success = iWechatLogin.login();

        // 无论登录成功失败，都要删除临时登录 QR 码
        iWechatLogin.deleteQR();

        return WebUtils.result(
                new JSONObject().fluentPut("isAlive", success),
                Const.HttpStatus.OK.getStatus(),
                success ? "登录成功。" : "登录失败。");
    }

    /**
     * 退出微信，调用此接口后，1 - 2 分钟，微信状态才会变更
     *
     * @return 返回信息
     * @date 2019-12-04 15:57:21
     */
    @GetMapping("logout")
    public JSONObject logout() {

        // 退出
        boolean success = iWechatLogin.logout();

        // 删除 QR 码
        iWechatLogin.deleteQR();

        return WebUtils.result(Const.HttpStatus.OK.getStatus(),
                success ? "退出成功，1 - 2 分钟后，微信状态才会变更，请耐心等待。" : "退出失败。");
    }

    /**
     * 微信是否退出
     *
     * @return 返回信息
     * @date 2019-12-04 15:57:41
     */
    @GetMapping("isAlive")
    public JSONObject isAlive() {
        return WebUtils.result(Const.HttpStatus.OK.getStatus(),
                StringUtils.getStr(iWechatLogin.isAlive()));
    }

    /**
     * 获取微信好友列表
     * http://127.0.0.1:8080/wechat/core/contact
     *
     * @return 返回信息
     * @date 2019-12-04 16:50:51
     */
    @GetMapping("core/contact")
    public JSONObject getContactList() {
        if (iWechatLogin.isAlive()) {
            return WebUtils.result(Core.getInstance().getContactList(),
                    Const.HttpStatus.OK.getStatus(),
                    "打印成功。");
        } else {
            return WebUtils.result(Const.HttpStatus.OK.getStatus(), "微信未登录。");
        }
    }

    /**
     * 获取微信添加到通讯录里的群聊
     * http://127.0.0.1:8080/wechat/core/group
     *
     * @return 返回信息
     * @date 2019-12-04 16:52:32
     */
    @GetMapping("core/group")
    public JSONObject getGroupList() {
        if (iWechatLogin.isAlive()) {
            return WebUtils.result(Core.getInstance().getGroupList(),
                    Const.HttpStatus.OK.getStatus(),
                    "打印成功。");
        } else {
            return WebUtils.result(Const.HttpStatus.OK.getStatus(), "微信未登录。");
        }
    }

    /**
     * 打印个人信息，包含好友、公众号等等
     * http://127.0.0.1:8080/wechat/core/console
     *
     * @return 返回信息
     * @date 2019-12-03 15:19:10
     */
    @GetMapping("core/console")
    public JSONObject consoleCore() {

        String info;
        if (iWechatLogin.isAlive()) {
            Core core = Core.getInstance();
            core.getContactList().forEach(p -> {
                Console.log("备注: {}\t\t\t\t昵称: {}\t\t\t\t用户名: {}",
                        p.getString("RemarkName"),
                        p.getString("NickName"),
                        p.getString("UserName"));
            });
            core.getGroupList().forEach(p -> {
                Console.log("备注: {}\t\t\t\t昵称: {}\t\t\t\t用户名: {}",
                        p.getString("RemarkName"),
                        p.getString("NickName"),
                        p.getString("UserName"));
            });
            info = "打印成功。";
        } else {
            info = "微信未登录。";
        }

        return WebUtils.result(Const.HttpStatus.OK.getStatus(), info);
    }

}
