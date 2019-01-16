// 重复按钮权限验证指令
export default (Vue) => {
  Vue.directive('hasMultipleBtn', {
    inserted (el, binding) {
      const value = binding.value[0]

      // 获取当前的权限按钮信息
      const permissions = binding.value[1]

      if (Object.keys(permissions).length) {

        let hasPermission = false

        if (permissions && permissions instanceof Array && value.length > 0) {
          hasPermission = permissions.includes(value)
        }

        if (permissions && permissions instanceof Object && typeof permissions !== "undefined") {
          hasPermission = Boolean(permissions[value])

        }

        if (hasPermission) {
          return false
        } else {
          el.parentNode && el.parentNode.removeChild(el)
        }

      }


    }
  })
}
