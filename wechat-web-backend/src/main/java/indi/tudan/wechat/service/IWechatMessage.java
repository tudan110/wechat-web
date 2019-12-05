package indi.tudan.wechat.service;

/**
 * 微信消息服务接口
 *
 * @author wangtan
 * @date 2019-12-04 13:48:04
 * @since 1.0
 */
public interface IWechatMessage {

    /**
     * 根据用户名发送消息
     *
     * @param userName 用户名
     * @param msg      消息
     * @date 2019-12-04 13:48:40
     */
    void sendMsgByUserName(String userName, String msg);

    /**
     * 根据昵称发送消息
     *
     * @param nickName 昵称
     * @param msg      消息
     * @date 2019-12-04 13:49:25
     */
    void sendMsgByNickName(String nickName, String msg);

    /**
     * 根据备注发送消息
     *
     * @param remarkName 备注
     * @param msg        消息
     * @date 2019-12-04 13:49:59
     */
    void sendMsgByRemarkName(String remarkName, String msg);

    /**
     * 根据群聊名称发送消息
     *
     * @param groupName 群聊名称
     * @param msg       消息
     * @date 2019-12-04 14:39:22
     */
    void sendMsgByGroupName(String groupName, String msg);
}
