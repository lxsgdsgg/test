// 数据模拟
import {request} from '../request/main'
import Adapter from 'axios-mock-adapter'
import * as mockData from './data'

export default {
  init () {
    // 创建Adapter 实例
    const mock = new Adapter(request, { delayResponse: 100 })

    // 模拟获取菜单
    mock.onGet(/menuData/).reply(() => {
      return new Promise((resolve) => {
        resolve([200, {code: 0, msg: 'success', data: mockData.menuData}])
      })
    })

    // 模拟获取菜单管理列表
    // mock.onPost(/getMemuData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.menuManageListData}])
    //   })
    // })

    // 模拟获取菜单数据组合列表数据
    // mock.onGet(/combinationPageBaseInfo/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.combinationBaseInfo}])
    //   })
    // })

    // 模拟获取菜单数据组合 单个条件明细
    // mock.onGet(/combinationDetailData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.combinationDetailInfo}])
    //   })
    // })

    // 模拟获取角色管理列表数据
    // mock.onPost(/getRoleData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.roleData}])
    //   })
    // })

    // 模拟获取部门级联选择器数据
    // mock.onGet(/getDeptOptions/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.deptCascaderOptions}])
    //   })
    // })

    mock.onAny().passThrough()
  }
}
