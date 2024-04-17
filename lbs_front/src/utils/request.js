import axios from 'axios'

const request = axios.create({
    // baseURL: 'http://10.132.95.113:8080',
    baseURL: 'http://192.168.31.173:8080',  //yt连
    timeout: 5000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    return config
}, error => {
    console.log('request error: ' + error)
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        return res
    }, error => {
        console.log('response error: ' + error)
        return Promise.reject(error)
    }
)


export default request

