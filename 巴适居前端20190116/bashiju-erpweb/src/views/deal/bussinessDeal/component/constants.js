export const DETAIL_MAP = {
  houseType: '户型：',
  buildSpace: '面积：',
  orientation: '朝向：',
  floorCount: '楼层：',
  houseUses: '用途：',
  propertyType: '产权性质：',
  buildDates: '建筑年代：',
  bank: '',
  blank: '',
  supplementInfo: '备注：',
  blank1: '',
  blank2: '',
  price: '成交价格：',
  receivablePrice: '中介类应收佣金：',
  commissionPrice: '应收佣金：',
  financePrice: '金融类应收佣金：'
}

export const DETAIL_TAB_OPTS = [
  {
    name: 'houseInfo',
    text: '基本信息'
  },
  {
    name: 'housePic',
    text: '佣金记录'
  },
  {
    name: 'relevantPic',
    text: '过户进度'
  },
  {
    name: 'houseVideo',
    text: '文件扫描'
  },
  {
    name: 'matchCustomer',
    text: '跟进记录'
  },
  {
    name: 'followRecord',
    text: '日志'
  },
  {
    name: 'lookRecord',
    text: '代收付款'
  }
]

export const DEAL_INFO = {
  agreementId: '合同编号',
  id: '成交编号',
  houseId: '房源编号',
  demandId: '客源编号',
  transactionType: '交易类型',
  dealTime: '成交时间',
  traderName: '成交人员',
  reference: '权证人员'
}

/**
 * 自定义表头 属性
 * prop (columns 值)
 * label (表头文本内容)
 */
export const FORM_THEAD = [
  {prop: 'agreementStatus',label: '成交状态'},
  {prop: 'transferStatus', label: '过户状态'},
  {prop: 'isDivide', label: '分成状态'},
  {prop: 'isLoan', label: '贷款状态'},
  {prop: 'id', label: '成交编号', width: 140},
  {prop: 'houseId', label: '房源编号', width: 110},
  {prop: 'demandId', label: '客源编号', width: 100},
  {prop: 'agreementId', label: '合同编号'},
  {prop: 'dealTime', label: '成交日期', width: 80},
  {prop: 'areaName', label: '区'},
  {prop: 'regionName', label: '片区'},
  {prop: 'communityName', label: '小区'},
  {prop: 'buildingHouseName', label: '门牌号'},
  {prop: 'owner', label: '业主'},
  {prop: 'custName', label: '客户'},
  {prop: 'buildSpace', label: '面积'},
  {prop: 'price', label: '成交价格'},
  {prop: 'commissionPrice', label: '中介应收'},
  {prop: 'actualCommission', label: '中介实应收', width: 90},
  {prop: 'receivedPrice', label: '中介已收'},
  {prop: 'waitReceivedPrice', label: '中介待收'},
  {prop: 'traderName', label: '成交人'},
  {prop: 'deptName', label: '成交部门'},
  {prop: 'operator', label: '录入人'},
  {prop: 'landCertifcate', label: '土地证号'},
  {prop: 'propCertificate', label: '产权证号'},
  {prop: 'referenceId', label: '权证人'},
  {prop: 'action', label: '操作', width: 160}
]

/**
 * 自定义表头 属性
 * prop (columns 值)
 * label (表头文本内容)
 */
export const RENT_FORM_THEAD = [
  {prop: 'agreementStatus',label: '成交状态'},
  {prop: 'transferStatus', label: '过户状态'},
  {prop: 'isDivide', label: '分成状态'},
  {prop: 'isLoan', label: '贷款状态'},
  {prop: 'id', label: '成交编号', width: 140},
  {prop: 'houseId', label: '房源编号', width: 110},
  {prop: 'demandId', label: '客源编号', width: 100},
  {prop: 'agreementId', label: '合同编号'},
  {prop: 'dealTime', label: '成交日期', width: 80},
  {prop: 'areaName', label: '区'},
  {prop: 'regionName', label: '片区'},
  {prop: 'communityName', label: '小区'},
  {prop: 'buildingHouseName', label: '门牌号'},
  {prop: 'owner', label: '业主'},
  {prop: 'custName', label: '客户'},
  {prop: 'buildSpace', label: '面积'},
  {prop: 'price', label: '成交价格'},
  {prop: 'commissionPrice', label: '中介应收'},
  {prop: 'actualCommission', label: '中介实应收', width: 90},
  {prop: 'receivedPrice', label: '中介已收'},
  {prop: 'waitReceivedPrice', label: '中介待收'},
  {prop: 'traderName', label: '成交人'},
  {prop: 'deptName', label: '成交部门'},
  {prop: 'operator', label: '录入人'},
  {prop: 'landCertifcate', label: '土地证号'},
  {prop: 'propCertificate', label: '产权证号'},
  {prop: 'referenceId', label: '权证人'},
  {prop: 'action', label: '操作', width: 160}
]

/**
 * 自定义表头 属性
 * prop (columns 值)
 * label (表头文本内容)
 */
export const ONE_HAND_FORM_THEAD = [
  {prop: 'agreementStatus',label: '成交状态'},
  {prop: 'transferStatus', label: '过户状态'},
  {prop: 'isDivide', label: '分成状态'},
  {prop: 'isLoan', label: '贷款状态'},
  {prop: 'id', label: '成交编号', width: 140},
  {prop: 'houseId', label: '房源编号', width: 110},
  {prop: 'demandId', label: '客源编号', width: 100},
  {prop: 'agreementId', label: '合同编号'},
  {prop: 'dealTime', label: '成交日期', width: 80},
  {prop: 'areaName', label: '区'},
  {prop: 'regionName', label: '片区'},
  {prop: 'communityName', label: '小区'},
  {prop: 'buildingHouseName', label: '门牌号'},
  {prop: 'owner', label: '业主'},
  {prop: 'custName', label: '客户'},
  {prop: 'buildSpace', label: '面积'},
  {prop: 'price', label: '成交价格'},
  {prop: 'commissionPrice', label: '中介应收'},
  {prop: 'actualCommission', label: '中介实应收', width: 90},
  {prop: 'receivedPrice', label: '中介已收'},
  {prop: 'waitReceivedPrice', label: '中介待收'},
  {prop: 'traderName', label: '成交人'},
  {prop: 'deptName', label: '成交部门'},
  {prop: 'operator', label: '录入人'},
  {prop: 'landCertifcate', label: '土地证号'},
  {prop: 'propCertificate', label: '产权证号'},
  {prop: 'referenceId', label: '权证人'},
  {prop: 'action', label: '操作', width: 160}
]
