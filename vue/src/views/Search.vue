<template>
  <v-app id="search">
    <v-container>
      <!-- 検索欄 -->
      <v-row>
        <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="ユーザーから探す"
            outlined
          />
        </v-col>
      </v-row>
      <router-link to="/reference">
        参照画面
      </router-link>
      <!-- 検索結果の画面 -->
      <v-card
        max-width="700"
        class="mx-auto"
      >
        <v-app-bar
          dark
          color="#26A69A"
        >
          <v-row>
            <v-col>
              <v-toolbar-title>検索結果</v-toolbar-title>
            </v-col>
          </v-row>
          <v-row>
            <v-toolbar-title>ユーザー</v-toolbar-title>
            <v-spacer />
            <v-toolbar-title>グラフタイトル</v-toolbar-title>
            <v-spacer />
            <v-toolbar-title>作成日時</v-toolbar-title>
            <v-spacer />
            <v-toolbar-title>更新日時</v-toolbar-title>
            <v-spacer />
          </v-row>
        </v-app-bar>
        <!-- 検索結果項目 -->
        <v-container>
          <v-row dense class="mx-auto">
            <v-card
              color="white"
            >
              <v-col cols="4">
                <v-toolbar-title>ユーザー</v-toolbar-title>
              </v-col>
              <v-spacer />
              <v-col cols="4">
                <v-toolbar-title>グラフタイトル</v-toolbar-title>
              </v-col>
              <v-spacer />
              <v-col cols="4">
                <v-toolbar-title>作成日時</v-toolbar-title>
              </v-col>
              <v-spacer />
              <v-col cols="4">
                <v-toolbar-title>更新日時</v-toolbar-title>
              </v-col>
              <v-spacer />
              <v-card-actions>
                <v-btn text>
                  <v-icon>mdi-search</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>

            <v-col
              v-for="(item, i) in items"
              :key="i"
              cols="12"
            >
              <v-card
                :color="item.color"
                dark
              >
                <div class="d-flex flex-no-wrap justify-space-between">
                  <div>
                    <v-card-title
                      class="headline"
                      v-text="item.title"
                    />

                    <v-card-subtitle v-text="item.artist" />
                  </div>

                  <v-avatar
                    class="ma-3"
                    size="125"
                    tile
                  >
                    <v-img :src="item.src" />
                  </v-avatar>
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    <!-- <SearchIput />
    <SearchResults /> -->
    </v-container>
  </v-app>
</template>

<script>
// import SearchIput from './components/SearchIput.vue'
// import SearchResults from './components/SearchResults.vue'

// export default {
//   name: 'Search',
//   components: {
//     SearchIput,
//     SearchResults
//   }
// }
</script>
