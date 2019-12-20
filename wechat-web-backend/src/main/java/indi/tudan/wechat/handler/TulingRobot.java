package indi.tudan.wechat.handler;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

    //String url = "http://www.tuling123.com/openapi/api";
    String url = "http://openapi.tuling123.com/openapi/api/v2";

    // 这里是我申请的图灵机器人 API 接口，每天只能 5000 次调用，建议自己去申请一个，免费的:)
    String apiKey = "a032c2eae4264fa9bc23dae9956dcd71";

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new TulingRobot();
        Wechat wechat = new Wechat(msgHandler, "C:/Users/tudan/Desktop/wechat-web");
        wechat.start();
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {
        String result = "";
        String text = msg.getText();
        String param = "{\n" +
                "\t\"reqType\":0,\n" +
                "    \"perception\": {\n" +
                "        \"inputText\": {\n" +
                "            \"text\": \"" + text + "\"\n" +
                "        },\n" +
                "        \"inputImage\": {\n" +
                "            \"url\": \"imageUrl\"\n" +
                "        },\n" +
                "        \"selfInfo\": {\n" +
                "            \"location\": {\n" +
                "                \"city\": \"北京\",\n" +
                "                \"province\": \"北京\",\n" +
                "                \"street\": \"信息路\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"userInfo\": {\n" +
                "        \"apiKey\": \"a032c2eae4264fa9bc23dae9956dcd71\",\n" +
                "        \"userId\": \"538498\"\n" +
                "    }\n" +
                "}";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key", apiKey);
        paramMap.put("info", text);
        paramMap.put("userid", "123456");
        String paramStr = JSON.toJSONString(paramMap);
        try {
            HttpEntity entity = myHttpClient.doPost(url, param);
            result = EntityUtils.toString(entity, "UTF-8");
            JSONObject obj = JSON.parseObject(result);
            if (obj.getString("code").equals("100000")) {
                result = obj.getString("text");
            } else {
                result = "处理有误";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
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
