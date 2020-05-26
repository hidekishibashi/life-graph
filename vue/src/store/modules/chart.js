import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  state: {
    contents: [],
    loaded: true
  },
  mutations: {
    setContents (state, data) {
      state.contents = data
      state.loaded = !state.loaded
    },
    resetContents (state) {
      state.contents = []
      state.loaded = true
    }
  },
  actions: {
    async setContents ({ commit }, userId) {
      const url = '/api/auth/reference?userID=' + userId
      await axios.get(url).then((res) => {
        commit('setContents', res.data)
      })
    },
    updateContents ({ commit }, userId) {
      const url = 'api/auth/reference/' + userId
      axios.get(url).then((res) => {
        commit('setContents', res.data)
      })
    }
  }
}
