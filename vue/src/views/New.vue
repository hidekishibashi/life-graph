<template>
  <v-data-table
    :headers="headers"
    :items="chartSets"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>My Life Graph</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        />
        <v-spacer />
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:actibator="{ on }">
            <v-btn
              color="primary"
              dark class="mb-2"
              v-on="on"
            >
              追加する
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-filed
                      v-model="editedItem.age"
                      laebl="年齢"
                    />
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn
                color="blue darken-1"
                text @click="close"
              >
                キャンセル
              </v-btn>
              <v-btn
                color="blue darken-1"
                text @click="save"
              >
                登録する
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
  </v-data-table>
</template>
<script>
export default {
  dialog: false,
  data: () => ({
    headers: [
      {
        text: '年齢',
        align: 'start',
        value: 'age'
      },
      { text: 'スコア', value: 'score' },
      { text: 'コメント', value: 'comment' },
      { text: 'タイトル', value: 'title' }
    ],
    chartSets: [
      {
        age: 10,
        score: 56,
        comment: 'コメントがここにきます',
        title: '苦難の人生'
      },
      {
        age: 20,
        score: 20,
        comment: 'コメントがここにきます'
      }
    ],
    editedIndex: -1,
    editedItem: {
      age: 0,
      score: 0,
      comment: '',
      title: ''
    },
    defaultItem: {
      age: 0,
      score: 0,
      comment: '',
      title: ''
    }
  }),
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? '追加する' : '編集する'
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  }
}
</script>
