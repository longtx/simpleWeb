import { baseUrl } from './env'


var root = baseUrl;
var axios = require('axios')

function apiAxios(method, url, params, success, failure) {
    axios({
            method: method,
            url: url,
            data: method === 'POST' || method === 'PUT' ? params : null,
            params: method === 'GET' || method === 'DELETE' ? params : null,
            baseURL: root,
            withCredentials: true,
            credentials: 'include'

        })
        .then(function(res) {
            if (res.data.status == 500110 || res.data.status == '40101') {
                localStorage.removeItem("user");
            }
            success(res.data)
        })
        //fai
        .catch(function(err) {
            let res = err.response
            if (err) {
                //  global redirect
            }
        })
}
//blob
function apiAxiosblob(method, url, params, success, failure, responseType) {
    axios({
            method: method,
            url: url,
            data: method === 'POST' || method === 'PUT' ? params : null,
            params: method === 'GET' || method === 'DELETE' ? params : null,
            baseURL: root,
            // 允许跨域请求中携带cookie
            withCredentials: true,
            // 是解决跨域session丢失的问题
            credentials: 'include',
            responseType: 'blob'
        })
        //suc  //test
        .then(function(res) {
            //如果状态为40101，则全局进行定向到login
            if (res.data.status == 40101 || res.data.status == '40101') {
                location.href = "#/login"
            }
            success(res.data)
        })
        //fai
        .catch(function(err) {
            let res = err.response
            if (err) {
                //  global redirect
                window.alert('api error, HTTP CODE: ' + res.status + "错误！")
            }
        })
}



// 返回在vue模板中的调用接口
export default {
    get: function(url, params, success, failure) {
        return apiAxios('GET', url, params, success, failure)
    },
    post: function(url, params, success, failure) {
        return apiAxios('POST', url, params, success, failure)
    },
    postblob: function(url, params, success, failure, responseType) {
        return apiAxiosblob('POST', url, params, success, failure, responseType)
    },
    put: function(url, params, success, failure) {
        return apiAxios('PUT', url, params, success, failure)
    },
    delete: function(url, params, success, failure) {
        return apiAxios('DELETE', url, params, success, failure)
    }
}