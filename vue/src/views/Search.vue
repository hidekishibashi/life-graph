<template>
  <v-app id="search">
    <br>
    <v-card
      width="1200"
      class="mx-auto"
    >
      <v-row>
        <v-col cols="12" sm="6" md="3">
          <v-card-text>
            <v-autocomplete
              v-model="model"
              :items="items"
              :loading="isLoading"
              :search-input.sync="search_2"
              color="white"
              hide-no-data
              hide-selected
              item-text="name"
              item-value="API"
              label="検索"
              placeholder="ここから入力できます"
              prepend-icon="mdi-database-search"
              return-object
            />
          </v-card-text>
        </v-col>
        <v-spacer />
        <v-col cols="4" sm="4">
          <v-card-text>
            <vue-daterange-picker
              dates-format="YYYY/MM/DD"
              format="YYYY/MM/DD"
              title-format="YYYY年 M月"
              :items_2="searchByData"
              :start-date="TwoWeeksAgo"
              :end-date="Date()"
              label="更新日時から検索"
              start-place-holder=""
              end-place-holder=""
              @get-dates="getDates"
            />
          </v-card-text>
        </v-col>
      </v-row>

      <v-toolbar max-width="1200" max-height="50"
                 class="mx-auto" color="#26A69A"
                 dark
                 outlined
      >
        <v-toolbar-title>検索結果</v-toolbar-title>
        <v-spacer />
      </v-toolbar>
      <v-toolbar max-width="1200" max-height="50"
                 class="mx-auto" color="#26A69A"
                 dark
      >
        <v-row>
          <v-col>
            <v-card-text>
              ユーザー名
            </v-card-text>
          </v-col>
          <v-col>
            <v-card-text>
              作成日時
            </v-card-text>
          </v-col>
          <v-col>
            <v-card-text>
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
                   color="#26A69A"
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
            <v-btn @click="reference()">
              表示
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
      const Id = this.model
      // this.$store.dispatch('setUserId', { Id: Id.id })
      this.$store.dispatch('setUserRef', { Id: Id.id })
      this.$router.push('/reference')
    }
  }
}
</script>
