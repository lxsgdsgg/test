import Vue from 'vue'
import Vuex from 'vuex'
import tagsView from './modules/tagsView'
import user from './modules/user'
import permission from './modules/permission'
import house from './modules/house'
import app from './modules/app'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    tagsView,
    user,
    permission,
    house
  },
  getters
})

export default store
