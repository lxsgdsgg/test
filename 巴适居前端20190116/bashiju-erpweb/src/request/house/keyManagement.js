/**************************钥匙管理后台接口********************/


import {onPost,onGet} from "../main";

export const queryKeyManagementData = params =>{
    return onPost('house/HsKey/queryHsKeyData',params)
}

export const queryKeyLog = params =>{
    return onPost('house/HsKey/queryHsKeyLog',params)
}

//封存
export const keySeal = params =>{
    return onPost('house/HsKey/HsKeySeal',params)
}

//借出
export const keyBorrowed = params =>{
    return onPost('house/HsKey/HsKeyBORROWED',params)
}
//退还
export const keyRecived = params =>{
    return onPost('house/HsKey/HsKeyRECIVED',params)
}

//钥匙管理下拉数据
export const keyManagementDropdown = params =>{
    return onPost('house/HsKey/keyManagementDropdown',params)
}

//归还钥匙
export const keyBack = params =>{
    return onPost('house/HsKey/keyBack',params)
}
