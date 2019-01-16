// 接口公共配置
import axios from 'axios'
import qs from 'qs'
import {getUrlParam} from '../common/js/utils'

let BASE_URL = 'http://sso.bashiju.com/'

const isProduction = Object.is(process.env.NODE_ENV, 'production')

if (isProduction) {
  BASE_URL = 'http://sso.bashiju.com/'
} else {
  BASE_URL = '/api/'
}

const request = axios.create({
  timeout: 5000, // 请求超时时间
  headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
})

// 添加请求拦截器
request.interceptors.request.use(
  config => {
    const menuId = getUrlParam('menu_id')
    if (menuId) {
      if (config.method === 'get') {
        config.params['menu_id'] = menuId
      } else if (config.method === 'post') {
        config.url = config.url + '?menu_id=' + menuId
      }
    }

    return config
  },
  err => {
    return Promise.reject(err)
  }
)

const onPost = (url, params) => {
  let _params = params || {}
  return new Promise((resolve, reject) => {
    request.post(BASE_URL + url, qs.stringify(_params)).then((response) => {
      resolve(response.data)
    }).catch((error) => {
      console.log(error)
      reject(error)
    })
  })
}

const onGet = (url, params) => {
  let _params = params || {}
  return new Promise((resolve, reject) => {
    request.get(BASE_URL + url, {params: _params}).then(response => {
      resolve(response.data)
    }).catch((error) => {
      console.log(error)
      reject(error)
    })
  })
}

export {onPost, onGet, request}
