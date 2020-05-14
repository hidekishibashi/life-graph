<template>
  <div id="login">
    <v-card width="400px" class="mx-auto">
      <v-card-title>
        <h1 class="mx-auto">
          ログイン
        </h1>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field type="mail" prepend-icon="mdi-gmail" label="メールアドレス" />
          <v-text-field type="password" prepend-icon="mdi-lock" append-icon="mdi-eye-off" label="パスワード" />
          <v-card-actions>
            <!-- ボタンのカラー変更は調査中 -->
            <v-btn color="teal">
              新規登録
            </v-btn>
            <v-spacer />
            <v-btn color="pink">
              ログイン
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped>
</style>

<script>

export default {
  layout: 'blank', // layouts/blank.vueを使用
  middleware: ['auth'],
  data () {
    return {
      valid: true,
      email: '',
      password: '',
      error: null
    }
  },
  methods: {

    async login () {
      this.error = null

      if (this.$refs.form.validate()) {
        return this.$auth
          .loginWith('local', {
            data: {
              email: this.email,
              password: this.password
            }
          })
          .catch(e => {
            this.error = 'ログインに失敗しました。IDかパスワードが間違っている可能性があります。'
          })
      }
    }
  },
  head () {
    return {
      title: 'ログイン'
    }
  }
}
</script>
<style scoped>

</style>
