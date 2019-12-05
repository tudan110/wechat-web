package indi.tudan.wechat.service.impl;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import indi.tudan.wechat.service.IWechatMessage;
import org.springframework.stereotype.Service;

/**
 * 微信消息服务
 *
 * @author wangtan
 * @date 2019-12-04 13:50:27
 * @since 1.0
 */
@Service
public class WechatMessageService implements IWechatMessage {

    /**
     * 根据用户名发送消息
     *
     * @param userName 用户名
     * @param msg      消息
     * @date 2019-12-04 13:51:08
     */
    @Override
    public void sendMsgByUserName(String userName, String msg) {
        MessageTools.sendMsgByUserName(msg, userName);
    }

    /**
     * 根据昵称发送消息
     *
     * @param nickName 昵称
     * @param msg      消息
     * @date 2019-12-04 13:54:15
     */
    @Override
    public void sendMsgByNickName(String nickName, String msg) {
        MessageTools.sendMsgByNickName(msg, nickName);
    }

    /**
     * 根据备注发送消息
     *
     * @param remarkName 备注
     * @param msg        消息
     * @date 2019-12-04 13:54:27
     */
    @Override
    public void sendMsgByRemarkName(String remarkName, String msg) {
        MessageTools.sendMsgByRemarkName(msg, remarkName);
    }

    /**
     * 根据群聊名称发送消息
     *
     * @param groupName 群聊名称
     * @param msg       消息
     * @date 2019-12-04 14:39:35
     */
    @Override
    public void sendMsgByGroupName(String groupName, String msg) {
        MessageTools.sendMsgByGroupName(msg, groupName);
    }
}
