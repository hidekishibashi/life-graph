import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  state: {
    contents: [],
    load: false
  },

  mutations: {
    setContents (state, payload) {
      state.contents = payload
      state.loaded = !state.loaded
    }
  },

  actions: {
    setContents ({ commit }, userId) {
      const url = 'api/auth/reference/' + userId
      axios.get(url).then((res) => {
        commit('setContents', res.data)
      })
    }
  }
}
