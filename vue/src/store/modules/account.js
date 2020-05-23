import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    accountInfo: ''
  },
  mutations: {
    setAccount (state, payload) {
      state.accountInfo = payload
    },
    resetAccountInfo (state) { // ログアウト後に情報が残らないように
      state.acountInfo = ''
    }
  },
  actions: {
    async accountAction ({ commit }, userId) { // 引数としてuserId（ログイン情報）
      const url = '/api/accountReference/' + userId
      await axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    }// axiosでurlから取得したデータをres.dataに。setAccountInfoに引数として渡す。
  }
}
