/*************我的预约后台接口*********/
import {onPost,onGet} from "../main";

export  const  queryReservationInfo = params =>{
  return onPost('manage/reservationWatchHouse/queryReservationWatchHouseByAgentId',params)
}


export  const  updateReservationsInfo = params =>{
  return onPost('manage/reservationWatchHouse/updateReservationsInfo',params)
}

export  const  cancelReservation = params =>{
  return onPost('manage/reservationWatchHouse/cancelReservation',params)
}


export  const  acceptlReservation = params =>{
  return onPost('manage/reservationWatchHouse/acceptlReservation',params)
}
