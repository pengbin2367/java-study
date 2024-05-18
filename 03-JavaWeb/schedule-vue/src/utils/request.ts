import axios from 'axios'
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosError, AxiosResponse } from 'axios'

const service: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080/',
    timeout: 30000
})

/* 请求拦截器 */
service.interceptors.request.use((config: InternalAxiosRequestConfig) => {
    //  伪代码
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`;
    // }
    return config
}, (error: AxiosError) => {
    return Promise.reject(error)
})

/* 响应拦截器 */
service.interceptors.response.use((response: AxiosResponse) => {
    // 根据自定义错误码判断请求是否成功
    // 将组件用的数据返回
    console.log(response)
    return response
}, (error: AxiosError) => {
    // 处理 HTTP 网络错误
    let message = ''
    // HTTP 状态码
    const status = error.response?.status
    switch (status) {
        case 401:
            message = 'token 失效，请重新登录'
            // 这里可以触发退出的 action
            break;
        case 403:
            message = '拒绝访问'
            break;
        case 404:
            message = '请求地址错误'
            break;
        case 500:
            message = '服务器故障'
            break;
        default:
            message = '网络连接故障'
    }
    return Promise.reject(error)
})

/* 导出封装的请求方法 */
export const http = {
    get<T=any>(url: string, config?: InternalAxiosRequestConfig) : Promise<T> {
        return service.get(url, config)
    },

    post<T=any>(url: string, data?: object, config?: InternalAxiosRequestConfig) :Promise<T> {
        return service.post(url, data, config)
    },

    put<T=any>(url: string, data?: object, config?: InternalAxiosRequestConfig) :Promise<T> {
        return service.put(url, data, config)
    },

    delete<T=any>(url: string, config?: InternalAxiosRequestConfig) : Promise<T> {
        return service.delete(url, config)
    }
}