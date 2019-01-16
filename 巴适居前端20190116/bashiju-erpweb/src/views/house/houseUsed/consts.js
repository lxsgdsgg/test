export const DETAIL_TAB_OPTS = [
  {
    name: 'houseInfo',
    text: '基本信息'
  },
  {
    name: 'housePic',
    text: '房屋照片'
  },
  {
    name: 'relevantPic',
    text: '相关照片'
  },
  {
    name: 'houseVideo',
    text: '房屋视频'
  },
  {
    name: 'matchCustomer',
    text: '匹配客户'
  },
  {
    name: 'followRecord',
    text: '房源跟进'
  },
  {
    name: 'lookRecord',
    text: '带看记录'
  },
  {
    name: 'logs',
    text: '日志'
  },
  {
    name: 'houseDescription',
    text: '房屋描述'
  },
  {
    name: 'changeHistory',
    text: '改价历史'
  },
  {
    name: 'agentFeedback',
    text: '经纪人反馈'
  }

]

export const DETAIL_MAP = {
  BASE: {
    titles: '房源标题',
    houseType: '房屋类型',
    floorCount: '楼层',
    orientation: '朝向',
    elevatorroomcount: '梯户',
    decoration: '装修',
    matching: '配套',
    buildSpace: '建筑面积',
    useSpace: '使用面积',
    structure: '建筑结构',
    buildDates: '建筑年代',
    actuality: '现状',
    propRightsLen: '产权年限',
    propertyType: '产权性质',
    labeld: '推荐标签',
    payType: '押一年付',
    remark: '备注',
  },

  TRANSACTION: {
    resourceType: '来源',
    certificateDate: '产证日期',
    isSynchron: '与外网同步',
    verificationCode: '房源核验码',
    lastTradingTime: '上次交易时间',
    entrustCode: '委托编号',
    // entrustCode: '独家编号',
    viewingType: '看房方式',
    keyCode: '钥匙编号',
    receipt: '钥匙收据',
    housingYears: '交易年限',
    deptname: '钥匙店',
    keystatus: '钥匙状态',
    isUploadCertificate: '房本备件',
    mortgageName: '抵押信息',
    heatingModeName: '供暖方式',
    tradingRightsName: '交易权属',
  },
}

export const RELATEAGENT_FIELD = {
  '1': '录入人',
  '2': '开盘人',
  '3': '维护人',
  '8': '拿钥匙人',
  '10': '图片人',
  '9': '独家信息',
  '7': '委托信息',
}

