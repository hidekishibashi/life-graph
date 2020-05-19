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
 <head>
    <title>売上</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  </head>
  <body>
    <form method="post">
      <table>
        <tr><td>売上ID : <input type="text" class="form-control" id="sales_id" name="salesId" th:value="${salesId}"/></td></tr>
        <tr><td>顧客ID : <input type="text" class="form-control" id="customer_id" name="customerId" th:value="${customerId}"/></td></tr>
        <tr><td>商品ID : <input type="text" class="form-control" id="goods_id" name="goodsId" th:value="${goodsId}"/></td></tr>
        <tr><td><input type="submit" value="検索"/></td></tr>
      </table>
    </form>
      <table border="1">
        <tr>
          <td>売上ID</td>
          <td>売上明細番号</td>
          <td>顧客ID</td>
          <td>顧客名</td>
          <td>商品ID</td>
          <td>商品名</td>
          <td>数量</td>
          <td>売上金額</td>
          <td>売上日時</td>
        </tr>
        <tr th:each="data : ${result}">
          <td th:text="${data.salesId}"/>
          <td th:text="${data.salesDetailNo}"/>
          <td th:text="${data.customerId}"/>
          <td th:text="${data.customer.customerName}"/>
          <td th:text="${data.userId}"/>
          <td th:text="${data.user.userName}"/>
          <td th:text="${data.quantity}"/>
          <td th:text="${#numbers.formatInteger(data.amount, 3, 'COMMA')}"/>
          <td th:text="${#dates.format(data.salesDate, 'yyyy/MM/dd HH:mm')}"/>
        </tr>
      </table>
  </body>
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
