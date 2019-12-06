package indi.tudan.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import indi.tudan.wechat.common.Const;
import indi.tudan.wechat.service.IWechatMessage;
import indi.tudan.wechat.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息控制器
 *
 * @author wangtan
 * @date 2019-12-03 14:48:55
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("msg")
public class WeChatMessageController {

    @Autowired
    private IWechatMessage iWechatMessage;

    /**
     * 根据用户名发送消息
     * http://127.0.0.1:8080/wechat/msg/sendByUserName?userName={}&msg=测试，能收到消息嘛
     *
     * @param userName 用户名
     * @param msg      消息内容
     * @return 返回信息
     * @date 2019-12-04 14:03:04
     */
    @GetMapping("sendByUserName")
    public JSONObject sendMsgByUserName(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "msg") String msg) {
        iWechatMessage.sendMsgByUserName(userName, msg);
        return WebUtils.result(Const.HttpStatus.OK.getStatus(), msg);
    }

    /**
     * 根据昵称发送消息
     * http://127.0.0.1:8080/wechat/msg/sendByNickName?nickName={}&msg=测试，能收到消息嘛
     *
     * @param nickName 昵称
     * @param msg      消息内容
     * @return 返回信息
     * @date 2019-12-03 16:20:35
     */
    @GetMapping("sendByNickName")
    public JSONObject sendMsgByNickName(@RequestParam(value = "nickName") String nickName,
                                        @RequestParam(value = "msg") String msg) {
        iWechatMessage.sendMsgByNickName(nickName, msg);
        return WebUtils.result(Const.HttpStatus.OK.getStatus(), msg);
    }

    /**
     * 根据备注发送消息
     * http://127.0.0.1:8080/wechat/msg/sendByRemarkName?remarkName={}&msg=测试，能收到消息嘛
     *
     * @param remarkName 备注
     * @param msg        消息内容
     * @return 返回信息
     * @date 2019-12-04 14:03:07
     */
    @GetMapping("sendByRemarkName")
    public JSONObject sendMsgByRemarkName(@RequestParam(value = "remarkName") String remarkName,
                                          @RequestParam(value = "msg") String msg) {
        iWechatMessage.sendMsgByRemarkName(remarkName, msg);
        return WebUtils.result(Const.HttpStatus.OK.getStatus(), msg);
    }

    /**
     * 根据群聊名称发送消息
     * http://127.0.0.1:8080/wechat/msg/sendByGroupName?groupName={}&msg=测试，能收到消息嘛
     *
     * @param groupName 群聊名称
     * @param msg       消息内容
     * @return 返回信息
     * @date 2019-12-04 14:41:03
     */
    @GetMapping("sendByGroupName")
    public JSONObject sendMsgByGroupName(@RequestParam(value = "groupName") String groupName,
                                         @RequestParam(value = "msg") String msg) {
        iWechatMessage.sendMsgByGroupName(groupName, msg);
        return WebUtils.result(Const.HttpStatus.OK.getStatus(), msg);
    }

}