// 是否置顶 是否靓房 ...
export const TAGS_1 = {
  isGood: '靓',
  isUnique: '独',
  taoBaoStatusId: '淘',
  isSetTop: '顶',
  isSchoolRoom: '学',
  isPicture: '图',
  isKeys: '钥',
  viewingCount: '看',
  isUrgent: '急',
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

export const DISC_STATUS_TYPE = [
  {
    label: '公盘',
    value: '2'
  },
  {
    label: '私盘',
    value: '1'
  }
]

export const rentPriceTypeId = [
  {
    label: '元/月',
    value: '1'
  },
  {
    label: '元/季度',
    value: '2'
  },
  {
    label: '元/年',
    value: '3'
  },
  {
    label: '元/月',
    value: '4'
  },
  {
    label: '元/平/月',
    value: '5'
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
  },
  {
    label: '地下',
    value: '6'
  },
  {
    label: '地上',
    value: '7'
  },
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

export const useTypes = [
  {
    fieldValue: '住宅',
    fieldCode: 'house'
  },
  {
    fieldValue: '别墅',
    fieldCode: 'villa'
  },
  {
    fieldValue: '商铺',
    fieldCode: 'shops'
  },
  {
    fieldValue: '公寓',
    fieldCode: 'apartment'
  },
  {
    fieldValue: '写字楼',
    fieldCode: 'officeBuiling'
  },
  {
    fieldValue: '仓库',
    fieldCode: 'wareHouse'
  },
  {
    fieldValue: '厂房',
    fieldCode: 'factory'
  },
  {
    fieldValue: '车位',
    fieldCode: 'parking'
  },
  {
    fieldValue: '土地',
    fieldCode: 'land'
  }
]

export const dateTypes = [
  {
    fieldValue: '录入日期',
    fieldCode: '1'
  },
  {
    fieldValue: '开盘日期',
    fieldCode: '2'
  },
  {
    fieldValue: '最近跟进日期',
    fieldCode: '3'
  },
  {
    fieldValue: '最近带看日期',
    fieldCode: '4'
  },
  {
    fieldValue: '租赁到期日期',
    fieldCode: '5'
  }
]

// 模糊查询坐栋数据
export const vagueQueryBuilding = [
  {label: '精确查找', type: 1},
  {label: '模糊查找', type: 2}
]

// 模糊查询其他  门牌号 电话 编号..
export const vagueQueryInfo = [
  {label: '房源编号', type: 1},
  {label: '业主姓名', type: 2},
  {label: '门牌号', type: 3},
  {label: '业主电话', type: 4},
  {label: '小区名称', type: 5},
  {label: '钥匙编号', type: 6},
  {label: '委托编号', type: 7},
  {label: '推荐标签', type: 8},
  {label: '房源备注', type: 9},
]

// 权限按钮
export const PERMISSION_BTN = {
  ADD_HOUSE: 'addHouse', // 新增房源
  EDIT_HOUSE: 'updateInfo', // 修改房源信息
  SET_PRIVATE_DISC: 'setPrivateDisc', // 设置私盘
  OPEN_DISC: 'opendisc', // 房源开盘
  HOUSE_TOP: 'houseTop', // 房源置顶
  CANCEL_TOP: 'cancelTopBtn', // 取消置顶
  CHANGE_STATUS: 'changeStatus', // 变更状态
  SET_KEY: 'setKey', // 设置钥匙
  CHANGE_MAINTAINER: 'changeMainter', // 变更维护人
  FROM_TAOBAO: 'fromTaobao', // 淘宝
  TO_TAOBAO: 'toTaobao', // 进入淘宝
  HOUSE_GOOD: 'houseGood', // 靓房
  WRITE_FOLLOW: 'writeFollow', // 写跟进
  WRITE_REMIND: 'writeRemind', // 写提醒
  CLOSE_DISC: 'closedisc', // 申请封盘
  ACCUSATION: 'accusation', // 举报
  CHANGE_USAGE: 'changeUsage', // 更改用途
  ADD_SOME_HOUSE: 'addSameHouse', // 新增类似房源
  SET_STORE: 'setStore', // 收藏
  LOOK_HOUSE_NUMBER: 'lookHousenumber', // 查看门牌
  SET_OVER_ALL_VIEW: 'setOverallview', // 设置全景
  UPDATE_OWNER: 'updateOwner', // 修改业主信息
  UPDATE_PHONE: 'updatePhone', // 修改电话
  DEL_PHONE: 'delPhone', // 删除电话
  ADD_PHONE: 'addPhone', // 新增电话
  CHANGE_OWNER: 'changeOwner', // 变更业主
  LOOK_PRICE_HISTORY: 'lookPriceDetail111', // 查看改价历史
  LOOK_ARCHIVES: 'lookArchives', // 查看客房档案
  LOOK_PHONE: 'lookPhone', // 查看电话
  LOOK_PRICE: 'lookPrice', // 查看底价
  UPDATE_PRICE: 'updatePrice', // 修改底价
  SET_IMG: 'setImg', // 设置图片人
  DELETE_HOUSE: 'deleteHouse', // 删除房源
  SET_UNIQUE: 'setUnique', // 设置独家
  SET_ENTRUST: 'setAgent', // 设置委托
  UPLOAD_IMG: 'uploadImg', // 上传图片
  COVER_BTN: 'coverBtn', // 设置封面
  UPLOAD_RELEVANT_IMG: 'uploadRelevantPicBtn', // 上传相关图片
  DOWNLOAD_IMG: 'downloadImg', // 下载图片
  DOWNLOAD_RELEVANT_IMG: 'downloadRelevantPicBtn', // 下载相关图片
  DEL_IMG: 'delPictureBtn', //删除图片
  DEL_RELEVANT_IMG: 'delPictureBtn', //删除相关图片
  SYNCHRO_BTN: 'synchroBtn', // 外网同步
  HOUSE_SALES_BTN: 'houseSalesBtn', // 禁看销控
  EXPORT_BTN: 'exportBtn', // 列表导出
  PRINT_HOUSE_BTN: 'printHouseBtn', // 房源打印
  CANCEL_GOOD: 'cancelGoodBtn', // 取消靓房
  DEL_VIDEO: 'delVideoBtn', // 删除视频
  FEED_BACK: 'feedbackBtn', // 写反馈
  SELL_DEAL: 'sellDealBtn', // 买卖成交
  RENT_DEAL: 'rentDealBtn', // 租赁成交
  SELL_CONTRACT: 'sellContractBtn', // 买卖合同
  RENT_CONTRACT: 'rentContractBtn', // 租赁合同
  LOOK_COMMUNITY_HOUSE: 'lookCommunityHouseBtn', // 查看小区房源
  LOOK_COMMUNITY: 'lookCommunityBtn', // 查看小区
  CALL_BTN: 'callBtn', // 隐号通话
  UPDATE_PIC_TYPE: 'pictureTypeBtn', // 修改图片类型
  UPDATE_RELEVANT_PIC_TYPE: 'pictureTypeBtn', // 修改相关图片类型
  SEND_BIND_CODE: 'sendBindCodeBtn', // 发送绑定码
}
