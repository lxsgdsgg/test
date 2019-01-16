const tagsView = {
  state: {
    visitedViews: [
      {
        fullPath: '/dashboard',
        hash: '',
        meta: {"title":"首页"},
        name: 'dashboard',
        path: '/dashboard'
      }
    ],

    dashboard: {
      fullPath: '/dashboard',
      hash: '',
      meta: {"title":"首页"},
      name: 'dashboard',
      path: '/dashboard'
    },

    cachedViews: [],
    showIframe: false,
    iframeTabData: []
  },
  mutations: {
    ADD_VISITED_VIEW: (state, view) => {
      if (state.visitedViews.some(v => v.path === view.path) || view.name === 'dashboard') return

      state.visitedViews.push(
        Object.assign({}, view, {
          title: view.meta.title || 'no-name'
        })
      )
    },
    ADD_CACHED_VIEW: (state, view) => {
      if (state.cachedViews.includes(view.name)) return

      if (!view.meta.noCache) {
        state.cachedViews.push(view.name)
      }
    },

    DEL_VISITED_VIEW: (state, view) => {
      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path) {
          state.visitedViews.splice(i, 1)
          break
        }
      }
    },

    DEL_CACHED_VIEW: (state, view) => {
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i)
          state.cachedViews.splice(index, 1)
          break
        }
      }
    },

    DEL_IFRAME_TAB_DATA: (state, view) => {
      for (const [i, v] of state.iframeTabData.entries()) {
        if (v.path === view.path) {
          state.iframeTabData.splice(i, 1)
          break
        }
      }
    },

    DEL_OTHERS_VISITED_VIEWS: (state, view) => {
      let visitedViews = [{...state.dashboard}]

      for (const [i, v] of state.visitedViews.entries()) {
        if (v.path === view.path) {
          state.visitedViews = visitedViews.concat(state.visitedViews.slice(i, i + 1))
        }
      }

    },
    DEL_OTHERS_CACHED_VIEWS: (state, view) => {
      for (const i of state.cachedViews) {
        if (i === view.name) {
          const index = state.cachedViews.indexOf(i)
          state.cachedViews = state.cachedViews.slice(index, index + 1)
          break
        }
      }
    },

    DEL_OTHERS_IFRAME_TAB_DATA: (state, view) => {
      for (const [i, v] of state.iframeTabData.entries()) {
        if (v.path === view.path) {
          state.iframeTabData = state.iframeTabData.slice(i, i + 1)
          break
        }
      }
    },

    DEL_ALL_VISITED_VIEWS: state => {
      state.visitedViews = [{...state.dashboard}]
    },

    DEL_ALL_CACHED_VIEWS: state => {
      state.cachedViews = []
    },

    DEL_ALL_IFRAME_TAB_DATA: state => {
      state.iframeTabData = []
    },

    UPDATE_VISITED_VIEW: (state, view) => {
      for (let v of state.visitedViews) {
        if (v.path === view.path) {
          v = Object.assign(v, view)
          break
        }
      }
    },

    ADD_IFRAME_TAB_DATA: (state, view) => {
      const { showIframe } = view.meta
      state.showIframe = showIframe
      if (showIframe) {
        if (state.iframeTabData.some(v => v.path === view.path)) return

        state.iframeTabData.push(
          Object.assign({}, view)
        )
      }

    },

    REFRESH_SELECTED_IFRAME: (state, view) => {
      for (const [i, v] of state.iframeTabData.entries()) {
        if (v.path === view.path) {
          let src = v.meta.src
          v.meta.src = `${src}?query=${new Date().getTime()}`
          break
        }
      }
    }

  },
  actions: {
    addView({ dispatch }, view) {
      dispatch('addVisitedView', view)
      dispatch('addCachedView', view)
    },
    addVisitedView({ commit }, view) {
      commit('ADD_VISITED_VIEW', view)
    },
    addCachedView({ commit }, view) {
      commit('ADD_CACHED_VIEW', view)
    },

    delView({ dispatch, state }, view) {
      return new Promise(resolve => {
        dispatch('delVisitedView', view)
        dispatch('delCachedView', view)
        dispatch('delIframeTabData', view)
        resolve({
          visitedViews: [...state.visitedViews],
          cachedViews: [...state.cachedViews],
          iframeTabData: [...state.iframeTabData]
        })
      })
    },
    delVisitedView({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_VISITED_VIEW', view)
        resolve([...state.visitedViews])
      })
    },
    delCachedView({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_CACHED_VIEW', view)
        resolve([...state.cachedViews])
      })
    },

    delIframeTabData ({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_IFRAME_TAB_DATA', view)
        resolve([...state.iframeTabData])
      })
    },

    delOthersViews({ dispatch, state }, view) {
      return new Promise(resolve => {
        dispatch('delOthersVisitedViews', view)
        dispatch('delOthersCachedViews', view)
        dispatch('delOthersIframeTabData', view)
        resolve({
          visitedViews: [...state.visitedViews],
          cachedViews: [...state.cachedViews],
          iframeTabData: [...state.iframeTabData]
        })
      })
    },

    delOthersVisitedViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_OTHERS_VISITED_VIEWS', view)
        resolve([...state.visitedViews])
      })
    },

    delOthersCachedViews({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_OTHERS_CACHED_VIEWS', view)
        resolve([...state.cachedViews])
      })
    },

    delOthersIframeTabData ({ commit, state }, view) {
      return new Promise(resolve => {
        commit('DEL_OTHERS_IFRAME_TAB_DATA', view)
        resolve([...state.iframeTabData])
      })
    },

    delAllViews({ dispatch, state }, view) {
      return new Promise(resolve => {
        dispatch('delAllVisitedViews', view)
        dispatch('delAllCachedViews', view)
        dispatch('delAllIframeTabData', view)
        resolve({
          visitedViews: [...state.visitedViews],
          cachedViews: [...state.cachedViews],
          iframeTabData: [...state.iframeTabData]
        })
      })
    },

    delAllVisitedViews({ commit, state }) {
      return new Promise(resolve => {
        commit('DEL_ALL_VISITED_VIEWS')
        resolve([...state.visitedViews])
      })
    },

    delAllCachedViews({ commit, state }) {
      return new Promise(resolve => {
        commit('DEL_ALL_CACHED_VIEWS')
        resolve([...state.cachedViews])
      })
    },

    delAllIframeTabData ({ commit, state }) {
      return new Promise(resolve => {
        commit('DEL_ALL_IFRAME_TAB_DATA')
        resolve([...state.iframeTabData])
      })
    },

    updateVisitedView({ commit }, view) {
      commit('UPDATE_VISITED_VIEW', view)
    },

    addIframeTabData ({ commit }, view) {
      commit('ADD_IFRAME_TAB_DATA', view)
    },

    refreshSelectedIframe ({ commit }, view) {
      commit('REFRESH_SELECTED_IFRAME', view)
    }
  }
}

export default tagsView
