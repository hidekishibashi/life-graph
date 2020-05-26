import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  state: {
    users: [],
    NUM: '',
    loaded: false
  },
  mutations: {
    schedule (state, res) {
      state.users = res
      state.NUM = res.length
      state.loaded = !state.loaded
    }
  },
  actions: {
    async schedule ({ commit }, dates) {
      const url = '/api/auth/life-graphs2?startDate=' + dates.dates.startDate + '&endDate=' + dates.dates.endDate
      axios.get(url).then((res) => {
        commit('schedule', res.data)
      })
    }
  }
}
