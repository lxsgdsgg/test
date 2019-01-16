// 数据模拟
import {request} from '../request/main'
import Adapter from 'axios-mock-adapter'
import * as mockData from './data'
import * as mockPermission from './data/permission'

export default {
  init() {
    // 创建Adapter 实例
    const mock = new Adapter(request)

    // 模拟获取菜单
    // mock.onGet(/menuData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.menuData}])
    //   })
    // })

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

    // 模拟获取部门级联选择数据
    // mock.onPost(/queryDeptSelect/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, mockData.deptCascaderOptions])
    //   })
    // })

    // 模拟获取部门管理列表数据
    // mock.onPost(/getDeptData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.deptData}])
    //   })
    // })

    // 模拟获取部门级联选择器数据
    // mock.onGet(/getDeptOptions/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.deptCascaderOptions}])
    //   })
    // })

    // 模拟获取角色管理 用户管理  权限菜单信息
    // mock.onGet(/getPermissionMenuData/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockPermission.menu}])
    //   })
    // })
    //
    // // 模拟获取角色管理 用户管理  权限设置信息
    // mock.onGet(/getPermissionDetail/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockPermission.editDetail}])
    //   })
    // })
    //
    // // 模拟获取角色管理 用户管理  获取组合条件信息
    // mock.onGet(/getPermissionCombinations/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockPermission.combinations}])
    //   })
    // })
    //
    // // 模拟获取角色管理 用户管理  获取组合条件信息
    // mock.onGet(/getPermissionCombinationBnts/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockPermission.combinationBtns}])
    //   })
    // })
    //
    // // 模拟获取角色管理 用户管理  获取组合条件信息
    // mock.onGet(/enterAcrossPermissionNew/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, mockPermission.enterAcrossPermissionData])
    //   })
    // })


    // mock.onGet(/getBtnPermission/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, mockPermission.getBtnPermission])
    //   })
    // })

    // 模拟获取普通用户角色授权数据
    // mock.onGet(/ordinaryPermission/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {combinationBtns: mockPermission.combinationBtns, combinations: mockPermission.combinations, menus: mockPermission.menu, per: mockPermission.per}])
    //   })
    // })

    // 首页任务完成情况
    // mock.onGet(/queryMonthtaskByUser/).reply(() => {
    //   return new Promise((resolve) => {
    //     resolve([200, {code: 0, msg: 'success', data: mockData.MonthtaskByUser}])
    //   })
    // })

    mock.onAny().passThrough()
  }
}
