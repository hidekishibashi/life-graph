import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true, // 名前空間
  state: {
    contents: [
      {
        age: 10,
        score: 60,
        comment: 'おおおおおおお'
      },
      {
        age: 20,
        score: 20,
        comment: 'おおおおおおお'
      },
      {
        age: 30,
        score: 40,
        comment: 'おおおおおおお'
      },
      {
        age: 50,
        score: 100,
        comment: 'おおおおおおお'
      },
      {
        age: 80,
        score: 90,
        comment: 'おおおおおおお'
      }
    ],
    load: false,
    loaded: true
  },
  mutations: {
    addContentMutation (state, payload) {
      state.contents = payload
    }
  },
  actions: {
    async addContent ({ commit }, userId) { // 引数としてuserId（auth.js）
      const url = '/api/reference/' + userId
      await axios.get(url).then(res => commit('addContentMutation', res.data))
        .catch(err => err)
    }
  }
}
