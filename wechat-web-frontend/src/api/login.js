import http from '@/axios/api.request'

const login = {}

login.login = () => {
  return http.request({
    url: 'login',
    method: 'get'
  })
}

login.getQR = () => {
  return http.request({
    url: 'getQR',
    method: 'get'
  })
}

export default login