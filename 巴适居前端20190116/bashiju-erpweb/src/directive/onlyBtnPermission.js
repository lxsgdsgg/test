import store from '../store'
import {getBtnPermission} from '@/request/app'

// 唯一按钮权限验证指令
export default (Vue) => {
  Vue.directive('hasOnlyBtn', {
    update (el, binding) {

      const value = binding.value

      // 获取当前的权限按钮信息
      const permissions = store.getters.btnPermission

      if (permissions.length) {
        let hasPermission = false
        if (permissions && permissions instanceof Array && value.length > 0) {
          permissions.forEach(item => {
            if (value === item.btnName) {
              if (item.permission === 1) {
                hasPermission = true
              } else if (item.permission === 0) {
                hasPermission = false
              }
            }
          })
        }

        if (hasPermission) {
          return false
        } else {
          el.parentNode && el.parentNode.removeChild(el)
        }
      } else {
        el.parentNode && el.parentNode.removeChild(el)
      }

    }
  })
}
