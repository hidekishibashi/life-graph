import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  state: {
    Id: ''
  },
  mutations: {
    setUserId (state, payload) {
      state.Id = payload
    }
  },
  actions: {
    async setUserRef ({ commit }, Id) {
      const url = '/api/auth/accounts/' + Id.Id
      axios.get(url).then((res) => {
        commit('setUserId', Id.Id)
      })
    }
  }
}
