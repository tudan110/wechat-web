import axios from "axios";

const baseUrl = process.env.NODE_ENV === 'development' ? window.config.baseUrl.dev : window.config.baseUrl.pro;

const HttpRequest = axios.create({
    baseURL: baseUrl, // api的base_url
    timeout: window.config.timeout // 请求超时时间
});

export default HttpRequest;
