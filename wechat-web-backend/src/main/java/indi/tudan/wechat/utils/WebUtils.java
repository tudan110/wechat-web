package indi.tudan.wechat.utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Web 工具类
 *
 * @author wangtan
 * @date 2019-12-04 10:01:56
 * @since 1.0
 */
public class WebUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private WebUtils() {
    }

    /**
     * 封装返回结果
     *
     * @param data   数据
     * @param status 状态
     * @param info   信息
     * @return 返回结果 json 对象
     * @date 2019-12-04 10:05:29
     */
    public static JSONObject result(Object data, int status, String info) {
        if (ObjectUtil.isNotEmpty(data)) {
            return new JSONObject()
                    .fluentPut("data", data)
                    .fluentPut("status", status)
                    .fluentPut("info", info);
        }
        return result(status, info);
    }

    /**
     * 封装返回结果
     *
     * @param status 状态
     * @param info   信息
     * @return 返回结果 json 对象
     * @date 2019-12-04 10:08:14
     */
    public static JSONObject result(int status, String info) {
        return new JSONObject()
                .fluentPut("status", status)
                .fluentPut("info", info);
    }

}
