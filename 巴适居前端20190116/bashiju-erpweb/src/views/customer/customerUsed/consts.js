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
  custRemark: '备注',
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
    value: '2',
    label: '我租'
  },
  {
    value: '3',
    label: '我购'
  },
  {
    value: '4',
    label: '他租'
  },
  {
    value: '5',
    label: '他购'
  },
  {
    value: '6',
    label: '未知'
  },
  {
    value: '7',
    label: '暂缓'
  },
  {
    value: '8',
    label: '撤单'
  }
]

export const TWO_OPTS = [
  {
    value: '1',
    label: '有效'
  },
  {
    value: '7',
    label: '暂缓'
  }
]
export const FOUR_OPTS = [
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
    label: '他购'
  },
  {
    value: '7',
    label: '暂缓'
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
//朝向
export const ORIENTATIONIDS = [
  {
    fieldValue: '东',
    fieldCode: '373'
  },
  {
    fieldValue: '南',
    fieldCode: '374'
  },
  {
    fieldValue: '西',
    fieldCode: '375'
  },
  {
    fieldValue: '北',
    fieldCode: '376'
  },
  {
    fieldValue: '东南',
    fieldCode: '377'
  },
  {
    fieldValue: '东北',
    fieldCode: '378'
  },
  {
    fieldValue: '西南',
    fieldCode: '379'
  },
  {
    fieldValue: '西北',
    fieldCode: '380'
  }
]
//装修
export const DECORATIONIDS = [
  {
    fieldValue: '毛坯',
    fieldCode: '101'
  },
  {
    fieldValue: '清水',
    fieldCode: '102'
  },
  {
    fieldValue: '简装',
    fieldCode: '103'
  },
  {
    fieldValue: '中装',
    fieldCode: '243'
  },
  {
    fieldValue: '精装',
    fieldCode: '244'
  },
  {
    fieldValue: '豪装',
    fieldCode: '245'
  }
]
//日期类型
export const DATETYPE = [
  {
    fieldValue: '录入日期',
    fieldCode: '1'
  },
  {
    fieldValue: '最近跟进日期',
    fieldCode: '2'
  },
  {
    fieldValue: '最近带看日期',
    fieldCode: '3'
  },
  {
    fieldValue: '租赁到期日期',
    fieldCode: '4'
  }
]
//日期类型
export const LEVELTYPE = [
  {
    fieldValue: 'A类客源',
    fieldCode: 'A'
  },
  {
    fieldValue: 'B类客源',
    fieldCode: 'B'
  },
  {
    fieldValue: 'C类客源',
    fieldCode: 'C'
  },
  {
    fieldValue: 'D类客源',
    fieldCode: 'D'
  }
]
//部门用户类型
export const AGENTTYPE = [
  {
    fieldValue: '录入人',
    fieldCode: 'zr'
  },
  {
    fieldValue: '维护人',
    fieldCode: 'wh'
  },
  {
    fieldValue: '封盘人',
    fieldCode: 'fp'
  }
  ]


export const MATCHINGS = [
  {
    fieldValue: '床',
    fieldCode: '381'
  },
  {
    fieldValue: '衣柜',
    fieldCode: '382'
  },
  {
    fieldValue: '书桌',
    fieldCode: '383'
  },
  {
    fieldValue: '空调',
    fieldCode: '384'
  },
  {
    fieldValue: '电视',
    fieldCode: '385'
  },
  {
    fieldValue: '洗衣机',
    fieldCode: '386'
  },
  {
    fieldValue: '宽带',
    fieldCode: '387'
  },
  {
    fieldValue: '油烟机',
    fieldCode: '388'
  },
  {
    fieldValue: '灶具',
    fieldCode: '389'
  },
  {
    fieldValue: '热水器',
    fieldCode: '390'
  },
  {
    fieldValue: '餐桌',
    fieldCode: '391'
  },
  {
    fieldValue: '沙发',
    fieldCode: '392'
  },
  {
    fieldValue: '马桶',
    fieldCode: '393'
  },
  {
    fieldValue: '电梯',
    fieldCode: '394'
  },
  {
    fieldValue: '冰箱',
    fieldCode: '395'
  },
]
