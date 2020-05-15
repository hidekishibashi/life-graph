<template>
  <v-app id="top">
    <v-container>
      <v-row style="height: 450px;"
             justify="center" align-content="center"
      >
        <!-- <p v-else> -->
        <v-col cols="12" sm="6" md="4">
          <v-card
            id="topSearchAft"
            class="mx-auto"
            max-width="344"
            outlined large
            color="#26A69A"
          >
            <v-list-item three-line>
              <v-list-item-content width="404px">
                <v-list-item-title outlined large color="#FFFFFF" class="white--text">
                  検索
                </v-list-item-title>
                <v-list-item-subtitle class="white--text">
                  希望の人生グラフを見ることができます。
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-card-actions>
              <router-link to="/search">
                <v-btn class="#777575--text" color="#FFFFFF">
                  検索する
                </v-btn>
              </router-link>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6" md="4">
          <v-card
            id="topEntry"
            class="mx-auto"
            max-width="344"
            outlined
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title id="entryTitle" class="#777575--text">
                  登録
                </v-list-item-title>
                <v-list-item-subtitle id="entrySubtitle" class="#777575--text">
                  人生グラフを作成できます。
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-card-actions>
              <router-link to="/new">
                <v-btn id="entryBtn" class="white--text" color="#26A69A">
                  登録する
                </v-btn>
              </router-link>
            </v-card-actions>
          </v-card>
        </v-col>
        <!-- </p> -->
      </v-row>
    </v-container>
  </v-app>
</template>
