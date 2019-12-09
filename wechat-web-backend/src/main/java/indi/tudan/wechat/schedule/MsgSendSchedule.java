package indi.tudan.wechat.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import indi.tudan.wechat.service.IWechatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 扫描预警结果表定时器
 *
 * @author wangtan
 * @date 2019-12-09 10:57:05
 * @since 1.0
 */
@Slf4j
@Component
public class MsgSendSchedule {

    /**
     * 接收人
     */
    @Value("${schedule.msg.receiver}")
    private String receiver;

    /**
     * 定时消息内容
     */
    @Value("${schedule.msg.text}")
    private String msgText;

    @Autowired
    private IWechatMessage iWechatMessage;

    //@Scheduled(
    //        initialDelayString = "${schedule.initialDelayString.send-msg}",
    //        fixedRateString = "${schedule.fixedRateString.test}"
    //)
    public void test() {

        // 容器启动后，延迟 10 秒后执行一次定时器，以后每隔 5 秒执行一次
        log.info("定时器测试。");
    }

    @Scheduled(
            initialDelayString = "${schedule.initialDelayString.send-msg}",
            fixedDelayString = "${schedule.fixedRateString.send-msg}"
    )
    public void sendMsg() {

        // 每 20 分钟执行一次
        if (WechatTools.getWechatStatus()) {
            iWechatMessage.sendMsgByUserName(receiver, StrUtil.format("{}: {}", DateUtil.now(), msgText));
        }
    }
}
