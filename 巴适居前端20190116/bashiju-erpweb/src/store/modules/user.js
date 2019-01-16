import {getUserInfo} from '../../request/app'
import {
  setStore,
  getStore,
  removeStore
} from '@/common/js/utils'

const user = {
  state: {
    info: getStore('userInfo') || null,
    name: getStore('userName') || ''
  },
  mutations: {
    SET_INFO: (state, info) => {
      state.info = info
      setStore({
        name: 'userInfo',
        content: info,
        type: 'session'
      })
    },

    SET_NAME: (state, name) => {
      state.name = name
      setStore({
        name: 'userName',
        content: name,
        type: 'session'
      })
    }
  },
  actions: {
    getUserInfo ({commit}) {
      return new Promise(resolve => {
        getUserInfo().then(res => {
          if (res.data) {
            commit('SET_INFO', res.data)
            commit('SET_NAME', res.data['realName'])
            resolve(res.data)
          }
        })
      })
    }
  }
}

export default user
