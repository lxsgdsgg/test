const house = {
  state: {
    used: {
      cachedList: [],
      cachedData: null
    },
  },

  mutations: {
    FIND_HOUSE_ACTIVE (state, {type, id}) {
      let cachedData = null

      state[type].cachedList.forEach(item => {
        if (item.id === id) {
          cachedData = item
        }
      })

      state[type].cachedData = cachedData
    },

    ADD_HOUSE_CACHED (state, {type, data}) {

      state[type].cachedList.push(
        Object.assign({}, data)
      )
    }
  },

  actions: {
    findHouseActive ({commit, state}, {type, id}) {
      return new Promise((resolve, reject) => {
        commit('FIND_HOUSE_ACTIVE', {type, id})

        if (state[type].cachedData) {
          resolve({...state[type].cachedData})
        } else {
          reject()
        }

      })
    },

    addHouseCached ({dispatch, commit}, {type, data}) {

      dispatch('findHouseActive', {type, id: data.id}).then(() => {

      }).catch(() => {
        return new Promise(resolve => {
          commit('ADD_HOUSE_CACHED', {type, data})
          resolve()
        })
      })

    }
  }
}

export default house
