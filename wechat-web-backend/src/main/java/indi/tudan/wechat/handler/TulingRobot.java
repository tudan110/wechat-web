package indi.tudan.wechat.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.tudan.wechat.config.TulingConfig;
import indi.tudan.wechat.utils.FileUtils;
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

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new TulingRobot();
        Wechat wechat = new Wechat(msgHandler, FileUtils.getQrPath());
        wechat.start();
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {

        // 获取帮助信息
        String help = TulingConfig.getHelp(msg);
        if (StrUtil.isNotBlank(help)) {
            return help;
        }

        // 添加或移除白名单，并返回问候语
        String greetings = TulingConfig.addOrRemoveWhiteList(msg);
        if (StrUtil.isNotBlank(greetings)) {
            return greetings;
        }

        // 不在白名单里，就返回空
        if (TulingConfig.isNotInWhitelist(msg)) {
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
                        .fluentPut("apiKey", TulingConfig.getTulingApiKey())
                        .fluentPut("userId", TulingConfig.getTulingUserId()));
        /* end: 封装 api 参数 */

        try {

            // 请求图灵机器人接口
            JSONObject response = JSON.parseObject(HttpUtil.post(TulingConfig.getTulingUrl(), JSON.toJSONString(param)));

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
        if (TulingConfig.isNotInWhitelist(msg)) {
            return "";
        }

        return "收到图片";
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {

        // 不在白名单里，就返回空
        if (TulingConfig.isNotInWhitelist(msg)) {
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
        if (TulingConfig.isNotInWhitelist(msg)) {
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

}
