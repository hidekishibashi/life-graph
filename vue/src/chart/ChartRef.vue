<script>
import { Line } from 'vue-chartjs'
export default {
  name: 'LifeChart',
  // propsとかで登録画面の情報を持ってくるかデータベースからデータ持ってこないとダメよね？？
  extends: Line,
  data () {
    return {
      data: {
        // ここに年齢が入るからlabelsの中は空にする
        labels: [],
        datasets: [
          {
            // ここにスコアが入るからlabelsの中は空にする
            data: [],
            borderColor: '#26A69A',
            fill: false,
            lineTension: 0.4
          }
        ]
      },
      options: {
        tooltips: {
          // ツールチップのデフォルト設定の無効化
          enabled: false,
          // カスタム
          custom: []
        },
        // 凡例消す
        legend: false,
        scales: {
          xAxes: [{
          //   scaleLabel: {
          //     display: true
          //   },
            gridLines: {
              // 縦線消す
              display: false
            },
            ticks: {
              display: false,
              beiginAtZero: true
            }
          }],
          yAxes: [{
            gridLines: {
              // zeroLineを残すため。理想は他の線はない状態？？要確認
              display: false,
              zeroLineColor: 'rgba(255, 99, 132, 1)'
            },
            ticks: {
              suggestedMax: 100,
              suggestedMin: -100
              // display: true
            }
          }]
        }
      }
    }
  },
  computed: {
    getContents () {
      return this.$store.state.chart.contents
    },
    // loadedがtrueの時,DOM化するようにする
    loaded () {
      return this.$store.state.chart.loaded
    }
  },
  // loadedがtureになる時、描画をさせる
  watch: {
    loaded: function () {
      this.setChart()
    }
  },
  mounted () {
    const userId = this.$store.state.refUser.Id
    console.log(this.$store.state.refUser.Id)
    console.log('上下')
    console.log(userId)
    console.log('userId')
    this.$store.dispatch('setContents', userId)
    console.log('setContentsは動かした')
    this.setChart()
  },
  methods: {
    setChart () {
      this.setLabels()
      this.setData()
      this.setComments()
      this.renderChart(this.data, this.options)
    },
    setLabels () {
      const ages = []
      this.getContents.map((content) => {
        ages.push(content.age)
      })
      // labelsの中に代入する
      this.data.labels = ages
    },
    setData () {
      const scores = []
      this.getContents.map((content) => {
        scores.push(content.score)
      })
      this.data.datasets[0].data = scores
    },
    setComments () {
      const comment = []
      this.getContents.map((content) => {
        comment.push(content.comment)
      })
      const comments = this.getContents.map((content) => {
        // commentsにageとcommentのオブジェクトを入れる
        return { age: content.age, comment: content.comment }
      })
      // debugger
      // console.log(comment)
      // index指定して取る方
      // console.log(comments[0].comment)
      // console.log(comments.find(contents => contents.age === 30).comment)
      this.options.tooltips.custom = function (tooltipModel) {
        // ツールチップ要素の指定
        var tooltipEl = document.getElementById('chartjs-tooltip')
        // 最初のレンダリング時に要素を作成する
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          // 対象のノードにtooltipEl要素を追加
          document.body.appendChild(tooltipEl)
        }
        // ツールチップがなければ非表示
        if (tooltipModel.opacity === 0) {
          tooltipEl.style.opacity = 0
          return
        }
        function getBody (bodyItem) {
          return bodyItem.lines
        }
        // テキストを設定する
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var bodyLines = tooltipModel.body.map(getBody)
          var innerHtml = '<thead>'
          // x軸の値を返してくれる
          titleLines.forEach(function (age) {
            // var index = comments.findIndex(({ age }) => age === ages)
            // var comment = comments[index].comment
            var comment = comments.find(contents => contents.age === parseInt(age)).comment
            innerHtml += '<tr><th>' + age + '歳' + '</th></tr>'
            // innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              // '<tr><td>' は多分改行
              if (comment) {
                innerHtml += '<tr><td>' + span + '満足度：' + body + ' ポイント' + '</td></tr>' + '内容：' + comment
              } else {
                innerHtml += '<tr><td>' + span + '満足度：' + body + ' ポイント' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        // 要素の寸法と、そのビューポートに対する位置
        var position = this._chart.canvas.getBoundingClientRect()
        // 表示、配置、およびフォントスタイルの設定
        tooltipEl.style.opacity = 1
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily
        tooltipEl.style.fontSize = tooltipModel.bodyFontSize + 'px'
        tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle
        tooltipEl.style.padding = tooltipModel.yPadding + 'px ' + tooltipModel.xPadding + 'px'
        tooltipEl.style.pointerEvents = 'none'
      }
    }
  }
}
</script>
