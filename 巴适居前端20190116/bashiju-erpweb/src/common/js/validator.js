/****************************************************/
/********************     自定义Element校验规则     *********/
/***************************************************/

/**
 * 校验手机号码
 */
export function checkMobile (rule, value, callback) {
  if (!value) callback()

  const reg = /^[1][3,4,5,7,8][0-9]{9}$/
  if (!reg.test(value)) {
    callback(new Error('格式不正确'))
  } else {
    callback()
  }

}

/**
 * 校验电话 同时验证手机号和固话
 * @param rule
 * @param value
 * @param callback
 * @constructor
 */
export function checkPhone (rule, value, callback){
  if (!value) callback()
  const mobileReg = /^[1][3,4,5,7,8][0-9]{9}$/
  const phoneReg = /^([0-9]{3,4}-)?[0-9]{7,8}$/

  if (mobileReg.test(value) || phoneReg.test(value)) {
    callback()
  } else {
    callback(new Error('格式不正确'))
  }
}

/**
 * 校验身份证号码
 */
export function checkCardNo (rule, value, callback) {
  if (!value) callback()

  const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
  if (!reg.test(value)) {
    callback(new Error('格式不正确'))
  } else {
    callback()
  }

}

// export function checkNum (rule, value, callback){
//   if(!this.buildingsEdit.nameType){
//     this.buildingsEdit.begin = ''
//     this.buildingsEdit.end = ''
//     this.$message({
//       type:'warning',
//       message:'请先选择类型'
//     })
//     return false
//   }
//   if (!value) callback()
//   let str= /^[A-Z]$/
//   if (!str.test(value) && this.buildingsEdit.nameType === '2'){
//     return callback(new Error('请输入大写字母'));
//   }
// }
