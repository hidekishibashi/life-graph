<template>
  <v-app id="search">
    <br>
    <v-card
      width="auto"
      color="#F4F2EC"
      flat
    >
     <v-row justify="center">
        <v-col cols="6"
               md="4" max-width="auto"
        >
          <v-card-text>
            <v-autocomplete
              v-model="model"
              :items="items"
              :loading="isLoading"
              :search-input.sync="search_2"
              color="#495183"
              background-color="#F4F2EC"
              outlined
              hide-no-data
              hide-selected
              item-text="name"
              item-value="API"
              label="検索"
              placeholder="ここから入力できます"
              prepend-icon="mdi-account-search"
              return-object
              class="ma-2"
              cache-items
              flat
              hide-details
              solo-inverted
            />
          </v-card-text>
        </v-col>
        <v-col />
        <v-col cols="3" >
          <v-card-text>
            <vue-daterange-picker
              dates-format="YYYY/MM/DD"
              format="YYYY/MM/DD"
              title-format="YYYY年 M月"
              :items_2="searchByData"
              :start-date="TwoWeeksAgo"
              :end-date="Date()"
              start-place-holder="日時検索"
              end-place-holder=""
              @get-dates="getDates"
              large
            />
          </v-card-text>
        </v-col>
      </v-row>

     <v-toolbar max-width="auto" max-height="50"
                 class="mx-2" color="#495183"
                 dark
                 flat
      >
        <v-toolbar-title class="title font-weight-bold">
          検索結果
        </v-toolbar-title>
        <v-spacer />
      </v-toolbar>
      <v-toolbar max-width="auto" max-height="50"
                 class="mx-2" color="#495183"
                 dark flat
      >
        <v-row>
                    <v-col>
            <v-card-text class="subtitle-2 font-weight-bold">
              ユーザー名
            </v-card-text>
          </v-col>
          <v-col>
            <v-card-text class="subtitle-2 font-weight-bold">
              作成日時
            </v-card-text>
          </v-col>
          <v-col>
            <v-card-text class="subtitle-2 font-weight-bold">
              更新日時
            </v-card-text>
          </v-col>
        </v-row>
      </v-toolbar>
      <v-divider />
      <v-expand-transition>
        <v-content v-if="model">
          <v-toolbar>
            <v-row v-for="(field, i) in fields"
                   :key="i"
                   color="#495183"
                   class="ma-2"
                   dark
            >
              <v-col v-if="field.key === 'name'">
                <!-- <v-card-subtitle v-text="field.key" /> -->
                <v-card-text v-text="field.value" />
              </v-col>
              <v-col v-else-if="field.key === 'created'">
                <!-- <v-card-subtitle v-text="field.key" /> -->
                <v-card-text v-text="field.value" />
              </v-col>
              <v-col v-else-if="field.key === 'updated'">
                <!-- <v-card-subtitle v-text="field.key" /> -->
                <v-card-text v-text="field.value" />
              </v-col>
            </v-row>
          </v-toolbar>
          <v-card-actions>
            <!-- <v-btn to="/reference"> -->
            <v-btn color="FF6F60" class="white--text" @click="reference()">
              グラフを表示する
            </v-btn>
          </v-card-actions>
        </v-content>
      </v-expand-transition>
      <v-card-actions>
        <v-spacer />
        <v-btn
          :disabled="!model"
          color="grey darken-3"
          @click="model = null"
        >
          Clear
          <v-icon right>
            mdi-close-circle
          </v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-app>
</template>

<script>
import moment from 'moment'
import VueDaterangePicker from 'vue-daterange-picker'
moment.locale('ja')
export default {
  name: 'Table',
  dialog: false,
  components: {
    VueDaterangePicker
  },
  data: () => ({
    descriptionLimit: 10,
    entries: [],
    isLoading: false,
    model: null,
    search_2: null,
    search_3: null,
    e6: [],
    e7: [],
    items_2: { name: '', created_at: '' },
    searchByData: [
      '1日前', '1週間前', '1ヶ月前', 'それより前'
    ],
    TwoWeeksAgo: moment().subtract(14, 'days').format('YYYY/MM/DD'),
    yesterday: moment().subtract(1, 'days').format('YYYY/MM/DD')
  }),
  computed: {
    fields () {
      if (!this.model) return []
      // console.log(Object.this.model)
      return Object.keys(this.model).map(key => {
        return {
          key,
          value: this.model[key] || 'n/a'
        }
      })
    },
    items () {
      return this.entries.map(entry => {
        const name = entry.name.length > this.descriptionLimit
          ? entry.name.slice(0, this.descriptionLimit)
          : entry.name
        return Object.assign({}, entry, { name })
      })
    },
    search () {
      return this.$store.state.auth.search
    }
  },
  watch: {
    search_2 (val) {
      // Items have already been loaded
      if (this.items.length > 0) return
      // Items have already been requested
      if (this.isLoading) return
      this.isLoading = true
      fetch('/api/auth/life-graphs')
        .then(function (response) {
          return response.json()
        })
        .then((response) => {
          this.entries = response
        })
        // .catch(err => {
        //   console.log(err)
        // })
        .finally(() => (this.isLoading = false))
      // async mounted () {
      //   await this.$store.dispatch('searchName')
      // }
    }
    // search_3 (val) {

    //     // console.log('res')
    //     // .then(res => res.json())
    //     .then(function (response) {
    //       return response.json()
    //     })
    //     .then((response) => {
    //       // const { entries } = response
    //       this.entries = response
    //       console.log(this.entries)
    //       // this.entries = entries
    //     })
    //     .catch(err => {
    //       console.log(err)
    //       console.log('err')
    //     })
    //     .finally(() => (this.isLoading = false))
    //   // async mounted () {
    //   //   await this.$store.dispatch('searchName')
    //   // }
    // }
  },
  methods: {
    // getDates: (dates) => {
    //   // console.log(dates) // Object {startDate: "2017-12-25T00:00:00+09:00", endDate: "2018-01-22T00:00:00+09:00"}
    //   // 取得した日付をイベントに渡す
    // },
    filter (val, search) {
      return val === search
    },
    reference () {
      // this.$store.commit('resetContents')
      const Id = this.model
      // this.$store.dispatch('setUserId', { Id: Id.id })
      this.$store.dispatch('setUserRef', { Id: Id.id })
      this.$router.push('/reference')
    }
  }
}
</script>

<style>
#search {
  background-color:#F4F2EC
}

img {
  display:none
}

.calender {
  color:#F4F2EC
}

</style>
