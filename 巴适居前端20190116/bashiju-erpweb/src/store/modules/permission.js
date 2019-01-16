import {getBtnPermission} from '@/request/app'
import router from '@/router'

const permission = {
  state: {
    btnPermission: [],
  },

  mutations: {
    SET_BTN_PERMISSION (state, permission) {
      state.btnPermission = permission
    },

    CLEAR_BTN_PERMISSION (state) {
      state.btnPermission = []
    }
  },

  actions: {
    getBtnPermission ({commit}, menuId) {
      return new Promise((resolve, reject) => {
        getBtnPermission({menu_id: menuId}).then(res => {
          commit('SET_BTN_PERMISSION', res['onlyBtnMenus'])
          resolve()
        }).catch(() => {
          reject()
        })
      })
    },

    clearBtnPermission ({commit}) {
      return new Promise(resolve => {
        commit('CLEAR_BTN_PERMISSION')
        resolve()
      })
    }
  }
}

export default permission
