package indi.tudan.wechat.handler;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.File;
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

    MyHttpClient myHttpClient = Core.getInstance().getMyHttpClient();

    String url = "http://openapi.tuling123.com/openapi/api/v2";

    // 这里是我申请的图灵机器人 API 接口，每天只能 100 次调用
    String apiKey = "a032c2eae4264fa9bc23dae9956dcd71";

    // 用户名
    String userId = "538498";

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new TulingRobot();
        Wechat wechat = new Wechat(msgHandler, "C:/Users/tudan/Desktop/wechat-web");
        wechat.start();
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {
        String fromUserName = msg.getFromUserName();
        String fromRemarkName = WechatTools.getRemarkNameByUserName(fromUserName);
        if (!"小蓝鲸".equals(fromRemarkName) && !"李进哥".equals(fromRemarkName)) {
            return "";
        }
        String result = "";
        String text = msg.getText();
        JSONObject param = new JSONObject();
        param.put("reqType", 0);
        param.put("perception",
                new JSONObject().fluentPut("inputText",
                        new JSONObject().fluentPut("text", text)));
        param.put("userInfo",
                new JSONObject()
                        .fluentPut("apiKey", apiKey)
                        .fluentPut("userId", userId));

        try {
            HttpEntity entity = myHttpClient.doPost(url, JSON.toJSONString(param));
            result = EntityUtils.toString(entity, "UTF-8");
            JSONObject obj = JSON.parseObject(result);
            JSONArray jsonArray = obj.getJSONArray("results");
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                stringList.add(jsonArray.getJSONObject(i).getJSONObject("values").getString("text"));
            }
            result = String.join("，", stringList);
        } catch (Exception e) {
            log.error("服务器解析错误。", e);
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {
        return "收到图片";
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        String fileName = String.valueOf(System.currentTimeMillis());
        String voicePath = "D://itchat4j/voice" + File.separator + fileName + ".mp3";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
        return "收到语音";
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        String fileName = String.valueOf(System.currentTimeMillis());
        String viedoPath = "D://itchat4j/viedo" + File.separator + fileName + ".mp4";
        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
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
