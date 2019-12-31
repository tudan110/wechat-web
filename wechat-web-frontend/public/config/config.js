window.config={
    /**
     * @description api 请求基础路径
     */
    baseUrl: {
        dev: 'http://localhost:9082/wechat/',
        // pro: 'http://localhost:9082/wechat/'
        pro: window.location.protocol + '//' + window.location.host + '9082'
    },
    timeout:120000
};
