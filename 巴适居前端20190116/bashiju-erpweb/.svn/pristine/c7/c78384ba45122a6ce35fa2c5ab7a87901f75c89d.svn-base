/*********************************************************************************/
/***************************** JSON, 数组常用自定义辅助工具函数 ***********************/
/*********************************************************************************/

/**
 * 自定义数组合并并去重函数
 */
export function mergeArray (arr1, arr2) {
  let _arr = []

  for(let i=0;i<arr1.length;i++){
    _arr.push(arr1[i])
  }

  for(let i=0;i < arr2.length;i++){
    let flag = true
    for(let j=0;j<arr1.length;j++){
      if(arr2[i] === arr1[j]){
        flag=false
        break
      }
    }
    if(flag){
      _arr.push(arr2[i])
    }
  }
  return _arr

}

/**
 *
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

/**
 * 数组深拷贝
 */

export function copyArr(arr) {
  let res = []
  for (let i = 0; i < arr.length; i++) {
    if (typeof arr[i] === 'object') {
      res.push(JSON.parse(JSON.stringify(arr[i])))
    }
  }
  return res
}

/**
 * 对象深拷贝
 */
export function deepClone (source) {

  const targetObj = source.constructor === Array ? [] : {} // 判断复制的目标是数组还是对象
  for (let keys in source) { // 遍历目标
    if (source.hasOwnProperty(keys)) {
      if (source[keys] && typeof source[keys] === 'object') { // 如果值是对象，就递归一下
        targetObj[keys] = source[keys].constructor === Array ? [] : {}
        targetObj[keys] = deepClone(source[keys])
      } else { // 如果不是，就直接赋值
        targetObj[keys] = source[keys]
      }
    }
  }
  return targetObj
}
