export const DETAIL_TAB_OPTS = [
  {
    name: 'customerInfo',
    text: '基本信息'
  },
  {
    name: 'matchHouse',
    text: '匹配客户'
  },
  {
    name: 'followRecord',
    text: '跟进记录'
  },
  {
    name: 'lookRecord',
    text: '带看记录'
  },
  {
    name: 'logs',
    text: '日志'
  }
 
]

export const DETAIL_MAP = {
  houseFloor: '楼层',
  orientation: '朝向',
  houseAage: '房龄',
  decoration: '装修',
  matching: '配套',
  houseType: '房屋类型',
  labeld: '推荐标签',
  reason: '需求原因',
  remark: '备注',
}

export const CUSTDETAIL_MAP = {
  custName: '客户姓名',
  levelType: '客户等级',
  sourceType: '来源',
  communicateStage: '沟通阶段',
  consumptIdeaName: '消费理念',
  nations: '民族',
  censusRegister: '国籍',
  email: '邮箱',
  IDCard: '证件号码',
  wxNum: '微信',
  qqNum: 'QQ',
  contactAddress: '联系地址',
  vehicle: '交通工具'
}

export const RELATEAGENT_FIELD = {
  '4': '录入人',
  '5': '维护人',
  '12': '封盘人',
}

// 是否置顶 是否靓房 ...
export const TAGS_1 = {
  taobaoStatusId: '淘',
  isSchoolRoom: '学',
  viewingCount: '带',
}

// 房源用途（住宅、别墅、土地、写字楼等） 房源交易类型（出售、出租） ...
export const TAGS_2 = [
  'houseUses', // 房源用途（住宅、别墅、土地、写字楼等）
  'transactionType', // 房源交易类型（出售、出租）
  'status', // 房源状态（有效、空置、他售、他租等）
  'discStatus', // 盘状态（公盘、私盘）
  'levelType' // 房源等级（A、B、C等）
]

export const STATUS_OPTS = [
  {
    value: '1',
    label: '有效'
  },
  {
    value: '4',
    label: '他租'
  },
  {
    value: '5',
    label: '他售'
  },
  {
    value: '6',
    label: '未知'
  },
  {
    value: '7',
    label: '自住'
  },
  {
    value: '8',
    label: '出租中'
  },
  {
    value: '9',
    label: '空置'
  },
  {
    value: '10',
    label: '撤单'
  }
]

export const GENDER_OPTS = [
  {
    value: '1',
    label: '先生'
  },
  {
    value: '2',
    label: '女士'
  },
  {
    value: '3',
    label: '公司'
  }
]

export const HOUSE_CLOSE_OPTS = [
  {
    value: '1',
    label: '封路径'
  },
  {
    value: '2',
    label: '封电话'
  }
]

export const TIP_OFF_OPTS = [
  {
    value: '1',
    label: '照片错误'
  },
  {
    value: '2',
    label: '信息失败'
  }
]

export const USAGE_OPTS = [
  {
    value: 'house',
    label: '住宅'
  },
  {
    value: 'villa',
    label: '别墅'
  },
  {
    value: 'apartment',
    label: '公寓'
  },
  {
    value: 'land',
    label: '土地'
  }
]

export const TRANSACTION_TYPE = [
  {
    label: '出售',
    value: '1'
  },
  {
    label: '出租',
    value: '2'
  },
  {
    label: '租售',
    value: '3'
  }
]

export const FLOOR_DES_OPTS = [
  {
    label: '底层',
    value: '1'
  },
  {
    label: '低层',
    value: '2'
  },
  {
    label: '中层',
    value: '3'
  },
  {
    label: '高层',
    value: '4'
  },
  {
    label: '顶层',
    value: '5'
  }
]

export const RELATE_TYPE_OPTS = [
  {
    label: '本人',
    value: '1'
  },
  {
    label: '配偶',
    value: '2'
  },
  {
    label: '亲戚',
    value: '3'
  },
  {
    label: '朋友',
    value: '4'
  },
  {
    label: '授权委托人',
    value: '5'
  }
]
