package indi.tudan.wechat.common;

/**
 * 常量及枚举
 *
 * @author wangtan
 * @date 2019-12-04 10:10:59
 * @since 1.0
 */
public class Const {

    /**
     * 发布版本字符串
     */
    public static final String RELEASE_MODE = "release";

    /**
     * null 字符串
     */
    public static final String NULL = "null";

    /**
     * Http 状态
     *
     * @date 2019-12-04 10:11:05
     */
    public enum HttpStatus {

        // Http 状态

        // 1**	信息，服务器收到请求，需要请求者继续执行操作
        CONTINUE(100),
        SWITCHING_PROTOCOLS(101),

        // 2**	成功，操作被成功接收并处理
        OK(200),
        CREATED(201),
        ACCEPTED(202),
        NON_AUTHORITATIVE_INFORMATION(203),
        NO_CONTENT(204),
        RESET_CONTENT(205),
        PARTIAL_CONTENT(206),

        // 3**	重定向，需要进一步的操作以完成请求
        MULTIPLE_CHOICES(300),
        MOVED_PERMANENTLY(301),
        FOUND(302),
        SEE_OTHER(303),
        NOT_MODIFIED(304),
        USE_PROXY(305),
        UNUSED(306),
        TEMPORARY_REDIRECT(307),


        // 4**	客户端错误，请求包含语法错误或无法完成请求
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        PAYMENT_REQUIRED(402),
        FORBIDDEN(403),
        NOT_FOUND(404),
        METHOD_NOT_ALLOWED(405),
        NOT_ACCEPTABLE(406),
        PROXY_AUTHENTICATION_REQUIRED(407),
        REQUEST_TIME_OUT(408),
        CONFLICT(409),
        GONE(410),
        LENGTH_REQUIRED(411),
        PRECONDITION_FAILED(412),
        REQUEST_ENTITY_TOO_LARGE(413),
        REQUEST_URI_TOO_LARGE(414),
        UNSUPPORTED_MEDIA_TYPE(415),
        REQUESTED_RANGE_NOT_SATISFIABLE(416),
        EXPECTATION_FAILED(417),

        // 5**	服务器错误，服务器在处理请求的过程中发生了错误
        INTERNAL_SERVER_ERROR(500),
        NOT_IMPLEMENTED(501),
        BAD_GATEWAY(502),
        SERVICE_UNAVAILABLE(503),
        GATEWAY_TIME_OUT(504),
        HTTP_VERSION_NOT_SUPPORTED(505);

        private int status;

        HttpStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }
    }
}
