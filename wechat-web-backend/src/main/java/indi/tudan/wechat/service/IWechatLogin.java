package indi.tudan.wechat.service;

/**
 * 微信登录服务接口
 *
 * @author wangtan
 * @date 2019-12-04 10:52:20
 * @since 1.0
 */
public interface IWechatLogin {

    /**
     * 获取 QR 码
     *
     * @date 2019-12-05 14:08:11
     */
    String getQR();

    /**
     * 删除 QR 码
     *
     * @date 2019-12-05 14:11:58
     */
    void deleteQR();

    /**
     * 微信登录接口
     *
     * @date 2019-12-04 10:54:09
     */
    boolean login();

    /**
     * 微信退出接口
     *
     * @date 2019-12-04 13:34:55
     */
    boolean logout();

    /**
     * 微信是否退出
     *
     * @date 2019-12-04 15:55:32
     */
    boolean isAlive();

}
