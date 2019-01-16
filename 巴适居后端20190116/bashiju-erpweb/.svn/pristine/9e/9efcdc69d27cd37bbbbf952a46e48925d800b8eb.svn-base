export default {
  /**
   *字符串转json
   *
   */
  stringToJson: function (data) {
    return JSON.parse(data)
  },

  /**
   *
   * @param json
   * @param key 键名
   * @param val 键值
   * @returns {Array}
   */
  jsonToArr (json, key, val) {
    let arr = []

    for (let k of Object.keys(json)) {
      let obj = {}
      obj[key] = k
      obj[val] = json[k]
      arr.push(obj)
    }

    return arr
  }
}
