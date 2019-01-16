/******************地铁线路管理后台接口*******************/

import {onPost,onGet} from "../main";

export  const queryMetroLine = params  =>{
  return onPost('manage/metro/queryMetro',params)
}

export  const delMetroLineById = params  =>{
  return onPost('manage/metro/delMetroById',params)
}

export  const saveOrUpdateMetroLine = params  =>{
  return onPost('manage/metro/saveOrUpdateMetro',params)
}
