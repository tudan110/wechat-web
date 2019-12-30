package indi.tudan.wechat.config;

import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import indi.tudan.wechat.common.Const;
import indi.tudan.wechat.utils.SettingUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 图灵配置类
 *
 * @author wangtan
 * @date 2019-12-30 09:25:16
 * @since 1.0
 */
public class TulingConfig {

    /**
     * 获取配置信息
     */
    private static Setting setting = SettingUtils.getInstance().getSetting();

    /**
     * Don't let anyone else instantiate this class
     */
    private TulingConfig() {
    }

    /**
     * 获取启用命令
     *
     * @return 启用命令
     * @date 2019-12-30 09:32:06
     * @since 1.0
     */
    public static List<String> getEnableCommands() {
        return StrSpliter.split(setting.getByGroup("enable", "control"),
                ',', 0, true, true);
    }

    /**
     * 获取禁用命令
     *
     * @return 禁用命令
     * @date 2019-12-30 09:32:37
     * @since 1.0
     */
    public static List<String> getDisableCommands() {
        return StrSpliter.split(setting.getByGroup("disable", "control"),
                ',', 0, true, true);
    }

    /**
     * 获取图灵机器人 API 接口地址
     *
     * @return 接口地址
     * @date 2019-12-30 09:34:29
     * @since 1.0
     */
    public static String getTulingUrl() {
        return setting.getByGroup("url", "api");
    }

    /**
     * 获取图灵机器人 apiKey
     *
     * @return apiKey
     * @date 2019-12-30 09:35:26
     * @since 1.0
     */
    public static String getTulingApiKey() {
        return setting.getByGroup("key", "api");
    }

    /**
     * 获取图灵机器人用户名
     *
     * @return 用户名
     * @date 2019-12-30 09:36:05
     * @since 1.0
     */
    public static String getTulingUserId() {
        return setting.getByGroup("userId", "api");
    }

    /**
     * 获取好友白名单
     *
     * @return 好友白名单
     * @date 2019-12-30 09:36:57
     * @since 1.0
     */
    public static Set<String> getFriendsWhitelist() {
        return new HashSet<>(StrSpliter.split(setting.getByGroup("friends", "whitelist"),
                ',', 0, true, true));
    }

    /**
     * 获取群聊白名单
     *
     * @return 群聊白名单
     * @date 2019-12-30 09:37:33
     * @since 1.0
     */
    public static Set<String> getGroupsWhitelist() {
        return new HashSet<>(StrSpliter.split(setting.getByGroup("groups", "whitelist"),
                ',', 0, true, true));
    }

    /**
     * 添加或移除白名单
     *
     * @param msg 消息体
     * @date 2019-12-25 15:13:47
     * @since 1.0
     */
    public static String addOrRemoveWhiteList(BaseMsg msg) {

        // 回复消息
        String result = "";

        // 好友白名单
        Set<String> friendsWhitelist = getFriendsWhitelist();

        // 群聊白名单
        Set<String> groupsWhitelist = getGroupsWhitelist();

        // 匹配到启用指令，且当前用户不在白名单里
        if (getEnableCommands().contains(msg.getText().trim()) && (isNotInWhitelist(msg) || isAdmin(msg))) {

            // 添加当前用户到白名单

            // 人的备注
            String fromRemarkName = !isAdmin(msg)
                    ? WechatTools.getRemarkNameByUserName(msg.getFromUserName())
                    : WechatTools.getRemarkNameByUserName(msg.getToUserName());

            if (StrUtil.isNotBlank(fromRemarkName) && !Const.NULL.equalsIgnoreCase(fromRemarkName)) {
                friendsWhitelist.add(fromRemarkName);
                setting.set("whitelist", "friends", String.join(",", friendsWhitelist));
                setting.store(SettingUtils.settingFilePath);
            }

            // 群聊昵称
            String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkGroup) && !Const.NULL.equalsIgnoreCase(fromRemarkGroup)) {
                groupsWhitelist.add(fromRemarkGroup);
                setting.set("whitelist", "groups", String.join(",", groupsWhitelist));
                setting.store(SettingUtils.settingFilePath);
            }

            result = setting.getByGroup("welcome", "greetings");
        }

        // 匹配到停用指令，且当前用户不在白名单里
        if (getDisableCommands().contains(msg.getText().trim()) && (isInWhitelist(msg) || isAdmin(msg))) {

            // 从白名单移除当前用户

            // 人的备注
            String fromRemarkName = !isAdmin(msg)
                    ? WechatTools.getRemarkNameByUserName(msg.getFromUserName())
                    : WechatTools.getRemarkNameByUserName(msg.getToUserName());

            if (StrUtil.isNotBlank(fromRemarkName) && !Const.NULL.equalsIgnoreCase(fromRemarkName)) {
                friendsWhitelist.remove(fromRemarkName);
                setting.set("whitelist", "friends", String.join(",", friendsWhitelist));
                setting.store(SettingUtils.settingFilePath);
            }

            // 群聊昵称
            String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkGroup) && !Const.NULL.equalsIgnoreCase(fromRemarkGroup)) {
                groupsWhitelist.remove(fromRemarkGroup);
                setting.set("whitelist", "groups", String.join(",", groupsWhitelist));
                setting.store(SettingUtils.settingFilePath);
            }

            result = setting.getByGroup("bye", "greetings");
        }

        return result;
    }

    /**
     * 是否在白名单
     *
     * @param msg 消息体
     * @return 是否在白名单
     * @date 2019-12-25 10:52:52
     * @since 1.0
     */
    public static boolean isInWhitelist(BaseMsg msg) {

        // 人的备注
        String fromRemarkName = WechatTools.getRemarkNameByUserName(msg.getFromUserName());

        // 群聊昵称
        String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

        return getFriendsWhitelist().contains(fromRemarkName) || getGroupsWhitelist().contains(fromRemarkGroup);
    }

    /**
     * 是否不在白名单
     *
     * @param msg 消息体
     * @return 是否不在白名单
     * @date 2019-12-25 15:02:43
     * @since 1.0
     */
    public static boolean isNotInWhitelist(BaseMsg msg) {
        return !isInWhitelist(msg);
    }

    /**
     * 是否是管理员，在这是号主本人
     *
     * @param msg 消息体
     * @return boolean
     * @date 2019-12-30 10:37:21
     * @since 1.0
     */
    public static boolean isAdmin(BaseMsg msg) {
        return Core.getInstance().getUserName().equalsIgnoreCase(msg.getFromUserName());
    }

    /**
     * 获取管理员，号主本人的昵称
     *
     * @return 号主昵称
     * @date 2019-12-30 10:44:35
     * @since 1.0
     */
    public static String getAdminNickName() {
        return Core.getInstance().getNickName();
    }

    /**
     * 获取帮助信息
     *
     * @param msg 消息体
     * @return 帮助信息
     * @author wangtan
     * @date 2019-12-30 11:00:09
     */
    public static String getHelp(BaseMsg msg) {
        if ("help".equalsIgnoreCase(msg.getText())) {
            return String.join("\n", setting.getMap("help").values());
        }
        return "";
    }

}
