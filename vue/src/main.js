import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
<<<<<<< HEAD
import Vuetify from 'vuetify'
=======
>>>>>>> develop
import vuetify from './plugins/vuetify'
// import "vuetify/dist/vuetify.min.css";

Vue.config.productionTip = false

new Vue({
  router,
  store,
<<<<<<< HEAD
  Vuetify,
=======
>>>>>>> develop
  vuetify,
  render: function (h) { return h(App) }
}).$mount('#app')
