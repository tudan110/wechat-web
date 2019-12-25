package indi.tudan.wechat.handler;

import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.tudan.wechat.common.Const;
import indi.tudan.wechat.utils.SettingUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 图灵机器人
 *
 * @author wangtan
 * @date 2019-12-20 17:15:44
 * @since 1.0
 */
@Slf4j
public class TulingRobot implements IMsgHandlerFace {

    /**
     * 获取配置信息
     */
    Setting setting = SettingUtils.getInstance().getSetting();

    /**
     * 启用
     */
    List<String> controlEnable = StrSpliter.split(setting.getByGroup("enable", "control"),
            ',', 0, true, true);

    /**
     * 停用
     */
    List<String> controlDisable = StrSpliter.split(setting.getByGroup("disable", "control"),
            ',', 0, true, true);

    /**
     * 这里是我申请的图灵机器人 API 接口，每天只能 100 次调用
     */
    String url = setting.getByGroup("url", "api");

    /**
     * apiKey
     */
    String apiKey = setting.getByGroup("key", "api");

    /**
     * 用户名
     */
    String userId = setting.getByGroup("userId", "api");

    /**
     * 好友白名单
     */
    List<String> friendsWhitelist = StrSpliter.split(setting.getByGroup("friends", "whitelist"),
            ',', 0, true, true);

    /**
     * 群聊白名单
     */
    List<String> groupsWhitelist = StrSpliter.split(setting.getByGroup("groups", "whitelist"),
            ',', 0, true, true);

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new TulingRobot();
        Wechat wechat = new Wechat(msgHandler, "C:/Users/tudan/Desktop/wechat-web");
        wechat.start();
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {

        // 添加或移除白名单
        addOrRemoveWhiteList(msg);

        // 不在白名单里，就返回空
        if (isNotInWhitelist(msg)) {
            return "";
        }

        // 返回消息内容
        String result = "";

        /* start: 封装 api 参数 */
        JSONObject param = new JSONObject();
        param.put("reqType", 0);
        param.put("perception",
                new JSONObject().fluentPut("inputText",
                        new JSONObject().fluentPut("text", msg.getText())));
        param.put("userInfo",
                new JSONObject()
                        .fluentPut("apiKey", apiKey)
                        .fluentPut("userId", userId));
        /* end: 封装 api 参数 */

        try {

            // 请求图灵机器人接口
            JSONObject response = JSON.parseObject(HttpUtil.post(url, JSON.toJSONString(param)));

            JSONArray results = response.getJSONArray("results");
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                stringList.add(results.getJSONObject(i).getJSONObject("values").getString("text"));
            }
            result = String.join("，", stringList);

        } catch (Exception e) {
            log.error("服务器解析错误，请联系管理员。", e);
            result = StrUtil.format("{}。\nstack info: {}",
                    "服务器解析错误，请联系土旦",
                    e.getMessage());
        }
        return result;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {

        // 不在白名单里，就返回空
        if (isNotInWhitelist(msg)) {
            return "";
        }

        return "收到图片";
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {

        // 不在白名单里，就返回空
        if (isNotInWhitelist(msg)) {
            return "";
        }

        //String fileName = String.valueOf(System.currentTimeMillis());
        //String voicePath = "D://itchat4j/voice" + File.separator + fileName + ".mp3";
        //DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
        return "收到语音";
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {

        // 不在白名单里，就返回空
        if (isNotInWhitelist(msg)) {
            return "";
        }

        //String fileName = String.valueOf(System.currentTimeMillis());
        //String viedoPath = "D://itchat4j/viedo" + File.separator + fileName + ".mp4";
        //DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
        return "收到视频";
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 添加或移除白名单
     *
     * @param msg 消息体
     * @date 2019-12-25 15:13:47
     * @since 1.0
     */
    private void addOrRemoveWhiteList(BaseMsg msg) {

        // 匹配到启用指令，且当前用户不在白名单里
        if (controlEnable.contains(msg.getText()) && isNotInWhitelist(msg)) {

            // 添加当前用户到白名单

            // 人的备注
            String fromRemarkName = WechatTools.getRemarkNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkName) && !Const.NULL.equalsIgnoreCase(fromRemarkName)) {
                friendsWhitelist.add(fromRemarkName);
                setting.set("whitelist", "friends", String.join(",", friendsWhitelist));
            }

            // 群聊昵称
            String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkGroup) && !Const.NULL.equalsIgnoreCase(fromRemarkGroup)) {
                groupsWhitelist.add(fromRemarkGroup);
                setting.set("whitelist", "groups", String.join(",", groupsWhitelist));
            }
        }

        // 匹配到停用指令，且当前用户不在白名单里
        if (controlEnable.contains(msg.getText()) && isInWhitelist(msg)) {

            // 从白名单移除当前用户

            // 人的备注
            String fromRemarkName = WechatTools.getRemarkNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkName) && !Const.NULL.equalsIgnoreCase(fromRemarkName)) {
                friendsWhitelist.remove(fromRemarkName);
                setting.set("whitelist", "friends", String.join(",", friendsWhitelist));
            }

            // 群聊昵称
            String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

            if (StrUtil.isNotBlank(fromRemarkGroup) && !Const.NULL.equalsIgnoreCase(fromRemarkGroup)) {
                groupsWhitelist.remove(fromRemarkGroup);
                setting.set("whitelist", "groups", String.join(",", groupsWhitelist));
            }
        }
    }

    /**
     * 是否在白名单
     *
     * @param msg 消息体
     * @return 是否在白名单
     * @date 2019-12-25 10:52:52
     * @since 1.0
     */
    private boolean isInWhitelist(BaseMsg msg) {

        // 人的备注
        String fromRemarkName = WechatTools.getRemarkNameByUserName(msg.getFromUserName());

        // 群聊昵称
        String fromRemarkGroup = WechatTools.getGroupNickNameByUserName(msg.getFromUserName());

        return friendsWhitelist.contains(fromRemarkName) || groupsWhitelist.contains(fromRemarkGroup);
    }

    /**
     * 是否不在白名单
     *
     * @param msg 消息体
     * @return 是否不在白名单
     * @date 2019-12-25 15:02:43
     * @since 1.0
     */
    private boolean isNotInWhitelist(BaseMsg msg) {
        return !isInWhitelist(msg);
    }

}
