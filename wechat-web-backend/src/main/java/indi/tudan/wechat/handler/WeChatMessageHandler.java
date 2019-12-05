package indi.tudan.wechat.handler;

import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信消息处理
 *
 * @author wangtan
 * @date 2019-12-03 14:44:14
 * @since 1.0
 */
public class WeChatMessageHandler implements IMsgHandlerFace {

    @Override
    public String textMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public String picMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public String voiceMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public String viedoMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public String nameCardMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public void sysMsgHandle(BaseMsg baseMsg) {

    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg baseMsg) {
        return "";
    }

    @Override
    public String mediaMsgHandle(BaseMsg baseMsg) {
        return "";
    }
}
