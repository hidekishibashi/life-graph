<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Lifechart',
  extends: Line,
  data () {
    return {
      data: {
        labels: [],
        datasets: [
          {
            label: '人生グラフ',
            backgroundColor: 'rgba(255,153,50)',
            data: [],
            fillColor: 'rgba(254,95,82,0.6)', // 線から下端までを塗りつぶす色
            strokeColor: 'rgba(254,95,82,1)', //  折れ線の色
            pointColor: 'rgba(254,95,82,1)', //  ドットの塗りつぶし色
            pointHighlightFill: 'rgba(86,84,82,1)', // マウスが載った際のドットの塗りつぶし色/pointHighlightStroke: 'yellow', // マウスが載った際のドットの枠線色
            // label:ここにスコアが入るからlabelの中は空にする
            borderWidth: 1,
            fill: false,
            lineTension: 0.4
          }
        ]
      },
      options: {
        maintainAspectRatio: false,
        tooltip: {
          enabled: false,
          custom: [] // 下で定義
          // 定型文のfunction()はデータの中で使えないから下の方で出力
        },
        legend: false, // 凡例
        scales: {
          xAxes: [{
            gridLines: {
              //  縦線消す
              display: true
            },
            ticks: {
              beginAtZero: true,
              suggestedmax: 100,
              min: -100,
              stepsize: 1
            }
          }],
          yAxes: [{
            gridLines: {
              //  zerolineを残すため
              display: true
            },
            ticks: {
              beginAtZero: true,
              suggestedmax: 100,
              min: -100,
              stepsize: 1
            }
          }]
        }
      },
      responsive: true
    }
  },
  computed: {
    checkContents () {
      return this.$store.state.chart.contents
    }
  },
  mounted () {
    // const userId = this.$store.state.auth.userId // 変数userIdを定義。ログイン情報。省略
    // await this.$store.dispatch('chart/addContent', userId)
    this.setAge()
    this.setScore()
    this.setComment()
    this.renderChart(this.data, this.options)
  },
  methods: { // 処理を埋める
    setAge () { // Age=.js age=vue
      this.data.labels = this.$store.state.chart.contents.map((content) => {
        return content.age
      })
    },
    setScore () {
      this.data.datasets[0].data = this.$store.state.chart.contents.map((content) => {
        return content.score
      })
    },
    setComment () {
    // 用意したcommentsという箱に、contents.ageという配列を持ったageと、contents.commentという配列を持った
      // commentをObjectとしてpushする
      const comments = this.checkContents.map((content) => {
        return { age: content.age, comment: content.comment }
      })
      const comment = []
      this.$store.state.chart.contents.map((content) => {
        comment.push(content.comment)
      })
      // tooltip設定
      // https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E5%A4%96%E9%83%A8%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%A0%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97
      this.options.tooltip.custom = function (tooltipModel) { // .~.~・・・名前空間
        // ツールチップ要素
        var tooltipEl = document.getElementById('chartjs-tooltip')
        // 最初のレンダリング時に要素を作成する
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          document.body.appendChild(tooltipEl)
        }
        // ツールチップがない場合は非表示にします
        if (tooltipModel.opacity === 0) {
          tooltipEl.style.opacity = 0
          return
        }
        // キャレット(ツールチップが指し示すもの)の位置を設定する
        tooltipEl.classList.remove('above', 'below', 'no-transform')
        if (tooltipModel.yAlign) {
          tooltipEl.classList.add(tooltipModel.yAlign)
        } else {
          tooltipEl.classList.add('no-transform')
        }
        function getBody (bodyItem) {
          return bodyItem.lines
        }
        // テキストを設定する
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var bodyLines = tooltipModel.body.map(getBody)
          var innerHtml = '<thead>'
          titleLines.forEach(function (age) {
            var comment = comments.find(contents => contents.age === age).comment
            innerHtml += '<tr><th>' + age + '</th></tr>'
            innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              if (comment) {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>' + 'コメント:' + comment
              } else {
                innerHtml += '<tr><td>' + span + '満足度:' + body + '%' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        // 'this'はツールチップ全体
        var position = this._chart.canvas.getBoundingClientRect()
        // 表示、配置、およびフォントスタイルの設定
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.backgroundcolor = 'rgba(228,232,239,1)'
        tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily
        tooltipEl.style.fontSize = tooltipModel._bodyFontSize
        tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle
        tooltipEl.style.padding = tooltipModel.yPadding + 'px ' + tooltipModel.xPadding + 'px'
        tooltipEl.style.pointerEvents = 'none'
      }
    }
  }
}
</script>
