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
      console.log('この下')
      console.log(state.Id)
    }
  },
  actions: {
    async setUserRef ({ commit }, Id) {
      console.log('setUserRef起動')
      console.log(Id.Id)
      const url = '/api/auth/accounts/' + Id.Id
      axios.get(url).then((res) => {
        commit('setUserId', Id.Id)
      })
    }
  }
}

// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)

// export default {
//   state: {
//     userID: '',
//   },
//   mutations: {
//     setUserId (state, Id) {
//       state.userID = Id
//     }
//   },
//   actions : {
//     setUserId (commit, Id) {
//       const url = '/api/auth/accounts/' + Id
//       axios.get(url).then((res) => {
//         commit('setUserId', res.data)
//       })
//     }
//   }
// }
