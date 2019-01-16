import Cookies from 'js-cookie'

export function hasClass (el, className) {
  let reg = new RegExp('(^|\\s)' + className + '(\\s|$)')
  return reg.test(el.className)
}

export function addClass (el, className) {
  if (hasClass(el, className)) {
    return
  }

  let newClass = el.className.split(' ')
  newClass.push(className)
  el.className = newClass.join(' ')
}

export function removeClass (el, className) {
  if (!hasClass(el, className)) {
    return
  }

  let classString = el.className
  const nameIndex = classString.indexOf(className)

  classString = classString.substr(0, nameIndex) + classString.substr(nameIndex + className.length)

  el.className = classString
}

export function getCookie (CookieKey) {
  return Cookies.get(CookieKey)
}

export function setCookie (CookieKey, value) {
  return Cookies.set(CookieKey, value)
}

export function removeCookie (CookieKey) {
  return Cookies.remove(CookieKey)
}

/**
 * 获取地址栏目标参数
 */
export function getUrlParam (name) {
  let reg = new RegExp('(^|\\?|&)' + name + '=([^&]*)(\\s|&|$)', 'i')
  if (reg.test(window.location.href)) {
    return unescape(RegExp.$2.replace(/\+/g, ' '))
  }
  return null
}

/**
 * 将具有父子关系的原始数据格式化成树形结构数据
 * @param data 原始数据
 * @param props 指定原始数据的 id 和 parent 属性值
 * @returns {Array} 树形结构数据
 */
// export function toTreeData (data, props) {
//   // const id = props.id
//   // const parentId = props.parent
//   // 删除 所有 children,以防止多次调用
//   data.forEach(function (item) {
//     delete item.children
//   })
//   // 将数据存储为 以 id 为 KEY 的 map 索引数据列
//   let map = {}
//   data.forEach(function (item) {
//     map[item.id] = item
//   })
//
//   let val = []
//
//   data.forEach(function (item) {
//     // 以当前遍历项，的pid,去map对象中找到索引的id
//     let parent = map[item.parentId]
//     // 如果找到索引，那么说明此项不在顶级当中,那么需要把此项添加到，他对应的父级中
//     if (parent) {
//       item['parent'] = parent
//       parent.children || (parent.children = []).push(item)
//     } else {
//       // 如果没有在map中找到对应的索引ID,那么直接把 当前的item添加到 val结果集中，作为顶级
//       val.push(item)
//     }
//   })
//
//   return val
// }

export function toTreeData (_data, props) {
  let data = _data.concat()
  // 删除 所有 children,以防止多次调用
  data.forEach(function (item) {
    delete item.children
  })

  // 将数据存储为 以 id 为 KEY 的 map 索引数据列
  var map = {}
  data.forEach(function (item) {
    map[item[props.id]] = item
  })
  //        console.log(map);

  var val = []
  data.forEach(function (item) {
    // 以当前遍历项，的pid,去map对象中找到索引的id
    var parent = map[item[props.parent]]
    if (parent) {
      (parent.children || (parent.children = [])).push(item)
    } else {
      // 如果没有在map中找到对应的索引ID,那么直接把 当前的item添加到 val结果集中，作为顶级
      val.push(item)
    }
  })

  return val
}

/**
 * 时间 格式化
 * @param 时间戳
 * @param 格式
 * @returns {string} 格式化后的字符串
 */
export function timeFormat (time, cFormat) {
  if (time) {
    const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
    let date
    if (typeof time === 'object') {
      date = time
    } else {
      if (('' + time).length === 10) time = parseInt(time) * 1000
      date = new Date(time)
    }
    const formatObj = {
      y: date.getFullYear(),
      m: date.getMonth() + 1,
      d: date.getDate(),
      h: date.getHours(),
      i: date.getMinutes(),
      s: date.getSeconds(),
      a: date.getDay()
    }
    const timeStr = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
      let value = formatObj[key]
      if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
      if (result.length > 0 && value < 10) {
        value = '0' + value
      }
      return value || 0
    })
    return timeStr
  } else {
    return ''
  }
}

/**
 * json 转数组 key value形式
 * @param json
 * @param key 键名
 * @param val 键值
 * @returns {Array}
 */
export function jsonToArr (json, key, val) {
  let arr = []

  for (let k of Object.keys(json)) {
    let obj = {}
    obj[key] = k
    obj[val] = json[k]
    arr.push(obj)
  }

  return arr
}
