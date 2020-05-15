<template>
  <v-app id="app">
    <Header />
    <router-view />
    <router-link to="/top">
      ログイン後のTop画面
    </router-link>
    <router-link to="/top2">
      ログイン前のTop画面
    </router-link>
    <router-link to="/reference">
      参照
    </router-link>
  </v-app>
</template>

<script>
import Header from './components/Header'
// import Login from './views/Login.vue'
export default {
  name: 'App',
  components: {
    Header
    // Login
  }
}
</script>
